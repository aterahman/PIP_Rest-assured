package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;

public class ExtentTestGenerator
{
    public static ExtentTest test;
    public static ExtentReports report;

    @BeforeTest
    public static void startreport()
    {
        String date = new SimpleDateFormat("dd-MM-YYYY-HH-mm-ss").format(new java.util.Date());
        ExtentSparkReporter spark = new ExtentSparkReporter(date+"Spark.html");
        report = new ExtentReports();
        report.attachReporter(spark);
        test = report.createTest("ExtentReport");
    }
    @AfterTest
    public static void endTest()
    {

        report.flush();
    }

}
