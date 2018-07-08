#!/usr/bin/env groovy
package breuer.jenkins.utils

import javaposse.jobdsl.dsl.Job

class JobUtils {

	static void addDefaults(Job job) {
		addDescription(job)
		addColorizedOutput(job)
		addTimestamps(job)
		addLogging(job)
	}
	
	static void addDescription(Job job) {
		addDescription(job, '<em style="color: red;">GENERATED JOB - MANUAL CHANGES WILL BE OVERWRITTEN</em>')
	}
	
	static void addDescription(Job job, String message) {
		job.with {
			description(message)
		}
	}
	
	static void addColorizedOutput(Job job) {
		job.with {
			wrappers {
				colorizedOutput()
			}
		}
	}
	
	static void addTimestamps(Job job) {
		job.with {
			wrappers {
				timestamps()
			}
		}
	}
	
	static void addLogging(Job job) {
        job.with {
            logRotator {
                numToKeep(10)
            }
        }
    }
}