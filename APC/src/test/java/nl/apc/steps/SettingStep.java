package nl.apc.steps;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.apc.context.TestContext;
import nl.apc.screens.SettingScreen;

public class SettingStep {

	private SettingScreen settingScreen;
	private String currentCurrency;

	public SettingStep(TestContext testContext) {
		settingScreen = testContext.pageManager().getSettingScreen();
	}

	@Then("Export Data to file")
	public void export_data_to_file() {
		settingScreen.clickOnMenuIcon();
		settingScreen.ClickOnSettings();
		settingScreen.exportToFile();
	}

	@Then("I should be able to export data to file")
	public void i_should_be_able_to_export_data_to_file() {
		Assert.assertTrue(settingScreen.exportFilePopUpDisplayed(), "Failure: I failed to export the data to File. ");
	}

	@When("Go to change Currency")
	public void go_to_change_currency() {
		settingScreen.clickOnMenuIcon();
		settingScreen.ClickOnSettings();
		currentCurrency = settingScreen.getCurrencyName();
	}

	@When("Change the Currency to {string}")
	public void change_the_currency_to(String currencyName) {
		settingScreen.changeCurrency(currencyName);
	}

	@Then("Currency Should Change to {string}")
	public void currency_should_change_to(String string) {
		Assert.assertNotEquals(settingScreen.getCurrencyName(), currentCurrency,
				"Failure: I failed to change the currency. ");
	}
}
