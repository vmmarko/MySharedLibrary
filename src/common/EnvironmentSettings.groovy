package common


/**
 *  EnvironmentSettings is a Singleton class with all the Environment settings for running the Jenkins build. 
 *  All the environment settings are retrieved via Environment json in Shared Library resources
 */
@Singleton
class EnvironmentSettings implements Serializable 
{
	public HashMap settings
}
