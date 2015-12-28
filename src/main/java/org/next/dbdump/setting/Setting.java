package org.next.dbdump.setting;

import lombok.Getter;
import org.next.dbdump.setting.strategy.GetValues;
import org.next.dbdump.setting.strategy.RawStrategy;
import org.next.dbdump.setting.strategy.SpringDatasourceStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Setting {
    private String driver;
    private String url;
    private String user;
    private String password;
    private String database;
    private String path;
    private DBType dbType;

    private static final Logger logger = LoggerFactory.getLogger(Setting.class);

    public Setting() throws IOException {
        ReadProperties config = new ReadProperties("config.properties");
        this.path = config.read("dbdump_csvpath");
        if (path == null) {
            path = "{classpath}../../testdata/";
        }
        if (path.contains("{classpath}")) {
            path = path.replace("{classpath}", getClass().getClassLoader().getResource("").getPath());
        }
        logger.debug("we will export file to {}", path);
        logger.debug("we will import file from {}", path);
        GetValues values;
        if ("raw".equals(config.read("dbdump_strategy"))) {
            values = new RawStrategy(config);
        } else {
            values = new SpringDatasourceStrategy();
        }
        this.driver = values.getDriver();
        this.url = values.getUrl();
        this.user = values.getUser();
        this.password = values.getPassword();
        if (this.driver.contains("mysql")) {
            this.dbType = DBType.MYSQL;
            Pattern pattern = Pattern.compile("^jdbc:mysql:\\/\\/(?:.+)\\/([\\w-_$]+)(?:.*)$");
            Matcher matcher = pattern.matcher(this.url);
            if (matcher.find())
                this.database = matcher.group(1);
        }
        if (this.driver.contains("h2")) {
            this.dbType = DBType.H2;
            Pattern pattern = Pattern.compile("^jdbc:h2:tcp:\\/\\/(?:.*)\\/~\\/([\\w-_$]+)(?:.*)$");
            Matcher matcher = pattern.matcher(this.url);
            if (matcher.find())
                this.database = matcher.group(1);
        }
    }

}
