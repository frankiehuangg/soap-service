package repository;

import db.DBInstance;
import db.DBInstanceImpl;
import model.Block;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlockRepository extends BaseRepository<Block> {
    private static BlockRepository instance;

    protected BlockRepository(DBInstance db, String tableName) {
        super(db, tableName);
    }

    public static BlockRepository getInstance() {
        if (instance == null) {
            instance = new BlockRepository(
                    DBInstanceImpl.getInstance(),
                    "blocks"
            );
        }

        return instance;
    }

    @Override
    public List<Block> findAll() throws SQLException {
        List<Block> result = new ArrayList<>();

        String query = "SELECT * FROM `blocks` ;";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Block block = new Block();
                block.constructFromSQL(rs);
                result.add(block);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Block> findAllByBlockingUserId(int blockingUserId) throws SQLException {
        List<Block> result = new ArrayList<>();

        String query = "SELECT * FROM `blocks` WHERE blocking_user_id = ? ;";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, blockingUserId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Block block = new Block();
                block.constructFromSQL(rs);
                result.add(block);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Block> findAllByBlockedUserId(int blockedUserId) throws SQLException {
        List<Block> result = new ArrayList<>();

        String query = "SELECT * FROM `blocks` WHERE blocked_user_id = ? ;";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, blockedUserId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Block block = new Block();
                block.constructFromSQL(rs);
                result.add(block);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Block findById(int blockingUserId, int blockedUserId) throws SQLException {
        String query = "SELECT * FROM  `blocks` WHERE blocking_user_id = ? AND blocked_user_id = ?";

        PreparedStatement statement = null;

        try {
            Block result = new Block();

            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, blockingUserId);
            statement.setInt(2, blockedUserId);

            ResultSet rs = statement.executeQuery();

            rs.next();

            result.constructFromSQL(rs);

            return result;
        } catch (Exception e) {
            e.printStackTrace();;
        }

        return null;
    }

    @Override
    public Block create(Block block) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO  `blocks` (blocking_user_id, blocked_user_id) VALUES ( ? , ? ) ;";

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, block.getBlockingUserId());
            statement.setInt(2, block.getBlockedUserId());

            int rs = statement.executeUpdate();

            if (rs > 0) {
                return this.findById(block.getBlockingUserId(), block.getBlockedUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(Block block) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM `blocks` WHERE blocking_user_id = ? AND blocked_user_id = ?";

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, block.getBlockingUserId());
            statement.setInt(2, block.getBlockedUserId());

            int rs = statement.executeUpdate();

            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
