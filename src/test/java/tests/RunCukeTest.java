package tests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/tests/features/",
        glue = {"tests.steps"},
        plugin = {"json:target/cucumber-reports/cucumber.json"},
        format = {
                "html:target/cucumber/ryanair.html",
                "json:target/cucumber/cucumber.json",
                "pretty"
        }
)

public class RunCukeTest {
}
