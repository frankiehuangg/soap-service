package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface FollowsService {
    @WebMethod
    public String test();
}
