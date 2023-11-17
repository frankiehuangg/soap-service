package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HealthService {
    @WebMethod
    public String checkHealth();
}
