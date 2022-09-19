package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";	
	    ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	    reporter.config().setReportName("Web Automatio Results");
	    reporter.config().setDocumentTitle("Test Results");
	    reporter.config().setTheme(Theme.DARK);
	    
	    
	    ExtentReports extent=new ExtentReports();
	    extent.attachReporter(reporter);
	    extent.setSystemInfo("Tester","Shivam Nagpal");
	    return extent;
	}

}
