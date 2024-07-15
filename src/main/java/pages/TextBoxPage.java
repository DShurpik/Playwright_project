package pages;

import basePages.BasePage;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextBoxPage extends BasePage {

    private final Locator fullNameField = getPage().locator("[id=userName]");
    private final Locator emailField = getPage().locator("[id=userEmail]");
    private final Locator currentAddressField = getPage().locator("[id=currentAddress]");
    private final Locator permanentAddressField = getPage().locator("[id=permanentAddress]");
    private final Locator submitBtn = getPage().getByText("Submit");
    private final Locator outPutField = getPage().locator("[id=output]");
    private final Locator outputName = getPage().locator("[id=name]");

    public TextBoxPage(Page page) {
        super(page);
    }

    public TextBoxPage fillFullNameField(String fullName) {
        fullNameField.fill(fullName);
        return this;
    }

    public TextBoxPage fillEmailField(String email) {
        emailField.fill(email);
        return this;
    }

    public TextBoxPage fillCurrentAddressField(String address) {
        currentAddressField.fill(address);
        return this;
    }

    public TextBoxPage fillPermanentAddressField(String permanentAddress) {
        permanentAddressField.fill(permanentAddress);
        return this;
    }

    public TextBoxPage clickSubmitBtn() {
        submitBtn.click();
        return this;
    }

    public boolean outputFieldIsVisible() {
        return outPutField.isVisible();
    }

    public boolean getOutputName(String expectedName) {
        return outPutField.textContent().contains(expectedName);
    }

    public Map<String, String> getResult() {
        List<ElementHandle> elements = getPage().querySelectorAll("#output .border" + " p");

        Map<String, String> resultMap = new HashMap<>();
        for (ElementHandle element : elements) {
            String text = element.textContent().trim();
            String[] parts = text.split(":", 2); // Split only at the first colon
            if (parts.length == 2) {
                resultMap.put(parts[0].trim(), parts[1].trim());
            }
        }
        return resultMap;
    }
}
