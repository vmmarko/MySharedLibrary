#!/usr/bin/env groovy
import common.*

node {
	stage ('Preparation') {
		echo "Preparation my !"

		def mvnHome

		// Get some code from a GitHub repository
		git 'https://github.com/vmmarko/Azure-training.git'
/*
		withMaven(
				maven: 'M3',
				mavenSettingsConfig: 'my-maven-settings',
				mavenLocalRepo: '.repository')
		{  sh "mvn clean install" }
	}
*/	
}