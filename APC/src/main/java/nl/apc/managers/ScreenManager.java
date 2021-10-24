package nl.apc.managers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import nl.apc.screens.DashboardScreen;
import nl.apc.screens.SettingScreen;

public class ScreenManager {
	
	AndroidDriver<AndroidElement> androidDriver;
	
	DashboardScreen dashboardScreen;
	SettingScreen settingScreen;

	public ScreenManager(AndroidDriver<AndroidElement> androidDriver) {
		this.androidDriver = androidDriver;
	}

	public DashboardScreen getDashboardScreen() {
		return (dashboardScreen == null) ? dashboardScreen = new DashboardScreen(this.androidDriver) : dashboardScreen;
	}
	
	public SettingScreen getSettingScreen() {
		return (settingScreen == null) ? settingScreen = new SettingScreen(this.androidDriver) : settingScreen;
	}

}
