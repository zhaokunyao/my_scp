import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UI {
    private Model model;
    private JFrame jf;
    private static final int WIDTH = 400;
    private static final int MARGIN_RIGHT = 25;
    private JTextArea jtaLocalPath;
    private PlaceholderTextField jtfHost;
    private PlaceholderTextField jtfUser;
    private PlaceholderTextField jtfPassword;
    private PlaceholderTextField jtfPort;
    private PlaceholderTextField jtfRemotePath;

    public UI(Model model) {
        this.model = model;
        createWindow();
    }

    private void createWindow() {
        jf = new JFrame();
        jf.setLayout(new BorderLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(WIDTH, 400);
        // center the window.
        jf.setLocationRelativeTo(null);

        createTopPanel();
        createCenterPanel();
        createBottomPanel();
    }

    private void createBottomPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp.setPreferredSize(new Dimension(WIDTH, 100));

        JProgressBar jpb = new JProgressBar();
        jpb.setPreferredSize(new Dimension(WIDTH - MARGIN_RIGHT, 30));
        jpb.setStringPainted(true);
        jpb.setString("进度条...");
        jp.add(jpb);

        jf.add(jp, BorderLayout.SOUTH);
    }

    private void createCenterPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));

        int height = 30;

        JComboBox<String> jcb = new JComboBox<String>();
        jcb.addItem("请选择远程服务器，选择之后将自动填充到下方");
        jcb.setPreferredSize(new Dimension(WIDTH - MARGIN_RIGHT, height));

        jtfHost = new PlaceholderTextField();
        jtfHost.setPlaceHolder("主机名或IP");
        jtfHost.setPreferredSize(new Dimension(150, height));

        jtfPort = new PlaceholderTextField();
        jtfPort.setPlaceHolder("端口(22)");
        jtfPort.setPreferredSize(new Dimension(50, height));

        jtfUser = new PlaceholderTextField();
        jtfUser.setPlaceHolder("用户名");
        jtfUser.setPreferredSize(new Dimension(60, height));

        jtfPassword = new PlaceholderTextField();
        jtfPassword.setPlaceHolder("密码");
        jtfPassword.setPreferredSize(new Dimension(100, height));

        jtfRemotePath = new PlaceholderTextField();
        jtfRemotePath.setPlaceHolder("远程路径 /tmp/ ");
        jtfRemotePath.setPreferredSize(new Dimension(WIDTH - MARGIN_RIGHT, height));

        JButton btn = new JButton();
        btn.setText("开始上传");

        jp.add(jcb);
        jp.add(jtfHost);
        jp.add(jtfPort);
        jp.add(jtfUser);
        jp.add(jtfPassword);
        jp.add(jtfRemotePath);
        jp.add(btn);

        jp.setPreferredSize(new Dimension(WIDTH, 200));
        jf.add(jp, BorderLayout.CENTER);
    }

    private void createTopPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));

        jtaLocalPath = new JTextArea();
        jtaLocalPath.setText(model.getPath());
        jtaLocalPath.setPreferredSize(new Dimension(300, 50));

        JLabel jLabel = new JLabel();
        jLabel.setText(getFileLength());

        jp.add(jtaLocalPath);
        jp.add(jLabel);

        jp.setPreferredSize(new Dimension(WIDTH, 100));
        jf.add(jp, BorderLayout.NORTH);
    }

    private String getFileLength() {
        if (StringUtils.isEmpty(model.getPath())) {
            return "未选中文件";
        }
        File file = new File(model.getPath());
        long size = file.length();
        if (size < 1000) {
            return size + " bytes";
        }
        if (size < 1000 * 1000) {
            return size / 1000.0 + " kb";
        }
        if (size < 1000 * 1000 * 1000) {
            return size / 1000.0 / 1000.0 + " mb";
        }
        return size + " bytes, too large";
    }

    public void show() {
        jf.setVisible(true);
    }

}
