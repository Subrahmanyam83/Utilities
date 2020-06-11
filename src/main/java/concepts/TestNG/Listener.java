package concepts.TestNG;

import org.testng.*;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Case Started");
        System.out.println();
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Started");
        System.out.println();
    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void onStart(ISuite suite) {
        System.out.println("SUITE STARTED");
        System.out.println();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("SUITE FINISHED");
    }
}
