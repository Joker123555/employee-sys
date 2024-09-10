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
    private JTextField   loginNameField;// 用户名输入框
    private JPasswordField passwordField;// 密码输入框
    private JButton loginButton;
    //注册按钮
    private JButton registerButton;


    private static ArrayList<User> allUsers = new ArrayList<>();

    //初始化几个用户
    static {
        allUsers.add(new User("超级管理员", "123456", "admin"));
        allUsers.add(new User("lijin", "lijin520", "lij"));
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
        //创建一个面板，表单2行2列
        //将用户名和密码放到两行上
        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 垂直间距为10
        formPanel.setOpaque(false); // 不显示背景色
        //在输入框前面添加用户名字样


        // 创建登录名和密码输入框
        loginNameField = new JTextField( 16);
        passwordField = new JPasswordField(16);

        formPanel.add(createLabelAndFieldPanel("用户名:", loginNameField));
        formPanel.add(createLabelAndFieldPanel("密  码:", passwordField));


        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false); // 不显示背景色
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");

        // 设置按钮的字体和颜色
        Font font = new Font("黑体", Font.BOLD, 16);
        loginButton.setFont(font);
        loginButton.setBackground(Color.decode("#4CAF50")); // 绿色
        loginButton.setForeground(Color.WHITE);

        registerButton.setFont(font);
        registerButton.setBackground(Color.decode("#FF5722")); // 橙红色
        registerButton.setForeground(Color.WHITE);

        buttonPanel.add(loginButton);
        //为登录按钮添加事件监听器
        loginButton.addActionListener(this);
        buttonPanel.add(registerButton);
        //为注册按钮添加事件监听器
        registerButton.addActionListener(this);

        // 添加按钮面板到表单面板
        formPanel.add(buttonPanel);

        // 添加表单面板到主面板
        mainPanel.add(formPanel, gbc);

        // 添加主面板到窗口
        add(mainPanel);

    }

    // 创建包含标签和输入框的JPanel
    private JPanel createLabelAndFieldPanel(String labelText, JComponent field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setOpaque(false); // 不显示背景色

        JLabel label = new JLabel(labelText, SwingConstants.RIGHT);
        label.setFont(new Font("黑体", Font.PLAIN, 14));
        label.setForeground(Color.decode("#333333"));

        if (field instanceof JTextField) {
            ((JTextField) field).setFont(new Font("黑体", Font.PLAIN, 16));
            ((JTextField) field).setForeground(Color.decode("#333333"));
        } else if (field instanceof JPasswordField) {
            ((JPasswordField) field).setFont(new Font("Dialog", Font.PLAIN, 16));
            ((JPasswordField) field).setForeground(Color.decode("#333333"));
            ((JPasswordField) field).setEchoChar('·'); // 确保密码输入框显示星号
        }

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#333333")),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        panel.add(label);
        panel.add(field);

        return panel;
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
                EmployeeManager employeeManager = new EmployeeManager();
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