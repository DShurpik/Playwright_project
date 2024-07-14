package pages;

import basePages.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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
        return outputName.textContent().contains(expectedName);
    }
}
