package ws;

import model.Block;
import repository.BlockRepository;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ws.BlockService")
public class BlockServiceImpl extends BaseService implements BlockService {
    @WebMethod
    public Block createBlock(int blockingUserId, int blockedUserId) {
        try {
            Block model = new Block(blockingUserId, blockedUserId);

            return BlockRepository.getInstance().create(model);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public boolean deleteBlock(int blockingUserId, int blockedUserId) {
        try {
            Block model = new Block(blockingUserId, blockedUserId);

            return BlockRepository.getInstance().delete(model);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    @WebMethod
    public List<Block> getBlockFromBlockingUserId(int blockingUserId) {
        try {
            return BlockRepository.getInstance().findAllByBlockingUserId(blockingUserId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public List<Block> getBlockFromBlockedUserId(int blockedUserId) {
        try {
            return BlockRepository.getInstance().findAllByBlockedUserId(blockedUserId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public boolean checkUserBlocking(int blockingUserId, int blockedUserId) {
        try {
            if (BlockRepository.getInstance().findById(blockingUserId, blockedUserId).equals(null)) {
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
