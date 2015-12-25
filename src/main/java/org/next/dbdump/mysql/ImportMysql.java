package org.next.dbdump.mysql;

import lombok.Getter;
import org.next.dbdump.ImportQuery;
import org.next.dbdump.setting.FileResolver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ImportMysql implements ImportQuery {
    private static final String QUERY = "load data local infile '%s' into table `%s` " +
            " fields terminated by ',' " +
            " enclosed by '\"'" +
            " lines terminated by '\\r\\n'" +
            " ignore 1 lines (%s);";
    private static final String DELETE_QUERY = "delete from %s";
    private String path;
    private boolean reset;
    private List<File> files;

    public ImportMysql(String path, boolean reset) {
        this.path = path;
        this.reset = reset;
        FileResolver fileResolver = new FileResolver(path);
        this.files = fileResolver.getFiles();
    }



    public List<String> getQueries() {
        List<String> result = new ArrayList<>();
        this.files.forEach(file -> {
            String table = file.getName().substring(0, file.getName().length() - 4);
            if (reset)
                result.add(String.format(DELETE_QUERY, table));
            result.add(String.format(QUERY, file.getAbsolutePath().replace("\\", "/"), table, getColumnNames(file)));
        });
        return result;
    }

    @Override
    public String getForeignKeyCheck(int value) {
        return String.format("SET foreign_key_checks = %d;", value);
    }

    public String getColumnNames(File file) {
        InputStream inputStream = null;
        String names = null;
        BufferedReader in = null;
        try {
            inputStream = new FileInputStream(file);
            in = new BufferedReader(new InputStreamReader(inputStream));
            names = in.readLine();
            if (names != null)
                names = names.replace("\"", "`");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return names;
    }


}
