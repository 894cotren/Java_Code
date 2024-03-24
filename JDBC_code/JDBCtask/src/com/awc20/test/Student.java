package com.awc20.test;

import javax.xml.crypto.Data;
import java.util.Date;

public class Student {
    private Integer id;
    private String name;
    private String telephone;
    private String addr;
    private Date birthday;

    public Student(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getDate() {
        return birthday;
    }

    public void setDate(Date date) {
        this.birthday = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", addr='" + addr + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
