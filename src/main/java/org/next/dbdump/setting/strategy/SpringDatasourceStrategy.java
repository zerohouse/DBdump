package org.next.dbdump.setting.strategy;

import lombok.Getter;
import org.next.dbdump.setting.ReadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Getter
public class SpringDatasourceStrategy implements GetValues {

    private String driver;
    private String user;
    private String url;
    private String password;
    private static final Logger logger = LoggerFactory.getLogger(SpringDatasourceStrategy.class);

    public SpringDatasourceStrategy() throws IOException {
        logger.debug("you choose spring datasource strategy, you have to set values application.properties file datasource information");
        ReadProperties application = new ReadProperties("application.properties");
        this.driver = application.read("spring.datasource.driverClassName");
        this.url = application.read("spring.datasource.url");
        this.user = application.read("spring.datasource.username");
        this.password = application.read("spring.datasource.password");
    }

}
