package flyus_using_junit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass 
{
	
	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static TakesScreenshot ts ;
	
	//public static Actions acc = new Actions(driver);
	//we cant write the above line. it will throw null pointer exception
	//coz when a class is created the static elements are initialized before object creation
	//itself. so driver will be pointing to null here. driver will be initialized only after we launch any browser
	public static Actions acc;
	public static Robot r;
	
	//returns an obj of Actions class
	public static Actions returnActionsObj()
	{
		acc = new Actions(driver);
		return acc;
	}
	
	
	//returns an object of Robot class
	public static Robot returnRobotObj() throws AWTException
	{
		 return r = new Robot();
		
	}
	
	//returns an typecasted object of JavaScriptExecutor
	public static JavascriptExecutor returnJSObj()
	{
		return js = (JavascriptExecutor)driver;
	}
	
	
	
	//maximize
	public static void maximize()
	{
		driver.manage().window().maximize();
	}
	
	//launch chrome browser
	public static void launchChrome()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\Selenium10AM\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		maximize();
	}
	
	//launch firefox browser
	public static void launchFirefox()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\Selenium10AM\\Driver\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	//launch internet explorer
	public static void launchIE()
	{
		System.setProperty("webdriver.ie.driver", "C:\\Users\\sathishPC\\Desktop\\selenium\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		maximize();
	}
	
	
	//open url
	public static void openURL(String URL)
	{
		driver.get(URL);
	}
	
	//fill text box - username, pwd, etc
	public static void fillText(WebElement e, String text)
	{
		e.sendKeys(text);
	}
	
	
	//mouseover action
	public static void mouseOver(WebElement e)
	{	
		
		returnActionsObj().moveToElement(e).perform();
	}
	
	//drag and drop
	public static void dragAndDrop(WebElement from, WebElement to)
	{
		returnActionsObj().dragAndDrop(from, to).perform();
	}
	
	
	//press Down Key n number of times
	public static void pressDownKey(int n) throws AWTException
	{
		
		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_DOWN);
			returnRobotObj().keyRelease(KeyEvent.VK_DOWN);
		}
	}
	
	//press up key n number of times
	public static void pressUpKey(int n) throws AWTException
	{
		
		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_UP);
			returnRobotObj().keyRelease(KeyEvent.VK_UP);
		}
	}
	
	//press tab key n number of times 
	
	public static void pressTab(int n) throws AWTException
	{
		
		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_TAB);
			returnRobotObj().keyRelease(KeyEvent.VK_TAB);
		}
	}
	
	//press enter key n number of times
	public static void pressEnter(int n) throws AWTException
	{

		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_ENTER);
			returnRobotObj().keyRelease(KeyEvent.VK_ENTER);
		}
	}
	
	//right click an element
	public static void rightClick(WebElement e)
	{
		returnActionsObj().contextClick(e).perform();;
	}
	
	
	//double click an element
	public static void doubleClick(WebElement e)
	{
		returnActionsObj().doubleClick(e).perform();
	}
	
	
	//take screenshot
	//default path set is --> C:\Users\sathishPC\Desktop\selenium\Programs\BaseClass\Screenshot\
	public static void screenshot(String fileName) throws IOException
	{
		ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\ThridProjectFlyUs\\Screenshots\\"+fileName));
	}
	
	
	//scrollDown to an element
	public static void scrollDown(WebElement e)
	{
		returnJSObj().executeScript("arguments[0].scrollIntoView();", e);
	}
	
	//scroll Up to an element
	public static void scrollUp(WebElement e)
	{
		returnJSObj().executeScript("arguments[0].scrollIntoView();", e);
	}
	
	
	//accept alert
	public static void acceptAlert()
	{
		driver.switchTo().alert().accept();
	}
	
	//dismiss alert
	public static void dismissAlert()
	{
		driver.switchTo().alert().dismiss();
	}
	
	//enter value in alert textbox
	public static void enterAlertText(String str)
	{
		driver.switchTo().alert().sendKeys(str);
	}
	
	//gets the text from alert box
	public static void getAlertText()
	{
		driver.switchTo().alert().getText();
	}
	
	//selects a dropdown by the visible text
	public static void selectByVisibleText(WebElement e, String str)
	{
		Select s = new Select(e);
		s.selectByVisibleText(str);
	}
	
	//switch to nth tab
	public static void switchTab(int n)
	{
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(handlesList.get(n-1));
	}
	
	
	//close n th tab
	public static void closeTab(int n)
	{
		switchTab(n);
		driver.close();
	}
	
	
	//hightlight a web element
	public static void highLight(WebElement e, String color)
	{
		String higlightColor = "arguments[0].setAttribute('style', 'background-color:" +color +";');";
		returnJSObj().executeScript(higlightColor, e);
	}
	
	
	//Excel read: this method takes file path of the excel, sheet name, row and cell number as arguments
	public static String excelReadSingleCell(String filePath, String sheetName, int rowNum, int cellNum) throws IOException
	{
		String cellValue = null;
		FileInputStream fis = new FileInputStream(filePath);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet(sheetName);
		Row r = s.getRow(rowNum);
		Cell c = r.getCell(cellNum);
		int cellTypeNum = c.getCellType();
		
		if(cellTypeNum==1)
		{
			cellValue = c.getStringCellValue();
		}
		
		else if(cellTypeNum == 0)
		{
			if(DateUtil.isCellDateFormatted(c))
			{
				Date dateValue = c.getDateCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				cellValue = sdf.format(dateValue);

			}
			
			else
			{
				double d = c.getNumericCellValue();
				long l = (long)d;
				cellValue = String.valueOf(l);
			}
		}
		
		return cellValue;
	}
	
	//Excel read one full row
	public static List<String> excelReadRow(String filePath, String sheetName, int rowNum) throws IOException
	{
		List<String> strList = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(filePath);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet(sheetName);
		Row r = s.getRow(rowNum);
		int numberOfCells = r.getPhysicalNumberOfCells();
		
		for(int i =0 ; i<numberOfCells; i++)
		{
			strList.add(excelReadSingleCell(filePath, sheetName, rowNum, i));
		}
	return strList;
	}
	
	//read all elements in an excel
	public static List<String> excelReadAll(String filePath, String sheetName) throws IOException
	{
		List<String> strList = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(filePath);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s = w.getSheet(sheetName);
		
		for(int i =0; i<s.getPhysicalNumberOfRows();i++)
		{
			strList.addAll(excelReadRow(filePath, sheetName, i));
			//excelReadRow(filePath, sheetName, i);
		}
			
		return strList;
	}
	
	//write a value in once cell
	public static void excelWriteSingleCell(String filePath, String sheetName, int rowNum, int cellNum, String cellValue) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(filePath);
		Workbook w = new XSSFWorkbook();		
		Sheet s = w.createSheet(sheetName);
		Row r = s.createRow(rowNum);
		Cell c = r.createCell(cellNum);
		c.setCellValue(cellValue);
		w.write(fos);
	}
	
	//write values in a row
	public static void excelWriteRow(String filePath, String sheetName, int rowNum, List<String> cellValues) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(filePath);
		Workbook w = new XSSFWorkbook();		
		Sheet s = w.createSheet(sheetName);
		Row r = s.createRow(rowNum);
		for(int i = 0; i<cellValues.size(); i++) 
		{
			Cell c = r.createCell(i);
			c.setCellValue(cellValues.get(i));
		}
		
		w.write(fos);
		
	}
	
	//write values in excel dynamically multiple rows
	public static void excelWriteDynamic() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the file path : ");
		String filePath = sc.nextLine();
		FileOutputStream fos = new FileOutputStream(filePath);
		Workbook w = new XSSFWorkbook();
		System.out.println("Please enter the sheet name: ");
		String sheetName = sc.nextLine();
		Sheet s = w.createSheet(sheetName);
		System.out.println("Please enter the number of rows : ");
		int numberOfRows = sc.nextInt();
		//String s1 = new String(sc.nextLine());//this is a dummy line - else we will get errors while using nextLine() below
		String cellValue;
		
		for(int i = 0; i<numberOfRows; i++)
		{
			int count =0;
			Row r = s.createRow(i);
			System.out.println("Please enter the number of elements you want to fill in row " +(i+1));
			int numberOfCells = sc.nextInt();
			
			for(int j = 0; j<numberOfCells; j++)
			{
				if(count==0)
				{
					String s1 = sc.nextLine();
					count++;
				}
				Cell c = r.createCell(j);
				System.out.println("please enter the value of cell " +(j+1) +" in row " +(i+1)+" : ");
			
				cellValue = sc.nextLine();
				System.out.println("the value is : "+cellValue);
				//sc.next() - if we use this, everything is working fine
				//sc.nextLine() - it is automatically taking the first input
				//as a 'space'. it is only accepting input from 2nd time.
				//above if condition is to solve this problem
				//see explanation below
				//https://www.geeksforgeeks.org/why-is-scanner-skipping-nextline-after-use-of-other-next-functions/
				c.setCellValue(cellValue);
			}
			
		}
		w.write(fos);
		
	}
	
	//find by xpath
		public static WebElement findByXpath(String xpath)
		{
			return driver.findElement(By.xpath(xpath));
		}
		
		//select date
		
		public static void selectDate() throws InterruptedException
		{
			String xpath1 = "(//tbody)[2]//tr[2]//td[1]";
			findByXpath(xpath1).click();
			String xpath2 = "(//tbody)[2]//tr[2]//td[3]";
			findByXpath(xpath2).click();


		}
}
