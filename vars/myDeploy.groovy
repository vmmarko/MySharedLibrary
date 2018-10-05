#!/usr/bin/env groovy

def call() {
	echo "Deploy !"
	
	def mvnHome
	
	stage('Tomcat Deploy') {
		if (isUnix()){
			sh '$MAVEN_HOME/mvn tomcat7:redeploy'
		} else {
			bat (/call mvn tomcat7:redeploy/)
		}
	}
}

