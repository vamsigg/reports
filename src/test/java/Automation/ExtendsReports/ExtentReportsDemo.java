package Automation.ExtendsReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo {

	ExtentReports extent;  //to handle in subsequent methods
	
	@BeforeMethod
	public void config()
	{
		//extentsparkreports,extentreports classes
		
		//extentsparkreports is responsible to create one HTML file and to do some configurations
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);	
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//extentreports is responsible to drive all the excution
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Vamsi");
	}
	
	@Test
	public void initialDemo()
	{
		ExtentTest test=extent.createTest("Initial Demo");//mondatorystep  //keep on monitoring result status of this test
		//one object is creating when we createatest by extentreports which is unique to test method
		
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\mfcwl\\Downloads\\chromedriver_win32\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());  
		driver.close();
		
		test.fail("Results do not match");  //if we want to fail intentionally for reports, but in console it is passed
		extent.flush();    //will notify test is done and no more monitor it      //helpful to get the report
	}
}
