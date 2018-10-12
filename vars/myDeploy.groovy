#!/usr/bin/env groovy
import common.*

def call(String stage) {
	echo "$stage"

	def mvnHome

		if (isUnix()){
			sh 'mvn tomcat7:redeploy'
		} else {
			bat (/call mvn tomcat7:redeploy/)
		}
}