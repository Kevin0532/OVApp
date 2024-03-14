package com.example.openov.database.models;

import com.example.openov.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Users extends Database
{
    public Users() throws SQLException { }

    public ResultSet getUsers() throws SQLException
    {
        return this.resultSet("SELECT * FROM users");
    }

    public ResultSet getUserByGbr(String gebruikersnaam) throws SQLException {
        String query = "SELECT * FROM users WHERE gebruikersnaam = '" + gebruikersnaam + "'";
        return this.resultSet(query);
    }

    public boolean addUser(HashMap data) throws SQLException
    {
        String wachtwoord = data.get("wachtwoord").toString();
        String gebruikersnaam = data.get("gebruikersnaam").toString();

        boolean query = this.execute(
                "INSERT INTO users (gebruikersnaam, wachtwoord) " +
                        "VALUES ('" + gebruikersnaam + "', '" + wachtwoord + "')"
        );

        return query;
    }
}
