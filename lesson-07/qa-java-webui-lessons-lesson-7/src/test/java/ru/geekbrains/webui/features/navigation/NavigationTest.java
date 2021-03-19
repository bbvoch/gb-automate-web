package ru.geekbrains.webui.features.navigation;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.webui.base.BaseUITest;
import ru.geekbrains.webui.common.Configuration;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.pages.LoginPage;

import static ru.geekbrains.webui.common.Configuration.BASE_URL;

@Feature("Navigation")
public class NavigationTest extends BaseUITest  {

    @Disabled
    @ParameterizedTest
    @MethodSource("navigationTabProvider")
    public void checkMainNavigationTabsTest(NavigationBarTabs barTab) {
        new LoginPage(driver)
            .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
            .checkUrl(BASE_URL)
            .getPageNavigation()
            .checkTabVisibility(barTab);
    }

    static NavigationBarTabs[] navigationTabProvider() {
        return NavigationBarTabs.values();
    }

}
