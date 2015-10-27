package com.atlassiantest.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditIssuePage {
	private final WebDriver myDriver;
	private final WebDriverWait wait;
	private String pageTitleString = "Edit Issue";

	By summaryLocator = By.id("summary");
	By updateLocator = By.id("edit-issue-submit");

	public EditIssuePage(WebDriver driver) {
		myDriver = driver;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains(pageTitleString));
	}

	public IssueNavigatorPage editIssue(String summary) {
		wait.until(ExpectedConditions.elementToBeClickable(summaryLocator))
				.sendKeys(summary);
		wait.until(ExpectedConditions.elementToBeClickable(updateLocator))
				.click();
		return new IssueNavigatorPage(myDriver);
	}
}
