package com.itheima.ui;

import com.itheima.ui.bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeManager extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private JButton searchButton;
    private JButton addButton;

    //准备一个静态集合对象，用于存储员工信息
    private static ArrayList<Employee> employees = new ArrayList<>();


    public EmployeeManager() {

    }
    public EmployeeManager(String username){
        super("欢迎，"+username+"登录绿人行公司员工管理系统！");
        initializeUI();
        setVisible(true);

    }

    private void initializeUI() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建顶部面板
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField(20);
        searchButton = new JButton("搜索");
        addButton = new JButton("添加");

        topPanel.add(searchField);
        topPanel.add(searchButton);
        // 添加添加按钮
        topPanel.add(addButton);

        // 创建表格模型
        model = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "姓名", "性别", "年龄", "电话", "职位", "入职日期", "薪水", "部门"}) {
            // 使姓名、性别、年龄、电话、职位、入职日期、薪水、部门列可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID列不可编辑
            }
        };

        // 初始化表格数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= 20; i++) {
            Date entryDate = new Date(System.currentTimeMillis() - (20 - i) * 30 * 24 * 60 * 60 * 1000L); // 模拟不同的入职日期
            model.addRow(new Object[]{i, "员工 " + i, "女", 30 + i % 5, "1380000000" + i, "工程师", sdf.format(entryDate), 8000 + i * 100, "技术部"});
        }

        // 创建表格
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setMinWidth(50); // 设置ID列宽度
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setResizable(false); // ID列不可调整大小
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加右键菜单
        addRightClickMenu();

        // 添加监听器
        searchButton.addActionListener(e-> searchAction(e)); // 搜索功能

        addButton.addActionListener(e-> {
            //创建一个新的窗口添加员工信息
            new AddEmployeeDialog(this).setVisible(true);
        });


        // 添加组件到主面板
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }



    private void addRightClickMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("修改");
        JMenuItem deleteItem = new JMenuItem("删除");

        editItem.addActionListener(this::editAction);
        deleteItem.addActionListener(this::deleteAction); // 直接引用方法

        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        // 选中当前行
                        table.setRowSelectionInterval(row, row);
                        // 显示右键菜单
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {

                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        table.setRowSelectionInterval(row, row);
                        //popupMenu是弹出菜单，show方法会显示菜单
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    private void searchAction(ActionEvent e) {
        String searchText = searchField.getText();
        // 在这里添加搜索逻辑
        JOptionPane.showMessageDialog(this, "搜索关键词: " + searchText);
    }


    private void editAction(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // 获取当前选中行的数据
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {

                rowData[i] = model.getValueAt(selectedRow, i);
            }

            // 创建对话框编辑数据
            String id = (String) JOptionPane.showInputDialog(this, "请输入员工ID:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[0]);
            String name = (String) JOptionPane.showInputDialog(this, "请输入员工姓名:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[1]);
            String gender = (String) JOptionPane.showInputDialog(this, "请输入员工性别:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[2]);
            String age = (String) JOptionPane.showInputDialog(this, "请输入员工年龄:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[3]);
            String phone = (String) JOptionPane.showInputDialog(this, "请输入员工电话:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[4]);
            String position = (String) JOptionPane.showInputDialog(this, "请输入员工职位:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[5]);
            String entryDate = (String) JOptionPane.showInputDialog(this, "请输入员工入职日期:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[6]);
            String salary = (String) JOptionPane.showInputDialog(this, "请输入员工薪水:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[7]);
            String department = (String) JOptionPane.showInputDialog(this, "请输入员工部门:", "编辑员工信息", JOptionPane.PLAIN_MESSAGE, null, null, rowData[8]);

            if (id != null && name != null && gender != null && age != null && phone != null && position != null && entryDate != null && salary != null && department != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "确认要保存更改吗？", "确认", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.setValueAt(id, selectedRow, 0);
                    model.setValueAt(name, selectedRow, 1);
                    model.setValueAt(gender, selectedRow, 2);
                    model.setValueAt(age, selectedRow, 3);
                    model.setValueAt(phone, selectedRow, 4);
                    model.setValueAt(position, selectedRow, 5);
                    model.setValueAt(entryDate, selectedRow, 6);
                    model.setValueAt(salary, selectedRow, 7);
                    model.setValueAt(department, selectedRow, 8);
                }
            }
        }
    }

    private void deleteAction(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "确认要删除该员工"+(selectedRow+1)+"吗？", "确认", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
            }
        }
    }

    public void addEmployee(Employee employee) {
        //添加到集合
        employees.add(employee);
        //展示到表格
        model.addRow(new Object[]{employee.getId(), employee.getName(), employee.getSex(), employee.getAge(), employee.getPhone(), employee.getPosition(), employee.getEntryDate(), employee.getSalary(), employee.getDepartment()});
    }
}