package ws;

import model.Follow;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface FollowService {
    @WebMethod
    public Follow createFollow(int followingUserId, int followedUserId);

    @WebMethod
    public boolean deleteFollow(int followingUserId, int followedUserId);

    @WebMethod
    public List<Follow> getFollowFromFollowingUserId(int followingUserId);

    @WebMethod
    public List<Follow> getFollowFromFollowedUserId(int followedUserId);

    @WebMethod
    public boolean checkUserFollowing(int followingUserId, int followedUserId);
}
