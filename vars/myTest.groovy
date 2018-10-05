#!/usr/bin/env groovy

def call() {
	echo "Testing !"
	
	def mvnHome
	
	if (isUnix()){
		sh '$MAVEN_HOME/mvn surefire-report:report'
	} else {
		bat (/call mvn surefire-report:report/)

}

