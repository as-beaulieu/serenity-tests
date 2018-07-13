package com.submissions;

import java.time.Instant;
//import com.github.javafaker.service.CountryService;
//import com.github.javafaker.service.FakeValuesService;
//import com.github.javafaker.service.RandomService;

public class Submission {

    private String id;
    private String string;
    private Integer integer;
    private Instant createDate;
    private Instant updateDate;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

     public static Submission newSubmissionForm(){
//        Faker faker = new Faker();
        Submission submission = new Submission();

        submission.integer = 777;
        submission.string = "here is some string";

        return submission;
     }


}
