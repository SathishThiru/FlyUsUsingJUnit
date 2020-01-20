package flyus_using_junit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test extends BaseClass {

	
	public static void selectDate()
	{
		launchChrome();
		openURL("https://www.flyus.com/");
		findByXpath("//input[@id='flys-date-0']").click();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String currentDateString = sdf.format(d);
		int currentDateInInteger = Integer.valueOf(currentDateString);
		System.out.println(currentDateInInteger);
		int count = 0;
		for(int i=1; i<=5;  i++)
		{
			if(count>0)
			{
				break;
			}
			String xpathOfEachTrInTbody2 = "(//tbody)[1]//tr["+i+"]";
			for(int j =1; j<=7; j++)
			{
				String xpathOfEachTdInEachTr = xpathOfEachTrInTbody2+"//td["+j+"]";
				int eachTdIntegerValue=0;
				 String eachTdStringValue = findByXpath(xpathOfEachTdInEachTr).getText();
				 System.out.println(eachTdStringValue);
				try 
				{
					 eachTdIntegerValue = Integer.valueOf(eachTdStringValue);
				}
				catch(NumberFormatException e)
				{
					System.out.println("");
				}
				
				if(eachTdIntegerValue>currentDateInInteger) 
				{

					//now we will be in a row in which the datevalue in webpage
					//is greater than the current date
					//we will click that date 
					findByXpath(xpathOfEachTdInEachTr).click();
					//to select another date
					//we will go to next tbody and select a date
					//String xpath = "(//tbody)[2]//tr["+i+"]";

					count++;
					break;
				
				}
				
				if((eachTdIntegerValue+3)>currentDateInInteger)
				{
					
				}
				
				
				
			}
		}
		
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		selectDate();
	}
}
