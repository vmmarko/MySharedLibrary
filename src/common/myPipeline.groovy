#!/usr/bin/groovy

package common

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



/*
 * def build() {

	mvn clean deploy -U
  }
  def test(name) {
	sh "/usr/local/bin/${name}"
  }
  def deploy(env,app) {
	aws cloudformation create-stack \
	--stack-name ${env}_{app)
	--parameters \
	  ParameterKey=Env,ParameterValue=${env}
	...(rest of the params go here)
  }

class myPipeline {

}


  */