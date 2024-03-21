package com.lmh.concurrency;

/**
 * @author lmh
 * @description: 一句话描述该类的功能
 * @projectName: concurrency
 * @className: Employee
 * @createDate: 2024/3/6 18:36
 */
public class Employee {
    private String name;
    private Integer age;

    private Department department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void sayJoke(String content) {
        System.out.println(this.name + "说" + content);
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("lmy");
        employee.setAge(20);
        Department department1 = new Department();
        department1.setDname("小卖部");
        employee.setDepartment(department1);
        employee.sayJoke("一言不合就开车");
    }
}

class Department {
    private String dname;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
