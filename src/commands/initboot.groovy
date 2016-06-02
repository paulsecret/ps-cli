@Grab(group='org.apache.commons', module='commons-lang3', version='3.0')
import org.apache.commons.lang3.Validate

class Params {

	final def name

	final def location

	final def profile

	Params(name, location, profile){
		this.name = validateName(name)			
		this.location = validateLocation(location)
		this.profile = validateProfile(profile)
	}

	private def validateName(name){
		Validate.notEmpty((String) name, 'Name of the project not provided')
		return name
	}

	private def validateLocation(location){
		Validate.notEmpty((String) location, 'Location of the project not provided')			
		return location
	}

	private def validateProfile(profile){
		Validate.notEmpty((String) profile, 'Profile is not defined. Currently supported profile are: web')
		return profile	
	}

	def getPath(){
		"${location}/${name}"
	}
}


[
	isDefault : { false },
	register: { cli -> cli.initboot('Initializes empty Spring Boot application') },
	supports: { option -> option.initboot },
	exec: { 
		readYesNo('Do you do drugs')
		readDirectory('Where do you keap them')
		def p = new Params(read('What is project name?'), read('What is project location?'), read('What is project profile?'))
		
		out "Initializig empty Spring Boot application with name '${p.name}' in directory ${p.location}" 
		out "1. Creating directory under ${p.path}"
		sh "mkdir", "-p", p.path
		out "2. Cloning template project"
		sh "git", "clone", "git@github.com:paulsecret/ps-app-bootblueprint.git", p.path
		out "3. Removing base directory ${p.path}/.git"
		sh "rm", "-Rf", "${p.path}/.git"
		out "Project ${p.name} created :)"
	}
]