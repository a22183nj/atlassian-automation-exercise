package com.atlassiantest.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atlassiantest.pagemodel.*;

public class NewJiraTest {
	public WebDriver driver;

	private String username = "peter.skiva@gmail.com";
	private String password = "peter.skiva@gmail.com";
	private String summary = "issue";
	private String editSummary = "editIssue";
	private String query = "reporter = currentUser() AND summary ~ " + summary + " AND status = Open";

	public void UserLogin() {
		(new DashBoardPage(driver)).Login().LoginSuccessful(username,
				password);
	}

	@Test(priority = 1)
	public void TestCreateIssue() {
		(new DashBoardLoggedInPage(driver)).CreateIssue()
				.CreateIssueWithDetails(summary).Logout();
	}

	@Test(priority = 2)
	public void TestSearchIssue() {
		IssueNavigatorPage page = new DashBoardLoggedInPage(driver)
				.searchIssue().changeDetailView().searchIssue(query);
		Assert.assertTrue(page.issueListNonEmpty());
		page.Logout();
	}

	@Test(priority = 3)
	public void TestEditIssue() {
		IssueNavigatorPage page = new DashBoardLoggedInPage(driver)
				.searchIssue().changeDetailView().searchIssue(query)
				.editIssue().editIssue(editSummary);
		page.Logout();
	}

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\yun\\Desktop\\skiva\\seleniumWebDriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\yun\\Desktop\\skiva\\seleniumWebDriver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.get("https://jira.atlassian.com/");
		driver.manage().window().maximize();
		UserLogin();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		driver.quit();
	}

}
