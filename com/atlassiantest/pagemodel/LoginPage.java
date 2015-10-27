package com.atlassiantest.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private final WebDriver myDriver;
	private final WebDriverWait wait;
	private String pageTitleString = "Log in to continue";

	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By loginLocator = By.id("login-submit");
	By checkboxLocator = By.id("rememberMe");

	public LoginPage(WebDriver driver) {
		myDriver = driver;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains(pageTitleString));
	}

	public DashBoardLoggedInPage LoginSuccessful(String userName,
			String passWord) {
		wait.until(ExpectedConditions.elementToBeClickable(usernameLocator))
				.sendKeys(userName);
		wait.until(ExpectedConditions.elementToBeClickable(passwordLocator))
				.sendKeys(passWord);
		wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(loginLocator))
				.click();
		return new DashBoardLoggedInPage(myDriver);
	}
}
