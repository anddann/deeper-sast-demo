package com.dashboardmanager.utils;

public class FileUtils {

    private static FileUtils instance = null;

    private static final String IMAGE_PATH = "./public/img/";
    private static final String USER_PATH = "./users/";

    public static FileUtils getInstance() {
        if (FileUtils.instance == null) FileUtils.instance = new FileUtils();
        return FileUtils.instance;
    }

    public String getImagePath() {
        return IMAGE_PATH;
    }

    public String getUserImagePath(String username) {
        String path = getImagePath();
        path += EncodingUtils.getInstance().decodeParameter(username);

        // do security check avoiding path traversal
        File file = new File(path);

        if (!file.toPath().normalize().startsWith(getImagePath())) {
            throw new IOException("Entry is outside of the target directory");
        }
        
        return path;
    }
}
