package nl.apc.managers;

import nl.apc.utils.ConfigFileReader;

public class FileReaderManager {
	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;
	
	private FileReaderManager() {
	}
 
	 public static FileReaderManager getInstance( ) {
	      return fileReaderManager;
	 }
 
	 public ConfigFileReader getConfigReader(String propertyFilePath) {
		 return (configFileReader == null) ? new ConfigFileReader(propertyFilePath) : configFileReader;
	 }
}
