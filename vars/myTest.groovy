#!/usr/bin/env groovy
import common.*

def call(String stage) {
	echo "$stage"

	def mvnHome

	if (isUnix()){
		sh 'mvn surefire-report:report'
	} else {
		bat (/call mvn surefire-report:report/)
	}
}
