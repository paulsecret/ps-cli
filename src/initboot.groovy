@Grab(group = 'org.apache.commons', module = 'commons-lang3', version = '3.0')
import org.apache.commons.lang3.Validate

class Params {

    final def name

    final def location

    final def profile

    Params(name, location, profile) {
        this.name = name
        this.location = location
        this.profile = profile
    }

    def getPath() {
        "${location}/${name}"
    }
}

return [
        isDefault: { false },
        register : { cli -> cli.initboot('Initializes empty Spring Boot application') },
        supports : { option -> option.initboot },
        exec     : {
            def p = new Params(read('What is the project name?'), readDirectory('What is project location?'), 'web')

            out("Initializig empty Spring Boot application with name '${p.name}' in directory ${p.location}")
            // ---------------------------------------
            out("1. Creating directory under ${p.path}")
            sh("mkdir -p ${p.path}")
            // ---------------------------------------
            out("2. Cloning template project")
            sh("git clone git@github.com:paulsecret/ps-app-bootblueprint.git ${p.path}")
            // ---------------------------------------
            out "3. Remove git repo"
            sh("rm -Rf ${p.path}/.git")
            // ---------------------------------------
            out("4. Renaming placeholders - directories")
            renameAllDirs(p.path, 'blueprint', p.name)
			// ---------------------------------------
            out("5. Renaming placeholders - files")
            renameAllPlacehoders(p.path, 'blueprint', p.name)
			// ---------------------------------------
            out("5. Init git repo")
            sh(p.path, "git init")
            sh(p.path, "git add .")
            sh(p.path, "git commit -m 'init'")
            // ---------------------------------------
            out "Project ${p.name} created :)"
	}
]
