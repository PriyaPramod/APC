## Cucumber Based Appium Automation Framework

In this File I'm going to explain <b>How to configure </b> and <b>Run this Framework</b>

The tech stack used for developing this framework are:
1. **JAVA** as the programming language for writing test code
2. **Appium** as a Automation tool for Automating Monefy App
3. **Rest Assured** as tool for automating Rest APIs
4. **Cucumber** as the BDD tool for developing the framework
5. **TestNg** as the unit test framework
6. **Maven** as the build tool
7. **Eclipse** as the preferred IDE for writing java code
8. **Extent Report** as the reporting tool
9. **Log4J** for generating logs

#### Getting Started
1. Install JDK 1.8 & set "JAVA_HOME" in environment variable
2. Install Eclipse
3. Install Node
4. Install Appium using NPM 
5. Set "ANDROID_HOME" & "Node_Path" as a environment variable
6. Download and Set "Maven_Home" as a environment variable
7. Real Android Device: Make sure developer options and USB debugging is enabled in that device and connect via USB cable
8. Install Cucumber Eclipse plugin

#### Cloning & Importing the Project
1. Clone the project from ```git clone https://github.com/PriyaPramod/APC.git```
2. Import the project (APC) in Eclipse ```File -> import -> General -> Existing Project into workspace -> Next -> Browse Project Location ```
3. Now click on ```Finsih``` wait until the Eclipse downloads all the dependencies
4. Right click on the project maven -> update project -> please wait till the project building completes

#### Running tests
``Note:`` Sometime JRE System Library might be referring the wrong Library, please do select the JDK path in Build Path ```
1. You can run the tests directly from the Eclipse, by right-clicking POM.xml and **maven test**.
2. After the execution Execution logs and HTML report will be generated with the time stamp under "reports" folder, right click on the APCExecutionResult.html to see the detailed results.
3. Framework will executes on Both Mac & Win [Make sure the above mentioned tools are installed and configured].
4. For Mac:
		-> Open the terminal
		-> Navigate to Project [Miro] Folder{root} directory 
		-> Execute the below command 
		```mvn clean install```
5. For Windows: 
		-> Open the command prompt
		-> Navigate to Project [Miro] Folder{root} directory 
		-> Execute the below command
		```mvn clean install```

#### Reason to choose BDD framework approach 
1. BDD is meant to be collaborative. Behaviour Driven Development (BDD) framework helps to attain all the prospects of a technical or business team.
2. Universal language which is very easy to describe
3. Business Value
4. Partnership between Business Analysts, Stake-holders, QA Team and developers
5. Separation of Test Cases from Test Code
6. Inherent Re-usability
7. Aspect-Oriented Controls
8. Easier Reviews
9. Clarity, Steam-lining, Shift left
10. Maintainable 

## Framework Execution flow

### APC

#### Step 1: Run -mvn clean install command 
#### Step 2: POM.xml starts downloading the dependencies and calls TestNG.xml
#### Step 3: TestNG.xml calls a class "ScenarioRunner.java" [which is under src/test/java/runner package
#### Step 4: In ScenarioRunner.java, CucumberOptions refers the feature files [which is under /Features/] folder, tags adds the scenarios to run, glue code refers the step definition package [src/test/java/nl.apc.steps], plugin uses extent report for report generation. 
#### Step 5: Once after referring all the required dependencies, Framework will start running the scenarios one by one, below is the one sample example of how a single scenario runs[Which is same for all the scenarios]
				#### [Step a]:: Executes before class - to add the system info to extent report
				#### [Step b]:: Control goes to "TestContext.java" class to initialize the android driver object - Android device, UDID, Name will be identified by Framework.
				#### [Step c]:: Control goes to "BaseTest.java" class and run the before hook function [Which executes before every scenario].
				#### [Step d]:: Scenario statement will be called to execute and corresponding step implementation will be called from the "nl.apc.steps".
				#### [Step e]:: In step definition, with the help of Pico Container [Dependency Injection] library framework gets the objects of the page object classes.
				#### [Step f]:: Corresponding methods from POM Classes will perform the actions, Assertion in Step Definition will validate the actions and after each step execution a screenshot will be taken and attached to the report.
				#### [Step g]:: Once all the scenario statements execution completes, after hook function from BaseTest.java will executes.
				#### [Step h]:: In Base test, Framework will check if the scenario is failed or not, if the scenario is failed, a screenshot and the exception message will be added to the extent report. 
				#### [Step i]:: New test will be called and above flow will continue till the last scenario executes.
				#### [Step k]:: A HTML report and a log will be created under the "report" folder
#### Step 6: Execution Completes
#### Step 7: We can examine the Framework execution by referring to HTML report and Log.

### Framework Folder Structure

### APC - Root Directory
	
	### src/main/java - For keeping all the reusable codes [Project Specific & Generic's]
		###nl.apc.context - For dependency injection using Pico Containers
		###nl.apc.managers - For Managing the driver, page, property file reader
		###nl.apc.screens - Page classes for web elements and actions
		###nl.apc.utils - All the utilities functions are developed under this package in different classes.
	
	### src/test/java - Execution package
		###nl.apc.runner - To specify what to scenarios to run
		###nl.apc.steps - Step implementations for all the steps in feature files
	
	### src/test/resources - test data and configuration folder
	    ###app - monefy APK is kept in this folder
		###dataset - To keep the test data
		###desiredcapabilities - Real device capabilities 
		###extent.properties - Extent report related configuration settings
		###log4j.properties - Log4j configuration information
		
	###Features - All the tests are written in Gherkin language in .feature files
	
	###reports - Execution status in HTML and Logs
		###timestamp folders - APCExecutionResult.html
		###log - APC.log
		
	###POM.xml - Project Object Modal file for all the dependency and executions.
	###TestNG.xml - To identify and run the ScenarioRunner.Java class
	###ReadMe.md - Details about Cucumber Based Appium Automation Framework


























