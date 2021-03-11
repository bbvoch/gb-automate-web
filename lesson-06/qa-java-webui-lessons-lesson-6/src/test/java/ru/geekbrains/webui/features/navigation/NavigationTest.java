package ru.geekbrains.webui.features.navigation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.webui.base.BaseUITest;
import ru.geekbrains.webui.common.Configuration;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.pages.LoginPage;

public class NavigationTest extends BaseUITest  {

    @ParameterizedTest
    @MethodSource("navigationTabProvider")
    public void checkMainNavigationTabsTest(NavigationBarTabs barTab) {
        new LoginPage(driver)
            .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
            .getPageNavigation()
            .checkTabVisibility(barTab);

    }

    static NavigationBarTabs[] navigationTabProvider() {
        return NavigationBarTabs.values();
    }

}
