package com.atlassiantest.pagemodel;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssuePage {
	private final WebDriver myDriver;
	private final WebDriverWait wait;
	private String pageTitleString = "Create Issue - Atlassian JIRA";

	By projectLocator = By.id("project-field");
	By issueTypeLocator = By.id("issuetype-field");
	By summaryLocator = By.id("summary");
	By priorityLocator = By.id("priority-field");
	By componentsLocator = By.id("components-textarea");
	By versionsLocator = By.id("versions-textarea");
	By environmentLocator = By.id("environment");
	By descriptionLocator = By.id("description");
	By browseLocator = By.className("issue-drop-zone__file");
	By progressbarLocator = By.cssSelector("span.upload-progress-bar__bar");
	By estimateLocator = By.id("timetracking");
	By labelsLocator = By.id("labels-textarea");
	By storyPointsLocator = By.id("customfield_10653");
	By epicLinkLocator = By.id("customfield_12931-field");
	By epicLinkSuggestionLocator = By
			.cssSelector("#customfield_12931-suggestions li:nth-child(1)");
	By regularExpressionLocator = By.id("customfield_14130");
	By createLocator = By.id("create-issue-submit");

	public CreateIssuePage(WebDriver driver) {
		myDriver = driver;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains(pageTitleString));
	}

	public DashBoardLoggedInPage CreateIssueWithDetails(String summary) {
		WebElement project = wait.until(ExpectedConditions
				.elementToBeClickable(projectLocator));
		project.clear();
		project.sendKeys("Demo");
		project.sendKeys(Keys.RETURN);

		WebElement issueType = wait.until(ExpectedConditions
				.elementToBeClickable(issueTypeLocator));
		issueType.clear();
		issueType.sendKeys("Improvement");
		issueType.sendKeys(Keys.RETURN);

		wait.until(ExpectedConditions.elementToBeClickable(summaryLocator))
				.sendKeys(summary);

		// new Select(myDriver.findElement(By.id("security")))
		// .selectByVisibleText("Logged-in users");

		WebElement priority = wait.until(ExpectedConditions
				.elementToBeClickable(priorityLocator));
		priority.clear();
		priority.sendKeys("Blocker");
		priority.sendKeys(Keys.ENTER);

		wait.until(ExpectedConditions.elementToBeClickable(componentsLocator))
				.sendKeys("Wings");
		wait.until(ExpectedConditions.elementToBeClickable(versionsLocator))
				.sendKeys("Production");
		wait.until(ExpectedConditions.elementToBeClickable(environmentLocator))
				.sendKeys("environment");
		wait.until(ExpectedConditions.elementToBeClickable(descriptionLocator))
				.sendKeys("description");

		File f = new File("C:\\Users\\yun\\Desktop\\Untitled.jpg");
		if (f.exists() && !f.isDirectory()) {
			WebElement browse = wait.until(ExpectedConditions
					.presenceOfElementLocated(browseLocator));
			((JavascriptExecutor) myDriver)
					.executeScript(
							"arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';",
							browse);
			browse.sendKeys(f.getAbsolutePath());
		}
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				String style = input.findElement(progressbarLocator)
						.getAttribute("style");
				return style.contains("100%");
			}
		};
		wait.until(condition);

		wait.until(ExpectedConditions.elementToBeClickable(estimateLocator))
				.sendKeys("4d");

		wait.until(ExpectedConditions.elementToBeClickable(labelsLocator))
				.sendKeys("Label1");
		wait.until(ExpectedConditions.elementToBeClickable(storyPointsLocator))
				.sendKeys("10");

		wait.until(ExpectedConditions.elementToBeClickable(epicLinkLocator))
				.sendKeys("Recently viewed");
		wait.until(
				ExpectedConditions
						.elementToBeClickable(epicLinkSuggestionLocator))
				.click();

		wait.until(
				ExpectedConditions
						.elementToBeClickable(regularExpressionLocator))
				.sendKeys("regular");

		wait.until(ExpectedConditions.elementToBeClickable(createLocator))
				.click();
		return new DashBoardLoggedInPage(myDriver);
	}
}
