package ma.projet.test;

import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.beans.Personne;
import ma.projet.services.DeveloppeurService;
import ma.projet.services.ManagerService;

public class Entreprise {
    public static void main(String[] args) {
        DeveloppeurService ds = new DeveloppeurService();
        ManagerService ms = new ManagerService();

        // Créer deux développeurs
        Developpeur dev1 = new Developpeur(0, "Ali", 4000);
        Developpeur dev2 = new Developpeur(0, "Fatima", 4500);
        ds.create(dev1);
        ds.create(dev2);
        // Créer un manager qui gère les deux développeurs
        Manager manager = new Manager(0, "Yassine", 6000);
        ms.create(manager);
        manager.ajouterEmploye(dev1); // Le manager gère le développeur Ali
        manager.ajouterEmploye(dev2); // Le manager gère le développeur Fatima

        // Créer un 3ème développeur
        Developpeur dev3 = new Developpeur(0, "Karim", 5000);
        ds.create(dev3);

        // Créer un directeur général qui gère le manager et le 3ème développeur
        Manager directeur = new Manager(0, "Mohamed", 8000);
        ms.create(directeur);
        directeur.ajouterEmploye(manager); // Le directeur gère le manager
        directeur.ajouterEmploye(dev3);     // Le directeur gère le 3ème développeur

        // Afficher la hiérarchie (les salaires)
        System.out.println("Directeur: " + directeur.getNom() + " - " + directeur.getSalaire());
        System.out.println("Manager: " + manager.getNom() + " - " + manager.getSalaire());
        System.out.println("Développeurs: ");

        // Afficher d'abord le 3ème développeur
        System.out.println(dev3.getNom() + " - " + dev3.getSalaire()); // Afficher le 3ème développeur

        // Puis afficher les développeurs gérés par le manager
        for (Personne dev : manager.getEquipes()) {
            System.out.println(dev.getNom() + " - " + dev.getSalaire());
        }
    }
}
