package org.next.dbdump.importdb;

import org.junit.Before;
import org.junit.Test;
import org.next.dbdump.ImportCSV;
import org.next.dbdump.mysql.ImportMysql;

public class ImportCSVTest {

    ImportCSV importCSV;

    @Before
    public void setUp() throws Exception {
        importCSV = new ImportCSV("C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/abc/", true);
    }

    @Test
    public void testExe() throws Exception {
        importCSV.exe();
    }

}