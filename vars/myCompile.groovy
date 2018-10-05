#!/usr/bin/env groovy

def call() {
	echo "Compile !"
	
	def mvnHome
	
	stage('Compile'){
       if (isUnix()){
          sh '$MAVEN_HOME/mvn compile' 
       } else {
           bat(/call mvn clean compile/)
       }
   }
}
