package stepDefinitions;

import dto.TestData;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Steps {
    private static final Logger logger = LoggerFactory.getLogger(Steps.class);
    List<TestData> list1 = new ArrayList<>();
    List<TestData> list2 = new ArrayList<>();
    boolean result = false;

    @DataTableType
    public TestData defineTestData(Map<String, String> row) {
        TestData data = new TestData();
        data.setName(row.get("name"));
        data.setPrice(Double.parseDouble(row.get("price")));
        data.setCategory(row.get("category"));
        return data;
    }

    @Given("I have the following items in the first list:")
    public void createList1(List<TestData> items) {
        for (TestData testData : items) {
            logger.info("Adding to the first list: name: {}, price: {}, category: {}", testData.getName(), testData.getPrice(), testData.getCategory());
            list1.add(testData);
        }
        logger.info("The first list contains the following items: {}", list1);
    }

    @Given("I have the following items in the second list:")
    public void createList2(List<TestData> items) {
        for (TestData testData : items) {
            logger.info("Adding to the second list: name: {}, price: {}, category: {}", testData.getName(), testData.getPrice(), testData.getCategory());
            list2.add(testData);
        }
        logger.info("The second list contains the following items: {}", list2);
    }

    @Then("I compare both lists")
    public void compareLists() {
        logger.info("Comparing the two lists...");
        assertThat(list1).containsExactlyInAnyOrderElementsOf(list2);
        logger.info("The two lists are the same!!!");
    }

}
