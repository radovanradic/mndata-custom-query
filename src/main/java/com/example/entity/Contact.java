package com.example.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;

import java.util.ArrayList;
import java.util.List;

@MappedEntity
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "contact", cascade = Relation.Cascade.PERSIST)
    private List<ContactAccount> contactAccounts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContactAccount> getContactAccounts() {
        return contactAccounts;
    }

    public void setContactAccounts(List<ContactAccount> contactAccounts) {
        this.contactAccounts = contactAccounts;
    }

    public void addContactAccount(ContactAccount contactAccount) {
        contactAccounts.add(contactAccount);
    }
}
