package com.util.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Pet on 2015-07-08.
 */
public class PrefixFilter implements FilenameFilter {
    private String filename;
    private String prefix;

    public PrefixFilter(String filename, String prefix) {
        this.filename = filename;
        this.prefix = prefix;
    }

    @Override
    public boolean accept(File dir, String name) {

        return name.startsWith(prefix);
    }
}
