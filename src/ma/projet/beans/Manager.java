package ma.projet.beans;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Personne {
    private List<Personne> equipes; // Liste des employés supervisés

    public Manager(int id, String nom, double salaire) {
        super(id, nom, salaire);
        this.equipes = new ArrayList<>(); // Initialisation de la liste
    }

    // Méthode pour ajouter un employé à l'équipe
    public void ajouterEmploye(Personne employe) {
        equipes.add(employe);
    }

    // Méthode pour obtenir la liste des employés supervisés
    public List<Personne> getEquipes() {
        return equipes;
    }

     }