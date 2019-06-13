package org.br.jdbc.dao.domain;

import java.util.Set;

public class Address {
    private int idAddress;
    private String rue;
    private String codePostale;
    private String ville;
    private Set<Contact> listContact;

    public Address() {

    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Set<Contact> getListContact() {
        return listContact;
    }

    public void setListContact(Set<Contact> listContact) {
        this.listContact = listContact;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("idAddress=").append(idAddress);
        sb.append(", rue='").append(rue).append('\'');
        sb.append(", codePostale=").append(codePostale);
        sb.append(", ville='").append(ville).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
