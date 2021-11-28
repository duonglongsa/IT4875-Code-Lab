package com.example.gmailui;


import com.github.javafaker.Faker;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EmailItem {

    private String sender;
    private String subject;
    private String brief;
    private String date;
    private boolean isImportant = false;

    public EmailItem(String sender, String subject, String brief, String date) {
        this.sender = sender;
        this.subject = subject;
        this.brief = brief;
        this.date = date;
    }

    public EmailItem(String sender, String subject, String brief, String date, boolean isImportant) {
        this.sender = sender;
        this.subject = subject;
        this.brief = brief;
        this.date = date;
        this.isImportant = isImportant;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public static EmailItem[] samples(){
        EmailItem[] emails = new EmailItem[20];
        Faker faker = new Faker();
        Format hourFormatter = new SimpleDateFormat("HH:mm a");
        Format dayFormatter = new SimpleDateFormat("dd-MM");

        for (int i = 0; i < 20; ++i){
            Date randomDate = faker.date().past(12+i, 10+i, TimeUnit.HOURS);
            if(dayFormatter.format(randomDate).equals(dayFormatter.format(new Date()))) {
                emails[i] = new EmailItem(faker.name().firstName(), faker.job().position(), faker.lorem().sentence(), hourFormatter.format(randomDate));
            } else {
                emails[i] = new EmailItem(faker.name().firstName(), faker.job().position(), faker.lorem().sentence(), dayFormatter.format(randomDate));
            }
        }

        return emails;
    }
}
