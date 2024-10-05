package ma.projet.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;
import ma.projet.beans.Developpeur;

public class DeveloppeurService implements IDao<Developpeur> {

    @Override
  
public boolean create(Developpeur o) {
    boolean r = false;
    String req = "INSERT INTO `developpeur` (nom, salaire) VALUES (?, ?)";
    try (Connection cn = Connexion.getConnection();
         PreparedStatement pst = cn.prepareStatement(req)) {
        if (cn != null) { // Vérifiez que la connexion est valide
            pst.setString(1, o.getNom());
            pst.setDouble(2, o.getSalaire());
            int n = pst.executeUpdate();
            if (n == 1) {
                r = true;
            }
        } else {
            System.err.println("Erreur de connexion à la base de données.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return r;
}


    @Override
    public boolean update(Developpeur o) {
        boolean r = false;
        String req = "UPDATE `developpeur` SET nom = ?, salaire = ? WHERE id = ?";
        try (Connection cn = Connexion.getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setString(1, o.getNom());
            pst.setDouble(2, o.getSalaire());
            pst.setInt(3, o.getId());
            int n = pst.executeUpdate();
            if (n == 1) {
                r = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public boolean delete(Developpeur o) {
        boolean r = false;
        String req = "DELETE FROM `developpeur` WHERE id = ?";
        try (Connection cn = Connexion.getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setInt(1, o.getId());
            int n = pst.executeUpdate();
            if (n == 1) {
                r = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public Developpeur findById(int id) {
        String req = "SELECT * FROM `developpeur` WHERE id = ?";
        try (Connection cn = Connexion.getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
    public List<Developpeur> findAll() {
        List<Developpeur> developpeurs = new ArrayList<>();
        String req = "SELECT * FROM `developpeur`";
        try (Connection cn = Connexion.getConnection();
             PreparedStatement pst = cn.prepareStatement(req);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                developpeurs.add(new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return developpeurs;
    }

    @Override
    public Developpeur getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Developpeur> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
