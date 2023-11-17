package ws;

import model.Notification;
import repository.NotificationRepository;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ws.NotificationService")
public class NotificationServiceImpl extends BaseService implements NotificationService {
    @WebMethod
    public Notification createNotification(int userId, String notificationContent) {
        try {
            this.validateAndRecord(userId, notificationContent);

            Notification model = new Notification(userId, notificationContent, "UNREAD");

            return NotificationRepository.getInstance().create(model);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public List<Notification> getAllNotifications() {
        try {
            this.validateAndRecord();

            return NotificationRepository.getInstance().findAll();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public List<Notification> getNotificationFromUserId(int userId) {
        try {
            this.validateAndRecord(userId);

            return NotificationRepository.getInstance().findAllNotificationsByUserId(userId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public List<Notification> getUnreadNotificationsFromUserId(int userId) {
        try {
            this.validateAndRecord(userId);

            return NotificationRepository.getInstance().findAllUnreadNotificationsByUserId(userId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod
    public Notification setNotificationToRead(int notificationId) {
        try {
            this.validateAndRecord(notificationId);

            return NotificationRepository.getInstance().setToRead(notificationId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
