package com.demo;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class New_Task {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("file:///Users/neerainx/Desktop/WEB%20PANEL/index.html");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        String parentTab = driver.getWindowHandle(); // ✅ correct

        driver.findElement(By.xpath("//a[contains(.,'Add Review')]")).click();
        Thread.sleep(2000);

        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs) {
            if (!tab.equals(parentTab)) {
                driver.switchTo().window(tab);
            }
        }

        driver.findElement(By.id("customer")).sendKeys("John Doe");
        driver.findElement(By.id("product")).sendKeys("Planters");
        driver.findElement(By.id("date")).sendKeys("2001-01-15"); // ✅ correct
        driver.findElement(By.id("comment")).sendKeys("Fragile Items, Handle with Care");

        driver.findElement(By.xpath("//button[@type='submit' and normalize-space()='Save']")).click(); // ✅ MUST CLICK

        Thread.sleep(2000);

        driver.switchTo().window(parentTab);
        driver.navigate().refresh(); 
        
        boolean isAdded = driver.getPageSource().contains("John Doe");
        
        if (isAdded) {
		    System.out.println("✅ Review added successfully and visible in table");
		} else {
		    System.out.println("❌ Review not found in table");
		}
        Thread.sleep(1500);
        driver.quit();
        }
}

        // COMMAND + CLICK to open Add Employee in new tab (Mac)
        /*Actions actions = new Actions(driver);
        actions
            .keyDown(Keys.COMMAND)
            .click(driver.findElement(By.xpath("//a[normalize-space()='Create']")))
            .keyUp(Keys.COMMAND)
            .build()
            .perform();

        Thread.sleep(3000);

        // Switch to new tab
        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs) {
            if (!tab.equals(parentTab)) {
                driver.switchTo().window(tab);
            }
        }
        Thread.sleep(5000);
        
     // ---------------- FILL REVIEW FORM ----------------

     // Select Customer
        WebElement customer =
                driver.findElement(By.xpath("//input[@name='customer_id']"));

        customer.sendKeys("aa");
        Thread.sleep(1500);
        customer.sendKeys(Keys.ARROW_DOWN);  // ensures focus
        customer.sendKeys(Keys.ENTER);


     // Select Product
        WebElement productField =
                driver.findElement(By.xpath("//input[@name='product_id']"));

        productField.click();
        productField.sendKeys("Artificial Beach");
        Thread.sleep(1500);
        productField.sendKeys(Keys.ARROW_DOWN);
        productField.sendKeys(Keys.ENTER);


     // Select Date
     driver.findElement(By.xpath("//input[@type='date']")).sendKeys("15-01-2001");

     // Add Comment
     String commentText = "Automation test review";
     driver.findElement(By.xpath("//textarea")).sendKeys(commentText);

     // Click Save
     driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

     Thread.sleep(5000);
     
     driver.switchTo().window(parentTab);
     Thread.sleep(5000);
     
     boolean reviewAdded = driver.findElements(By.xpath("//td[contains(text(),'" + commentText + "')]")).size() > 0;

    		if (reviewAdded) {
    		    System.out.println("✅ Review added successfully and visible in table");
    		} else {
    		    System.out.println("❌ Review not found in table");
    		}
	}

}*/
