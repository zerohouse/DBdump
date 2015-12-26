package org.next.dbdump.setting;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FileResolver {
    private List<File> files;
    private static final Logger logger = LoggerFactory.getLogger(FileResolver.class);

    public FileResolver(String path) {
        File directory = new File(path);
        if (!directory.isDirectory()) {
            logger.debug("{} is Not Source Directory, Nothing to Import", path);
            this.files = new ArrayList<>();
            return;
        }
        this.files = listFilesForFolder(
                new File(path));
    }

    private List<File> listFilesForFolder(File folder) {
        List<File> result = new ArrayList<>();
        for (final File file : folder.listFiles()) {
            if (file.isDirectory()) {
                return listFilesForFolder(file);
            }
            if (file.getName().endsWith(".csv"))
                result.add(file);
        }
        return result;
    }


}
