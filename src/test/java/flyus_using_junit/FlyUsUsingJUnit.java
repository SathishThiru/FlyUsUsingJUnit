package flyus_using_junit;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlyUsUsingJUnit extends BaseClass
{
	public static int count=1;
	
	@BeforeClass
	public static void openUrl() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\sathishPC\\\\Desktop\\\\selenium\\\\Programs\\\\Selenium10AM\\\\Driver\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		openURL("https://www.flyus.com/");
		screenshot("mainPage.jpeg");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		String mainPageTitle = driver.getTitle();
//		boolean mainTitleConfirm = mainPageTitle.contains("Cheap");
//		Assert.assertTrue("Verifying main page?" , mainTitleConfirm);
	}
	
	@AfterClass
	public static void quit()
	{
		System.out.println("Total number of test cases run is: " +count);
		//driver.quit();
	}
	
	@Before
	public void startTime()
	{
		Date d = new Date();
		System.out.println("The start time of test case " +count +" is "+d);
	}
	
	@After
	public void endTime()
	{
		Date d = new Date();
		System.out.println("The end time of test case " +count +" is "+d);
		count++;
	}
	
	@Test
	//click fb and go to new page. verify page title
	public void test01() throws InterruptedException, AWTException, IOException 
	{
		
//		//scroll down concept used
//		//clicking fb page
		scrollDown(findByXpath("//img[@src='img/social/fb-icon.svg']"));
		findByXpath("//img[@src='img/social/fb-icon.svg']").click();
			
//		//windows handling concept used
//		//switching to main tab
		switchTab(1);
	}
	
	@Test
	//switch back to main tab. fill from place and verify it
	public void test02() throws IOException, InterruptedException, AWTException
	{	
//		//filling from place: here list, sendkeys and excelread concepts used
		List<String> from= excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 1);
//		//filling from place fetched from excel sheet
		fillText(findByXpath("//input[@id='flys-dpt-0']"), from.get(0));
		Thread.sleep(1000);
//		//robot class used
		pressDownKey(1);
		pressEnter(1);
		WebElement fromWebElement =findByXpath("//input[@id='flys-dpt-0']");
		String valueFilledInFromTextBox = fromWebElement.getAttribute("value");
		boolean b = valueFilledInFromTextBox.contains("London");
		Assert.assertTrue("verifying if from place is filled?", b);
	}
	
	@Test
	//fill destination and verifying it
	public void test03() throws IOException, InterruptedException, AWTException
	{
		List<String> to= excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 1);
		fillText(findByXpath("//input[@id='flys-arr-0']"), to.get(1));
		Thread.sleep(1000);
		pressDownKey(1);
		pressEnter(1);
		WebElement toWebElement =findByXpath("//input[@id='flys-arr-0']");
		String valueFilledInToTextBox = toWebElement.getAttribute("value");
		boolean b = valueFilledInToTextBox.contains("India");
		Assert.assertTrue("verifying if to place is filled?", b);

	}
	
	@Test
	//enter date
	public void test04() throws InterruptedException, IOException
	{
		selectDate();
		screenshot("dateSelected.jpeg");
	}

	@Test
	public void test05() throws AWTException
	{
		pressTab(2);
		findByXpath("//button[@type='submit']").click();

	}
	
	@Test
	//after submitting goes to next page. verify page title and click select
	public void test06() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		//closing a float window which aappears after few seconds
		//findByXpath("(//button[@ng-click='paModalCtrl.close()'])[1]").click();
		String title = driver.getTitle();
		boolean b =title.contains("Flyus");
		screenshot("searchResults.jpeg");
		Assert.assertTrue("verifying select page title: ", b);
		findByXpath("(//img[@src='img/white-arrow-continue.svg'])[1]").click();
	}
	
	@Test
	//after selecting it goes to itinerary details page
	public void test07()
	{
		//in this page we have a line 'confirm itinerary details'
		//we will check if it is available. if it is available means then we are in right page
		Assert.assertEquals("verifying if we are in itenary page or not:", "Confirm Itinerary Details",findByXpath("//h2[text()='Confirm Itinerary Details']").getText());

	}
	
	@Test
	//select Mr. and verify it
	public void test08() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		screenshot("selectedFlight.jpeg");
		//selecting title
		selectByVisibleText(findByXpath("//select[@name='paxes[ADULT-0][title]']"), "Mr.");
		Assert.assertTrue("verifying if title is selected", findByXpath("//select[@name='paxes[ADULT-0][title]']").isDisplayed());
	}


	@Test
	public void test09()
	{
		//selecting gender
		selectByVisibleText(findByXpath("//select[@id='gender-pax-0']"), "Male");
		Assert.assertTrue("verifying if gender is selected", findByXpath("//select[@id='gender-pax-0']").isDisplayed());

	}

	@Test
	public void test10() throws IOException
	{
		//entering firstname
		
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='fname-pax-0']"), name.get(0) );
		String s = findByXpath("//input[@id='fname-pax-0']").getAttribute("value");
		Assert.assertEquals("verifying the entered first name :", name.get(0), s );

	}
	
	@Test
	public void test11() throws IOException
	{
		//entering lastname
		
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='lname-pax-0']"), name.get(1));
		String s = findByXpath("//input[@id='lname-pax-0']").getAttribute("value");
		Assert.assertEquals("verifying the entered last name :", name.get(1), s );

	}

	@Test
	public void test12() throws IOException
	{
//		//entering dob. first clicking..then entering
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		findByXpath("//input[@id='paxes-ADULT-0-dob']").click();
		fillText(findByXpath("//input[@id='paxes-ADULT-0-dob']"),  name.get(2));
		String s = findByXpath("//input[@id='paxes-ADULT-0-dob']").getAttribute("value");
		Assert.assertEquals("verifying the entered dob :", name.get(2), s );

	}
	@Test
	public void test13() throws IOException
	{
//		//entering billing details first name
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='cc-first-name']"), name.get(0));
		String s = findByXpath("//input[@id='cc-first-name']").getAttribute("value");
		Assert.assertEquals("verifying the first name in billing :", name.get(0), s );

	}
	
	@Test
	public void test14() throws IOException
	{
//		//entering billing details last name
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='cc-last-name']"), name.get(1));
		String s = findByXpath("//input[@id='cc-last-name']").getAttribute("value");
		Assert.assertEquals("verifying the last name in billing :", name.get(1), s );

	}
	
	@Test
	public void test15() throws IOException
	{
//		//entering billing details last name
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("(//input[@id='billing-address'])[1]"), name.get(3));
		String s = findByXpath("(//input[@id='billing-address'])[1]").getAttribute("value");
		Assert.assertEquals("verifying the address entered in billing :", name.get(3), s );

	}
		
		//selecting country
	@Test
	public void test16() throws InterruptedException
	{
		selectByVisibleText(findByXpath("//select[@id='cc-country']"), "India");
		Thread.sleep(2000);
		Select s = new Select(findByXpath("//select[@id='cc-country']"));
		WebElement selectedOption = s.getFirstSelectedOption();
		Assert.assertEquals("verifying the selected country :", "India", selectedOption.getText() );

	}
	
	//selecting state
	@Test
	public void test17() throws InterruptedException
	{
		selectByVisibleText(findByXpath("//select[@id='cc-region']"), "Tamil Nadu");		Thread.sleep(3000);
		Thread.sleep(2000);
		Select s = new Select(findByXpath("//select[@id='cc-region']"));
		WebElement selectedOption = s.getFirstSelectedOption();
		Assert.assertEquals("verifying the selected region :", "Tamil Nadu", selectedOption.getText() );
	}

	//entering city name
	@Test
	public void test18() throws InterruptedException, IOException
	{
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='cc-city']"), name.get(4));
		
		String s = findByXpath("//input[@id='cc-city']").getAttribute("value");
		Assert.assertEquals("verifying the entered city in billing :", name.get(4), s );
	}
	
	
	
	//, zip code, 
	
	@Test
	public void test19() throws InterruptedException, IOException
	{
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='cc-zip']"), name.get(5));		
		String s = findByXpath("//input[@id='cc-zip']").getAttribute("value");
		Assert.assertEquals("verifying the zip code :", name.get(5), s );
	}
	
	@Test
	//phone
	public void test20() throws InterruptedException, IOException
	{
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='cc-phone']"), name.get(6));
		String s = findByXpath("//input[@id='cc-phone']").getAttribute("value");
		Assert.assertEquals("verifying the phone number in billing :", name.get(6), s );
	}
	
		//email 
	@Test
	public void test21() throws InterruptedException, IOException
	{
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='cc-email']"), name.get(7));
		String s = findByXpath("//input[@id='cc-email']").getAttribute("value");
		Assert.assertEquals("verifying the email in billing :", name.get(7), s );
	}

	//email confirmation
	@Test
	public void test22() throws InterruptedException, IOException
	{
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		fillText(findByXpath("//input[@id='cc-email-conf']"), name.get(7));
		String s = findByXpath("//input[@id='cc-email-conf']").getAttribute("value");
		Assert.assertEquals("verifying the confirmation email in billing :", name.get(7), s );
	}
				
//		//getting text from website and writing in excel
	@Test
	public void test23() throws InterruptedException, IOException
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String price = findByXpath("(//h2[@class='m-t-none m-b-xs ng-binding'])[2]").getText();
		excelWriteSingleCell("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\FlyUsUsingJUnit\\ExcelFiles\\ExcelOutputRecordingFile.xlsx", "sheet1", 0, 0, price);
		screenshot("last.jpeg");
		String valueInExcelCell =excelReadSingleCell("C:\\\\Users\\\\sathishPC\\\\Desktop\\\\selenium\\\\Programs\\\\FlyUsUsingJUnit\\\\ExcelFiles\\\\ExcelOutputRecordingFile.xlsx", "sheet1", 0, 0);
		Assert.assertEquals("verifying the entered input in excel sheet :",price, valueInExcelCell);

	}
	}




