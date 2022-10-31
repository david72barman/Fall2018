package Seasson5part2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.module.FindException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Test;

public class CRMTest {

		WebDriver driver;
		String browser = null;

		@BeforeTest
		public void readConfig(){
		//inputStream //BufferdReader //FileReader //Scanner

		Properties prop = new Properties();
		try{
		InputStream input = new FileInputStream("./src/main/java/config/config.priorities");
		prop.load(input);
		browser = prop.getProperty("browser");
		System.out.println("Browser used: " + browser);

		}catch(IOException e){
		e.printStackTrace();
		}
		}

		@BeforeMethod
		public void init(){
		if(browser.equalsIgnoreCase("chrome")){
		//Setting up the property
		System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
		//Creating web driver instance
		driver = new ChromeDriver();

		}else if(browser.equalsIgnoreCase("Firefox")){
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		driver = new FirefaxDriver();


		//Maximizing Browser
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		//Get to the site
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


		@Test (priority=1)
		public void loginTest() throws FindException{

		//Element library
		By USERNAME_FIELD_LOCATOR = By.id ("username");
		By PASSWORD_FIELD_LOCATOR = By.id ("password");
		By SIGNIN_BUTTON_LOCATOR = By.name ("login");
		By DASHBOARD_BUTTON_LOCATOR = By.xpath("//span[contains(text(),'Dshboard']");

		//Data
		String loginId = "demo@techfios.com";
		String password = "abc123";

		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();

		waitForElement(driver, 3, DASHBOARD_BUTTON_LOCATOR);

		String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON_LOCATOR).getText();
		Assert.asserEquals("DashBoard", dashboardValidationText,"Wrong Page!!!");

		}

		private boolean asserEquals(String string, String dashboardValidationText, String string2) {
			// TODO Auto-generated method stub
			return false;
		}

		@Test (priority=2)
		public void addCustomerTest() throws InterruptedException{

		//Element Library
		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		By DASHBOARD_BUTTON = By.xpath("//span[conains(text(), 'DashBoard']");
		By CUSTOMERS_BUTTON = By.xpath ("//span[conains(text(), 'Customers']");
		By ADD_CUSTOMER_BUTTON = By.xpath ("//a[conains(text(), 'Add Customer']");
		By ADD_CONTACT_LOCATOR = By.xpath ("//h5[conains(text(), 'Add contact']");
		By FULL_NAME_FIELD = By.xpath ("//input[@id='account']");
		By COMPANY_NAME_FIELD=By.xpath("//input[@id='company']");
		By EMAIL_FIELD=By.xpath("//input[@id='email']");
		By PHONE_FIELD=By.xpath("//input[@id='phone']");
		By ADDRESS_FIELD=By.xpath("//input[@id='address']");
		By CITY_FIELD=By.xpath("//input[@id='city']");
		By STATE_REGION_FIELD=By.xpath("//input[@id='state']");
		By ZIP_FIELD=By.xpath("//input[@id='zip']");
		By SUBMIT_BUTTON=By.xpath("//button[@class='btn btn-primary']");
		By LIST_CONTACTS_BUTTON=By.xpath("//a[contains(text(),'List Contacts']");

		//Login Data
		String loginId="demo@techfios";
		String password="abc123";

		//Test Data
		String fullName = "Test Spring";
		String companyName = "Techfios";
		String emailAddress = "spring@gmail.com";
		String phoneNumber = "231656564";

		//Perform Login in
		driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();

		//Validate Dashboard Page
		waitForElement(driver, 3, DASHBOARD_BUTTON);
		String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON).getText();
		Assert.asserEquals("DashBoard", dashboardValidationText,"Wrong Page!!!");

		driver.findElement(CUSTOMERS_BUTTON).click();
		driver.findElement(ADD_CUSTOMER_BUTTON).click();
		waitForElement(driver,3,ADD_CONTACT_LOCATOR);

		//Generate Random Number

		Random rnd= new Random();
		int random = rnd.nextint(999);

		//FIll out add customners form
		driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + randomNum);
		driver.findElement(EMAIL_FIELD).sendKeys(randomNum + emailAddress);

		//AfterMethod
		public void tearDown(){

		driver.close();
		driver.quit();

		public void waitForElement(WebDriver driver,int timeInSeconds, By locator){
		WebDriverWait wait = new WebDriverWait(Driver,timeInSeconds);
		wait.until(ExpectedConditions.visinbilityOfElementLocated(locator));

		}
	
}