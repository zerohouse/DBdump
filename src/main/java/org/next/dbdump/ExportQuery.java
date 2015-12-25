package org.next.dbdump;

import org.next.dbdump.h2.ExportH2;
import org.next.dbdump.mysql.ExportMysql;
import org.next.dbdump.setting.DBType;
import org.next.dbdump.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface ExportQuery {
    List<String> getQueries(String table);

    String getTableColumn();

    Logger logger = LoggerFactory.getLogger(ExportQuery.class);

    static ExportQuery getQuery(Setting setting, String path) {
        if (DBType.H2.equals(setting.getDbType())) {
            logger.debug("use h2");
            return new ExportH2(path);
        }
        if (DBType.MYSQL.equals(setting.getDbType())) {
            logger.debug("use mysql");
            return new ExportMysql(path, setting.getDatabase());
        }
        logger.debug("not supported db");
        return null;
    }
}
