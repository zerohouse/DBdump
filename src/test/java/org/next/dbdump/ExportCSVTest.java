package org.next.dbdump;

import org.junit.Before;
import org.junit.Test;
import org.next.dbdump.h2.ExportH2;
import org.next.dbdump.mysql.ExportMysql;

import java.io.IOException;
import java.sql.SQLException;

public class ExportCSVTest {

    ExportCSV exportCSV;

    @Test
    public void exportDBTest() throws Exception {
        exportCSV = new ExportCSV();
        exportCSV.exe();
    }

}