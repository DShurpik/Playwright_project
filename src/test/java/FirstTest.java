import basePages.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TextBoxPage;

import static pages.Navigation.*;

public class FirstTest extends BaseTest {

    @Test
    public void test1() {
        TextBoxPage textBoxPage = new TextBoxPage(getPage());

        textBoxPage.open("http://85.192.34.140:8081/");
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField("D S")
                .fillEmailField("ds@gmail.com")
                .fillCurrentAddressField("Minsk")
                .fillPermanentAddressField("Minsk")
                .clickSubmitBtn();

        Assert.assertTrue(textBoxPage.outputFieldIsVisible());
        Assert.assertTrue(textBoxPage.getOutputName("D S"));
    }
}
