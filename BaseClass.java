package com.YLighting.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.remote.RemoteTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass 
{

	public static WebDriver driver;
	public static Properties prop;
	

	public WebDriver launchBrowser() throws IOException 
	{
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\YLighting_Config\\YLighting_Config.properties");
		prop.load(fis);
		String browsername = prop.getProperty("browser");
		if (browsername.equals("Chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			//WebDriverWait wait=new WebDriverWait(driver,3);
		}
		return driver;
	}

	public Properties init_prop() 
	{
		
		try 
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/YLighting_Config/YLighting_Config.properties");
			prop.load(fis);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return prop;
	}
	public static String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");  
		  LocalDateTime now = LocalDateTime.now();
		  String dt=dtf.format(now);
		  System.out.println(dt);
		  File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String filepath=System.getProperty("user.dir")+"\\reports\\screenshots\\"+testcaseName+dt+".png";
		FileUtils.copyFile(srcFile, new File(filepath));
		return filepath;

		}
	public static void scroll()
	{
		 JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,400)", "");
	}
		
	public static ExtentReports report;
	public ExtentTest test;

	public static String path = System.getProperty("user.dir") + "\\Reports\\index.html";
	public synchronized static ExtentReports testReport() {

		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		//report = new ExtentReports();
		//report.attachReporter(reporter);
		//report.setSystemInfo("Tester", "Suhali D");
		//extent.getStats();
		//extent.generateRecentStatus();
		return report;

	}
/*public static void screenShot(String path, String testcaseName, WebDriver driver) throws Exception {
			TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des = new File(path);
		FileUtils.copyFile(src, des);*/
	}
	
	

