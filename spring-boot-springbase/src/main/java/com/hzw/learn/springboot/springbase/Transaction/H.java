package com.hzw.learn.springboot.springbase.Transaction;

public class H {
    public Integer id;
    public String name;
    public Integer age;
    public String notnull;

    public H() {
    }

    public H(Integer id, String name, Integer age, String notnull) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.notnull = notnull;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNotnull() {
        return notnull;
    }

    public void setNotnull(String notnull) {
        this.notnull = notnull;
    }
}
