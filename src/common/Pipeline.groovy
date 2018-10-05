package common

import GlobalSettings

public class Pipeline implements Serializable
{
	private static Pipeline instance = new Pipeline()

	private static PipelineSteps ps = new PipelineSteps()

	private Pipeline()
	{}

	public static Pipeline getInstance()
	{
		if (instance == null)
		{
			instance = new Pipeline()
		}
		return instance
	}

	def readFromResources(String resourcePath)
	{
		ps.readFromResources(resourcePath)
	}

	def printLine(String text, String logLevel)
	{
		ps.printLine(text, logLevel)
	}

	def executeCommand(String command,GlobalSettings gs)
	{
		ps.executeCommand(command, gs)
	}

	def executeCommandWithEnvironment(String batchCommand, GlobalSettings globalSettings, List<String> environmentPaths)
	{
		ps.executeCommandWithEnvironment(batchCommand,globalSettings,environmentPaths)
	}

	def executeCommandFromDirectory (String executeFrom, String commandToExecute, GlobalSettings globalSettings)
	{
		ps.executeCommandFromDirectory(executeFrom, commandToExecute, globalSettings)
	}

	public void executeCommandWithCredentialsFromDirectory(String executeFrom,  String commandToExecute, Map credentials, GlobalSettings globalSettings)
	{
		ps.executeCommandWithCredentialsFromDirectory(executeFrom, commandToExecute, credentials, globalSettings)
	}

	public void executeCommandWithCredentialsFromDirectory(String executeFrom,  String commandToExecute, List credentialsList, GlobalSettings globalSettings)
	{
		ps.executeCommandWithCredentialsFromDirectory(executeFrom, commandToExecute, credentialsList, globalSettings)
	}

	public Map getEnvVarsMap()
	{
		ps.getEnvVarsMap()
	}

	public String getVersionNumber(Map versionData)
	{
		ps.getVersionNumber(versionData)
	}

	def readFile(filePath)
	{
		return ps.readFile(filePath)
	}

	public String executeReadFile(String filePath, String directory)
	{
		return ps.executeReadFile(filePath, directory)
	}

	public String executePwd()
	{
		return ps.executePwd()
	}

	def writeFile(String filePath, String content)
	{
		return ps.writeFile(filePath,content)
	}

	def deleteDirectory(String directoryPath)
	{
		return ps.deleteDirectory(directoryPath)
	}

	def executeGit(scm)
	{
		ps.executeGit(scm)
	}

	def executeSvn(scm)
	{
		ps.executeSvn(scm)
	}

	def executeSscm(scm)
	{
		ps.executeSscm(scm)
	}

	def executeWithCredentials(classParameter, username, password, batString, gs)
	{
		ps.executeWithCredentials(classParameter, username, password, batString, gs)
	}

	def executeWithCredentials(directory, classParameter, username, password, batString, gs)
	{
		ps.executeWithCredentials(directory, classParameter, username, password, batString, gs)
	}

	def checkFileExists(String file, String source, GlobalSettings gs)
	{
		ps.checkFileExists(file,source, gs)
	}

	def lookForFiles(String pattern, String location, GlobalSettings gs)
	{
		ps.lookForFiles(pattern,location,gs)
	}

	def executeXUnit(String analyzer, String pattern, String failure, String unstable)
	{
		ps.executeXUnit(analyzer,pattern,failure,unstable)
	}

	def sendExtendedEmailNotification(String emailContent,Object jobRecipientList)
	{
		ps.sendExtendedEmailNotification(emailContent,jobRecipientList)
	}

	def getBuildStartTime()
	{
		return ps.getBuildStartTime()
	}

	def getCurrentBuildStatus()
	{
		return ps.getCurrentBuildStatus()
	}

	def currentTimeMillis()
	{
		return ps.currentTimeMillis()
	}

	def getTestResults()
	{
		return ps.getTestResults()
	}

	def getRecentBuildsTestResultsUrl(String buildUrl)
	{
		return ps.getRecentBuildsTestResultsUrl(buildUrl)
	}

	def getBuildTestResultsUrl(String buildUrl)
	{
		return ps.getBuildTestResultsUrl(buildUrl)
	}

	def getStartedBy()
	{
		return ps.getStartedBy()
	}

	def getWorkspaceArtifactsUrl(String buildUrl)
	{
		return ps.getWorkspaceArtifactsUrl(buildUrl)
	}

	def publishCoverageResults(HashMap methodSettings, GlobalSettings globalSettings)
	{
		ps.publishCoverageResults(methodSettings, globalSettings)
	}

	def publishJacocoCoverageResults(String jacocoReportPath, String srcPath, String classPath)
	{
		ps.publishJacocoCoverage(jacocoReportPath, srcPath, classPath)
	}

	public String executeCommandReturnStandardOutput(String command, String nodeType)
	{
		return ps.executeCommandReturnStandardOutput(command, nodeType)
	}

	public String executeCommandReturnStandardOutput(String command, String nodeType, String username, String password)
	{
		return ps.executeCommandReturnStandardOutput(command, nodeType, username, password)
	}

	public String executeCommandReturnStandardOutput(String directory, String command, String nodeType)
	{
		return ps.executeCommandReturnStandardOutput(directory, command, nodeType)
	}

	public String executeCommandReturnStandardOutput(String directory, String command, String nodeType, String username, String password)
	{
		return ps.executeCommandReturnStandardOutput(directory, command, nodeType, username, password)
	}

	public void publishHtmlReport(String reportDirectory, String reportName)
	{
		ps.publishHtmlReport(reportDirectory, reportName)
	}

	public void publishCoberturaReport(String reportFilePattern)
	{
		ps.publishCoberturaReport(reportFilePattern)
	}

	public Object getArtifactoryServerProperties()
	{
		ps.getArtifactoryServerProperties()	
	}
	
	public void executeArtifactoryDownload(String artifactoryServerId,String downloadSpecification)
	{
		ps.executeArtifactoryDownload(artifactoryServerId, downloadSpecification)
	}

	public Map executeArtifactoryUpload(String artifactoryServerId, String uploadSpecification, List<String> buildInfoEnvVariables)
	{
		return ps.executeArtifactoryUpload(artifactoryServerId, uploadSpecification, buildInfoEnvVariables)
	}

	public Object executeHttpRequest(acceptType, authentication, contentType, ignoreSslErrors, httpMethod, responseHandle, url)
	{
		return ps.executeHttpRequest(acceptType, authentication, contentType, ignoreSslErrors, httpMethod, responseHandle, url)
	}

	public Object getArtifactProperties(String artifactoryID, String artifactoryPath)
	{
		return ps.getArtifactProperties(artifactoryID, artifactoryPath)
	}

	public void executeNunit()
	{
		ps.executeNunit()
	}

	public Map executeBuild(String jobPath, String buildParameters)
	{
		return ps.executeBuild(jobPath, buildParameters)
	}

	public void executeSetEnvVariable(String name, String value)
	{
		ps.executeSetEnvVariable(name, value)
	}

	public String executeGetEnvVariable(String name)
	{
		return ps.executeGetEnvVariable(name)
	}

	public Map executeReadJsonText(String text)
	{
		ps.executeReadJsonText(text)
	}

	public void publishHtmlReport(String directory, String indexFile, String name)
	{
		ps.publishHtmlReport(directory, indexFile, name)
	}

	public void executeArtifactoryPromoteBuild(String buildName, String buildNumber, String sourceRepository, String targetRepository)
	{
		ps.executeArtifactoryPromoteBuild(buildName, buildNumber, sourceRepository, targetRepository)
	}

	public void executeWriteFile(String path, String contents)
	{
		ps.executeWriteFile(path, contents)
	}

	void executeWithDockerContainer(String image, List<String> scripts)
	{
		ps.executeWithDockerContainer(image, scripts)
	}

	void executeSh(List<String> scripts)
	{
		ps.executeSh(scripts)
	}
	
	void executeSh(String script)
	{
		ps.executeSh(script)
	}

	public String executeGetArtifactoryServerUrl()
	{
		return ps.executeGetArtifactoryServerUrl()
	}

	public Object executeGetContentForHttpRequestResponse(response)
	{
		return ps.executeGetContentForHttpRequestResponse(response)
	}

	boolean executeIsUnix()
	{
		ps.executeIsUnix()
	}
}
