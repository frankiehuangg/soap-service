package ws;

import model.Notification;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface NotificationService {
    @WebMethod
    public Notification createNotification(int userId, String notificationContent);

    @WebMethod
    public List<Notification> getNotificationFromUserId(int userId);

    @WebMethod
    public List<Notification> getUnreadNotificationsFromUserId(int userId);

    @WebMethod
    public Notification setNotificationToRead(int notificationId);
}
