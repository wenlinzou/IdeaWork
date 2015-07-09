package com.util.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Pet on 2015-07-08.
 */
public class PrefixFilter implements FilenameFilter {
    private String prefix;

    public PrefixFilter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean accept(File dir, String name) {

        return name.startsWith(prefix);
    }
}
