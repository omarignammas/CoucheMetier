package ma.gov.emsi.etudiant.dao;

import ma.gov.emsi.etudiant.model.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EtudiantDaoImpl implements IEtudiantDao {

    @Override
    public Etudiant getEtudiantByCin(String cin) {
        String sql = "SELECT * FROM etudiants WHERE cin = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cin);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setCin(rs.getString("cin"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setEmail(rs.getString("email"));

                System.out.println("Étudiant trouvé en BD : " + cin);
                return etudiant;
            }

            System.out.println("Aucun étudiant trouvé avec CIN : " + cin);
            return null;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche : " + e.getMessage());
            e.printStackTrace();
            return null;

        } finally {
            closeResources(rs, stmt, conn);
        }
    }
    @Override
    public boolean addEtudiant(Etudiant etudiant) {
        String sql = "INSERT INTO etudiants (cin, nom, prenom, email) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, etudiant.getCin());
            stmt.setString(2, etudiant.getNom());
            stmt.setString(3, etudiant.getPrenom());
            stmt.setString(4, etudiant.getEmail());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Étudiant ajouté : " + etudiant.getCin());
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout : " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, stmt, conn);
        }
    }

    @Override
    public boolean deleteEtudiant(String cin) {
        String sql = "DELETE FROM etudiants WHERE cin = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cin);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Étudiant supprimé : " + cin);
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, stmt, conn);
        }
    }

    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}