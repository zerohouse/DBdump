package org.next.dbdump.setting;

import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FileResolver {
    private List<File> files;

    public FileResolver(String path) {
        this.files = listFilesForFolder(new File(path));
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
