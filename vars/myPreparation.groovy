#!/usr/bin/env groovy
import common.*

node {
def call() {
	echo "Preparation my !"
	
	def mvnHome
	
	// Get some code from a GitHub repository
	git 'https://github.com/vmmarko/Azure-training.git'
		
}
}