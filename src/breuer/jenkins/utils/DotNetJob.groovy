#!/usr/bin/env groovy
package breuer.jenkins.utils

import javaposse.jobdsl.dsl.Job

def solutions = findFiles glob: '**/*.sln'
echo "Solution count: ${solutions.size()}"

/*solutions.each {
	echo "Building job for ${it.name}"
	Job currentJob = job(it.name)
	JobUtils.addDefaults(currentJob)
	currentJob.With {
		shell('echo Hello World!')
	}
}*/

job("TestDotNet") {
    steps {
        shell 'echo Hello from new DotNet job'
    }
}					
						
						