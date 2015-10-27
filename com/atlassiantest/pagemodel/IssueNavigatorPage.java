package com.atlassiantest.pagemodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class IssueNavigatorPage {
	private final WebDriver myDriver;
	private final WebDriverWait wait;

	By searchboxLocator = By.id("advanced-search");
	By searchButtonLocator = By.cssSelector("button.aui-item");
	By issueListLocator = By.className("issue-list");
	By editLocator = By.cssSelector("#opsbar-edit-issue_container > li:nth-child(1) > a:nth-child(1)");
	By profileLocator = By.id("header-details-user-fullname");
	By logoutLocator = By.id("log_out");
	By layoutSwitchButtonLocator = By.cssSelector("#layout-switcher-button");
	By detailViewLocator = By.cssSelector("a[data-layout-key=split-view]");
	
	public IssueNavigatorPage(WebDriver driver) {
		myDriver = driver;
		wait = new WebDriverWait(driver, 60);
	}
	
	public IssueNavigatorPage changeDetailView() {
		wait.until(
				ExpectedConditions
						.elementToBeClickable(layoutSwitchButtonLocator))
				.click();
		WebElement detailView = myDriver.findElement(detailViewLocator);
		((JavascriptExecutor) myDriver).executeScript(
				"arguments[0].style.border='3px solid red'", detailView);
		wait.until(ExpectedConditions.elementToBeClickable(detailViewLocator))
				.click();
		return this;
	}
	
	public IssueNavigatorPage searchIssue(String query) {
		WebElement searchbox = wait.until(ExpectedConditions
				.elementToBeClickable(searchboxLocator));
		searchbox.clear();
		searchbox.sendKeys(query);
		wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator))
				.click();

		try {
			Thread.sleep(5000); // wait for search result list to refresh
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public boolean issueListNonEmpty() {
		WebElement issueList = null;
		try {
			issueList = wait.until(ExpectedConditions
					.presenceOfElementLocated(issueListLocator));
		}
		catch (TimeoutException e) {
			return false;
		}

		List<WebElement> list = issueList.findElements(By.xpath(".//li"));
		System.out.println("Issue list size is " + list.size());
		return (list.size() != 0);
	}
	
	public EditIssuePage editIssue() {
		Assert.assertTrue(issueListNonEmpty());
		wait.until(ExpectedConditions.elementToBeClickable(editLocator))
				.click();
		return new EditIssuePage(myDriver);
	}

	public void Logout() {
		// http://stackoverflow.com/questions/11908249/debugging-element-is-not-clickable-at-point-error
		// fix for Chrome browser
		WebElement profile = myDriver.findElement(profileLocator);
		((JavascriptExecutor) myDriver).executeScript("arguments[0].click();",
				profile);
		WebElement logout = myDriver.findElement(logoutLocator);
		((JavascriptExecutor) myDriver).executeScript("arguments[0].click();",
				logout);
	}
}
