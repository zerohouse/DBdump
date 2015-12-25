package org.next.dbdump.setting.strategy;

import lombok.Getter;
import org.next.dbdump.setting.ReadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class RawStrategy implements GetValues {

    private String driver;
    private String user;
    private String url;
    private String password;

    private static final Logger logger = LoggerFactory.getLogger(RawStrategy.class);

    public RawStrategy(ReadProperties config) {
        logger.debug("you choose raw strategy, you have to set values config.properties \"dbdump_driver\", \"dbdump_user\", \"dbdump_url\", \"dbdump_password\"");
        this.driver = config.read("dbdump_driver");
        this.user = config.read("dbdump_user");
        this.url = config.read("dbdump_url");
        this.password = config.read("dbdump_password");
    }

}
