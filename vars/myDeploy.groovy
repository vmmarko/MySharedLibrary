#!/usr/bin/env groovy
import common.*

node {
	stage ('Deploy') {
		echo "Deploy !"

		def mvnHome

		if (isUnix()){
			sh '$MAVEN_HOME/mvn tomcat7:redeploy'
		} else {
			bat (/call mvn tomcat7:redeploy/)
		}
	}
}


