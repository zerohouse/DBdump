package org.next.dbdump.mysql;

import org.next.dbdump.ExportQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExportMysql implements ExportQuery {

    private static final Logger logger = LoggerFactory.getLogger(ExportMysql.class);

    private static final String SELECT_TABLE_NAME = "select '%s' into @tableName;";
    private static final String SELECT_OUTPUT_FILE = "select '%s' into @outputFile;";
    private static final String SELECT_COLUMN_NAMES = "select group_concat(concat(\"'\",column_name, \"'\")) into @columnNames from information_schema.columns where table_name=@tableName and table_schema='%s';";
    private static final String MAKE_QUERY = "SET @query = CONCAT(\"select * from ((SELECT \",@columnNames,\") UNION (SELECT * FROM `\",@tableName,\"`)) as a\tINTO OUTFILE '\", @outputFile, \"' fields TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\\\"' ESCAPED BY '' LINES TERMINATED BY '\\r\\n'\");";
    private static final String PREPARE_STMT = "PREPARE stmt FROM @query;\n";
    private static final String EXECUTE_STMT = "EXECUTE stmt;";

    private String path;
    private String database;

    public ExportMysql(String path, String database) {
        this.path = path;
        this.database = database;
    }

    @Override
    public List<String> getQueries(String tableName) {
        logger.debug(path + tableName + ".csv will write");
        String filePath = path + tableName + ".csv";
        File file = new File(filePath);
        if (file.exists())
            file.delete();
        List<String> result = new ArrayList<>();
        result.add(String.format(SELECT_TABLE_NAME, tableName));
        result.add(String.format(SELECT_OUTPUT_FILE, filePath));
        result.add(String.format(SELECT_COLUMN_NAMES, database));
        result.add(MAKE_QUERY);
        result.add(PREPARE_STMT);
        result.add(EXECUTE_STMT);
        return result;
    }

    @Override
    public String getTableColumn() {
        return "Tables_in_next-lecturemanager";
    }
}
