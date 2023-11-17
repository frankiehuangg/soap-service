package ws;

import model.Follow;
import repository.FollowRepository;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ws.FollowService")
public class FollowServiceImpl implements FollowService {
    @WebMethod
    public Follow createFollow(int followingUserId, int followedUserId) {
        try {
            Follow model = new Follow(followingUserId, followedUserId);

            return FollowRepository.getInstance().create(model);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public boolean deleteFollow(int followingUserId, int followedUserId) {
        try {
            Follow model = new Follow(followingUserId, followedUserId);

            return FollowRepository.getInstance().delete(model);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    @WebMethod
    public List<Follow> getFollowFromFollowingUserId(int followingUserId) {
        try {
            return FollowRepository.getInstance().findAllByFollowingUserId(followingUserId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public List<Follow> getFollowFromFollowedUserId(int followedUserId) {
        try {
            return FollowRepository.getInstance().findAllByFollowedUserId(followedUserId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public boolean checkUserFollowing(int followingUserId, int followedUserId) {
        try {
            if (FollowRepository.getInstance().findById(followingUserId, followedUserId).equals(null)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}
