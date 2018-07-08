#!/usr/bin/env groovy

import breuer.jenkins.utils.JobUtils
import jenkins.model.Jenkins
import hudson.model.User
import hudson.security.Permission
import hudson.EnvVars

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    pipeline {
		agent any
		stages {
			/*stage('Testing') {
				steps {
					script {
						EnvVars envVars = build.getEnvironment(listener);

						def filename = envVars.get('WORKSPACE')
						echo "Workspace: ${filename}"
					}
				}
			}*/
			stage('Scan for new jobs') {
				steps {
					echo 'Scanning...'
					echo "${pwd()}"
					echo "${WORKSPACE}"
					/*script {
						bat 'set > env.txt'
						String[] envs = readFile('env.txt').split("\r?\n")

						for(String vars: envs){
							println(vars)
						}
					}*/
					script {
						def baseDir = "${WORKSPACE}".split("\\")[ -1 ]
						echo "BaseDir: ${baseDir}"
						echo "RelDir: ../${baseDir}@libs/breuer-jenkins-lib/src/breuer/jenkins/utils/DotNetJob.groovy"
						jobDsl(removedJobAction: 'DELETE', removedViewAction: 'DELETE',
							targets: "../${baseDir}@libs/breuer-jenkins-lib/src/breuer/jenkins/utils/DotNetJob.groovy", unstableOnDeprecation: true)
					}
				}
			}

			stage('Build jobs') {
				steps {
					echo pipelineParams.message
					
				}
			}
		}
	}
}