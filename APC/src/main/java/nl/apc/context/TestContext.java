package nl.apc.context;

import nl.apc.managers.DriverManager;
import nl.apc.managers.FileReaderManager;
import nl.apc.managers.ScreenManager;
import nl.apc.utils.IConstants;

public class TestContext {

	private ScreenManager screenManager;
	private DriverManager driverManager;

	public TestContext() {
		
		if (FileReaderManager.getInstance().getConfigReader(IConstants.EnvPath).getProperty("Environment")
				.equalsIgnoreCase("mobile")) {
			driverManager = new DriverManager();
			screenManager = new ScreenManager(driverManager.getAndroidDriver());
		}
	}

	public DriverManager driverManager() {
		return driverManager;
	}

	public ScreenManager pageManager() {
		return screenManager;
	}
}
