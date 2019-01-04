package tests;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/Regression_Job_Stats/resources/features/myfeature.feature"
        ,glue = {"steps"}
        )
public class MyTest {
}
