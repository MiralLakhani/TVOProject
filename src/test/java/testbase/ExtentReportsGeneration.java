package testbase;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.Utility;


public class ExtentReportsGeneration {

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest node;
	
	public static Logger log = Logger.getLogger(ExtentReportsGeneration.class);
	//ExtentReports extent;
	/*
	 * Initialize the extent report
	 * 
	 * @param String exeType
	 */
	public static void initializeReport(String exeType)
	{
		htmlReporter =  new ExtentSparkReporter("./Reports/extentReport.html");
		extent = new ExtentReports();
		//htmlReporter.config().setAutoCreateRelativePathMedia(true);
		//htmlReporter.config().setCSS("css-string");
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("page title");
		htmlReporter.config().setEncoding("utf-8");
		//htmlReporter.config().setJS("js-string");
		//htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setReportName(exeType);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");	
	}
	
	public static void addNode(String str) throws IOException
	{
		node = test.createNode(str);
		//node.addScreenCaptureFromBase64String(Utility.attacheSceenshot());
		test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.attacheSceenshot()+"jpg").build());
	}
	
	public static void addTestPass(String str) throws IOException
	{
		test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.attacheSceenshot()+"jpg").build());
	}
	public static void addTestFail(String str) throws IOException
	{
		test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.attacheSceenshot()+"jpg").build());
	}
	
	public static void addNodeFail(String str) throws IOException
	{
		node = test.createNode(str);
		node.fail("Step Faile");
		node.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.attacheSceenshot()+"jpg").build());

	}
	
	public static void addTest(String str) throws IOException
	{
		test=extent.createTest(str);
	}
	
	/*
	 * completeReport method - Flush method to generate the complete report
	 */
	public static void completeReport()
	{
		extent.flush();
	}
}

