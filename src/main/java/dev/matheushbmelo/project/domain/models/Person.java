package dev.matheushbmelo.project.domain.models;

import dev.matheushbmelo.project.domain.exceptions.AtributeException;

public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        if (name == null) {
            throw new AtributeException("O nome não pode ser nulo");
        }
        if (age == null) {
            throw new AtributeException("A idade não pode ser nula");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return age >= 18;
    }
}

