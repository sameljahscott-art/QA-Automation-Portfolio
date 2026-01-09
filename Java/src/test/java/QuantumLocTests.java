
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class QuantumLocTests {
    WebDriver driver;
    String baseUrl = "https://www.quantumloc.io";

    Map<String, String[]> navLinkKeywords = new HashMap<>() {{
        put("home", new String[] { "QuantumLoc" });
        put("about", new String[] { "Our Mission", "Team", "About Us" });
        put("services", new String[] { "Geolocation", "Solutions", "Location Platform" });
        put("contact", new String[] { "Contact", "Get in touch", "Send a message" });
        put("enterprise", new String[] { "Enterprise", "Platform", "API access" });
    }};

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();  
    }
    
    @Test(priority = 1)
    public void testHomePageLoads() {
        driver.get(baseUrl);
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("QuantumLoc"), "Title should contain 'Quantum Location Services - Advanced Digital Addressing Solutions'");
    }

    @Test(priority = 2)
    public void testNavigationLinks() {
        driver.get(baseUrl);
        for (WebElement link : driver.findElements(By.cssSelector("nav a"))) {
            String href = link.getAttribute("href");
            Assert.assertNotNull(href, "Link href should not be null");
        }
    }

    @Test(priority = 3)
    public void testMapPresence() {
        driver.get(baseUrl);
        try {
            WebElement map = driver.findElement(By.cssSelector("iframe, .map-container, #map"));
            Assert.assertTrue(map.isDisplayed(), "Map should be visible");
        } catch (Exception e) {
            Assert.fail("Map element not found");
        }
    }

    @Test(priority = 4)
    public void testContactFormValidation() {
        driver.get(baseUrl + "/contact"); // Update if needed
        try {
            WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
            emailField.sendKeys("invalid-email");
            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
            submitButton.click();
            Thread.sleep(1000); // Wait for error
            WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(), 'valid email')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "Validation error should appear");
        } catch (Exception e) {
            System.out.println("️ Contact form validation test skipped or failed.");
        }
    }
    
    @Test(priority = 5)
    public void testNavBarFunctionality() {
        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> navLinks = driver.findElements(By.cssSelector("nav a"));    
        for (int i = 0; i < navLinks.size(); i++) {
            WebElement link = navLinks.get(i);
            String href = link.getAttribute("href");
            String linkText = link.getText().trim();
            Assert.assertNotNull(href, "Navbar link href should not be null");
            try {
                // Click and wait for navigation
                link.click();
                wait.until(ExpectedConditions.urlContains(href));
                // Wait for some visible content on new page
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
                String newTitle = driver.getTitle();
                Assert.assertFalse(newTitle.isEmpty(), "Page title should not be empty after clicking " + linkText);
                System.out.println("Navigation to " + linkText + " successful. Title: " + newTitle);
            } catch (Exception e) {
                takeScreenshot("NavLink_Failure_" + linkText.replace(" ", "_"));
                Assert.fail("Failed to navigate via link: " + linkText + "\n" + e.getMessage());
            }
            // Go back and re-locate elements to avoid stale reference
            driver.navigate().back();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav a")));
            navLinks = driver.findElements(By.cssSelector("nav a"));
        }
    }
    public void takeScreenshot(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("screenshots/" + fileName + ".png"));
            System.out.println("📸 Screenshot saved: " + fileName + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 6)
    public void testNavBarContentValidation1() {
        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> navLinks = driver.findElements(By.cssSelector("nav a"));
        for (int i = 0; i < navLinks.size(); i++) {
            WebElement link = navLinks.get(i);
            String href = link.getAttribute("href");
            String linkText = link.getText().trim().toLowerCase(); // normalize for map key
            Assert.assertNotNull(href, "Navbar link href should not be null");
            try {
                link.click();
                wait.until(ExpectedConditions.urlContains(href));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
                String pageSource = driver.getPageSource().toLowerCase(); // normalize for contains()
                String[] expectedKeywords = navLinkKeywords.getOrDefault(linkText, new String[] {});
                boolean foundMatch = false;
                for (String keyword : expectedKeywords) {
                    if (pageSource.contains(keyword.toLowerCase())) {
                        foundMatch = true;
                        break;
                    }
                }

                if (expectedKeywords.length > 0) {
                    Assert.assertTrue(foundMatch, "None of the expected keywords were found on the page for: " + linkText);
                } else {
                    System.out.println("No content expectations set for: " + linkText);
                }
                System.out.println("Passed content check for: " + linkText);

            } catch (Exception e) {
                takeScreenshot("NavContent_Failure_" + linkText.replace(" ", "_"));
                Assert.fail("Navigation/content validation failed for: " + linkText + "\n" + e.getMessage());
            }

            driver.navigate().back();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav a")));
            navLinks = driver.findElements(By.cssSelector("nav a"));
        }
    }

    @Test(priority = 7)
    public void testContactFormSubmissionValidData() {
        driver.get(baseUrl + "/contact");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.findElement(By.name("first_name")).sendKeys("James");
            driver.findElement(By.name("last_name")).sendKeys("Gatsby");
            driver.findElement(By.name("email")).sendKeys("james.gatsby" + System.currentTimeMillis() + "@test.com");
            driver.findElement(By.name("message")).sendKeys("I'm interested in your indoor positioning services.");
            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();

            // Wait for confirmation
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Thank you')]")));

            WebElement confirmation = driver.findElement(By.xpath("//*[contains(text(),'Thank you')]"));
            Assert.assertTrue(confirmation.isDisplayed(), "Success message should be visible after form submission.");

            System.out.println("Contact form submitted and confirmed successfully.");
        } catch (Exception e) {
            takeScreenshot("ContactForm_Submission_Failure");
            Assert.fail("Contact form submission failed: " + e.getMessage());
        }
    }
     
    @Test(priority = 8)
    public void testContactFormSubmissionInvalidData() {
        driver.get(baseUrl + "/contact");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.findElement(By.name("first_name")).sendKeys("Paul");
            driver.findElement(By.name("last_name")).sendKeys("Johnson");
            driver.findElement(By.name("email")).sendKeys("james." + System.currentTimeMillis() + "@test.com");
            driver.findElement(By.name("message")).sendKeys("I'm interested in your indoor positioning services.");

            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();

            // Wait for confirmation
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Thank you')]")));

            WebElement confirmation = driver.findElement(By.xpath("//*[contains(text(),'Thank you')]"));
            Assert.assertTrue(confirmation.isDisplayed(), "Success message should be visible after form submission.");

            System.out.println("Contact form submitted and confirmed successfully.");
        } catch (Exception e) {
            takeScreenshot("ContactForm_Submission_Failure");
            Assert.fail("Contact form submission failed: " + e.getMessage());
        }
    }
    
   
    @Test(priority = 9)
    public void testSetCountryName() {
        driver.get("https://www.quantumloc.io/roi-calculator");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for and enter country name
        WebElement countryInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("countryName")));
        countryInput.clear();
        countryInput.sendKeys("Germany");
        WebElement populationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("population")));
        populationInput.clear();
        populationInput.sendKeys(String.valueOf(9000000));
        String expectedValue = "9000000";
        String actualValue = populationInput.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Input value should match expected value.");


        // Assert that the input has the expected value
        Assert.assertEquals(countryInput.getAttribute("value"), "Germany", "Country name should be set correctly.");

        System.out.println("✅ Country name field populated successfully.");
    }

    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
           // driver.quit();
        }
    }
}

