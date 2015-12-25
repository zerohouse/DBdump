package org.next.dbdump;

import org.next.dbdump.setting.Setting;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExportCSV {
    private static final String SHOW_TABLES = "show tables";
    private ExportQuery query;
    private Setting setting;

    public ExportCSV() throws ClassNotFoundException, SQLException, IOException {
        setting = new Setting();
        this.query = ExportQuery.getQuery(setting, setting.getPath());
    }

    public List<List<String>> getQueries() throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName(setting.getDriver());
        connection = DriverManager.getConnection(setting.getUrl(), setting.getUser(), setting.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs;
        rs = statement.executeQuery(SHOW_TABLES);
        List<List<String>> result = new ArrayList<>();
        while (rs.next()) {
            result.add(query.getQueries(rs.getString(query.getTableColumn())));
        }
        statement.close();
        connection.close();
        return result;
    }

    public void exe() throws SQLException, ClassNotFoundException {
        getQueries().forEach(queries -> {
            Execute execute = null;
            try {
                execute = new Execute(setting);
                final Execute finalExecute = execute;
                queries.forEach(query -> {
                    try {
                        finalExecute.execute(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                execute.close();
            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

}
