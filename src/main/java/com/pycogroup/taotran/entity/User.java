package com.pycogroup.taotran.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Document(collection = "User")
public class User extends ResourceSupport implements IDocument {

    @Id
    private String id;

    @Field
    @NotNull
    @NotBlank
    private String name;

    @Field
    @Length(min = 8, max = 100)
    private String password;

    @Field
    @Min(10)
    @Max(100)
    private int age;

    @Override
    public String getDocId() {
        return id;
    }

    public void setDocId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
