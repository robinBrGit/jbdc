package org.br.jdbc.dao;

import org.br.jdbc.dao.dal.ContactDAO;
import org.br.jdbc.dao.domain.Address;
import org.br.jdbc.dao.domain.Contact;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppDAO {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        dspMainMenu();
            /*
            if (response.equals("2")){
                System.out.println("Taper l'id du contact :");
                responseInt=sc.nextInt();
                sc.nextLine();
                try {
                    System.out.println(contactDAO.findById(responseInt));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            */
    }
    public static void dspMainMenu() {
        int response;
        boolean first = true;
        do {
            if ( !first ) {
                System.out.println( "***********************************************" );
                System.out.println( "* Mauvais choix, merci de recommencer !       *" );
                System.out.println( "***********************************************" );
            }
            System.out.println( "**************************************" );
            System.out.println( "*****************Menu*****************" );
            System.out.println( "* 1 - Ajouter un contact             *" );
            System.out.println( "* 2 - Modifier un contact            *" );
            System.out.println( "* 3 - Supprimer un contact           *" );
            System.out.println( "* 4 - Lister les contacts            *" );
            System.out.println( "* 5 - Rechercher un contact          *" );
            System.out.println( "* 6 - Quitter                       *" );
            System.out.println( "**************************************" );
            System.out.print( "*Votre choix : " );
            try {
                response = sc.nextInt();
            } catch ( InputMismatchException e ) {
                response = -1;
            } finally {
                sc.nextLine();
            }
            first = false;
        } while ( 1 > response || 6 < response );
        switch ( response ) {
            case 1:
                addContact();
                break;
            case 2:
                editContact();
                break;
            case 3:
                deleteContact();
                break;
            case 4:
                dspContacts( true );
                break;
            case 5:
                //TODO
                break;
        }
    }

    public static void addContact() {
        System.out.println( "**************************************" );
        System.out.println( "**********Ajout d'un contact**********" );

        ContactDAO contactDAO = new ContactDAO();
        Contact contact = new Contact();
        Address address = new Address();
        //on demande les valeur et on les affecte
        System.out.println("Entrer la rue :");
        address.setRue(sc.nextLine());
        System.out.println("Entrer le code postale :");
        address.setCodePostale(sc.nextLine());
        System.out.println("Entrer la ville :");
        address.setVille(sc.nextLine());
        System.out.println("Entrer l'adresse email :");
        contact.setEmail(sc.nextLine());
        System.out.println("Entrer le prenom :");
        contact.setFirstName(sc.nextLine());
        System.out.println("Entrer le nom de famille :");
        contact.setLastName(sc.nextLine());
        contact.setAddress(address);

        try {
            contactDAO.create(contact);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println( "Nouveau contact ajouté ..." );
    }

    public static void editContact() {
        System.out.println( "*********************************************" );
        System.out.println( "**********Modification d'un contact**********" );
        dspContacts( false );
        System.out.print( "Entrer l'identifiant du contact : " );
        String id = sc.nextLine();
    }

    public static void deleteContact() {
        System.out.println( "*********************************************" );
        System.out.println( "***********Suppression d'un contact**********" );
        dspContacts( false );
        System.out.print( "Entrer l'identifiant du contact : " );
        String id = sc.nextLine();

    }

    public static void dspContact( Contact contact ) {
        System.out.println(contact);
    }

    public static void dspContacts( boolean dspHeader ) {
        if ( dspHeader ) {
            System.out.println( "**************************************" );
            System.out.println( "********Liste de vos contacts*********" );
        }
        //TODO
        System.out.println( "**************************************" );
    }
}
