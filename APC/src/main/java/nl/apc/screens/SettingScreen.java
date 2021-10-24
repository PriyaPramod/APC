package nl.apc.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingScreen extends BaseScreen {

	public SettingScreen(AndroidDriver<AndroidElement> androidDriver) {
		super(androidDriver);
	}

	@AndroidFindBy(accessibility = "Settings")
	private AndroidElement menuIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	private AndroidElement settingsButton;

	@AndroidFindBy(id = "com.monefy.app.lite:id/buttonExportToCsv")
	private AndroidElement exportToFileButton;

	@AndroidFindBy(id = "com.monefy.app.lite:id/export_to_csv_warning")
	private AndroidElement exportToFileMessage;

	@AndroidFindBy(id = "com.monefy.app.lite:id/ok_button")
	private AndroidElement okButton;

	@AndroidFindBy(id = "com.monefy.app.lite:id/currency_name")
	private AndroidElement currencyName;
	
	@AndroidFindBy(id= "com.monefy.app.lite:id/search_src_text")
	private AndroidElement currencySearchTextbox;
	
	@AndroidFindBy(xpath = "//*[@resource-id='com.monefy.app.lite:id/currency_list']/android.widget.LinearLayout")
	private AndroidElement currencyItem;

	public String getCurrencyName() {
		value = currencyName.getText();
		print("Current Selected Currency is: " + value);

		return value;
	}

	public void changeCurrency(String currencyNameText) {
		click(currencyName, "Clicked on the currency to change the currency. ");
		sendKeys(currencySearchTextbox, currencyNameText);
		click(currencyItem, "Selected the "+currencyNameText+ " from the Currency ");
	}
	
	public void clickOnMenuIcon() {
		click(menuIcon, "Clicked on the menu Icon dots on top right corner of the screen. ");
	}

	public void ClickOnSettings() {
		click(settingsButton, "Clicked on the Settings button in Menu Side bar. ");
	}

	public void exportToFile() {
		mobileActions.scrollToElementUsingVisibleText("Export to file");
		click(exportToFileButton, "Clicked on the export to File button under Setting menu Item. ");
	}

	public boolean exportFilePopUpDisplayed() {
		flag = elementDisplayed(exportToFileMessage);
		if (flag) {
			print("I successfully able to export the data to CSV file. ");
		}
		return flag;
	}

}
