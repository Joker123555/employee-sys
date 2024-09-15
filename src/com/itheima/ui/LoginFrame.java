package com.itheima.ui;

import com.itheima.ui.bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class LoginFrame extends JFrame implements ActionListener {
    private JTextField loginNameField;// 用户名输入框
    private JPasswordField passwordField;// 密码输入框
    private JButton loginButton;
    //注册按钮
    private JButton registerButton;


    private static ArrayList<User> allUsers = new ArrayList<>();

    //初始化几个用户
    static {
        allUsers.add(new User("超级管理员", "123456", "admin"));
        allUsers.add(new User("李锦", "lijin520", "lij"));
        allUsers.add(new User("韩珂欣", "hkxin520", "hanke"));
    }

    public LoginFrame() {
        createAndShowGUI();
        setVisible(true);
    }

    private void createAndShowGUI() {

        // 设置窗口大小
        setSize(400, 300);
        setTitle("绿人行公司员工管理系统 - 登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 创建主面板并设置布局
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 内边距

        // 设置主面板的背景色
        mainPanel.setBackground(Color.decode("#F0F8FF")); // 淡蓝色背景

        // 添加标题
        gbc.gridy = 0; // 第一行
        gbc.gridx = 0; // 居中
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JLabel titleLabel = new JLabel("绿人行公司员工管理系统");
        titleLabel.setFont(new Font("黑体", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.decode("#333333"));
        mainPanel.add(titleLabel, gbc);

        // 创建居中的登录表单面板
        gbc.gridy = 1; // 第二行
        gbc.gridx = 0; // 居中
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // 创建表单面板
        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 垂直间距为10
        formPanel.setOpaque(false); // 不显示背景色
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); // 使用 BoxLayout,

        // 创建用户名标签
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setOpaque(false); // 设置为不透明，使用父容器的背景色
        usernameLabel.setBackground(Color.decode("#F0F8FF")); // 设置背景色
        usernameLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.decode("#333333"));

        // 创建用户名输入框
        loginNameField = new JTextField(16);
        loginNameField.setFont(new Font("黑体", Font.PLAIN, 16));
        loginNameField.setForeground(Color.decode("#333333"));
        loginNameField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#333333")), BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // 创建密码标签
        JLabel passwordLabel = new JLabel("密  码:");
        passwordLabel.setOpaque(false); // 设置为不透明，使用父容器的背景色
        passwordLabel.setBackground(Color.decode("#F0F8FF"));
        passwordLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.decode("#333333"));

        // 创建密码输入框
        passwordField = new JPasswordField(10);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 16));
        passwordField.setForeground(Color.decode("#333333"));
        passwordField.setEchoChar('*'); // 显示星号
        passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#333333")), BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        buttonPanel.setOpaque(false); // 不显示背景色

        loginButton = new JButton("登录");
        registerButton = new JButton("注册");

        // 设置按钮的字体和颜色
        Font buttonFont = new Font("黑体", Font.BOLD, 16);
        loginButton.setFont(buttonFont);
        loginButton.setBackground(Color.decode("#4CAF50")); // 绿色
        loginButton.setForeground(Color.WHITE);

        registerButton.setFont(buttonFont);
        registerButton.setBackground(Color.decode("#FF5722")); // 橙红色
        registerButton.setForeground(Color.WHITE);

        // 使用 BoxLayout 并手动放置组件以减小标签和输入框之间的距离
        JPanel labelFieldPanel = new JPanel();
        labelFieldPanel.setLayout(new BoxLayout(labelFieldPanel, BoxLayout.Y_AXIS));
        labelFieldPanel.add(Box.createVerticalStrut(10)); // 上方留空隙

// 创建标签和输入框的水平布局
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createHorizontalStrut(5)); // 水平间距为5
        usernamePanel.add(loginNameField);
        labelFieldPanel.add(usernamePanel);

        labelFieldPanel.add(Box.createVerticalStrut(10)); // 中间留空隙

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createHorizontalStrut(5)); // 水平间距为5
        passwordPanel.add(passwordField);
        labelFieldPanel.add(passwordPanel);

        labelFieldPanel.add(Box.createVerticalStrut(10)); // 下方留空隙
        labelFieldPanel.setBackground(Color.decode("#F0F8FF"));
        formPanel.setOpaque(false); // 不显示背景色

        buttonPanel.add(loginButton);
        // 为登录按钮添加事件监听器
        loginButton.addActionListener(this);
        buttonPanel.add(registerButton);
        // 为注册按钮添加事件监听器
        registerButton.addActionListener(this);

        // 添加表单面板到主面板

        formPanel.add(labelFieldPanel);
        formPanel.add(buttonPanel);

        mainPanel.add(formPanel, gbc);

        // 添加主面板到窗口
        add(mainPanel);

    }


//    //哈希密码
//    private String hashPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Error hashing password", e);
//        }
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //这里有登录和注册两个按钮
        //需要判断出来是登录还是注册
        JButton btn = (JButton) e.getSource();
        if (btn == loginButton) {
            //如果是登陆按钮
            //独立功能，独立方法
            login();
        } else {
            //如果是注册按钮
            System.out.println("注册过来的。。");
        }
    }

    private void login() {
        //1.获取用户输入的用户名和密码
        String loginName = loginNameField.getText();
        String password = new String(passwordField.getPassword());
        //2.遍历集合，判断用户名密码是否匹配，如果匹配，则登录成功，否则登录失败
        User user = findUserByLoginName(loginName);
        if (user != null) {
            //3.判断密码是否匹配
            if (user.getPassword().equals(password)) {
                //登录成功
                JOptionPane.showMessageDialog(this, "登录成功");
                //跳转到主界面
                //创建主界面
                EmployeeManager employeeManager = new EmployeeManager(user.getUsername());
                //设置主界面可见
                employeeManager.setVisible(true);
                //关闭当前登录界面
                this.dispose();
            } else {
                //登录失败
                JOptionPane.showMessageDialog(this, "密码错误");
            }

        } else {
            JOptionPane.showMessageDialog(this, "用户不存在");
        }
    }

    //根据登录名称查找用户对象，如果找到，返回用户对象，否则返回null,用for循环根据索引遍历
    private User findUserByLoginName(String loginName) {
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            if (user.getLoginName().equals(loginName)) {
                return user;
            }
        }
        return null;
    }


}