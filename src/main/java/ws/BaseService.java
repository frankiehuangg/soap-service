package ws;

import com.sun.net.httpserver.HttpExchange;
import lombok.var;
import model.APIKey;
import model.Logging;
import repository.APIKeyRepository;
import repository.LoggingRepository;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class BaseService {
    @Resource
    WebServiceContext context;
    private final String httpExchangeKey = "com.sun.xml.internal.ws.http.exchange";

    protected  void recordClient(String endpoint, String description, String IPAddress) throws SQLException {
        System.out.println("Client " + IPAddress + " called " + endpoint + " with description " + description);

        String timestamp = new Timestamp(System.currentTimeMillis()).toString().split("\\.")[0];

        Logging model = new Logging(description, IPAddress, endpoint, timestamp);
        Logging log = LoggingRepository.getInstance().create(model);
    }

    private String getRemoteAddr() {
        MessageContext mc = context.getMessageContext();
        HttpExchange httpExchange = (HttpExchange) mc.get(this.httpExchangeKey);

        System.out.println("Remote addr: " + httpExchange.getRemoteAddress());

        return httpExchange.getRemoteAddress().toString().replace("/", "");
    }

    protected String getClientByAPIKey() throws Exception {
        MessageContext mc = context.getMessageContext();
        Map<String, Object> requestHeader = (Map) mc.get(mc.HTTP_REQUEST_HEADERS);

        System.out.println(requestHeader.get("api-key"));

        String APIKey = ((List<String>) requestHeader.get("api-key")).get(0);
        System.out.println("API key: " + APIKey);

        List<APIKey> validAPIKeys = APIKeyRepository.getInstance().findAll();
        for (APIKey validAPIKey : validAPIKeys) {
            if (validAPIKey.getKey().equals(APIKey)) {
                return validAPIKey.getClient();
            }
        }

        throw new Exception("Invalid API Key");
    }

    protected void validateAndRecord(Object ...params) throws Exception {
        String client = this.getClientByAPIKey();
        var ptrTrace = Thread.currentThread().getStackTrace()[2];
        String endpoint = ptrTrace.getClassName() + "." + ptrTrace.getMethodName();

        this.recordClient(endpoint, this.buildDesc(client, params), this.getRemoteAddr());
    }

    private String buildDesc(String client, Object ...params) {
        String paramsStr = Arrays.stream(params)
                .map(e -> "[" + e + "]")
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        return client + ":parameters{" + paramsStr + "}";
    }
}
