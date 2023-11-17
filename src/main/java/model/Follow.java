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
public class Follow extends BaseModel {
    private Integer followingUserId;
    private Integer followedUserId;

    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.followingUserId = rs.getInt("following_user_id");
        this.followedUserId  = rs.getInt("followed_user_id");
    }
}
