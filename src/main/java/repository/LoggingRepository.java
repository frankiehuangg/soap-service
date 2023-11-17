package repository;

import db.DBInstance;
import db.DBInstanceImpl;
import model.Logging;

import java.sql.SQLException;
import java.sql.Statement;

public class LoggingRepository extends BaseRepository {
    private static LoggingRepository instance;

    protected LoggingRepository(DBInstance db, String tableName) {
        super(db, tableName);
    }

    public static LoggingRepository getInstance() {
        if (instance == null) {
            instance = new LoggingRepository(
                    DBInstanceImpl.getInstance(),
                    "logging"
            );
        }

        return instance;
    }

    public Logging create(Logging log) throws SQLException {
        try {
            Statement stmt = this.db.getConnection().createStatement();
            int rs = stmt.executeUpdate("INSERT INTO " + this.tableName + " (description, IP, endpoint, requested_at) VALUES ('" + log.getDescription() + "', '" + log.getIP() + "', '" + log.getEndpoint() + "', '" + log.getRequestLocation() + "')");
            if (rs > 0) {
                return log;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
