package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MockCaptchaAutomation {

    public static void main(String[] args) {

        // If chromedriver is not in PATH, uncomment and set path
        // System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        // 🔹 Open local HTML file
        driver.get("file:////Users/neerainx/Documents/captcha-form.html");

        // Test Case 1 Verify with success attempt
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("email")).sendKeys("john.doe@test.com");

        // 🔹 Read CAPTCHA text
        WebElement captchaText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("captchaText"))
        );
        String captchaValue = captchaText.getText();
        System.out.println("CAPTCHA value = " + captchaValue);

        // 🔹 Enter CAPTCHA
        driver.findElement(By.id("captchaInput")).sendKeys(captchaValue);

        // 🔹 Submit form
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // 🔹 Verify success message
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );

        System.out.println("Result Message: " + successMsg.getText());

        driver.navigate().refresh();
        
    // Test Case 2 - Verify with Fail attempt
        driver.findElement(By.id("firstName")).sendKeys("Kohl");
        driver.findElement(By.id("lastName")).sendKeys("Slick");
        driver.findElement(By.id("email")).sendKeys("kohl.slick@test.com");

        // --- STEP 2: Read CAPTCHA ---
        WebElement captchaText1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("captchaText"))
        );
        String correctCaptcha = captchaText1.getText();
        System.out.println("Correct CAPTCHA = " + correctCaptcha);

        driver.findElement(By.id("captchaInput")).sendKeys("WRONG1");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        System.out.println("Error Message: " + errorMsg.getText());

        driver.findElement(By.id("captchaInput")).clear();

        driver.findElement(By.xpath("//button[text()='Refresh']")).click();

        WebElement newCaptchaText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("captchaText"))
        );
        String newCaptcha = newCaptchaText.getText();
        System.out.println("New CAPTCHA = " + newCaptcha);

        driver.findElement(By.id("captchaInput")).sendKeys(newCaptcha);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement successMsg1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        System.out.println("Final Message: " + successMsg1.getText());

        
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}

