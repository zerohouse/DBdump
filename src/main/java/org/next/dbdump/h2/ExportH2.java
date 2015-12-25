package org.next.dbdump.h2;

import org.next.dbdump.ExportQuery;

import java.util.ArrayList;
import java.util.List;

public class ExportH2 implements ExportQuery {

    private static final String QUERY = "call CSVWRITE ('%s%s.csv', 'SELECT * FROM %s', 'charset=utf-8')";
    private String path;

    public ExportH2(String path) {
        this.path = path;
    }

    @Override
    public List<String> getQueries(String tableName) {
        List<String> result = new ArrayList<>();
        result.add(String.format(QUERY, path, tableName, tableName));
        return result;
    }

    @Override
    public String getTableColumn() {
        return "TABLE_NAME";
    }
}
