#!/usr/bin/env groovy
package com.ps.cli

@Grab(group='org.apache.commons', module='commons-lang3', version='3.0')
import org.apache.commons.lang3.Validate

return [
	isDefault : { true },
	register : { cli -> cli.h('Display help') },
	supports : { o -> o.h },
	exec : {  
		out cli.usage() 
	}
]