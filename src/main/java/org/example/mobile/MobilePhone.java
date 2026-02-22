package org.example.mobile;

import java.util.ArrayList;
import java.util.List;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    // Test bunu da isteyebilir diye (mevcut kalsın)
    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    // ✅ Testin istediği constructor: (String, List<Contact>)
    public MobilePhone(String myNumber, List<Contact> contacts) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
        if (contacts != null) {
            this.myContacts.addAll(contacts);
        }
    }

    public String getMyNumber() {
        return myNumber;
    }

    public ArrayList<Contact> getMyContacts() {
        return myContacts;
    }

    public boolean addNewContact(Contact contact) {
        if (contact == null || contact.getName() == null) return false;

        if (findContact(contact.getName()) >= 0) {
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (oldContact == null || newContact == null) return false;

        int oldIndex = findContact(oldContact);
        if (oldIndex < 0) {
            return false;
        }

        int existingIndex = findContact(newContact.getName());
        if (existingIndex >= 0 && existingIndex != oldIndex) {
            return false;
        }

        myContacts.set(oldIndex, newContact);
        return true;
    }

    public boolean removeContact(Contact contact) {
        if (contact == null) return false;

        int index = findContact(contact);
        if (index < 0) {
            return false;
        }

        myContacts.remove(index);
        return true;
    }

    public int findContact(Contact contact) {
        if (contact == null || contact.getName() == null) return -1;
        return findContact(contact.getName());
    }

    public int findContact(String contactName) {
        if (contactName == null) return -1;

        for (int i = 0; i < myContacts.size(); i++) {
            Contact c = myContacts.get(i);
            if (c.getName() != null && c.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        int index = findContact(name);
        if (index >= 0) {
            return myContacts.get(index);
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (Contact c : myContacts) {
            System.out.println(c.getName() + " -> " + c.getPhoneNumber());
        }
    }
}