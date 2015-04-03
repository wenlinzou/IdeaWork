package com.util.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Pet on 2015-04-02.
 */
public class ContainsSuffixFilter implements FilenameFilter {
    private String suffix;

    public ContainsSuffixFilter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }
}
