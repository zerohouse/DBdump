package org.next.dbdump.setting;

import lombok.Getter;

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
    private DBType dbType;

    public Setting() throws IOException {
        ReadProperties readProperties = new ReadProperties();
        this.driver = readProperties.read("spring.datasource.driverClassName");
        this.url = readProperties.read("spring.datasource.url");
        this.user = readProperties.read("spring.datasource.username");
        this.password = readProperties.read("spring.datasource.password");
        if (this.driver.contains("mysql")) {
            this.dbType = DBType.MYSQL;
            Pattern pattern = Pattern.compile("^jdbc:mysql:\\/\\/(?:.+)\\/([\\w-_$]+)(?:.+)$");
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
