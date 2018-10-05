@Library('MySharedLibrary@master')_
import common.*

node {

  	stage('Demo') {
  		myDemo 'Vladislav'
  		echo "Hello Nikola"
	}
	
	stage('Preparation') {
		echo "Preparation !"
		checkout scm
	
  		def mvnHome
	
		// Get some code from a GitHub repository
		git 'https://github.com/vmmarko/Azure-training.git'
	}

	stage('Compile') {
		echo "Compile !"
		
 		def mvnHome
	
		if (isUnix()){
			sh '$MAVEN_HOME/mvn compile'
		} else {
			bat(/call mvn clean compile/)
		}
	}
	
	stage('build') {
	    //build code (ex: mvn deploy)
	    echo "Building !"
	    
	    def mvnHome
	    
		// Run the maven build
		if (isUnix()) {
			sh '$MAVEN_HOME/mvn package'
		} else {
			bat(/call mvn package/)
		}
	}
 
	stage('Test') {
	    //test code (ex: sh /path/to/test/script.sh)
	    echo "Testing !"
	
		def mvnHome
		
		if (isUnix()){
			sh '$MAVEN_HOME/mvn surefire-report:report'
		} else {
			bat (/call mvn surefire-report:report/)
			}
	
	}
	
	stage('Deploy') {
	    //deploy code (ex: aws cloudformation create-stack)
	    def mvnHome
	
		if (isUnix()){
			sh '$MAVEN_HOME/mvn tomcat7:redeploy'
		} else {
			bat (/call mvn tomcat7:redeploy/)
		}
	}
	
}


//String buildSettingsJson = 'resources/stages/execution/parameterized/Build_Settings.json'

//builds.Component().executeFromWorkspace(buildSettingsJson, 'linux', 'DEBUG')


