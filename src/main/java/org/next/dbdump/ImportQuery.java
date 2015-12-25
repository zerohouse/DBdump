package org.next.dbdump;

import org.next.dbdump.h2.ImportH2;
import org.next.dbdump.mysql.ImportMysql;
import org.next.dbdump.setting.DBType;
import org.next.dbdump.setting.Setting;

import java.util.List;

public interface ImportQuery {
    String getPath();

    List<String> getQueries();

    String getForeignKeyCheck(int value);

    static ImportQuery getQuery(Setting setting, String path, boolean reset) {
        if(DBType.H2.equals(setting.getDbType()))
            return new ImportH2(path, reset);
        if(DBType.MYSQL.equals(setting.getDbType()))
            return new ImportMysql(path, reset);
        return null;
    }
}
