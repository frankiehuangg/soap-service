package repository;

import db.DBInstance;
import db.DBInstanceImpl;
import model.Follow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowRepository extends BaseRepository<Follow> {
    private static FollowRepository instance;

    protected FollowRepository(DBInstance db, String tableName) {
        super(db, tableName);
    }

    public static FollowRepository getInstance() {
        if (instance == null) {
            instance = new FollowRepository(
                    DBInstanceImpl.getInstance(),
                    "follows"
            );
        }

        return instance;
    }

    @Override
    public List<Follow> findAll() throws SQLException {
        List<Follow> result = new ArrayList<>();

        String query = "SELECT * FROM `follows`";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Follow follow = new Follow();
                follow.constructFromSQL(rs);
                result.add(follow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Follow> findAllByFollowingUserId(int followingUserId) throws SQLException {
        List<Follow> result = new ArrayList<>();

        String query = "SELECT * FROM `follows` WHERE following_user_id = ? ;";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, followingUserId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Follow follow = new Follow();
                follow.constructFromSQL(rs);
                result.add(follow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Follow> findAllByFollowedUserId(int followedUserId) throws SQLException {
        List<Follow> result = new ArrayList<>();

        String query = "SELECT * FROM `follows` WHERE followed_user_id = ? ; ";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, followedUserId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Follow follow = new Follow();
                follow.constructFromSQL(rs);
                result.add(follow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;
    }

    public Follow findById(int followingUserId, int followedUserId) throws SQLException {
        String query = "SELECT * FROM `follows` WHERE following_user_id = ? AND followed_user_id = ? ; ";

        PreparedStatement statement = null;

        try {
            Follow result = new Follow();

            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, followingUserId);
            statement.setInt(2, followedUserId);

            ResultSet rs = statement.executeQuery();

            rs.next();

            result.constructFromSQL(rs);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Follow create(Follow follow) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO `follows` (following_user_id, followed_user_id) VALUES ( ? , ? ) ;";

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, follow.getFollowingUserId());
            statement.setInt(2, follow.getFollowedUserId());

            int rs = statement.executeUpdate();

            if (rs > 0) {
                return this.findById(follow.getFollowingUserId(), follow.getFollowedUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(Follow follow) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM `follows` WHERE following_user_id = ? AND followed_user_id = ? ;" ;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, follow.getFollowingUserId());
            statement.setInt(2, follow.getFollowedUserId());

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
