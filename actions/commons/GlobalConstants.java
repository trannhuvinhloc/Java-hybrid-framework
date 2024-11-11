package commons;


import java.io.File;

public class GlobalConstants {
    //System info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String SEPARATOR = File.separator;

    //App info User
    public static final String LOCAL_USER_URL = "http://demo.nopcommerce";
    public static final String LIVE_USER_URL = "https://demo.nopcommerce.com";
    public static final String USER_PASSWORD = "123456";

    //App info Admin
    public static final String LOCAL_ADMIN_URL = "http://demo.nopcommerce/admin";
    public static final String LIVE_ADMIN_URL = "https://admin-demo.nopcommerce.com";

    public static final String ADMIN_EMAL = "admin@gmail.com";
    public static final String ADMIN_PASSWORD = ADMIN_EMAL;

    //Wait time
    public static final long SHORT_TIMEOUT = 10;
    public static final long LONG_TIMEOUT = 20;
    public static final long SHORT_SLEEP = 1;
    public static final long LONG_SLEEP = 3;

    //Retry case failed
    public static final int RETRY_NUMBER = 3;

    //Download - Upload folder
    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + SEPARATOR + "downloadFiles" + SEPARATOR;

    //Browser Log and Extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + SEPARATOR + "browserLogs" + SEPARATOR;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + SEPARATOR + "browserExtensions" + SEPARATOR;

    //HTML Report folder
    public static final String EXTENT_PATH = PROJECT_PATH + SEPARATOR + "htmlExtent" + SEPARATOR;
    public static final String ALLURE_PATH = PROJECT_PATH + SEPARATOR + "htmlAllure" + SEPARATOR;

    //Data test - Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + SEPARATOR + "testDatas" + SEPARATOR;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + SEPARATOR + "environmentConfig" + SEPARATOR;
}
