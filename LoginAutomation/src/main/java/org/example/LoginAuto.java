package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class LoginAuto {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");

        //Methods for Test Cases
        testValidLogin(driver);
        testInValidLogin(driver);
        testEmptyFields(driver);

        // Run CSV-driven test
        testLoginFromCSV(driver, "credentials.csv");

        //Closing browser
        driver.quit();
    }

    private static void testValidLogin(WebDriver driver) {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button.radius")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        if (successMsg.getText().contains("You logged into a secure area!")){
            System.out.println("Test Case 1 Passed: Valid Login Successful");
        }else{
            System.out.println("Test Case 1 Failed: Valid Login UnSuccessful");
        }
    }

    private static void testInValidLogin(WebDriver driver) {
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("rajkumar");
        driver.findElement(By.id("password")).sendKeys("Raj123");
        driver.findElement(By.cssSelector("button.radius")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        if (errorMsg.getText().contains("Your username is invalid!")) {
            System.out.println("Test Case 2 Passed: Invalid Login error displayed");
        } else {
            System.out.println("Test Case 2 Failed: Invalid Login error not displayed");
        }
    }

    private static void testEmptyFields(WebDriver driver) {
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.cssSelector("button.radius")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        if (errorMsg.isDisplayed()) {
            System.out.println("Test Case 3 Passed: Empty fields error displayed");
        } else {
            System.out.println("Test Case 3 Failed: Empty fields error not displayed");
        }
    }

    private static void testLoginFromCSV(WebDriver driver, String s) {
        System.out.println("\nRunning CSV-driven login tests...");

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\GrowAI\\Project Submission\\Selenium_Project_by_Anant_Giri\\Java_Selenium_Project\\credentials.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] creds = line.split(",");
                if (creds.length < 2) continue; // Skip invalid rows

                String username = creds[0].trim();
                String password = creds[1].trim();

                driver.navigate().to("https://the-internet.herokuapp.com/login");

                driver.findElement(By.id("username")).sendKeys(username);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.cssSelector("button.radius")).click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement message = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
                );

                if (message.getText().contains("You logged into a secure area!")) {
                    System.out.println("CSV Test Passed: (" + username + ", " + password + ")");
                } else {
                    System.out.println("CSV Test Failed: (" + username + ", " + password + ")");
                }
            }
        } catch (IOException e) {
            System.out.println("⚠Error reading CSV file: " + e.getMessage());
        }

    }
}