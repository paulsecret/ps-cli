def sh(Object... command) {
	try {
		def process= new ProcessBuilder(*command).redirectErrorStream(true).start()
		process.inputStream.eachLine { println it }
		return process.exitValue()
	} catch (IllegalThreadStateException exp) {
		return 0
	}
}