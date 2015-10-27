package com.atlassiantest.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardPage {
	private final WebDriver myDriver;
	private final WebDriverWait wait;
	private String pageTitleString = "System Dashboard - Atlassian JIRA";
	
	By loginLocator = By.linkText("Log In");
	
    public DashBoardPage(WebDriver driver) {
    	myDriver = driver;
    	wait = new WebDriverWait(driver, 60);
    	wait.until(ExpectedConditions
				.titleContains(pageTitleString));
    }
    
    public LoginPage Login() {
    	wait.until(ExpectedConditions.elementToBeClickable(loginLocator)).click();
    	return new LoginPage(myDriver);
    }
}
