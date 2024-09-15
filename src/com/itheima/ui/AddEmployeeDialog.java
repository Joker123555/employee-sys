package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//专门用来添加员工信息的窗口
public class AddEmployeeDialog extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JComboBox<String> genderComboBox;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField positionField;
    private JTextField entryDateField;
    private JTextField salaryField;
    private JTextField departmentField;

    public AddEmployeeDialog() {

        initUI();
    }
    //初始化界面，提供很多输入框，
    //"ID", "姓名", "性别", "年龄", "电话", "职位", "入职日期", "薪水", "部门"
    //提供一个添加按钮和一个取消按钮

    private void initUI() {
        // 设置窗口大小
        setSize(400, 500);
        setTitle("添加员工信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 设置窗口居中显示

        // 创建主面板并设置布局
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#F0F8FF")); // 淡蓝色背景

        // 创建表单面板
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setOpaque(false); // 不显示背景色

        // 设置 GridBagLayout 的 LayoutParams
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 10); // 上下左右的内边距
        gbc.anchor = GridBagConstraints.EAST; // 标签右对齐


        // 创建输入框
        idField = new JTextField(10);
        nameField = new JTextField(10);
        genderComboBox = new JComboBox<>(new String[]{"女", "男"});
        ageField = new JTextField(10);
        phoneField = new JTextField(10);
        positionField = new JTextField(10);
        entryDateField = new JTextField(10);
        salaryField = new JTextField(10);
        departmentField = new JTextField(10);

        //设置字体和大小
        Font lableFont = new Font("黑体", Font.PLAIN, 16);

        // 添加标签和输入框
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("ID:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("姓名:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("性别:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(genderComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("年龄:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("电话:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("职位:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("入职日期:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(entryDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("薪水:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("部门:"), gbc);
        formPanel.setFont(lableFont);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(departmentField, gbc);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false); // 不显示背景色
        JButton addButton = new JButton("添加");
        JButton cancelButton = new JButton("取消");

        // 设置按钮的字体和颜色
        Font font = new Font("黑体", Font.BOLD, 16);
        addButton.setFont(font);
        addButton.setBackground(Color.decode("#4CAF50")); // 绿色
        addButton.setForeground(Color.WHITE);
        cancelButton.setFont(font);
        cancelButton.setBackground(Color.decode("#FF5722")); // 橙红色
        cancelButton.setForeground(Color.WHITE);

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // 为按钮添加事件监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 添加员工信息的逻辑
                String id = idField.getText();
                String name = nameField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
                String age = ageField.getText();
                String phone = phoneField.getText();
                String position = positionField.getText();
                String entryDate = entryDateField.getText();
                String salary = salaryField.getText();
                String department = departmentField.getText();

                // 验证输入是否为空
                if (!id.isEmpty() && !name.isEmpty() && !gender.isEmpty() && !age.isEmpty() && !phone.isEmpty()
                        && !position.isEmpty() && !entryDate.isEmpty() && !salary.isEmpty() && !department.isEmpty()) {
                    JOptionPane.showMessageDialog(AddEmployeeDialog.this, "员工信息已添加");
                    dispose(); // 关闭对话框
                } else {
                    JOptionPane.showMessageDialog(AddEmployeeDialog.this, "请填写完整信息！");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 关闭对话框
            }
        });

        // 添加表单面板和按钮面板到主面板
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 添加主面板到窗口
        add(mainPanel);
    }

}
