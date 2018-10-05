package common

import hudson.model.*
import hudson.model.Cause.*
import hudson.plugins.git.util.*
import hudson.tasks.test.*
import wk.health.cibs.exceptions.*
import EnvironmentSettings
import GlobalSettings

/**
 *  PipelineSteps class runs inside Jenkins context and performs all the Plugin/Jenkins commands and API
 */
Map getEnvVarsMap()
{
	// Initialize build and ENV settings using JSON and required ENV variables
	Map envVarsMap = [:]
	envVarsMap[Constants.ENV_BUILD_NUMBER] = env.BUILD_NUMBER
	envVarsMap[Constants.ENV_JOB_BASE_NAME] = env.JOB_BASE_NAME
	envVarsMap[Constants.ENV_BUILD_URL] = env.BUILD_URL
	envVarsMap[Constants.ENV_WORKSPACE] = env.WORKSPACE
	envVarsMap[Constants.ENV_JOB_NAME] = env.JOB_NAME
	envVarsMap
}

def printLine(String text, String logLevel)
{
	//ansi color plugin colors the Console output in the color layout named the same way as the Log level
	ansiColor(logLevel)
	{  println(text)  }
}

def readFromResources(String resourcePath)
{
	return libraryResource(resourcePath)
}

def executeCommand(String command,GlobalSettings globalSettings)
{
	Log.instance.debug("Executing Command:\n" + command)

	def statusCode

	if(globalSettings.nodeType.contains(Constants.NODE_LABEL_WINDOWS))
	{
		statusCode = bat returnStatus: true, script: command
	}
	else
	{
		statusCode = sh returnStatus: true, script: command
	}

	if (statusCode != 0)
	{
		//throw new IllegalBatchStatusCodeException("Command Failed to Execute. Returned Status Code: " + statusCode)
	}
}

def executeCommandWithEnvironment(String command,GlobalSettings globalSettings, List<String> environmentPaths)
{
	def statusCode
	Log.instance.debug("Running tools:")
	for (String toolPath : environmentPaths)
	{
		Log.instance.debug("Tool on path: ${toolPath}")
	}

	withEnv(environmentPaths)
	{
		if(globalSettings.nodeType.contains(Constants.NODE_LABEL_WINDOWS))
		{
			statusCode = bat returnStatus: true, script: command
		}
		else
		{
			statusCode = sh returnStatus: true, script: command
		}
	}

	if (statusCode != 0)
	{
		//throw new IllegalBatchStatusCodeException("Command Failed to Execute. Returned Status Code: " + statusCode)
	}
}

def executeCommandFromDirectory(String executeFrom, String commandToExecute, GlobalSettings globalSettings)
{
	dir(executeFrom?.trim() ? executeFrom : globalSettings.workspaceFolder)
	{ executeCommand(commandToExecute, globalSettings) }
}

public void executeCommandWithCredentialsFromDirectory(String executeFrom, String commandToExecute, Map credentials, GlobalSettings globalSettings)
{
	switch(credentials.type)
	{
		case Constants.CREDENTIALS_TYPE_AZURE_SERVICE_PRINCIPAL:
			withCredentials([azureServicePrincipal(credentials.id)]) {
				executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
			}
			break
		case Constants.CREDENTIALS_TYPE_AWS_CREDENTIALS_BINDING:
			withCredentials([[
					$class: Constants.CREDENTIALS_TYPE_AWS_CREDENTIALS_BINDING,
					accessKeyVariable: Constants.CREDENTIALS_TYPE_AWS_USER_MAPPING,
					credentialsId: credentials.id,
					secretKeyVariable: Constants.CREDENTIALS_TYPE_AWS_PASSWORD_MAPPING]])
			{
				executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
			}
			break
		case Constants.CREDENTIALS_TYPE_USERNAME_PASSWORD_SEPARATE_CREDENTIALS_BINDING:
			String usernameVariableName = Utilities.isNotNullOrEmpty(credentials.usernameVariableName) ? credentials.usernameVariableName : Constants.ENVIRONMENT_VARIABLE_CIBS_USERNAME
			String passwordVariableName = Utilities.isNotNullOrEmpty(credentials.passwordVariableName) ? credentials.passwordVariableName : Constants.ENVIRONMENT_VARIABLE_CIBS_PASSWORD
			withCredentials([usernamePassword(
				credentialsId: credentials.id,
				passwordVariable: passwordVariableName,
				usernameVariable: usernameVariableName)])
			{
				executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
			}
			break
		case Constants.CREDENTIALS_TYPE_SECRET_FILE:
			String variableName = Utilities.isNotNullOrEmpty(credentials.variableName) ? credentials.variableName : Constants.ENVIRONMENT_VARIABLE_CIBS_SECRET_FILE
			withCredentials([file(
				credentialsId: credentials.id,
				variable: variableName)])
			{
				executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
			}
			break
		case Constants.CREDENTIALS_TYPE_SECRET_TEXT:
			String variableName = Utilities.isNotNullOrEmpty(credentials.variableName) ? credentials.variableName : Constants.ENVIRONMENT_VARIABLE_CIBS_SECRET_TEXT
			withCredentials([string(credentialsId: credentials.id, variable: variableName)])
			{
				executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
			}
			break
		default:
			//throw new IllegalMethodSettingsException("Specified Credentials Type is not supported: ${credentials.type}!")
			break
	}
}

public void executeCommandWithCredentialsFromDirectory(String executeFrom, String commandToExecute, List credentialsList, GlobalSettings globalSettings)
{
	List stepCredentialsList = []

	for (Map credentials in credentialsList)
	{
		switch(credentials.type)
		{
			case Constants.CREDENTIALS_TYPE_AZURE_SERVICE_PRINCIPAL:
				stepCredentialsList.add(azureServicePrincipal(credentials.id))
				break
			case Constants.CREDENTIALS_TYPE_AWS_CREDENTIALS_BINDING:
				stepCredentialsList.add([$class: Constants.CREDENTIALS_TYPE_AWS_CREDENTIALS_BINDING, accessKeyVariable: Constants.CREDENTIALS_TYPE_AWS_USER_MAPPING, credentialsId: credentials.id, secretKeyVariable: Constants.CREDENTIALS_TYPE_AWS_PASSWORD_MAPPING])
				break
			case Constants.CREDENTIALS_TYPE_USERNAME_PASSWORD_SEPARATE_CREDENTIALS_BINDING:
				String usernameVariable = Utilities.isNotNullOrEmpty(credentials.usernameVariableName) ? credentials.usernameVariableName : Constants.ENVIRONMENT_VARIABLE_CIBS_USERNAME
				String passwordVariable = Utilities.isNotNullOrEmpty(credentials.passwordVariableName) ? credentials.passwordVariableName : Constants.ENVIRONMENT_VARIABLE_CIBS_PASSWORD
				stepCredentialsList.add(usernamePassword(credentialsId: credentials.id, passwordVariable: passwordVariable, usernameVariable: usernameVariable))
				break
			case Constants.CREDENTIALS_TYPE_SECRET_FILE:
				String variable = Utilities.isNotNullOrEmpty(credentials.variableName) ? credentials.variableName : Constants.ENVIRONMENT_VARIABLE_CIBS_SECRET_FILE
				stepCredentialsList.add(file(credentialsId: credentials.id, variable: variable))
				break
			case Constants.CREDENTIALS_TYPE_SECRET_TEXT:
				String variableName = Utilities.isNotNullOrEmpty(credentials.variableName) ? credentials.variableName : Constants.ENVIRONMENT_VARIABLE_CIBS_SECRET_TEXT
				stepCredentialsList.add([string(credentialsId: credentials.id, variable: variableName)])
				{
					executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
				}
				break
			default:
				//throw new IllegalMethodSettingsException("Specified Credentials Type is not supported: ${credentials.type}!")
				break
		} // End Switch Credentials Type
	} // End For Each Credentials in CredentialsList

	withCredentials(stepCredentialsList)
	{
		executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
	}
}

public String getVersionNumber(Map versionData)
{
	String projectStartDate = versionData.projectStartDate
	String versionNumberString = versionData.versionNumberString
	String versionPrefix = versionData.versionPrefix
	String overrideBuildsAllTime = versionData.overrideBuildsAllTime

	String versionNumber
	if (Utilities.isNotNullOrEmpty(overrideBuildsAllTime))
	{
		versionNumber = VersionNumber projectStartDate: projectStartDate, versionNumberString: versionNumberString, versionPrefix: versionPrefix, overrideBuildsAllTime: overrideBuildsAllTime
	}
	else
	{
		versionNumber = VersionNumber projectStartDate: projectStartDate, versionNumberString: versionNumberString, versionPrefix: versionPrefix
	}

	versionNumber
}

def String readFile(String filePath)
{
	Log.instance.debug("Reading file at path: " + filePath)

	return readFile(file: filePath)
}

public String executeReadFile(String filePath, String directory)
{
	String content
	dir(directory)
	{  content = readFile filePath  }

	return content
}

public String executePwd()
{
	return pwd()
}

def writeFile(String filePath, String content)
{
	Log.instance.debug("Writting file at ${filePath} with content: \n ${content}")

	writeFile file: filePath, text: content
}

public void executeGit(HashMap scmSettings)
{
	boolean shallow = true
	int depth = 1
	boolean noTags = true

	if (scmSettings.doShallowCheckout != null && scmSettings.doShallowCheckout == false)
	{
		shallow = false
		depth = 0
		noTags = false
	}

	boolean disableSubmodules = true
	boolean recursiveSubmodules = false

	if (scmSettings.submodules != null && (scmSettings.submodules.checkout as String).toLowerCase() == Constants.SUBMODULES_CHECKOUT_ALL)
	{
		disableSubmodules = false
		recursiveSubmodules = scmSettings.submodules.recursive != null ? scmSettings.submodules.recursive : recursiveSubmodules
	}

	if (Utilities.isNotNullOrEmpty(scmSettings.executeFrom))
	{
		executeGitStep(scmSettings.scmUrl, scmSettings.scmBranch, shallow, depth, noTags, disableSubmodules, recursiveSubmodules, scmSettings.executeFrom)
	}
	else
	{
		executeGitStep(scmSettings.scmUrl, scmSettings.scmBranch, shallow, depth, noTags, disableSubmodules, recursiveSubmodules)
	}
}

public void executeGitStep(String url, String branch, boolean shallow, int depth, boolean noTags, boolean disableSubmodules, boolean recursiveSubmodules)
{
	checkout(changelog: false, scm: [$class: 'GitSCM', branches: [[name: branch]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CheckoutOption', timeout: EnvironmentSettings.instance.settings.environment.scm.timeout], [$class: 'CloneOption', depth: depth, noTags: noTags, reference: '', shallow: shallow, timeout: EnvironmentSettings.instance.settings.environment.scm.timeout], [$class: 'SubmoduleOption', disableSubmodules: disableSubmodules, parentCredentials: true, recursiveSubmodules: recursiveSubmodules, reference: '', timeout: EnvironmentSettings.instance.settings.environment.scm.timeout, trackingSubmodules: false], [$class: 'LocalBranch', localBranch: branch]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials, url: url]]])
}

public void executeGitStep(String url, String branch, boolean shallow, int depth, boolean noTags, boolean disableSubmodules, boolean recursiveSubmodules, String directory)
{
	dir(directory)
	{
		executeGitStep(url, branch, shallow, depth, noTags, disableSubmodules, recursiveSubmodules)
	}
}

public void executeSvn(HashMap scmSettings)
{
	if (Utilities.isNotNullOrEmpty(scmSettings.executeFrom))
	{
		dir(scmSettings.executeFrom)
		{
			checkout(changelog: false, scm: [$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, clearWorkspace: true, includedRegions: '', locations: [[credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials, depthOption: 'infinity', ignoreExternalsOption: true, remote: scmSettings.scmUrl, local: '.']], workspaceUpdater: [$class: 'UpdateUpdater']])
		}
	}
	else
	{
		checkout(changelog: false, scm: [$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, clearWorkspace: true, includedRegions: '', locations: [[credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials, depthOption: 'infinity', ignoreExternalsOption: true, remote: scmSettings.scmUrl, local: '.']], workspaceUpdater: [$class: 'UpdateUpdater']])
	}
}

public void executeSscm(HashMap scmSettings)
{
	if (Utilities.isNotNullOrEmpty(scmSettings.executeFrom))
	{
		dir(scmSettings.executeFrom)
		{
			sscm credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials, url: scmSettings.scmUrl
		}
	}
	else
	{
		sscm credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials, url: scmSettings.scmUrl
	}
}


def executeWithCredentials(classParameter, username, password, batString, gs)
{
	withCredentials([[
			$class: classParameter,
			credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials,
			usernameVariable: username,
			passwordVariable: password
		]])
	{
		Pipeline.instance.executeCommand(batString,gs)
	}
}

def executeWithCredentials(directory, classParameter, username, password, batString, gs)
{
	dir(directory)
	{
		return executeWithCredentials(classParameter, username, password, batString, gs)
	}
}

def checkFileExists(String fileName, String source, GlobalSettings gs)
{
	dir(source?.trim() ? source : gs.workspaceFolder)
	{  return fileExists(fileName)  }
}

def lookForFiles(String pattern, String location, GlobalSettings gs)
{
	def searchResults = []

	dir(location?.trim() ? location : gs.workspaceFolder)
	{
		searchResults = findFiles(glob: pattern)
	}

	return searchResults
}

def executeXUnit(String analyzer, String pattern, String failure, String unstable)
{
	def testTimeMargin = Constants.TEST_TIME_MARGIN

	Log.instance.debug("XUnit analyzer: ${analyzer}")
	Log.instance.debug("XUnit pattern: ${pattern}")
	Log.instance.debug("XUnit failure: ${failure}")
	Log.instance.debug("XUnit unstable: ${unstable}")
	Log.instance.debug("XUnit time margin: ${testTimeMargin}")

	// testTimeMargin - number of seconds for the plugin to analyse the results before it hangs - currently set to 3000 in constants
	step([
		$class: 'XUnitBuilder', testTimeMargin: testTimeMargin, thresholdMode: 1,
		thresholds: [[
				$class: 'FailedThreshold', failureThreshold: failure, unstableThreshold: unstable
			]],
		tools: [[
				$class: analyzer,
				//if deleteOutputFiles is set to false, we would get another XML file with results of the analysis
				deleteOutputFiles: true,
				failIfNotNew: true,
				//pattern for finding the results files from Test Tool execution - in case of MsTest those are .trx
				pattern: pattern,
				skipNoTestFiles: false,
				stopProcessingIfError: true
			]]])
}

def sendExtendedEmailNotification(String emailContent, Object jobRecipientList)
{
	def recipientList = EnvironmentSettings.instance.settings.environment.emailNotification.buildMgrRecipients
	def mimeType = EnvironmentSettings.instance.settings.environment.emailNotification.mimeType
	def replyTo = EnvironmentSettings.instance.settings.environment.emailNotification.replyTo
	def subject = EnvironmentSettings.instance.settings.environment.emailNotification.subject

	if(jobRecipientList.trim())
	{
		recipientList = recipientList.plus(",")
		recipientList = recipientList.plus(jobRecipientList)

		Log.instance.debug("Sending email to: ${Constants.TEXT_NEW_LINE} ${recipientList}")
	}

	emailext body:emailContent, mimeType: mimeType, replyTo: replyTo, subject: subject, to: recipientList
}

def deleteDirectory(String directoryPath)
{
	dir(directoryPath)
	{
		Log.instance.debug("Trying to delete folder ${directoryPath}")

		deleteDir()

		Log.instance.debug("Deleted folder ${directoryPath}")
	}
}

//to do - check if we can use this place to get current build status without depending on common/functional testing build
public String getBuildStartTime()
{
	return currentBuild.startTimeInMillis
}

public String getCurrentBuildStatus()
{
	return currentBuild.result
}

public long currentTimeMillis()
{
	return System.currentTimeMillis()
}

//Method for getting the URL to the Test Results Analyzer in the Jenkins UI for the current pipeline for last 15 builds
public String getRecentBuildsTestResultsUrl(String buildUrl)
{
	Log.instance.debug("Build url: ${buildUrl}")
	String buildNumber = currentBuild.number
	return buildUrl.split("${buildNumber}/")[0].concat("test_results_analyzer")
}

//Method for getting the URL to the Unit Tests results in the Jenkins UI for the current build
public String getBuildTestResultsUrl(String buildUrl)
{
	def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
	if(testResultAction != null)
	{
		return "${buildUrl}testReport"
	}

	return "No test report links generated."
}

//Method for getting the URL to the build artifacts - Currently only providing the link to the Workspace in the Jenkins UI
public String getWorkspaceArtifactsUrl(String buildUrl)
{
	def workspace = "${buildUrl}execution/node/6/ws/"
	Log.instance.debug("Workspace: ${workspace}")
	return workspace
}

//Method for getting the String with Unit Test results for the current build with number of Failed, Total and Skipped unit tests.
public String getTestResults()
{
	def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
	String summary

	if (testResultAction != null)
	{
		def total = testResultAction.getTotalCount()
		def failed = testResultAction.getFailCount()
		def skipped = testResultAction.getSkipCount()

		summary = "Test results:"
		summary = summary + ("Passed: " + (total - failed - skipped))
		summary = summary + (", Failed: " + failed)
		summary = summary + (", Skipped: " + skipped)

	}
	else
	{
		summary = "No unit tests performed."
	}
	return summary
}

//Method for providing the String from Jenkins Console UI showing Started By <user name>
public String getStartedBy()
{
	def action = currentBuild.rawBuild.getActions(CauseAction.class)
	return action[0].causes[0].shortDescription
}

//public publishCoberturaCoverage(String coberturaReportFile)
public publishCoberturaCoverage(String coberturaReportFile, boolean onlyStable, boolean failUnhealthy, boolean failUnstable, boolean autoUpdateHealth, boolean autoUpdateStability, boolean zoomCoverageChart, boolean failNoReports, sourceEncoding, int maxNumberOfBuilds)
{
	Log.instance.debug("Report search file pattern: ${coberturaReportFile}")
	Log.instance.debug("Publishing results to Jenkins UI:")
	step([$class: 'CoberturaPublisher' , coberturaReportFile: coberturaReportFile, onlyStable: onlyStable, failUnhealthy: failUnhealthy, failUnstable: failUnstable, autoUpdateHealth: autoUpdateHealth, autoUpdateStability: autoUpdateStability, zoomCoverageChart: zoomCoverageChart, failNoReports: failNoReports, sourceEncoding: sourceEncoding, maxNumberOfBuilds: maxNumberOfBuilds])
}

public void publishCoverageResults(HashMap methodSettings, GlobalSettings globalSettings)
{
	Log.instance.debug("publishCoverageResults methodSettings input values: " + methodSettings)
	if (methodSettings.coverageTool.toLowerCase().contains(Constants.COVERAGE_TOOL_JACOCO))
	{
		String scriptString = EnvironmentSettings.instance.settings.environment.envtools.python + " " + EnvironmentSettings.instance.settings.environment.codeCoverage.cover2coverTool + " ${methodSettings.inputFile} >> ${globalSettings.workspaceFolder}/${Constants.COBERTURA_REPORT_NAME}"
		Log.instance.debug("Jacoco xml report path: " + globalSettings.workspaceFolder + "/" + methodSettings.inputFile)
		Log.instance.debug("Converting Jacoco xml to Cobertura: " + scriptString)
		executeCommand(scriptString, globalSettings)
		Log.instance.debug("Publishing Cobertura coverage")
		publishCoberturaCoverage("${Constants.TEXT_ANT_ANY_FOLDER_REGEX}${Constants.COBERTURA_REPORT_NAME}", methodSettings.onlyStable, methodSettings.failUnhealthy, methodSettings.failUnstable, methodSettings.autoUpdateHealth, methodSettings.autoUpdateStability, methodSettings.zoomCoverageChart, methodSettings.failNoReports, methodSettings.sourceEncoding, methodSettings.maxNumberOfBuilds)
	}
	else if (methodSettings.coverageTool.toLowerCase().contains(Constants.COVERAGE_TOOL_COBERTURA))
	{		
		Log.instance.debug("Publishing Cobertura coverage")
		publishCoberturaCoverage(methodSettings.inputFile, methodSettings.onlyStable, methodSettings.failUnhealthy, methodSettings.failUnstable, methodSettings.autoUpdateHealth, methodSettings.autoUpdateStability, methodSettings.zoomCoverageChart, methodSettings.failNoReports, methodSettings.sourceEncoding, methodSettings.maxNumberOfBuilds)
	}
	else
	{
		//throw new IllegalMethodSettingsException("Failed to publish results: coverageTool is missing in displayCoverage method settings.")
	}
}

public publishJacocoCoverage(String jacocoInputFile, String sourcePath, String classPath)
{
	Log.instance.debug("Publishing Jacoco results to Jenkins UI:")
	step([$class: 'JacocoPublisher', execPattern: jacocoInputFile, sourcePattern: sourcePath, classPattern: classPath])
}

public String executeCommandReturnStandardOutput(String command, String nodeType)
{
	return nodeType.contains(Constants.NODE_LABEL_WINDOWS) ? bat(returnStdout: true, script: "@${command}") : sh(returnStdout: true, script: command)
}

public String executeCommandReturnStandardOutput(String directory, String command, String nodeType)
{
	dir(directory)
	{
		return executeCommandReturnStandardOutput(command, nodeType)
	}
}

public String executeCommandReturnStandardOutput(String command, String nodeType, String username, String password)
{
	withCredentials([[
			$class: "UsernamePasswordMultiBinding",
			credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials,
			usernameVariable: username,
			passwordVariable: password
		]])
	{
		return executeCommandReturnStandardOutput(command, nodeType)
	}
}

public String executeCommandReturnStandardOutput(String directory, String command, String nodeType, String username, String password)
{
	withCredentials([[
			$class: "UsernamePasswordMultiBinding",
			credentialsId: EnvironmentSettings.instance.settings.environment.scm.credentials,
			usernameVariable: username,
			passwordVariable: password
		]])
	{
		return executeCommandReturnStandardOutput(directory, command, nodeType)
	}
}

/**
 * <ul>
 * 		<li><strong>allowMissing</strong> - If checked, will allow report to be missing and build will not fail on missing report</li>
 * 		<li><strong>alwaysLinkToLastBuild</strong> - If this control and "Keep past HTML reports" are checked, publish the link on project level even if build failed.</li>
 * 		<li><strong>keepAll</strong> - If checked, archive reports for all successful builds, otherwise only the most recent</li>
 * 		<li><strong>reportDir</strong> - The path to the HTML report directory relative to the workspace.</li>
 * 		<li><strong>reportFiles</strong> - The file(s) to provide links inside the report directory</li>
 * 		<li><strong>reportName</strong> - The name of the report to display for the build/project, such as "Code Coverage"</li>
 * 		<li><strong>reportTitles</strong> - The optional title(s) for the report files, which will be used as the tab names. If this is not provided, file names will be used instead.</li>
 * </ul> 
 */
public void publishHtmlReport(String reportDirectory, String reportName)
{
	publishHTML([
		allowMissing: false,
		alwaysLinkToLastBuild: false,
		keepAll: true,
		reportDir: reportDirectory,
		reportFiles: "index.htm",
		reportName: reportName,
		reportTitles: ""
	])
}

/**
 * <ul>
 * 		<li><strong>autoUpdateHealth</strong> - Auto update threshold for health on successful build.</li>
 * 		<li><strong>autoUpdateStability</strong> - Auto update threshold for stability on successful build</li>
 * 		<li><strong>coberturaReportFile</strong> - This is a file name pattern that can be used to locate the cobertura xml report files (for example with Maven2 use **\/target/site/cobertura/coverage.xml (the backslash is there to escape the comment-ending token)). The path is relative to the module root unless you have configured your SCM with multiple modules, in which case it is relative to the workspace root. Note that the module root is SCM-specific, and may not be the same as the workspace root.</li>
 * 		<li><strong>failUnhealthy</strong> - Unhealthy projects will be failed.</li>
 * 		<li><strong>failUnstable</strong> - Unstable projects will be failed.</li>
 * 		<li><strong>maxNumberOfBuilds</strong> - Only graph the most recent N builds in the coverage chart, 0 disables the limit.</li>
 * 		<li><strong>onlyStable</strong> - Include only stable builds, i.e. exclude unstable and failed ones.</li>
 * 		<li><strong>sourceEncoding</strong> - Encoding when showing files.</li>
 * 		<li><strong>zoomCoverageChart</strong> - Zoom the coverage chart and crop area below the minimum and above the maximum coverage of the past reports.</li>
 * </ul>
 */
public void publishCoberturaReport(String reportFilePattern)
{
	step([
		$class: "CoberturaPublisher",
		autoUpdateHealth: false,
		autoUpdateStability: false,
		coberturaReportFile: reportFilePattern,
		failUnhealthy: false,
		failUnstable: false,
		maxNumberOfBuilds: 0,
		onlyStable: false,
		sourceEncoding: "ASCII",
		zoomCoverageChart: false
	])
}

public Object getArtifactoryServerProperties()
{
	return Artifactory.server(EnvironmentSettings.instance.settings.environment.artifactory.artifactoryServerId)
}

public Object getArtifactProperties(artifactoryServerId, artifactoryPath)
{
	def server = getArtifactoryServerProperties()
	def artifactoryResponse = executeHttpRequest('APPLICATION_JSON', "${EnvironmentSettings.instance.settings.environment.artifactory.artifactoryJenkinsCredentialsId}", 'APPLICATION_JSON', true, 'GET', "", server.url + "/api/storage/" + artifactoryPath)
	def artifactoryResponseJson = Utilities.parseJson(artifactoryResponse.content)
	def arifactoryResponseData = [:]

	return artifactoryResponseJson
}

public Object executeHttpRequest(acceptType, authentication, contentType, ignoreSslErrors, httpMethod, responseHandle, url)
{
	def httpResponseObj =  httpRequest acceptType: acceptType,
	authentication: authentication,
	contentType: contentType,
	ignoreSslErrors: ignoreSslErrors,
	httpMode: httpMethod,
	url: url
	if ((httpResponseObj.status < 100) && (httpResponseObj.status > 399 ))
	{
		//throw new IllegalMethodSettingsException("Failed to get HTTP response to ${url}: Http request returned staus ${httpResponseObj.status}.")
	}
	return httpResponseObj

	/*
	 * httpRequest acceptType: 'APPLICATION_JSON',
	 * authentication: 'f8bb9d29-6082-40a9-b17f-83fc2cf1a322',
	 * contentType: 'APPLICATION_JSON',
	 * customHeaders: [[maskValue: false, name: '', value: '']],
	 * ignoreSslErrors: true,
	 * httpMode: 'GET',
	 * url: 'http://10.234.12.212:8080/artifactory/api/storage/ovid-generic-snapshot-public/validation_service.tar'
	 */
}

public void executeArtifactoryDownload(String artifactoryServerId,String downloadSpecification)
{
	def server = Artifactory.server artifactoryServerId
	server.download(downloadSpecification)
}

public Map executeArtifactoryUpload(String artifactoryServerId, String uploadSpecification, List<String> buildInfoEnvVariables)
{
	def buildInfo = Artifactory.newBuildInfo()

	//start capturing env values
	buildInfo.env.capture = true

	//specify env values to capture
	for(def envVar in buildInfoEnvVariables)
	{
		buildInfo.env.filter.addInclude(envVar)
	}

	def server = Artifactory.server artifactoryServerId

	server.upload spec: uploadSpecification, buildInfo: buildInfo

	server.publishBuildInfo buildInfo

	Map buildInfoOutput = [:]
	buildInfoOutput.name = buildInfo.name
	buildInfoOutput.number = buildInfo.number

	buildInfoOutput
}

public void executeNunit()
{
	nunit(testResultsPattern: Constants.REPORT_FILE_UNIT_TESTS_NUNIT)
}

public Map executeBuild(String jobPath, String buildParameters)
{
	return build(job: jobPath, parameters: [string(name: Constants.BUILD_PARAMETERS_RELEASE, value: buildParameters)]).getBuildVariables()
}

public void executeSetEnvVariable(String name, String value)
{
	env[name] = value
}

public String executeGetEnvVariable(String name)
{
	return env[name]
}

public Map executeReadJsonText(String text)
{
	readJSON(file: '', text: text)
}

public void publishHtmlReport(String directory, String indexFile, String name)
{
	publishHTML([
		allowMissing: false,
		alwaysLinkToLastBuild: false,
		keepAll: false,
		reportDir: directory,
		reportFiles: indexFile,
		reportName: name,
		reportTitles: ''
	])
}

public void executeArtifactoryPromoteBuild(String buildName, String buildNumber, String sourceRepository, String targetRepository)
{
	Map promotionConfig = new HashMap()

	// Mandatory parameters
	promotionConfig.buildName = buildName
	promotionConfig.buildNumber = buildNumber
	promotionConfig.targetRepo = targetRepository

	// Optional parameters
	// promotionConfig.comment = 'This is a promotion comment.'
	// promotionConfig.status = 'Status'
	// promotionConfig.includeDependencies = false
	// promotionConfig.copy = false
	// promotionConfig.failFast = true

	if (Utilities.isNotNullOrEmpty(sourceRepository))
	{
		promotionConfig.sourceRepo = sourceRepository
	}

	Artifactory.server(EnvironmentSettings.instance.settings.environment.artifactory.artifactoryServerId).promote(promotionConfig)
}

public void executeWriteFile(String path, String contents)
{
	writeFile file: path, text: contents
}

void executeWithDockerContainer(String image, List<String> scripts)
{
	withDockerContainer(image)
	{ executeSh(scripts) }
}

void executeSh(List<String> scripts)
{
	for (String script in scripts)
	{
		executeSh(script)
	}
}

void executeSh(String script)
{
	sh(script)
}

public String executeGetArtifactoryServerUrl()
{
	return Artifactory.server(EnvironmentSettings.instance.settings.environment.artifactory.artifactoryServerId).url
}

public Object executeGetContentForHttpRequestResponse(response)
{
	return response.getContent()
}

boolean executeIsUnix()
{
	isUnix()
}
