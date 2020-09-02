package common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SystemParams {

    public static Map<String, Object> params = new HashMap<>();

    static {
        if (params == null) params = new HashMap<>();
        String os = System.getProperty("os.name").toLowerCase();
        params.put("OS_LINUX", !os.startsWith("win"));
        params.put("FILE_PATH_FOR_LINUX",  File.separator + "home" + File.separator + "root" + File.separator + "file" + File.separator);
        params.put("FILE_PATH_FOR_WINDOWS",  "f:/file/");
    }

}
