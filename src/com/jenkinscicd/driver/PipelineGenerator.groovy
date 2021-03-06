

freeStyleJob('JobGenerator_'+ENV) {
	description('A pipeline that will generate the jobs from github to Jenkins for the branch: '+ENV)
	parameters {
		stringParam('ENV', ENV, "This is the environment variable associated to the branch")
	}
	
	scm {
		git {
			remote {
				name('origin')
				url('https://github.com/himynameisfil/JenkinsCICD.git')
				credentials('GitCredential')
			}
			branch(ENV)
			if (!ENV.equals("master")) {
				extensions {
					mergeOptions {
						remote('origin')
						branch('master')
					}
				}
			}
		}
	}
	triggers {
		githubPush()
	}
	
	steps {
		dsl {
			external('./src/com/jenkinscicd/driver/PipelineGenerator.groovy')
			removeAction('DISABLE')
		}
	}
}

freeStyleJob('CryptoArb_'+ENV) {
	description('A pipeline that will deploy Crypto code '+ENV)
	description('A pipeline that will deploy Crypto code'+ENV)
	parameters {
		stringParam('ENV', ENV, "This is the environment variable associated to the branch")
	}
	
	scm {
		git {
			remote {
				name('origin')
				url('https://github.com/himynameisfil/CryptoArb.git')
				credentials('GitCredential')
			}
			branch(ENV)
			if (!ENV.equals("master")) {
				extensions {
					mergeOptions {
						remote('origin')
						branch('master')
					}
				}
			}
		}
	}

	triggers {
		githubPush()
	}
}