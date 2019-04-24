package com.jackiew;

import cucumber.runtime.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class ResourceAdapter implements Resource {
    org.springframework.core.io.Resource springResource;

    public ResourceAdapter(org.springframework.core.io.Resource springResource) {
        this.springResource = springResource;
    }

    public String getPath() {
        try {
            return springResource.getFile().getPath();
        } catch (IOException e) {
            try {
                return springResource.getURL().toString();
            } catch (IOException e1) {
                e1.printStackTrace();
                return "";
            }
        }
    }

    public String getAbsolutePath() {
        try {
            return springResource.getFile().getAbsolutePath();
        } catch (IOException e) {
            return null;
        }
    }

    public InputStream getInputStream() throws IOException {
        return this.springResource.getInputStream();
    }

    public String getClassName(String extension) {

        String path = this.getPath();
        if (path.startsWith("jar:")) {
            path = path.substring(path.lastIndexOf("!") + 2);
            return path.substring(0, path.length() - extension.length()).replace('/', '.');
        } else {
            path = path.substring(path.lastIndexOf("classes") + 8);
            return path.substring(0, path.length() - extension.length()).replace('\\', '.');
        }

    }
}
