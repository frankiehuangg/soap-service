package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseModel {
    public abstract void constructFromSQL(ResultSet rs) throws SQLException;
}
