package ws;

import model.Block;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface BlockService {
    @WebMethod
    public Block createBlock(int blockingUserId, int blockedUserId);

    @WebMethod
    public boolean deleteBlock(int blockingUserId, int blockedUserId);

    @WebMethod
    public List<Block> getBlockFromBlockedUserId(int blockedUserId);

    @WebMethod
    public List<Block> getBlockFromBlockingUserId(int blockingUserId);

    @WebMethod
    public boolean checkUserBlocking(int blockingUserId, int blockedUserId);
}
