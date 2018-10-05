#!/usr/bin/env groovy
import common.*

public class myPreparation {
	node {
		stage ('Preparation') {
			echo "Preparation my !"

			// Get some code from a GitHub repository
			git 'https://github.com/vmmarko/Azure-training.git'

			withMaven(
			// Maven installation declared in the Jenkins "Global Tool Configuration"
			maven: 'M3',
			// Maven settings.xml file defined with the Jenkins Config File Provider Plugin
			// Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
			mavenSettingsConfig: 'my-maven-settings',
			mavenLocalRepo: '.repository') {

				// Run the maven build
				sh "mvn clean install"

			}
		}
	}
}