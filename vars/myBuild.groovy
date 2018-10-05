#!/usr/bin/env groovy

package builds

//class myBuild {
	
//}

def call(String name = 'Vladislav') {
	echo "Hello, ${name}."
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
		//junit '**/target/surefire-reports/TEST-.xml'
	} else {
		bat (/call mvn surefire-report:report/)
		// junit '**/target/surefire-reports/TEST-.xml'}
	}
}

def deploy(env,app) {
	if (isUnix()){
		sh '$MAVEN_HOME/mvn tomcat7:redeploy'
	} else {
		bat (/call mvn tomcat7:redeploy/)
	}
}