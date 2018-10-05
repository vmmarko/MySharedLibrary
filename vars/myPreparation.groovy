#!/usr/bin/env groovy

def call() {
	echo "Preparation my !"
	
	def mvnHome
	
	// Get some code from a GitHub repository
	git 'https://github.com/vmmarko/Azure-training.git'
		
}