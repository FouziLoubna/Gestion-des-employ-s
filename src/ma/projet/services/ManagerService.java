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
import ma.projet.beans.Manager;

public class ManagerService implements IDao<Manager> {

    @Override
    public boolean create(Manager o) {
        boolean r = false;
        String req = "INSERT INTO `manager` (nom, salaire) VALUES (?, ?)";
        try (Connection cn = Connexion. getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setString(1, o.getNom());
            pst.setDouble(2, o.getSalaire());
            int n = pst.executeUpdate();
            if (n == 1) {
                r = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public boolean update(Manager o) {
        boolean r = false;
        String req = "UPDATE `manager` SET nom = ?, salaire = ? WHERE id = ?";
        try (Connection cn = Connexion. getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setString(1, o.getNom());
            pst.setDouble(2, o.getSalaire());
            pst.setInt(3, o.getId());
            int n = pst.executeUpdate();
            if (n == 1) {
                r = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public boolean delete(Manager o) {
        boolean r = false;
        String req = "DELETE FROM `manager` WHERE id = ?";
        try (Connection cn = Connexion. getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setInt(1, o.getId());
            int n = pst.executeUpdate();
            if (n == 1) {
                r = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public Manager findById(int id) {
        String req = "SELECT * FROM `manager` WHERE id = ?";
        try (Connection cn = Connexion. getConnection();
             PreparedStatement pst = cn.prepareStatement(req)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    public List<Manager> findAll() {
        List<Manager> managers = new ArrayList<>();
        String req = "SELECT * FROM `manager`";
        try (Connection cn = Connexion. getConnection();
             PreparedStatement pst = cn.prepareStatement(req);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                managers.add(new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return managers;
    }

    @Override
    public Manager getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Manager> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
