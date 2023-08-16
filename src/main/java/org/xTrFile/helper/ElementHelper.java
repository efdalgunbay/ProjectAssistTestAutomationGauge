package org.xTrFile.helper;

import org.xTrFile.model.ElementInfo;
import org.openqa.selenium.By;

public class ElementHelper {

    public static By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getByType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getByType().equals("id")) {
            by = By.id(elementInfo.getValue());
        }else if (elementInfo.getByType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getByType().equals("class")) {
            by = By.className(elementInfo.getValue());
        } else if (elementInfo.getByType().equals("name")) {
            by = By.name(elementInfo.getValue());
        }
        return by;
    }
}
