package javaCode;

import org.testng.annotations.Test;

public class StringFormat {

    String delete_Afghanistan_country = "//td[@data-key='country' and text()='Afghanistan']/preceding-sibling::td[@data-key='females' and text()='384187']/preceding-sibling::td/button[contains(@class,'remove')]";
    String delete_by_country_females = "//td[@data-key='country' and text()='%s']/preceding-sibling::td[@data-key='females' and text()='%s']/preceding-sibling::td/button[contains(@class,'remove')]";
    String delete_by_country = "//td[@data-key='country' and text()='%s']/preceding-sibling::td/button[contains(@class,'remove')]";

    @Test
    public void mainTest() {
        System.out.println(delete_Afghanistan_country);

        System.out.println(String.format(delete_by_country_females, "Vietnam", "1234"));
        System.out.println(String.format("//td[@data-key='country' and text()='%s']/preceding-sibling::td[@data-key='females' and text()='%s']/preceding-sibling::td/button[contains(@class,'remove')]", "Afghanistan", "384187"));

        System.out.printf(delete_by_country_females + "%n", "Thai", "12345");
        System.out.printf("//td[@data-key='country' and text()='%s']/preceding-sibling::td[@data-key='females' and text()='%s']/preceding-sibling::td/button[contains(@class,'remove')] %n", "Afghanistan", "384187");

        String a = String.format(delete_by_country_females, "US", "01234");
        System.out.println(a);
    }

    @Test
    public void castParameter() {
        System.out.println(getLocatorByValues(delete_by_country_females, "Campuchia", String.valueOf(134)));
        System.out.println(getLocatorByValues(delete_by_country_females, "Thai", "126889"));
        System.out.println(getLocatorByValues(delete_by_country, "Uk"));
    }

    private String getLocatorByValues(String locator, String... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }
}
