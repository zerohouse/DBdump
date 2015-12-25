package org.next.dbdump;

import org.next.dbdump.h2.ImportH2;
import org.next.dbdump.mysql.ImportMysql;
import org.next.dbdump.setting.DBType;
import org.next.dbdump.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface ImportQuery {

    List<String> getQueries();

    String getForeignKeyCheck(int value);

    Logger logger = LoggerFactory.getLogger(ImportQuery.class);

    static ImportQuery getQuery(Setting setting, String path, boolean reset) {
        if (DBType.H2.equals(setting.getDbType())) {
            logger.debug("use h2");
            return new ImportH2(path, reset);
        }
        if (DBType.MYSQL.equals(setting.getDbType())) {
            logger.debug("use mysql");
            return new ImportMysql(path, reset);
        }
        logger.debug("not supported db");
        return null;
    }
}
