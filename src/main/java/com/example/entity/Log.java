package com.example.entity;

import javax.persistence.*;

/**
 * @Author: ZengFK
 * @Date: 2024/3/20 10:32
 */

@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "time")
    private String time;
    @Column(name = "username")
    private String userName;
    @Column(name = "ip")
    private String ip;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Log(Integer id, String name, String time, String userName, String ip) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.userName = userName;
        this.ip = ip;
    }
}
