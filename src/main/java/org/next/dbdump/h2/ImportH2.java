package org.next.dbdump.h2;

import lombok.Getter;
import org.next.dbdump.ImportQuery;
import org.next.dbdump.setting.FileResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ImportH2 implements ImportQuery {
    private static final Logger logger = LoggerFactory.getLogger(ImportH2.class);

    private static final String QUERY = "insert into %s select * from CSVREAD('%s', null , 'charset=UTF-8 fieldDelimiter=\" NULL=NULL');";
    private static final String DELETE_QUERY = "delete from %s";

    private String path;
    private boolean reset;
    private List<File> files;

    public ImportH2(String path, boolean reset) {
        this.path = path;
        this.reset = reset;
        FileResolver fileResolver = null;
        fileResolver = new FileResolver(path);
        this.files = fileResolver.getFiles();
    }

    @Override
    public List<String> getQueries() {
        List<String> result = new ArrayList<>();
        this.files.forEach(file -> {
            String table = file.getName().split("\\.")[0];
            if (reset)
                result.add(String.format(DELETE_QUERY, table));
            logger.debug("file {} will imported to table {}", file.getAbsolutePath(), table);
            result.add(String.format(QUERY, table, file.getAbsolutePath()));
        });
        return result;
    }

    @Override
    public String getForeignKeyCheck(int value) {
        return String.format("SET foreign_key_checks = %d;", value);
    }
}
