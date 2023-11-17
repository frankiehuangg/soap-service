package repository;

import db.DBInstance;
import db.DBInstanceImpl;
import model.Notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepository extends BaseRepository<Notification> {
    private static NotificationRepository instance;

    protected NotificationRepository(DBInstance db, String tableName) {
        super(db, tableName);
    }

    public static NotificationRepository getInstance() {
        if (instance == null) {
            instance = new NotificationRepository(
                    DBInstanceImpl.getInstance(),
                    "notifications"
            );
        }

        return instance;
    }

    @Override
    public List<Notification> findAll() throws SQLException {
        List<Notification> result = new ArrayList<>();

        String query = "SELECT * FROM `notifications` ;";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.constructFromSQL(rs);
                result.add(notification);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public List<Notification> findAllNotificationsByUserId(int userId) throws SQLException {
        List<Notification> result = new ArrayList<>();

        String query = "SELECT * FROM `notifications` WHERE user_id = ? ;";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.constructFromSQL(rs);
                result.add(notification);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public List<Notification> findAllUnreadNotificationsByUserId(int userId) throws SQLException {
        List<Notification> result = new ArrayList<>();

        String query = "SELECT * FROM `notifications` WHERE user_id = ? AND status = \"UNREAD\" ;";

        PreparedStatement statement = null;

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.constructFromSQL(rs);
                result.add(notification);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public Notification findById(int notificationId) throws SQLException {
        String query = "SELECT * FROM `notifications` WHERE notification_id = ? ;";

        PreparedStatement statement = null;

        try {
            Notification result = new Notification();

            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, notificationId);

            ResultSet rs = statement.executeQuery();

            rs.next();

            result.constructFromSQL(rs);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Notification create(Notification notification) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO `notifications` (user_id, notification_content, status) VALUES ( ? , ? , ? );";

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, notification.getUserId());
            statement.setString(2, notification.getNotificationContent());
            statement.setString(3, String.valueOf(notification.getStatus()));

            int rs = statement.executeUpdate();

            if (rs > 0) {
                return this.findById(notification.getNotificationId());
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Notification setToRead(int notificationId) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE `notifications` SET status = \"READ\" WHERE notification_id = ? ; ";

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, notificationId);

            int rs = statement.executeUpdate();

            if (rs > 0) {
                return this.findById(notificationId);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public int deleteNotificationByNotificationId(int notificationId) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM `notifications` WHERE notification_id = ? ;";

        try {
            statement = this.db.getConnection().prepareStatement(query);

            statement.setInt(1, notificationId);

            return statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
}
