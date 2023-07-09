package com.example;

import com.example.entity.Contact;
import com.example.entity.ContactAccount;
import com.example.repository.ContactRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

@MicronautTest
class ContactRepositoryTest {

    @Inject
    ContactRepository contactRepository;

    @Test
    void testSearch() {
        Contact c1 = new Contact();
        c1.setName("Contact1");

        ContactAccount ca1 = new ContactAccount();
        ca1.setAccountName("CA1");
        ContactAccount ca2 = new ContactAccount();
        ca2.setAccountName("CA2");
        c1.addContactAccount(ca1);
        c1.addContactAccount(ca2);

        contactRepository.save(c1);

        Optional<Contact> optContact = contactRepository.findById(c1.getId());
        Assertions.assertTrue(optContact.isPresent());
        Contact contact = optContact.get();
        Assertions.assertEquals(contact.getContactAccounts().size(), 2);

        List<Contact> contacts = contactRepository.searchContactsByAccountName("CA%");
        Assertions.assertEquals(1, contacts.size());
        Assertions.assertEquals(2, contacts.get(0).getContactAccounts().size());

        contacts = contactRepository.searchContactsByAccountName("CA1%");
        Assertions.assertEquals(1, contacts.size());
        Assertions.assertEquals(1, contacts.get(0).getContactAccounts().size());
        Assertions.assertEquals("CA1", contacts.get(0).getContactAccounts().get(0).getAccountName());

        contacts = contactRepository.searchContactsByAccountName("CA2%");
        Assertions.assertEquals(1, contacts.size());
        Assertions.assertEquals(1, contacts.get(0).getContactAccounts().size());
        Assertions.assertEquals("CA2", contacts.get(0).getContactAccounts().get(0).getAccountName());

        contacts = contactRepository.searchContactsByAccountName("CA3%");
        Assertions.assertEquals(0, contacts.size());
    }

}
