package com.itheima.ui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//员工信息："ID", "姓名", "性别", "年龄", "电话", "职位", "入职日期", "薪水", "部门"
public class Employee {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String phone;
    private String position;//职位
    private String entryDate;//入职日期
    private String salary;//薪水
    private String department;//部门
}
