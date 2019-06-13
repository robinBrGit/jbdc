package org.br.jdbc.dao.domain;

import java.util.Objects;
import java.util.Set;

public class Contact {
    private int idContact;
    private String email;
    private String firstName;
    private String lastName;
    private Address address;


    //Constructor
    public Contact() {

    }

    //Getter Setter
    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if(this.address != null){
            this.address.getListContact().remove(this);
        }
        this.address = address;
        if(this.address != null){
            this.address.getListContact().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return email.equals(contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContact, email, firstName, lastName, address);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("idContact=").append(idContact);
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
