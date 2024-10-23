package pageUIs.jqueryScript;

public class AppendGridPageUI {
    public static final String LOAD_DATA_BUTTON = "css=button#load";
    public static final String DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER = "xpath=//th[text()='%s']/preceding-sibling::th";
    public static final String DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX = "xpath=//tr[%s]/td[%s]/input";
    public static final String DYNAMIC_COUNTRY_DROPDOWN_BY_ROW_INDEX = "xpath=//tr[%s]/td[4]//select";
    public static final String DYNAMIC_NPO_CHECKBOX_BY_ROW_INDEX = "xpath=//tr[%s]/td[5]//input";
    public static final String DYNAMIC_ACTION_BUTTON_BY_NAME = "XPATH=//tr[%s]/td//button[starts-with(@title,'%s')]";
}
