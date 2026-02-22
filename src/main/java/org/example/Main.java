package org.example;

import org.example.mobile.Contact;
import org.example.mobile.MobilePhone;
import org.example.models.Grocery;

public class Main {
    public static void main(String[] args) {

        // Grocery test
        // Grocery.startGrocery();

        // MobilePhone test
        MobilePhone phone = new MobilePhone("555-0000");
        phone.addNewContact(Contact.createContact("Bob", "31415926"));
        phone.addNewContact(Contact.createContact("Alice", "16180339"));
        phone.printContacts();
    }
}