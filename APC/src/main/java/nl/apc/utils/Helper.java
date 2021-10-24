package nl.apc.utils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.service.ExtentService;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import nl.apc.managers.FileReaderManager;


public class Helper {

	private final static Random RANDOM = new SecureRandom();
	private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
	private static int value;
	
	public static int getRandomInt() {
		return RandomUtils.nextInt(100, 999);
	}
	
	public static boolean hasDigits(String value) {
		boolean flag = false;
		if (value.matches(".*\\d.*")) {
			flag = true;
		}
		return flag;
	}
	
	public static int getRandomInt(int startRange, int endRange) {
		return RandomUtils.nextInt(startRange, endRange);
	}

	/*public static byte[] getByteScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = new byte[0];
		try {
			fileContent = FileUtils.readFileToByteArray(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}
	*/

	public static String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

	public static void addSystemInfo() {
		ExtentService.getInstance().setSystemInfo("ApplicationName",
				FileReaderManager.getInstance().getConfigReader(IConstants.CONFIG).getProperty("ProjectName"));
		ExtentService.getInstance().setSystemInfo("Author", System.getProperty("user.name"));
		ExtentService.getInstance().setSystemInfo("OperatingSystem", System.getProperty("os.name"));
		
	}

	public static void print(String strToPrint) {
		ExtentCucumberAdapter
				.addTestStepLog("<span style=\"color: #ff0000;\"><string>Info:</strong></span>" + strToPrint);
	}

	public static String getStrongPassword() {
		return Helper.generateRandomString(3) + Helper.getRandomInt() + "*" + Helper.generateRandomString(5);
	}
	
	public static String getValidRandomEMail() {
		return Helper.generateRandomString(6)+"@"+Helper.generateRandomString(4)+".com";
	}
	
	public static String getTodaysDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("E, d MMM");
		return sf.format(date.getTime());
	}

	public static String getPreviousDate() {
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("E, d MMM");
		return sf.format(findPrevDay(today));
	}

	private static Date findPrevDay(Date date) {
		return new Date(date.getTime() - MILLIS_IN_A_DAY);
	}

	public static int parseStringToInt(String valueToParse) {

		if (hasDigits(valueToParse)) {
			value = Integer.parseInt(valueToParse);
		} else {
			throw new RuntimeException("Unable to parse " + valueToParse);
		}
		return value;
	}

	public static int returnDigits(String strValue) {
		String value = strValue.replaceAll("[^0-9.]", "");
		return (int) Double.parseDouble(value);
	}

	public static byte[] getByteScreenshot(AndroidDriver<AndroidElement> androidDriver) {
		File src = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = new byte[0];
		try {
			fileContent = FileUtils.readFileToByteArray(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss");
		return sf.format(date.getTime());
	}

	public static String getAbsolutePathOfFile(String currentRelativePath) {
		String absolutePath = "";
		try {
			String apkPath = System.getProperty("user.dir") + currentRelativePath;
			absolutePath = new File(apkPath).getAbsolutePath();
		} catch (Exception exp) {
			System.out.println("Exception: While trying to get the Absolute path of a file: " + currentRelativePath
					+ " Exception is: " + exp.toString());
		}
		return absolutePath;
	}

}
