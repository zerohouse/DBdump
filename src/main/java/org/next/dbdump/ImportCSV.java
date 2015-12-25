package org.next.dbdump;

import org.next.dbdump.setting.Setting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportCSV {

    private ImportQuery query;
    private Setting setting;

    public ImportCSV(boolean reset) throws IOException {
        setting = new Setting();
        this.query = ImportQuery.getQuery(setting, setting.getPath(), reset);
    }


    private List<String> getQueries() {
        List<String> result = new ArrayList<>();
        result.add(query.getForeignKeyCheck(0));
        result.addAll(query.getQueries());
        result.add(query.getForeignKeyCheck(1));
        return result;
    }

    public void exe() throws SQLException, IOException, ClassNotFoundException {
        Execute execute = new Execute(setting);
        getQueries().forEach(query -> {
            try {
                execute.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        execute.close();
    }
}
