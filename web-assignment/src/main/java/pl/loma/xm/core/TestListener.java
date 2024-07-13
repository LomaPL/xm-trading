package pl.loma.xm.core;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

@Slf4j
public final class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error("---- TEST FAILED ----", tr.getThrowable());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("---- TEST PASSED -----");
    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        log.error("---- TEST CONFIGURATION FAILED ----", itr.getThrowable());
    }

}
