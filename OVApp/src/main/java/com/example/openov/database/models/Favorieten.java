package com.example.openov.database.models;

import com.example.openov.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Favorieten extends Database
{

    public Favorieten() throws SQLException { }

    public ResultSet getFavorieten(int usr_id) throws SQLException {
        String query = "SELECT * FROM favorieten WHERE fav_usr_id = '" + usr_id + "'";
        ResultSet resultSet = this.resultSet(query);

        if (resultSet.next()) {
            return resultSet;
        } else {
            return null;
        }
    }


    public void delete(int fav_id) throws SQLException
    {
        String query = "DELETE favorieten WHERE fav_id = '" + fav_id + "'";
        this.execute(query);
    }

    public void addFavoriet(int usr_id, String trajextnaam) throws SQLException {
        String query = "INSERT INTO favorieten (fav_usr_id, trajectnaam) VALUES ('" + usr_id + "', '" + trajextnaam + "')";
        this.execute(query);
    }

}
