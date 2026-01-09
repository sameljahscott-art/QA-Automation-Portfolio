package NinjaTutural;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_1 {

    @Test
    public void verifyRegistrationWithFields() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Registration flow
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("input-firstname")).sendKeys("rose");
        driver.findElement(By.id("input-lastname")).sendKeys("scott");
        driver.findElement(By.id("input-email")).sendKeys(generateEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("4044222");
        driver.findElement(By.id("input-password")).sendKeys("jsoa2r");
        driver.findElement(By.id("input-confirm")).sendKeys("jsoa2r");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // ✅ Verify heading after registration
        String expectedHeading = "Your Account Has Been Created!";
        WebElement heading = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"));
        
        Assert.assertTrue(heading.isDisplayed(), "Heading is not displayed!");
        Assert.assertEquals(heading.getText().trim(), expectedHeading, "Heading text does not match!");
System.out.println("Your Account Has Been Created");

String expectedHeading1 = "Congratulations! Your new account has been success";
WebElement heading2 = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! Your new account has been success')]"));

Assert.assertTrue(heading.isDisplayed(), "Heading is not displayed!");
Assert.assertEquals(heading.getText().trim(), expectedHeading, "Heading text does not match!");
System.out.println("Congratulations! Your new account has been success");

//p[contains(text(),'Congratulations! Your new account has been success')]
    }

    public String generateEmail() {
        Date date = new Date();
        String timestamp = date.toString().replaceAll("[\\s,:]", "");
        return "user" + timestamp + "@example.com";
    }
}
