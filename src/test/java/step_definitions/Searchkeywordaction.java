package step_definitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;

import reusability.Components;
import reusability.Utilities;

/**
 * This class contains BDD tests using TestNG Framework
 * 
 * @author p.nvs.vivek
 *
 */
public class Searchkeywordaction {
	
	WebDriver driver;
	ExtentReports report = TestRunner.report;
	HashMap<String, String> hm;
	ExtentTest feature;
	ExtentTest scenario;
	WebElement searchBox;
	WebElement searchButton;

	@BeforeMethod
	public void setUp() throws IOException
	{
		hm = Utilities.readExcel_dataDriven(Components.getProperties("TDMPath"), Components.getProperties("SheetName"));
		driver = Components.selectBrowser(hm.get("Browser"));
		driver.manage().window().maximize();
	}
	
	@Test
	public void Searchkeywordingoogle()
	{
		feature = report.createTest(hm.get("Feature Name"));
		scenario = feature.createNode(Scenario.class, "Search a keyword in google");
		if(user_has_opened_google())
		{
			scenario.createNode(Given.class, "user_has_opened_google").pass("pass");
		}
		else
		{
			scenario.createNode(Given.class, "user_has_opened_google").fail("fail");
		}
		
		if(there_is_a_text_box_for_search())
		{
			scenario.createNode(When.class, "there_is_a_text_box_for_search").pass("pass");
		}
		else
		{
			scenario.createNode(When.class, "there_is_a_text_box_for_search").fail("fail");
		}
		
		if(user_wants_to_search_a_keyword_by_typing_it_in_text_box())
		{
			scenario.createNode(Then.class, "user_wants_to_search_a_keyword_by_typing_it_in_text_box").pass("pass");
		}
		else
		{
			scenario.createNode(Then.class, "user_wants_to_search_a_keyword_by_typing_it_in_text_box").fail("fail");
		}
		
		if(google_displays_the_results())
		{
			scenario.createNode(And.class, "google_displays_the_results").pass("pass");
		}
		else
		{
			scenario.createNode(And.class, "google_displays_the_results").fail("fail");
		}
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.close();
	}
	
	public boolean user_has_opened_google() {
	    // Write code here that turns the phrase above into concrete actions
		try
		{
		driver.get("http://www.google.com/");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		return true;
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			return false;
		}
		
	}

	public boolean there_is_a_text_box_for_search() {
	    // Write code here that turns the phrase above into concrete actions
		boolean check = false;
		try
		{
		searchBox = driver.findElement(By.id("lst-ib"));
		if(searchBox.isDisplayed())
		{
			System.out.println("searchBox is present");
			check = true;
		}
		else
		{
			System.out.println("searchBox is not present");
			check = false;		
		}
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			check = false;
		}
		return check;
	}

	public boolean user_wants_to_search_a_keyword_by_typing_it_in_text_box() {
	    // Write code here that turns the phrase above into concrete actions
		try
		{
		searchBox.sendKeys(hm.get("Search_Keyword"));
		searchButton = driver.findElement(By.id("_fZl"));
		searchButton.click();
		return true;
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			return false;
		}
	}

	public boolean google_displays_the_results() {
	    // Write code here that turns the phrase above into concrete actions
		boolean check = false;
		try
		{
		List<WebElement> list = driver.findElements(By.tagName("a"));
		int flag=0;
		for(int i=0; i<list.size();i++)
		{
			 if(list.get(i).getText().contains(hm.get("Expected Text")))
			 {
				 flag=1;
				 break;
			 }
		}
		 if(flag==1)
			{
				System.out.println("Expected link is present");
				check = true;
			}
		 else
		 {
			 System.out.println("Expected link is not present");
				check = false;
		 }
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			check = false;
		}
		return check;
	}
}
