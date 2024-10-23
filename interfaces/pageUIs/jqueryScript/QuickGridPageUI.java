package pageUIs.jqueryScript;

public class QuickGridPageUI {
    public static final String DYNAMIC_PAGING_LINK = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String DYNAMIC_HEADER_TEXTBOX_BY_NAME = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
    public static final String DYNAMIC_DATA_ROW = "xpath=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
    public static final String DYNAMIC_ACTION_BUTTON_BY_COUNTRY = "XPATH=//td[@data-key='country' and text()='%s']/preceding-sibling::td/button[contains(@class,'%s')]";
    public static final String DYNAMIC_ALL_DATA_OF_COLUMN_BY_NAME = "css=td[data-key='%s']";
}
