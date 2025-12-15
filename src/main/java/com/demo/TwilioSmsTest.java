package com.demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.Duration;

public class TwilioSmsTest {

    // Twilio Credentials
    public static final String ACCOUNT_SID = "ACbc9287c8647337e911f692c945708c90";
    public static final String AUTH_TOKEN = "59673de2ad2b0e1e8e0a83d8d524ee24";
    public static final String TWILIO_NUMBER = "+14846235398"; // Your Twilio virtual number

public static String sendOtpViaTwilio() throws InterruptedException {

        // ------------------ 1️⃣ Send OTP via Twilio ------------------
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String otpCode = generateOtp();

        Message message = Message.creator(
                new PhoneNumber("+918320305496"), // YOUR number
                new PhoneNumber(TWILIO_NUMBER),
                "Your OTP is: " + otpCode
        ).create();

        System.out.println("OTP Sent SID: " + message.getSid());
        System.out.println("OTP Generated: " + otpCode);

        Thread.sleep(3000); // wait for SMS delivery

        return otpCode; // ✅ IMPORTANT
    }

    // ================== MAIN ==================
    public static void main(String[] args) throws InterruptedException {

        // 1️⃣ Get OTP
        String fetchedOtp = sendOtpViaTwilio();
        System.out.println("Fetched OTP: " + fetchedOtp);

        // 2️⃣ Selenium
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("file:///Users/neerainx/Documents/OTPTwilio.html");

        driver.findElement(By.id("phone"))
              .sendKeys("+918320305496");

        driver.findElement(By.id("message"))
              .sendKeys("OTP Sent");

        driver.findElement(By.id("sendOtpBtn")).click();

        Thread.sleep(2000);

        driver.findElement(By.id("otp"))
              .sendKeys(fetchedOtp);

        driver.findElement(By.id("submitBtn")).click();

        Thread.sleep(3000);
        driver.quit();
    }

    // ================== OTP GENERATOR ==================
    public static String generateOtp() {
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }
}