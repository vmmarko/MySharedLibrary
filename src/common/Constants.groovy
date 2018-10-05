package common

public class Constants
{
	static final String TEXT_HASH = "#"
	static final String TEXT_COLON = ":"
	static final String TEXT_SPACE = " "
	static final String TEXT_NEW_LINE = "\n"
	static final String TEXT_PERIOD = "."
	static final String TEXT_SINGLE_QUOTE = "'"
	static final String TEXT_DOUBLE_QUOTE = "\""
	static final String TEXT_ANT_ANY_FOLDER_REGEX = "**/*"

	static final String LOG_LEVEL_ERROR = "ERROR"
	static final String LOG_LEVEL_INFO = "INFO"
	static final String LOG_LEVEL_DEBUG = "DEBUG"

	static final String SCM_TYPE_TFS = "TFS"
	static final String SCM_TYPE_GIT = "GIT"
	static final String SCM_TYPE_SVN = "SVN"
	static final String SCM_TYPE_SSCM = "SSCM"

	static final String TFS_USERNAME = 'TFS_UN'
	static final String TFS_PASSWORD = 'TFS_PW'

	static final String BUILD_SETTINGS_SOURCE_RESOURCE = "RESOURCE"
	static final String BUILD_SETTINGS_SOURCE_SCM = "SCM"
	static final String BUILD_SETTINGS_SOURCE_PARAMETER = "PARAMETER"
	static final String BUILD_SETTINGS_SOURCE_WORKSPACE = "WORKSPACE"
	static final String BUILD_SETTINGS_SOURCE_REUSABLE_WORKSPACE_FOLDER = "ws"

	static final String BUILD_TYPE_RELEASE = "RELEASE"
	static final String BUILD_TYPE_COMPONENT = "COMPONENT"

	static final String PATHS_PACKAGE_FOLDER = "packaging"

	static final String BUILD_STATUS_FAILURE = "FAILURE"
	static final String BUILD_STATUS_SUCCESS = "SUCCESS"
	static final String BUILD_STATUS_UNSTABLE = "UNSTABLE"

	static final String ENV_BUILD_NUMBER = "BUILD_NUMBER"
	static final String ENV_JOB_BASE_NAME = "JOB_BASE_NAME"
	static final String ENV_WORKSPACE = "WORKSPACE"
	static final String WORKSPACE_BASE_DEFAULT = "workspace"
	static final String WORKSPACE_BASE_CUSTOM = "ws"
	static final String ENV_BUILD_URL = "BUILD_URL"
	static final String ENV_JOB_NAME = "JOB_NAME"
	static final String ENV_RELEASE_BUILD_PARAMETERS = "RELEASE_BUILD_PARAMETERS"

	static final String NODE_LABEL_LINUX = "linux"
	static final String NODE_LABEL_WINDOWS = "windows"

	static final String PATH_SEPARATOR_WINDOWS = "\\"
	static final String PATH_SEPARATOR_LINUX = "/"

	static final String METHOD_SCM = "scm"
	static final String METHOD_MSBUILD = "msbuild"
	static final String METHOD_NUGET = "nuget"
	static final String METHOD_POWERSHELL = "powershell"
	static final String METHOD_SHARED_ASSEMBLY_FILE_GENERATE = "sharedAssemblyFileGenerate"
	static final String METHOD_MAVEN = "mvn"
	static final String METHOD_MSTEST = "mstest"
	static final String METHOD_CREATE_TAG_ON_EXISTING_REPOSITORY = "createTagOnExistingRepository"
	static final String METHOD_EXECUTE_BUILDS = "executeBuilds"
	static final String METHOD_DEPLOY_TO_ENVIRONMENT = "deployToEnvironment"
	static final String METHOD_SEND_BUILD_RESULT_EMAIL = "sendBuildResultEmail"
	static final String METHOD_DELETE_FOLDERS = "deleteFolders"
	static final String METHOD_ANT = "ant"
	static final String METHOD_ANALYZER = "analyzer"
	static final String METHOD_BEGIN_SONAR_SCANNER= "beginSonarScanner"
	static final String METHOD_END_SONAR_SCANNER = "endSonarScanner"
	static final String METHOD_SONAR_SCANNER = "sonarScanner"
	static final String METHOD_COVERAGE = "displayCoverage"
	static final String METHOD_PUBLISH_JACOCO_COVERAGE = "publishCoverageJacoco"
	static final String METHOD_BATCH = "executeBatchCommand"
	static final String METHOD_BASH = "executeBashCommand"
	static final String METHOD_ARTIFACTORY_DOWNLOAD = "artifactoryDownload"
	static final String METHOD_ARTIFACTORY_UPLOAD = "artifactoryUpload"
	static final String METHOD_ARTIFACTORY_CHEF_UPLOAD = "artifactoryChefUpload"
	static final String METHOD_ARTIFACTORY_PROMOTE_BUILD = "artifactoryPromoteBuild"
	static final String METHOD_CHEF = "chefDeploy"
	static final String METHOD_CPP = "cppMakeLinux"
	static final String METHOD_NUNIT = "nunit"
	static final String METHOD_PHPUNIT = "phpUnit"
	static final String METHOD_COMPOSER = "composer"
	static final String METHOD_ARCHIVE = "archive"
	static final String METHOD_NPM = "npm"
	static final String METHOD_CAKE = 'cake'
	static final String METHOD_MAKE = 'make'
	static final String METHOD_DOCKER = 'docker'
	static final String METHOD_COVERITY = 'coverity'

	static final String METHOD_CAKE_PRETTY = 'CAKE'
	static final String METHOD_POWERSHELL_PRETTY = 'PowerShell'
	static final String METHOD_MAKE_PRETTY = 'MAKE'
	static final String METHOD_BASH_PRETTY = 'BASH'
	static final String METHOD_DOCKER_PRETTY = 'Docker'
	static final String METHOD_COVERITY_PRETTY = 'Coverity'

	static final String STAGE_SCM = "SCM"
	static final String STAGE_VERSIONING = "updateAssemblyVersions"
	static final String STAGE_VERSIONING_ASSEMBLY_FILE = "Versioning: Assembly File Generation"
	static final String STAGE_BUILD = "Build"
	static final String STAGE_UNIT_TESTING = "Unit Testing"
	static final String STAGE_TAG = "Tag"
	static final String STAGE_PACKAGING = "Packaging"
	static final String STAGE_ARTIFACT_STORAGE = "Artifact Storage"
	static final String STAGE_DEPLOY = "Deploy"
	static final String STAGE_WORKSPACE_CLEANUP = "Workspace Cleanup"
	static final String STAGE_EMAIL_NOTIFICATION = "Email Notification"
	static final String STAGE_COMPONENT_BUILD = "Component Build"
	static final String STAGE_STATIC_CODE_ANALYSIS = "Static Code Analysis"

	static final String STAGE_RELEASE_PROMOTE_COMPONENT_BUILDS = "Promote Component Builds"
	static final String STAGE_RELEASE_EXECUTE_COMPONENT_BUILDS = "Execute Component Builds"
	static final String STAGE_RELEASE_EXECUTE_SMOKE_TESTS = "Execute Smoke Tests"
	static final String STAGE_RELEASE_DEPLOY_COMPONENT_BUILDS = "Deploy Component Builds"

	static final String ENVIRONMENT_SETTINGS_LOCATION = "wk/health/cibs/component/environment/Environment."

	static final String EXTENSION_JSON = ".json"

	static final String ENVIRONMENT_DEV = "DEV"
	static final String ENVIRONMENT_QA = "QA"
	static final String ENVIRONMENT_PROD = "PROD"

	static final String TEST_TIME_MARGIN = "3000"

	static final String BUILD_VARIABLES_VERSION_NUMBER = "VersionNumber"
	static final String BUILD_VARIABLES_BUILD_ID= "BuildId"
	static final String BUILD_VARIABLES_COMPLETE_VERSION_NUMBER= "CompleteVersionNumber"
	static final String BUILD_VARIABLES_COMPONENT_NAME = "ComponentName"
	static final String BUILD_VARIABLES_REVISION_NUMBER_FOR_SVN = "RevisionNumberForSvn"
	static final String BUILD_VARIABLES_COMMIT_HASH_FOR_GIT = "CommitHashForGit"
	static final String BUILD_VARIABLES_CHANGESET_NUMBER_FOR_TFS = "ChangesetNumberForTfs"
	static final String BUILD_VARIABLES_SCM_DATE_TIME_LOCAL = "DateTimeLocal"
	static final String BUILD_VARIABLES_SCM_DATE_TIME_GMT = "DateTimeGmt"
	static final String BUILD_VARIABLES_SCM_CURRENT_GIT_BRANCH_NAME = "CurrentGitBranchName"
	static final String BUILD_VARIABLES_COMPONENT_TO_DEPLOY_COMPONENT_NAME = "ComponentToDeployComponentName"
	static final String BUILD_VARIABLES_COMPONENT_TO_DEPLOY_VERSION_NUMBER = "ComponentToDeployVersionNumber"
	static final String BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_PATH = "ComponentToDeployRepositoryPath"
	static final String BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_NAME = "ComponentToDeployRepositoryName"
	static final String BUILD_VARIABLES_COMPONENT_TO_DEPLOY_REPOSITORY_FILE = "ComponentToDeployRepositoryFile"
	static final String BUILD_VARIABLES_COMPONENT_TO_DEPLOY_ARTIFACTORY_BUILD_NAME = "ComponentToDeployArtifactoryBuildName"
	static final String BUILD_VARIABLES_COMPONENT_TO_DEPLOY_ARTIFACTORY_BUILD_NUMBER = "ComponentToDeployArtifactoryBuildNumber"

	static final String COVERAGE_TOOL_JACOCO = "jacoco"
	static final String COVERAGE_TOOL_COBERTURA = "cobertura"

	static final String TOOL_NAME_JAVA = "java"
	static final String TOOL_NAME_ANT = "ant"
	static final String TOOL_NAME_MAKE = "make"
	static final String TOOL_NAME_CMAKE = "cmake"

	static final int SVN_VERSION_1_6 = 10
	static final int SVN_VERSION_1_7 = 12

	static final String SVN_FOLDER_WORKING_COPY = ".svn"
	static final String SVN_FILE_FORMAT = "format"
	static final String SVN_FILE_ENTRIES = "entries"

	static final String COMMAND_CAT = "cat"
	static final String COMMAND_TYPE = "type"
	static final String COMMAND_KNIFE = "knife"

	static final String COMMAND_CONCATENATION_TOKEN = "&&"

	static final String COBERTURA_REPORT_NAME = "cobertura.xml"

	static final String REPORT_FILE_UNIT_TESTS_NUNIT = "TestResult.xml"

	static final String BUILD_PARAMETERS = "buildParameters"
	static final String BUILD_PARAMETERS_RELEASE = "releaseBuildParameters"

	static final String CREDENTIALS_TYPE_AZURE_SERVICE_PRINCIPAL = "azureServicePrincipal"

	static final String CREDENTIALS_TYPE_AWS_CREDENTIALS_BINDING = "AmazonWebServicesCredentialsBinding"
	static final String CREDENTIALS_TYPE_AWS_USER_MAPPING = "AWS_ACCESS_KEY_ID"
	static final String CREDENTIALS_TYPE_AWS_PASSWORD_MAPPING = "AWS_SECRET_ACCESS_KEY"

	static final String CREDENTIALS_TYPE_USERNAME_PASSWORD_SEPARATE_CREDENTIALS_BINDING = "usernamePassword"

	static final String CREDENTIALS_TYPE_SECRET_FILE = 'secretFile'
	static final String CREDENTIALS_TYPE_SECRET_TEXT = 'secretText'

	static final String REGEX_USER_PROVIDED_BUILD_PARAMETER_FOR_BUILD_SETTINGS_JSON = '\\@[a-zA-Z]+?\\@'

	static final String REPOSITORY_DEV = 'DEV'
	static final String REPOSITORY_QA = 'QA'
	static final String REPOSITORY_LIVE = 'LIVE'

	static final String ENVIRONMENT_VARIABLE_CIBS_USERNAME = 'CIBS_USERNAME'
	static final String ENVIRONMENT_VARIABLE_CIBS_PASSWORD = 'CIBS_PASSWORD'

	
	static final String REUSABLE_WS_FULL_COPY = 'COPY_FULL'
	static final String REUSABLE_WS_COPY_GENERATED = 'COPY_GENERATED'
	
	static final String GIT_FOLDER_NAME = ".git"
	static final String GIT_SHALLOW_LOCK_FILE_NAME = "shallow.lock"
	
	static final String ENVIRONMENT_VARIABLE_CIBS_SECRET_FILE = 'CIBS_SECRET_FILE'
	static final String ENVIRONMENT_VARIABLE_CIBS_SECRET_TEXT = 'CIBS_SECRET_TEXT'

	static final String SUBMODULES_CHECKOUT_ALL = 'all'
	static final String SUBMODULES_CHECKOUT_LIST = 'list'

	static final String COVERITY_INTERMEDIATE_DIRECTORY_NAME = 'idir'

	static final String ENVIRONMENT_VARIABLE_TOKEN_BASH = '$'
	static final String ENVIRONMENT_VARIABLE_TOKEN_BATCH = '%'
	static final String ENVIRONMENT_VARIABLE_TOKEN_POWERSHELL = '$env:'
}
