#!/usr/bin/env groovy

import java.util.UUID

/* 
 ==================================================== 
 BASE
 ==================================================== 
*/

def getCurrentDir(){
	def path = new File(".").absolutePath
	path.take(path.size() - 2)
}

def withEachCommand(Closure worker){
	new File("${currentDir}/src/").eachFile worker
}

class ExecContext {
	
	static def YES_NO_RESPONSE = ['y', 'n']

	def cli 

	def args

	def currentDir

	def sh(String path, List command) {
		try {
			def process = new ProcessBuilder(*command).directory(new File(path)).redirectErrorStream(true).start()
			process.inputStream.eachLine { println it }
			return process.exitValue()
		} catch (IllegalThreadStateException exp) {
			return 0
		}
	}


	def sh(String path, String command) {
		sh(path, command.tokenize(' '))		
	}

	def sh(String command) {
		sh(currentDir, command)  
	}

	def out(message){
		println message
	}	

	def read(message){
		System.console().readLine "${message}:"
	}	

	def readWithValidation(message, errorMessageGenerator, validator){
		String response = read(message)
		
		try {
			if (validator(response)){
				return response
			}
		} catch (exp){}

		out(errorMessageGenerator(response))
		readWithValidation(message, errorMessageGenerator, validator)
	}	

	def readYesNo(message){
		readWithValidation("${message} (y/n)", { 'Invalid answer. Please type y/n.' }, { YES_NO_RESPONSE.contains(it) }) == 'y'
	}

	def readDirectory(message){
		readWithValidation(message, { "Directory '${it}' doen't exit or its a file." }, { new File(it).exists() && new File(it).isDirectory() })
	}

	def renameAllDirs(workDir, from, to){
		sh(workDir, ['find', '.', '-name', 'blueprint', '-type', 'd', '-exec', 'bash', '-c', "mv \$1 \${1//${from}/${to}}", '--', '{}', ';'])
	}	

	def renameAllPlacehoders(workDir, from, to){
		sh(workDir, ['find', '.', '-type', 'f', '-exec', 'bash', '-c', "sed -i 's/${from}/${to}/g' \$1 ", '--', '{}', ';'])
	}	
}

/* 
 ==================================================== 
 EXEC
 ==================================================== 
*/

// find commands
def COMMANDS = []
withEachCommand { COMMANDS << evaluate(it) }
// register them
def cli = new CliBuilder(usage:'O(t)cli')
COMMANDS.each { it.register(cli) }
// find current one
def options = cli.parse(args)
def command = COMMANDS.find { it.supports(options) } ?: COMMANDS.find { it.isDefault() }
// execute it
new ExecContext(cli: cli, args: options.arguments(), currentDir: currentDir).with command.exec
