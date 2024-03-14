package com.example.openov.database;

import com.example.openov.Utilz.DbSettings;

import java.sql.*;

public class Database
{
    private String db_host = DbSettings.db_host;
    private String db_user = DbSettings.db_user;
    private String db_pass = DbSettings.db_pass;
    private String db_name = DbSettings.db_name;
    private int db_port = DbSettings.db_port;
    private Connection connection;
    private Statement statement;

    public Database() throws SQLException
    {
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://" + this.db_host + ":" + this.db_port + "/" + this.db_name,
                this.db_user,
                this.db_pass
        );

        this.statement = connection.createStatement();
    }

    public Connection connection()
    {
        return this.connection;
    }

    public Statement statement()
    {
        return this.statement;
    }

    public ResultSet resultSet(String sql) throws SQLException
    {
        return this.statement.executeQuery(sql);
    }

    public boolean execute(String sql) throws SQLException
    {
        return this.statement.execute(sql);
    }

    public ResultSet getLastInsertId() throws SQLException
    {
        return this.statement.executeQuery("SELECT LAST_INSERT_ID()");
    }
}
