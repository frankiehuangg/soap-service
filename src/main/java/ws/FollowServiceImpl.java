package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "ws.FollowsService")
public class FollowsServiceImpl implements FollowsService {
    @WebMethod
    public String test() {
        return "OK";
    }
}
