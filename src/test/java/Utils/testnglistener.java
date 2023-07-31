package Utils;


import com.aventstack.extentreports.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class testnglistener implements ITestListener
{

    public static final Logger log = LogManager.getLogger(testnglistener.class.getName());

    @Override
    public void onTestStart(ITestResult result)
    {
        log.info("Test has started"+result);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        log.info("PASSED TEST "+result);

        ExtentTestGenerator.test.log(Status.PASS,"Passed test"+result);

    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        log.info("FAILED TEST "+result);
        ExtentTestGenerator.test.log(Status.FAIL,"Failed Test"+result);

    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        /* compiled code */
    }

}