#!/usr/bin/env groovy

def call() {
	echo "Preparation !"
	
	def mvnHome
	
	stage('Preparation') {
		// Get some code from a GitHub repository
		git 'https://github.com/vmmarko/Azure-training.git'
		
	}
}