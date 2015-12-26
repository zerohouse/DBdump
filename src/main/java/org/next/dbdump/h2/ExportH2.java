package org.next.dbdump.h2;

import org.next.dbdump.ExportQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExportH2 implements ExportQuery {

    private static final Logger logger = LoggerFactory.getLogger(ExportH2.class);

    private static final String QUERY = "call CSVWRITE ('%s%s.csv', 'SELECT * FROM %s', 'charset=UTF-8 fieldDelimiter=\" NULL=NULL')";
    private String path;

    public ExportH2(String path) {
        this.path = path;
    }

    @Override
    public List<String> getQueries(String tableName) {
        List<String> result = new ArrayList<>();
        logger.debug(path + tableName + ".csv will write");
        result.add(String.format(QUERY, path, tableName, tableName));
        return result;
    }

    @Override
    public String getTableColumn() {
        return "TABLE_NAME";
    }
}
