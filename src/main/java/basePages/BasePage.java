package basePages;

import com.microsoft.playwright.Page;
import pages.Navigation;

public abstract class BasePage {
    private final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    public void open(String url) {
        getPage().navigate(url);
    }

    public void navigateTo(Navigation menuName) {
        getPage().getByText(menuName.name()).click();
    }

    public void navigateToMenu(Navigation menuItem) {
        getPage().getByText(menuItem.getItem()).click();
    }
}
