package petSitter.tests_Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = "src/test/resources/features",
                glue = "petSitter.tests_Cucumber.stepsDefinitions",
                plugin={"pretty", "html:target/cucumber-reports.html"}
               // tags= "@Login or @InvalidPassword"


        )



public class TestRunner {
}
