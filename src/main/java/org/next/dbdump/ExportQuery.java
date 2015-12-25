package org.next.dbdump;

import org.next.dbdump.h2.ExportH2;
import org.next.dbdump.mysql.ExportMysql;
import org.next.dbdump.setting.DBType;
import org.next.dbdump.setting.Setting;

import java.util.List;

public interface ExportQuery {
    List<String> getQueries(String table);

    String getTableColumn();

    static ExportQuery getQuery(Setting setting, String path) {
        if(DBType.H2.equals(setting.getDbType()))
            return new ExportH2(path);
        if(DBType.MYSQL.equals(setting.getDbType()))
            return new ExportMysql(path, setting.getDatabase());
        return null;
    }
}
