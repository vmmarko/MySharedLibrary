#!/usr/bin/env groovy

def call() {
	echo "Testing !"
	
	def mvnHome
	
	stage('Test') {
		if (isUnix()){
			sh '$MAVEN_HOME/mvn surefire-report:report'
			//junit '**/target/surefire-reports/TEST-.xml'
		} else {
			bat (/call mvn surefire-report:report/)
			// junit '**/target/surefire-reports/TEST-.xml'}
		}
}

