package com.fxpi.rest.webservices.restfulwebservices.Beans;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class Users {

    private Integer id;

    @Size(min = 2, message = "Name should have atleast two characters")
    private String name;

    @Past
    private Date birthDate;

    public Users(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
