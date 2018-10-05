package common

import Constants
//import wk.health.cibs.common.JsonOutputCibs
//import wk.health.cibs.common.Log
//import wk.health.cibs.common.Utilities

/**
 * GlobalSettings class includes all the Global properties for running the Jenkins Build
 */
public class GlobalSettings implements Serializable
{
	public String nodeType

	public String workspaceFolder

	public Object envVarsMap

	private HashMap buildVariables

	private HashMap scmMethodSettings

	public List componentsBuildParameters = new ArrayList()

	public String repositoryPath = ""
	public String repositoryName = ""
	public String repositoryFile = ""
	public String artifactoryBuildName = ""
	public String artifactoryBuildNumber = ""
	public String reusableWorkspaceType= ""
	public String reusableWorkspaceLocation= ""
	public String numberedWorkspaceLocation= ""

	private Map buildParameters = new HashMap()
	private Map releaseBuildParameters = new HashMap()

	public GlobalSettings(String nodeType, String workspaceFolder, Object envVarsMap)
	{
		this.nodeType = nodeType
		this.workspaceFolder = workspaceFolder
		this.envVarsMap = envVarsMap
		this.buildVariables = new HashMap()
	}

	public void setWorkspaceFolder(value)
	{
		this.workspaceFolder = value
	}

	public void setVersionNumber(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_VERSION_NUMBER, value)
	}

	public String getVersionNumber()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_VERSION_NUMBER)
	}

	public void setCompleteVersionNumber(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPLETE_VERSION_NUMBER, value)
	}

	public String getCompleteVersionNumber()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPLETE_VERSION_NUMBER)
	}

	public void setBuildId(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_BUILD_ID, value)
	}

	public String getBuildId()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_BUILD_ID)
	}

	public void setComponentName(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_NAME, value)
	}

	public String getComponentName()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_NAME)
	}

	public void setDateTimeLocal(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_SCM_DATE_TIME_LOCAL, value)
	}

	public String getDateTimeLocal()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_SCM_DATE_TIME_LOCAL)
	}

	public void setDateTimeGmt(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_SCM_DATE_TIME_GMT, value)
	}

	public String getDateTimeGmt()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_SCM_DATE_TIME_GMT)
	}

	public void setCommitHashForGit(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMMIT_HASH_FOR_GIT, value)
	}

	public String getCommitHashForGit()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMMIT_HASH_FOR_GIT)
	}

	public void setRevisionNumberForSvn(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_REVISION_NUMBER_FOR_SVN, value)
	}

	public String getRevisionNumberForSvn()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_REVISION_NUMBER_FOR_SVN)
	}

	public void setChangesetNumberForTfs(value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_CHANGESET_NUMBER_FOR_TFS, value)
	}

	public String getChangesetNumberForTfs()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_CHANGESET_NUMBER_FOR_TFS)
	}

	public void setToDeployComponentName(String value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_COMPONENT_NAME, value)
	}

	public String getComponentToDeployComponentName(String value)
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_COMPONENT_NAME)
	}

	public void setToDeployVersionNumber(String value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_VERSION_NUMBER, value)
	}

	public String getComponentToDeployVersionNumber(String value)
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_VERSION_NUMBER)
	}

	public void setToDeployRepositoryPath(String value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_PATH, value)
	}

	public String getComponentToDeployRepositoryPath(String value)
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_PATH)
	}

	public void setToDeployRepositoryName(String value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_NAME, value)
	}

	public String getComponentToDeployRepositoryName()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_NAME)
	}

	public void setToDeployRepositoryFile(String value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_FILE, value)
	}

	public String getComponentToDeployRepositoryFile()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_FILE)
	}

	public void setToDeployArtifactoryBuildName(String value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_ARTIFACTORY_BUILD_NAME, value)
	}

	public String getComponentToDeployArtifactoryBuildName()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_ARTIFACTORY_BUILD_NAME)
	}

	public void setToDeployArtifactoryBuildNumber(String value)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_ARTIFACTORY_BUILD_NUMBER, value)
	}

	public String getComponentToDeployArtifactoryBuildNumber()
	{
		return (String)this.buildVariables.get(Constants.BUILD_VARIABLES_COMPONENT_TO_DEPLOY_ARTIFACTORY_BUILD_NUMBER)
	}

	public void setScmMethodSettings(HashMap scmMethodSettings)
	{
		this.scmMethodSettings = scmMethodSettings
	}

	public HashMap getScmMethodSettings()
	{
		return this.scmMethodSettings
	}

	public void setScmBranch(String branchName)
	{
		this.buildVariables.put(Constants.BUILD_VARIABLES_SCM_CURRENT_GIT_BRANCH_NAME, branchName)
	}

	public void logBuildVariables()
	{
		Log.instance.debug("""Global Settings Build Variables:
${JsonOutputCibs.prettyPrint(JsonOutputCibs.toJson(this.buildVariables))}""")
	}

	public String getResultBuildParameters()
	{
		Map buildParameters = new HashMap()
		buildParameters.name = this.getComponentName()
		buildParameters.versionNumber = this.getCompleteVersionNumber()
		if (Utilities.isNotNullOrEmpty(this.repositoryPath))
		{
			buildParameters.repositoryPath = this.repositoryPath
		}
		if (Utilities.isNotNullOrEmpty(this.repositoryName))
		{
			buildParameters.repositoryName = this.repositoryName
		}
		if (Utilities.isNotNullOrEmpty(this.repositoryFile))
		{
			buildParameters.repositoryFile = this.repositoryFile
		}
		if (Utilities.isNotNullOrEmpty(this.artifactoryBuildName))
		{
			buildParameters.artifactoryBuildName = this.artifactoryBuildName
		}
		if (Utilities.isNotNullOrEmpty(this.artifactoryBuildNumber))
		{
			buildParameters.artifactoryBuildNumber = this.artifactoryBuildNumber
		}
		return groovy.json.JsonOutput.toJson(buildParameters)
	}

	public void setBuildParameters(Map buildParameters)
	{
		this.buildParameters = buildParameters
	}

	public Map getBuildParameters()
	{
		return this.buildParameters
	}

	public void setReleaseBuildParameters(Map buildParameters)
	{
		this.releaseBuildParameters = buildParameters
	}

	public Map getReleaseBuildParameters()
	{
		return this.releaseBuildParameters
	}

	public void setReusableWorkspaceType(String value)
	{
		this.reusableWorkspaceType=value
	}

	public void setReusableWorkspaceLocation(String value)
	{
		this.reusableWorkspaceLocation=value
	}

	public void setNumberedWorkspaceLocation(String value)
	{
		this.numberedWorkspaceLocation=value
	}
}
