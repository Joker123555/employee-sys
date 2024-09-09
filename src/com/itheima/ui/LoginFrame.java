package com.itheima.ui;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginFrame extends JFrame {
    private JPanel usernamePanel;// 用户名输入框
    private  JPanel passwordPanel;// 密码输入框
    private JButton loginButton;
    private JButton resetButton;
    private static final long serialVersionUID = 1L;

    public LoginFrame() {

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
        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3行1列，垂直间距为10
        formPanel.setOpaque(false); // 不显示背景色

        // 添加用户名和密码输入框
        usernamePanel = createLabelAndFieldPanel("用户名:", new JTextField(20));
        passwordPanel = createLabelAndFieldPanel("密  码:", new JPasswordField(20));

        // 添加到表单面板
        formPanel.add(usernamePanel);
        formPanel.add(passwordPanel);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false); // 不显示背景色

        loginButton = new JButton("登录");
        resetButton = new JButton("注册");

        // 设置按钮的字体和颜色
        Font font = new Font("黑体", Font.BOLD, 16);
        loginButton.setFont(font);
        loginButton.setBackground(Color.decode("#4CAF50")); // 绿色
        loginButton.setForeground(Color.WHITE);

        resetButton.setFont(font);
        resetButton.setBackground(Color.decode("#FF5722")); // 橙红色
        resetButton.setForeground(Color.WHITE);

        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton);

        // 添加按钮面板到表单面板
        formPanel.add(buttonPanel);

        // 添加表单面板到主面板
        mainPanel.add(formPanel, gbc);

        // 添加主面板到窗口
        add(mainPanel);


        resetButton.addActionListener(e -> {
            JTextField usernameField = (JTextField) usernamePanel.getComponent(1);
            JPasswordField passwordField = (JPasswordField) passwordPanel.getComponent(1);
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus(); // 让用户名输入框获得焦点
        });
    }

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

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }


}