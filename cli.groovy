#!/usr/bin/env groovy

/* 
 ==================================================== 
 BASE
 ==================================================== 
*/

def getCurrentDir(){
	new File(".").absolutePath.replace('.', '')	
}

def withEachCommand(Closure worker){
	new File("${currentDir}src/commands/").eachFile worker
}

class ExecContext {
	
	static def YES_NO_RESPONSE = ['y', 'n']

	def cli 

	def args

	def sh(Object... command) {
		try {
			def process= new ProcessBuilder(*command).redirectErrorStream(true).start()
			process.inputStream.eachLine { println it }
			return process.exitValue()
		} catch (IllegalThreadStateException exp) {
			return 0
		}
	}

	def out(message){
		println message
	}	

	def read(message){
		System.console().readLine "${message} :"
	}	

	def readWithValidation(message, errorMessage, validator){
		String response = read(message)
		
		try {
			if (validator(response)){
				return response
			}
		} catch (exp){}

		out(errorMessage)
		readWithValidation(message, errorMessage, validator)
	}	

	def readYesNo(message){
		readWithValidation("${message} (y/n)", 'Invalid answer. Please type y/n.', { YES_NO_RESPONSE.contains(it) }) == 'y'
	}

	def readDirectory(message){
		readWithValidation(message, 'Directory not exits or its a file.', { new File(it).exists() && new File(it).isDirectory() })
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

new ExecContext(cli: cli, args: options.arguments()).with command.exec