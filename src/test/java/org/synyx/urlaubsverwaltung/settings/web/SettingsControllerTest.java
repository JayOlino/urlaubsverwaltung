package org.synyx.urlaubsverwaltung.settings.web;

import org.junit.Assert;
import org.junit.Test;
import org.synyx.urlaubsverwaltung.web.ControllerConstants;

public class SettingsControllerTest {

    @Test
    public void getAuthorizedRedirectUrl() {
        SettingsController cut = new SettingsController(null, null, null, null, "unknown");

        String actual = cut.getAuthorizedRedirectUrl("http://localhost:8080/web/settings", ControllerConstants.OATUH_REDIRECT_REL);
        String expected = "http://localhost:8080/web" + ControllerConstants.OATUH_REDIRECT_REL;
        Assert.assertEquals(expected, actual);
    }
}
