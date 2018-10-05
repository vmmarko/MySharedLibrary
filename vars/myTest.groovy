#!/usr/bin/env groovy

node {
	stage ('Testing') {
		echo "Testing !"

		def mvnHome

		if (isUnix()){
			sh '$MAVEN_HOME/mvn surefire-report:report'
		} else {
			bat (/call mvn surefire-report:report/)
		}
	}
}


