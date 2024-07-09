import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FirstTest extends BaseTest{

    @Test
    public void textBoxFillTest() {
        page.navigate("http://85.192.34.140:8081/");
        page.getByText("Elements").click();
        page.getByText("Text Box").click();
        page.fill("[id=userName]", "111111");
        page.fill("[id=userEmail]", "threadqa@gmail.com");
        page.fill("[id=currentAddress]", "somewhere");
        page.click("[id=submit]");

        //Проверяем, что после заполнения формы, появился другой блок
        Assert.assertTrue(page.isVisible("[id=output]"));
        //Проверяем, что в появившемся блоке, текст содержит предыдущий текст
        Assert.assertTrue(page.locator("[id=name]").textContent().contains("111111"));
    }

    @Test
    public void screenCreate() {
        page.navigate("http://85.192.34.140:8081/");
        page.getByText("Elements").click();
        page.getByText("Buttons").click();

        page.evaluate("() => {" +
                "    const element = document.querySelector('#rightClickBtn');" +
                "    if (element) {" +
                "        element.scrollIntoView({ behavior: 'smooth', block: 'center' });" +
                "    }" +
                "}");

        page.waitForTimeout(2000);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("src/screens/referenceScreenshot.png")));
    }

    @Test
    public void test1() throws IOException {
        page.navigate("http://85.192.34.140:8081/");
        page.getByText("Elements").click();
        page.getByText("Buttons").click();

        page.evaluate("() => {" +
                "    const element = document.querySelector('#rightClickBtn');" +
                "    if (element) {" +
                "        element.scrollIntoView({ behavior: 'smooth', block: 'center' });" +
                "    }" +
                "}");

        page.waitForTimeout(2000);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("src/screens1/1.png")));

        BufferedImage expected = ImageIO.read(new File("src/screens/referenceScreenshot.png"));
        BufferedImage actual = ImageIO.read(new File("src/screens1/1.png"));

        ImageComparison imageComparison = new ImageComparison(expected, actual);

        ImageComparisonResult result = imageComparison.compareImages();

        boolean isMatch = result.getImageComparisonState() == ImageComparisonState.MATCH;

        Assert.assertTrue(isMatch, "Screens are not same!!!");

        /**

        if (result.getImageComparisonState() == ImageComparisonState.MATCH) {
            System.out.println("Скриншоты совпадают!!!!!!!!!!!!!!");
        } else {
            System.out.println("Скриншоты не совпадают.");
            // Можно сохранить результат сравнения с отличиями
            File resultDestination = new File("comparisonResult.png");
            ImageIO.write(result.getResult(), "png", resultDestination);
            System.out.println("Результат сравнения сохранен в " + resultDestination.getAbsolutePath());
        }

         **/
    }
}
