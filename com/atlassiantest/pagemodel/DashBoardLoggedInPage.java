package com.atlassiantest.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardLoggedInPage {
	private final WebDriver myDriver;
	private final WebDriverWait wait;
	private String pageTitleString = "System Dashboard - Atlassian JIRA";

	By createLocator = By.id("create_link");
	By issuesLocator = By.id("find_link");
	By searchLocator = By.id("issues_new_search_link_lnk");
	By profileLocator = By.id("header-details-user-fullname");
	By logoutLocator = By.id("log_out");

	public DashBoardLoggedInPage(WebDriver driver) {
		myDriver = driver;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains(pageTitleString));
	}

	public CreateIssuePage CreateIssue() {
		wait.until(ExpectedConditions.elementToBeClickable(createLocator))
				.click();
		return new CreateIssuePage(myDriver);
	}

	public IssueNavigatorPage searchIssue() {
		wait.until(ExpectedConditions.elementToBeClickable(issuesLocator))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(searchLocator))
				.click();
		return new IssueNavigatorPage(myDriver);
	}

	public void Logout() {
		// http://stackoverflow.com/questions/11908249/debugging-element-is-not-clickable-at-point-error
		WebElement profile = myDriver.findElement(profileLocator);
		((JavascriptExecutor) myDriver).executeScript(
				"arguments[0].click();", profile);
		
		wait.until(ExpectedConditions.elementToBeClickable(logoutLocator))
				.click();
	}
}
