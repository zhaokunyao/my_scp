import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class RegisterHandler {
    public void go() throws Exception {
        String curPath = Paths.get("./").toAbsolutePath().getParent().toString();

        // register 目录名; 属性名 ; 在菜单上的展示文本
        addRegister("HKEY_CLASSES_ROOT\\*\\shell\\myutil", "MUIVerb", "MyUtil");
        // 目录乐; 默认属性;
        addRegister("HKEY_CLASSES_ROOT\\*\\shell\\myutil\\command", "", "\\\"cmd.exe\\\" /k \\\" echo aaaaa-%1\\\"");

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
