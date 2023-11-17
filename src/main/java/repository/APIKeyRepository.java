package repository;

import db.DBInstance;
import db.DBInstanceImpl;
import model.APIKey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class APIKeyRepository extends BaseRepository<APIKey> {
    private static APIKeyRepository instance;

    protected APIKeyRepository(DBInstance db, String tableName) {
        super(db, tableName);
    }

    public static APIKeyRepository getInstance() {
        if (instance == null) {
            instance = new APIKeyRepository(
                    DBInstanceImpl.getInstance(),
                    "api_keys"
            );
        }

        return instance;
    }

    @Override
    public List<APIKey> findAll() throws SQLException {
        List<APIKey> result = new ArrayList<>();

        Statement stmt = this.db.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tableName);
        while (rs.next()) {
            APIKey subscription = new APIKey();
            subscription.constructFromSQL(rs);
            result.add(subscription);
        }

        return result;
    }
}
