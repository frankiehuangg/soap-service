package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Notification extends BaseModel {
    private Integer notificationId;
    private Integer userId;
    private String notificationContent;
    private NotificationStatus status;

    public Notification(int userId, String notificationContent, String status) {
        this.userId = userId;
        this.notificationContent = notificationContent;
        this.status = NotificationStatus.fromStatusCode(status);
    }

    @XmlEnum(String.class)
    public enum NotificationStatus {
        UNREAD,
        READ;

        public static NotificationStatus fromStatusCode(String value) {
            for (NotificationStatus status : NotificationStatus.values()) {
                if (status.toString().equalsIgnoreCase(value)) {
                    return status;
                }
            }

            return null;
        }
    }

    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.notificationId = rs.getInt("notification_id");
        this.userId = rs.getInt("user_id");
        this.notificationContent = rs.getString("notification_content");
        this.status = NotificationStatus.fromStatusCode(rs.getString("status"));
    }
}
