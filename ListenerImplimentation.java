package CommonUtils;

import org.testng.ITestContext;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplimentation implements ITestListener {
	
	ExtentReports report;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is started");
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"Testscript passed", true);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is passed");
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"Testscript passed", true);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is fail");
		String message = result.getThrowable().toString();
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"Testscript fail"+message, true);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is skipped");
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"Testscript passed", true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("to fined the execution");
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"Testscript passed", true);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"Testscript passed", true);

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
//		System.out.println("Test start the execution");
	//	Reporter.log("TEstscript start", true);
		
		// Create the object of ExtentSparkReporter
		// Use  ExtentSparkReporter class just to configure extent report
		JavaUtil jutil = new JavaUtil();
		ExtentSparkReporter reporter = new ExtentSparkReporter("./extentreport/report"+jutil.getRandomNumber()+".html");
		reporter.config().setDocumentTitle("VtigerCRM");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setReportName("Organization");
		
	//	Use ExtentReports to generate extentreport
	    report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("OS", "Window");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("chromeversion", "120");
		report.setSystemInfo("Author", "Omkar");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	//	Reporter.log("TEstscript finesed", true);
		
		report.flush();
	}

}