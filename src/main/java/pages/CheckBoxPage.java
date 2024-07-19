package pages;

import basePages.BasePage;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CheckBoxPage extends BasePage {

    private final Locator expandAllBtn = getPage().locator("button[aria-label='Expand all']");
    private final Locator items = getPage().locator("//span[@class='rct-title']");
    private final Locator itemsChecked = getPage().locator("svg.rct-icon.rct-icon-check");
    private final Locator outputResult = getPage().locator("#result .text-success");

    //private final By titleItemBy = By.xpath(".//ancestor::span[@class='rct-text']");



    public CheckBoxPage(Page page) {
        super(page);
    }

    public CheckBoxPage clickExpandAllBtn() {
        expandAllBtn.click();
        return this;
    }

    public void clickRandomItem() {
        int itemCount = items.count();
        Random random = new Random();
        int randomIndex = random.nextInt(itemCount);
        items.nth(randomIndex).click();
    }

    public String getCheckedCheckBoxes() {

        List<String> allTexts = itemsChecked.first().locator("../../../..//span[contains(@class, 'rct-title')]").allInnerTexts();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : allTexts) {
            stringBuilder.append(s.toLowerCase().replaceAll(" ", "")
                    .replaceAll("doc", "")
                    .replaceAll("\\.", "")).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public String getOutputResult() {
        List<String> allTexts = outputResult.allTextContents();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : allTexts) {
            stringBuilder.append(s.toLowerCase().replaceAll(" ", "")
                    .replaceAll("doc", "")
                    .replaceAll("\\.", "")).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    /**
    public void clickCheckboxName(String checkboxName) {
        driver.findElement(By.xpath("//span[text()='" + checkboxName + "']")).click();
    }

    public String getExpectedResult(String checkboxName) {
        StringBuilder str = new StringBuilder();
        str.append(checkboxName.toLowerCase()
                .replaceAll("doc", "")
                .replaceAll("\\.", "")
                .replaceAll(" ", ""));
        return str.toString();
    }
     **/


}
