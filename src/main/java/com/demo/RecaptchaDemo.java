package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RecaptchaDemo {

    public static void main(String[] args) throws InterruptedException {

        // ---- SETUP ----
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("load-extension=/Users/neerainx/Desktop/NopeCHA-CAPTCHA-Solver-Chrome-Web-Store");

        WebDriver driver = new ChromeDriver(options);
        
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(30));
        driver.manage().window().maximize();
        
            driver.get("https://phptravels.net/signup");

            // STEP 2: Enter Email
            WebElement firstNameField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstname']")));
            firstNameField.sendKeys("First");
            
            WebElement lastNameField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='last_name']")));
            lastNameField.sendKeys("Last");
            
            WebElement countryDropdown = driver.findElement(By.xpath("//button[@title='Select Country']"));
            countryDropdown.click();
            WebElement indiaOption = driver.findElement(By.xpath("//li[contains(.,'India')]"));
            indiaOption.click();

            System.out.println("India selected successfully!");

            
            WebElement phoneNumber = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='phone']")));
            phoneNumber.sendKeys("1212121212");
          
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
            emailField.sendKeys("mishajosh@yopmail.com");
          
            WebElement passwordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
            passwordField.sendKeys("Test@123");
          
            Thread.sleep(40000);

            System.out.println("✔ Waiting for nopeCHA extension to fill captcha...");
            	
            wait.until(ExpectedConditions.elementToBeClickable(By.id("submitBTN")));

            	WebElement submitBtn = wait.until(
            	    ExpectedConditions.elementToBeClickable(By.id("submitBTN"))
            	);

            	submitBtn.click();

            System.out.println("Login successful.");
            
            Thread.sleep(9000);
            driver.quit();
    }
}

        // ----------------------------------------------------------------- ----
      /*  driver.get("https://www.google.com/recaptcha/api2/demo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ---- SWITCH TO CAPTCHA IFRAME ----
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("iframe[src*='recaptcha']")));
        driver.switchTo().frame(iframe);

        // ---- CLICK THE CHECKBOX ----
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("recaptcha-anchor")));
        checkbox.click();

        // ---- WAIT FOR MANUAL CAPTCHA COMPLETION ----
        System.out.println("\n⚠️ Please solve the CAPTCHA manually...\n");
        driver.switchTo().defaultContent();

        Thread.sleep(60000); // 60 sec wait for manual solving

        System.out.println("✔ CAPTCHA likely solved. Attempting to submit form...");

        // ---- CLICK SUBMIT BUTTON AFTER CAPTCHA ----
        try {
            WebElement submitBtn = driver.findElement(By.id("recaptcha-demo-submit"));
            submitBtn.click();
            System.out.println("✔ Form submitted successfully!");
        } catch (Exception e) {
            System.out.println("⚠️ Could not submit. CAPTCHA probably not solved.");
        }

        Thread.sleep(5000);
        driver.quit();
    }
}*/

///---------------------------------------------------------------///

/*package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RecaptchaDemo {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Google official reCAPTCHA test page (free, no challenge)
        driver.get("https://www.google.com/recaptcha/api2/demo");
        Thread.sleep(5000);

        // Switch into iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src*='recaptcha']")));

        // Click checkbox
        WebElement checkbox = driver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
        checkbox.click();

        // Switch back
        driver.switchTo().defaultContent();

        Thread.sleep(2000);

        // Click submit button
        driver.findElement(By.id("recaptcha-demo-submit")).click();

        Thread.sleep(2000);

        System.out.println("Page Title After Submit: " + driver.getTitle());

        driver.quit();
    }
}


/*package com.demo;

import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import okhttp3.*;

public class RecaptchaDemo {

    static String API_KEY = "CAP-EC6611F25B8516AB8F4CB43294EE8300D92C2E3D5633742F89F3D2FA47EBAFAD";  // <-- PUT YOUR KEY HERE

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Google reCAPTCHA v2 Demo Page
        String url = "https://www.google.com/recaptcha/api2/demo";
        driver.get(url);

        Thread.sleep(5000);

        // Correct demo sitekey (Google provides officially)
        String sitekey = "6Le-wvkSAAAAAPBMRTvw0Q4Muexq9bi0DJwx_mJ-";

        // Create task (correct order: apiKey, websiteURL, siteKey)
        String taskId = createTask(API_KEY, url, sitekey);
        System.out.println("Task ID: " + taskId);

        // Get captcha token
        String token = getSolution(taskId);
        System.out.println("Captcha Token: " + token);

        // Inject solution into hidden textarea
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "document.getElementById('g-recaptcha-response').style.display='block';" +
            "document.getElementById('g-recaptcha-response').value='" + token + "';"
        );

        Thread.sleep(2000);

        // Submit form
        driver.findElement(By.id("recaptcha-demo-submit")).click();

        Thread.sleep(5000);
        driver.quit();
    }

    // ---------------- CREATE TASK ----------------

    private static String createTask(String apiKey, String websiteURL, String siteKey) throws Exception {

        OkHttpClient client = new OkHttpClient();

        JSONObject task = new JSONObject();
        task.put("type", "ReCaptchaV2TaskProxyless");
        task.put("websiteURL", websiteURL);
        task.put("websiteKey", siteKey);

        JSONObject body = new JSONObject();
        body.put("clientKey", apiKey);
        body.put("task", task);

        Request request = new Request.Builder()
                .url("https://api.capsolver.com/createTask")
                .post(RequestBody.create(
                        body.toString(),
                        MediaType.parse("application/json")
                ))
                .build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();

        System.out.println("API Response = " + result);

        JSONObject json = new JSONObject(result);

        if (json.getInt("errorId") != 0) {
            throw new RuntimeException("CapSolver Error: " + json.getString("errorDescription"));
        }

        return json.getString("taskId");
    }

    // ---------------- GET SOLUTION ----------------

    public static String getSolution(String taskId) throws Exception {

        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("clientKey", API_KEY);
        json.put("taskId", taskId);

        RequestBody body = RequestBody.create(
            json.toString(),
            MediaType.parse("application/json")
        );

        while (true) {

            Request request = new Request.Builder()
                    .url("https://api.capsolver.com/getTaskResult")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String resultStr = response.body().string();

            JSONObject result = new JSONObject(resultStr);

            System.out.println("Checking Status → " + resultStr);

            if (result.getString("status").equals("ready")) {
                return result.getJSONObject("solution")
                             .getString("gRecaptchaResponse");
            }

            Thread.sleep(3000); // wait & retry
        }
    }
}*/
