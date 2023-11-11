package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "ws.HealthService")
public class HealthServiceImpl implements HealthService {
    @WebMethod
    public String test() {
        return "OK";
    }
}
