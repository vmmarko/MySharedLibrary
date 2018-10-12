#!/usr/bin/env groovy
import common.*
import hudson.plugins.git.GitSCM


def call(String myGit){
	//node {
		//stage ('Preparation') {
			echo "Preparation my !"+${myGit}

			def mvnHome

			// Get some code from a GitHub repository
			git ${myGit}
			//git 'https://github.com/vmmarko/Azure-training.git'

						/*
			 withMaven(
			 maven: 'M3',
			 mavenSettingsConfig: 'my-maven-settings',
			 mavenLocalRepo: '.repository')
			 {  sh "mvn clean install" }
			 }
			 */	
		//}
	//}
}

/*

import hudson.plugins.git.GitSCM

def call(String url) {
	git "${url.getText('utf-8')}"
}

*/