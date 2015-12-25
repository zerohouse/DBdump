package org.next.dbdump.mysql;

import org.next.dbdump.ExportQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExportMysql implements ExportQuery {


    private static final String QUERY = "select '%s' into @tableName;";
    private static final String QUERY2 = "select '%s' into @outputFile;";
    private static final String QUERY3 = "select group_concat(concat(\"'\",column_name, \"'\")) into @columnNames from information_schema.columns where table_name=@tableName and table_schema='%s';";
    private static final String QUERY4 = "SET @query = CONCAT(\"select * from ((SELECT \",@columnNames,\") UNION (SELECT * FROM `\",@tableName,\"`)) as a\tINTO OUTFILE '\", @outputFile, \"' fields TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\\\"' ESCAPED BY '' LINES TERMINATED BY '\\r\\n'\");";
    private static final String QUERY5 = "PREPARE stmt FROM @query;\n";
    private static final String QUERY6 = "EXECUTE stmt;";

    private String path;
    private boolean reset;
    private String database;

    public ExportMysql(String path, String database) {
        this.reset = reset;
        this.path = path;
        this.database = database;
    }

    @Override
    public List<String> getQueries(String tableName) {
        String filePath = path + tableName + ".csv";
        File file = new File(filePath);
        if (file.exists())
            file.delete();
        List<String> result = new ArrayList<>();
        result.add(String.format(QUERY, tableName));
        result.add(String.format(QUERY2, filePath));
        result.add(String.format(QUERY3, database));
        result.add(QUERY4);
        result.add(QUERY5);
        result.add(QUERY6);
        return result;
    }

    @Override
    public String getTableColumn() {
        return "Tables_in_next-lecturemanager";
    }
}
