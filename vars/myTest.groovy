#!/usr/bin/env groovy

node {
	stage ('Test') {
		echo "Test !"

		def mvnHome

		if (isUnix()){
			sh '$MAVEN_HOME/mvn surefire-report:report'
		} else {
			bat (/call mvn surefire-report:report/)
		}
	}
}


