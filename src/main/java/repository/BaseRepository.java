package repository;

import db.DBInstance;

import java.util.List;

public abstract class BaseRepository<BaseModel> {
    protected DBInstance db;
    protected String tableName;

    protected BaseRepository(DBInstance db, String tableName) {
        this.db = db;
        this.tableName = tableName;
    }

    public List<BaseModel> findAll() throws Exception {
        throw new Exception("Not implemented");
    }

    public BaseModel create(BaseModel model) throws Exception {
        throw new Exception("Not implemented");
    }

    public BaseModel update(BaseModel model) throws Exception {
        throw new Exception("Not implemented");
    }

    public boolean delete(BaseModel model) throws Exception {
        throw new Exception("Not implemented");
    }
}
