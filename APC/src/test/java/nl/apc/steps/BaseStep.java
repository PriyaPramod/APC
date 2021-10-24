package nl.apc.steps;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import nl.apc.context.TestContext;
import nl.apc.managers.FileReaderManager;
import nl.apc.utils.Helper;
import nl.apc.utils.IConstants;
import nl.apc.utils.Log;

public class BaseStep {

	TestContext testContext;
	Scenario scenario;

	public BaseStep(TestContext testContext) {
		this.testContext = testContext;
	}

	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		Log.startTestCase(scenario.getName());
		ExtentCucumberAdapter.addTestStepLog("***********************************************");
		ExtentCucumberAdapter.addTestStepLog("Test execution started for Scenario --> " + scenario.getName());
		ExtentCucumberAdapter.addTestStepLog("***********************************************");
	}

	@After
	public void tearDown(Scenario scenario) {
		ExtentCucumberAdapter.addTestStepLog("***********************************************");
		ExtentCucumberAdapter.addTestStepLog("Test execution completed for Scenario --> " + scenario.getName());
		ExtentCucumberAdapter.addTestStepLog("***********************************************");

		if (FileReaderManager.getInstance().getConfigReader(IConstants.EnvPath).getProperty("Environment")
				.equalsIgnoreCase("mobile")) {
			if (scenario.isFailed()) {
				scenario.attach(Helper.getByteScreenshot(testContext.driverManager().getAndroidDriver()), "image/png",
						scenario.getName());
			}
			testContext.driverManager().closeAndroidDriver();
		}
		Log.endTestCase(scenario.getName());
	}

	@AfterStep
	public void addingScreenshotAfterEveryStep() {
		if (FileReaderManager.getInstance().getConfigReader(IConstants.EnvPath).getProperty("Environment")
				.equalsIgnoreCase("mobile")) {
			scenario.attach(Helper.getByteScreenshot(testContext.driverManager().getAndroidDriver()), "image/png",
					"Screenshot" + Helper.getRandomInt(1, 10000));
		}
	}

}
