package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Block extends BaseModel {
    private Integer blockingUserId;
    private Integer blockedUserId;

    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.blockingUserId = rs.getInt("blocking_user_id");
        this.blockedUserId  = rs.getInt("blocked_user_id");
    }
}
