package org.next.dbdump;

import org.next.dbdump.setting.Setting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute {

    private Connection connection;
    private Statement statement;


    public Execute(Setting setting) throws ClassNotFoundException, SQLException, IOException {
        Class.forName(setting.getDriver());
        connection = DriverManager.getConnection(setting.getUrl(), setting.getUser(), setting.getPassword());
        statement = connection.createStatement();
    }


    public void execute(String query) throws SQLException {
        statement.execute(query);
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
