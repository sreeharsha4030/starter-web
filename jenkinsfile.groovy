https://github.com/sreeharsha4030/starter-web
pipeline
	agent any
	
		tools {
				jdk "java-1.8"
				}
		stages {
			stage('SCM checkout') {
				steps {
					git url: 'https://github.com/sreeharsha4030/starter-web.git'
				}
			}
			
			stage ('archiving artifacts') {
				steps {
					archiveArtifacts '**/*.html'
				}
			}
			
			stage('transfer artifacts') {
				steps {
						sshPublisher(publishers: [sshPublisherDesc(configName: 'webserver', transfers: [sshTransfer(excludes: '', execCommand: '', execTimeout: 120000, flatten: true,
						markEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/var/www/html', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '**/*.html')],
						usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)])
						
				}
			}
		}

