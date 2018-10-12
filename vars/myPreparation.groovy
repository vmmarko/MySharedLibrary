#!/usr/bin/env groovy
//import common.*


def call(String myGit){
	//node {
		//stage ('Preparation') {
			echo "Preparation my ! - ${myGit}"

			def mvnHome

			git "${myGit}"
			
			if (isUnix()){
				sh '$MAVEN_HOME/mvn compile'
			 } else {
				 bat(/call mvn clean compile/)
			 }

}

