package org.next.dbdump;

import org.junit.Before;
import org.junit.Test;
import org.next.dbdump.setting.Setting;

import static org.junit.Assert.*;

public class ExecuteTest {


    @Test
    public void testGetPropValues() throws Exception {
        Execute execute = new Execute(new Setting());
        execute.execute("select 'content' into @tableName;");
        execute.execute("select 'C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/user.csv' into @outputFile;");
        execute.execute("select group_concat(concat(\"'\",column_name, \"'\")) into @columnNames from information_schema.columns where table_name=@tableName;");
        execute.execute("SET @query = CONCAT(\"select * from ((SELECT \",@columnNames,\") UNION (SELECT * FROM `\",@tableName,\"`)) as a\tINTO OUTFILE '\", @outputFile, \"' fields TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\\\"' LINES TERMINATED BY '\\r\\n'\");");
        execute.execute("PREPARE stmt FROM @query;\n");
        execute.execute("EXECUTE stmt;");
    }
}