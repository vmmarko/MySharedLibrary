#!/usr/bin/env groovy
import common.*

node {
	stage ('Compile') {
		echo "Compile !"

		def mvnHome

		if (isUnix()){
			sh '$MAVEN_HOME/mvn compile'
			echo "Compile Unix"
		} else {
			bat(/call mvn clean compile/)
			echo "Compile Win"
		}
	}
	echo "Compile end"
}


