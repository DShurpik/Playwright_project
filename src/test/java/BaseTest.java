import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private Browser browser;
    protected Page page;
    private BrowserContext context;

    @BeforeMethod
    public void setUp() {
        /**browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));*/

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();

        options.setSlowMo(1000)
                .setTimeout(10000) // Works like implicitWait in selenium
                .setChannel("chrome")
                .setHeadless(false);


        Browser browser = Playwright
                .create()
                .chromium()
                .launch(options);

        context = browser.newContext();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(false));

        page = context.newPage();
        page.setViewportSize(1366, 768);
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
    }

}
