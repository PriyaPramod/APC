package nl.apc.context;

import nl.apc.managers.DriverManager;
import nl.apc.managers.ScreenManager;

public class TestContext {

	private ScreenManager screenManager;
	private DriverManager driverManager;

	public TestContext() {
		driverManager = new DriverManager();
		screenManager = new ScreenManager(driverManager.getAndroidDriver());
	}

	public DriverManager driverManager() {
		return driverManager;
	}

	public ScreenManager pageManager() {
		return screenManager;
	}
}
