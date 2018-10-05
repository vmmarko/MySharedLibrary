#!/usr/bin/groovy

package common

class myPipeline {

}

def build() {
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