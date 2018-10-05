#!/usr/bin/env groovy
import common.*

node {
	stage ('Build') {
		echo "Build !"
		
		def mvnHome
		// Run the maven build
		if (isUnix()) {
			sh '$MAVEN_HOME/mvn package'
		} else {
			bat(/call mvn package/)
		}

	}
}

//package builds

//class myBuild {
	
//}
/*

withMaven(
			// Maven installation declared in the Jenkins "Global Tool Configuration"
			maven: 'M3',
			// Maven settings.xml file defined with the Jenkins Config File Provider Plugin
			// Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
			mavenSettingsConfig: 'my-maven-settings',
			mavenLocalRepo: '.repository') {
	 
		  // Run the maven build
		  sh "mvn clean install"
	 
		}
		
		
		
def call() {
	echo "Build !"
	
	def mvnHome
	
	// Run the maven build
	if (isUnix()) {
		sh '$MAVEN_HOME/mvn package'
	} else {
		bat(/call mvn package/)
	}
		 
}



 
	git 'https://github.com/vmmarko/Azure-training.git'
	
	if (isUnix()){
		sh '$MAVEN_HOME/mvn compile'
	} else {
		bat(/call mvn clean compile/)
	}
	
	if (isUnix()) {
		sh '$MAVEN_HOME/mvn package'
	 } else {
		bat(/call mvn package/)
	 }
	 
	 if (isUnix()){
		 sh '$MAVEN_HOME/mvn surefire-report:report'
	 } else {
		 bat (/call mvn surefire-report:report/)
	 }
	 
	 if (isUnix()){
		 sh '$MAVEN_HOME/mvn tomcat7:redeploy'
	 } else {
		 bat (/call mvn tomcat7:redeploy/)
	 } 

 
 
 
def preparation(){
	// Get some code from a GitHub repository
	git 'https://github.com/vmmarko/Azure-training.git'
}


def compile(){
	if (isUnix()){
		sh '$MAVEN_HOME/mvn compile'
	} else {
		bat(/call mvn clean compile/)
	}
}


def build() {
    //mvn clean deploy -U
	if (isUnix()) {
		sh '$MAVEN_HOME/mvn package'
	 } else {
		bat(/call mvn package/)
	 }
}

def test() {
    //sh "/usr/local/bin/${name}"
	if (isUnix()){
		sh '$MAVEN_HOME/mvn surefire-report:report'
	} else {
		bat (/call mvn surefire-report:report/)
	}
}

def deploy(env,app) {
	if (isUnix()){
		sh '$MAVEN_HOME/mvn tomcat7:redeploy'
	} else {
		bat (/call mvn tomcat7:redeploy/)
	}
}

*/