package clients;

import utils.ConfigHandler;
import utils.HttpClientWrapper;

import java.util.HashMap;
import java.util.Map;

public class AppClient {
    private static AppClient instance;
    private final String APP_URL;

    private AppClient() {
        String APP_URL_KEY = "app.url";
        this.APP_URL = ConfigHandler.getInstance().get(APP_URL_KEY);
    }

    public static AppClient getInstance() {
        if (instance == null) {
            instance = new AppClient();
        }
        return instance;
    }

    public void testGet() {
        System.out.println(new HttpClientWrapper(this.APP_URL + "/api").get());
    }

    public void testPost() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "user");
        params.put("password", "pass");
        System.out.println(new HttpClientWrapper(this.APP_URL + "/api").post(params));
    }
}
