import utils.ConfigHandler;

import javax.xml.ws.Endpoint;

public class App {
    private final static String SERVER_HOST_KEY = "server.host";
    private final static String SERVER_PORT_KEY = "server.port";

    public static void main(String[] args) {
        try {
            ConfigHandler ch = ConfigHandler.getInstance();
            String host = ch.get(SERVER_HOST_KEY);
            String port = ch.get(SERVER_PORT_KEY);

            System.out.println("Starting server at " + host + ":" + port);
            Endpoint.publish(host + ":" + port + "/health", new ws.HealthServiceImpl());
            Endpoint.publish(host + ":" + port + "/blocks", new ws.BlockServiceImpl());
            Endpoint.publish(host + ":" + port + "/follows", new ws.FollowServiceImpl());
            Endpoint.publish(host + ":" + port + "/notifications", new ws.NotificationServiceImpl());
            System.out.println("Server started at " + host + ":" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
