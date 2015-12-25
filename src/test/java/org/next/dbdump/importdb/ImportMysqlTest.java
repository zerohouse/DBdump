package org.next.dbdump.importdb;

import org.junit.Before;
import org.junit.Test;
import org.next.dbdump.mysql.ImportMysql;

import java.io.File;

public class ImportMysqlTest {

    ImportMysql importMysql;

    @Before
    public void setup(){
        importMysql = new ImportMysql("C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/", false);
    }

    @Test
    public void testGetColumnNames() throws Exception {
        importMysql.getColumnNames(new File("C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/content.csv"));
    }


    @Test
    public void testGetQueries() throws Exception {
        importMysql.getQueries();
    }
}