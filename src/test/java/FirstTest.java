import basePages.BaseTest;
import dataGenerator.Generator;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CheckBoxPage;
import pages.TextBoxPage;

import static pages.Navigation.*;

public class FirstTest extends BaseTest {

    @Parameters({"fullname", "email", "currentAddress", "permanentAddress"})
    @Test
    public void fillingTextForm(String fullName, String email, String currentAddress, String permanentAddress) {
        TextBoxPage textBoxPage = new TextBoxPage(getPage());

        textBoxPage.open("http://85.192.34.140:8081/");
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(fullName)
                .fillEmailField(email)
                .fillCurrentAddressField(currentAddress)
                .fillPermanentAddressField(permanentAddress)
                .clickSubmitBtn();

        Assert.assertTrue(textBoxPage.outputFieldIsVisible());
        Assert.assertTrue(textBoxPage.getOutputName("D S"));
        Assert.assertTrue(textBoxPage.getOutputName("Minsk"));
    }

    @Test(description = "Have used a faker for filling the user's data")
    public void fillFieldsByFaker() {
        Generator user = new Generator();
        TextBoxPage textBoxPage = new TextBoxPage(getPage());

        textBoxPage.open("http://85.192.34.140:8081/");
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(user.getFullName())
                .fillEmailField(user.getEmail())
                .fillCurrentAddressField(user.getCurrentAddress())
                .fillPermanentAddressField(user.getPermanentAddress())
                .clickSubmitBtn();

        Assert.assertEquals(user.getFullName(), textBoxPage.getResult().get("Name"));
        Assert.assertEquals(user.getEmail(), textBoxPage.getResult().get("Email"));
        Assert.assertEquals(user.getCurrentAddress(), textBoxPage.getResult().get("Current Address"));
        Assert.assertEquals(user.getPermanentAddress(), textBoxPage.getResult().get("Permanent Address"));
    }

    @Test(description = "Click checkbox randomly, and check result")
    public void clickCheckboxRandomly() {
        CheckBoxPage checkBoxPage = new CheckBoxPage(getPage());

        checkBoxPage.open("http://85.192.34.140:8081/");
        checkBoxPage.navigateTo(ELEMENTS);
        checkBoxPage.navigateToMenu(CHECK_BOX);

        checkBoxPage.clickExpandAllBtn();
        checkBoxPage.clickRandomItem();

        String actualResult = checkBoxPage.getCheckedCheckBoxes();
        String expectedResult = checkBoxPage.getOutputResult();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
