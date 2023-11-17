package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Logging extends BaseModel {
    private String description;
    private String IP;
    private String endpoint;
    private String requestLocation;
    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.description = rs.getString("description");
        this.IP = rs.getString("IP");
        this.endpoint = rs.getString("endpoint");
        this.requestLocation = rs.getTimestamp("request_location").toString();
    }
}
