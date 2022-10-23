import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class RegisterHandler {
    public void go() throws Exception {
        // String curPath = Paths.get("./").toAbsolutePath().getParent().toString();

        // todo: first query, if exists, delete it.
        String key = String.format("HKEY_CLASSES_ROOT\\*\\shell\\%s", Constant.REG_DIR_NAME);
        String data = Constant.MENU_ITEM_TITLE;
        addRegister(key, "MUIVerb", data);

        key = String.format("HKEY_CLASSES_ROOT\\*\\shell\\%s\\command", Constant.REG_DIR_NAME);

        // data = "\\\"cmd.exe\\\" /k \\\" echo aaaaa-%1\\\"";
        data = "\\\"d:\\test.exe\\\" \\\"%1\\\"";
        addRegister(key, "", data);
    }

    private void addRegister(String key, String v, String data) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        String cmd = null;
        if ("".equals(v)) {
            cmd = "reg add \"" + key + "\" /ve /d \"" + data + "\" /f";
        } else {
            cmd = "reg add \"" + key + "\" /v \"" + v + "\" /d \"" + data + "\" /f";
        }
        System.out.println(cmd);
        Process proc = runtime.exec(cmd);
        proc.waitFor();
        System.out.println(readStream(proc.getErrorStream()));
        System.out.println(readStream(proc.getInputStream()));
    }

    private String readStream(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
            String content = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                content += line;
            }
            return content;
        }
    }

}
