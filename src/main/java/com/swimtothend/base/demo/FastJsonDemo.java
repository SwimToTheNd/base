package com.swimtothend.base.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * yyyy-MM-dd HH:mm:ss
 * yyyyMMdd HHmmss
 * yyyy年MM月dd日 HH时mm分ss秒
 * create by BloodFly at 2019/3/6
 */
public class FastJsonDemo {

    public static void main(String[] args) {
        People people = new People();
        people.setName("小明");
        people.setAddress("长安198号");
        people.setAge(32);
        people.setBirthday(Calendar.getInstance().getTime());
        people.setMoney(new BigDecimal("10023192739"));

        System.out.println(people);
        String peopleJson = JSON.toJSONString(people);
        System.out.println(peopleJson);
        People people1 = JSON.parseObject(peopleJson, People.class);
        System.out.println(people1);

    }
}

class People {
    private String name;
    private int age;
    @JSONField(format = "#,###")
    private BigDecimal money;
    @JSONField(format = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date birthday;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }
}
