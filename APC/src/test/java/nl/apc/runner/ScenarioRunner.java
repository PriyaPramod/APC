package nl.apc.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import nl.apc.utils.AppiumServer;
import nl.apc.utils.Helper;

/**
 * @author PRAMOD KS
 */
@CucumberOptions(features = { "Features/" }, tags = "@APC", glue = "nl.apc.steps", plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "pretty" }, monochrome = true, publish = true)
public class ScenarioRunner extends AbstractTestNGCucumberTests{

	AppiumServer appiumServer;
	
	@BeforeClass
	@Parameters({ "Environment" })
	public void beforeClass(@Optional("api") String Environment) {
		Helper.setProperty(Environment);
		Helper.addSystemInfo();
		if (Environment.equalsIgnoreCase("Mobile")) {
			appiumServer = new AppiumServer();
			appiumServer.startAppiumServer();
			System.out.println("Started Appium server. ");
		}
	}

	@AfterClass
	@Parameters({ "Environment" })
	public void afterClass(@Optional("api") String Environment) {
		if (Environment.equalsIgnoreCase("Mobile")) {
			appiumServer.stopAppiumServer();
			System.out.println("Stopped Appium server. ");
		}
	}
	
}
