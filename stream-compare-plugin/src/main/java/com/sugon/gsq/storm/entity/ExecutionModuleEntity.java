package com.sugon.gsq.storm.entity;

import java.io.Serializable;
import java.sql.Connection;

public class ExecutionModuleEntity implements Serializable {

    private static final long serialVersionUID = 6946239949792899238L;
    private Connection connection;
    private String compareTableName;
    private String password;
    private String[] compareFieldOfJdbc;
    private String[] compareField;
    private String sql;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String[] getCompareFieldOfJdbc() {
        return compareFieldOfJdbc;
    }

    public void setCompareFieldOfJdbc(String[] compareFieldOfJdbc) {
        this.compareFieldOfJdbc = compareFieldOfJdbc;
    }

    public String[] getCompareField() {
        return compareField;
    }

    public void setCompareField(String[] compareField) {
        this.compareField = compareField;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getCompareTableName() {
        return compareTableName;
    }

    public void setCompareTableName(String compareTableName) {
        this.compareTableName = compareTableName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
