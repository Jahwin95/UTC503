// Classe DAOAcces.java modifiée
import java.sql.*;
import java.util.ArrayList;

public class DAOAcces {
    private Connection conn;
    private Statement stmt;
    
    // Constructeur
    public DAOAcces() throws ClassNotFoundException, SQLException {
        String strClassName = "com.mysql.jdbc.Driver";
        String login = "root";
        String motdepasse = "";
        String strUrl = "jdbc:mysql://localhost:3306/exercice?useSSL=false";
        
        Class.forName(strClassName);
        conn = DriverManager.getConnection(strUrl, login, motdepasse);
        stmt = conn.createStatement();
    }
    
    // Constructeur avec paramètres de connexion
    public DAOAcces(String host, String port, String dbName, String login, String motdepasse) 
        throws ClassNotFoundException, SQLException {
        String strClassName = "com.mysql.jdbc.Driver";
        String strUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false";
        
        Class.forName(strClassName);
        conn = DriverManager.getConnection(strUrl, login, motdepasse);
        stmt = conn.createStatement();
    }
    
    // Méthode pour fermer la connexion
    public void fermerConnexion() throws SQLException {
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
        System.out.println("Connexion fermée avec succès");
    }
    
    // Méthode pour lister le contenu de la table en mode console
    public void lister() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM Acces");
        System.out.println("Liste des utilisateurs :");
        System.out.println("------------------------");
        while(rs.next()) {
            System.out.println("Id[" + rs.getInt(1) + "] " + rs.getString(2) 
                + " [" + rs.getString("statut") + "] " + rs.getInt("age"));
        }
        rs.close();
    }
    
    // Nouvelle méthode ListerDAO qui retourne un ArrayList d'objets Acces
    public ArrayList<Acces> listerDAO() throws SQLException {
        ArrayList<Acces> listeAcces = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Acces");
        
        while(rs.next()) {
            Acces acces = new Acces(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("statut"),
                rs.getInt("age")
            );
            listeAcces.add(acces);
        }
        rs.close();
        return listeAcces;
    }
    
    // Méthode ajouter avec paramètres individuels
    public boolean ajouter(int id, String nom, String login, String password, 
                          String statut, int age) throws SQLException {
        String query = "INSERT IGNORE INTO Acces VALUES (" 
                      + id + ",'" + nom + "','" + login + "','" 
                      + password + "','" + statut + "'," + age + ")";
        int result = stmt.executeUpdate(query);
        return result > 0;
    }
    
    // Nouvelle méthode ajouterDAO qui prend un objet Acces
    public boolean ajouterDAO(Acces a) throws SQLException {
        String query = "INSERT IGNORE INTO Acces VALUES (" 
                      + a.getId() + ",'" + a.getNom() + "','" + a.getLogin() + "','" 
                      + a.getPassword() + "','" + a.getStatut() + "'," + a.getAge() + ")";
        int result = stmt.executeUpdate(query);
        return result > 0;
    }
    
    // Méthode supprimer avec id en paramètre
    public boolean supprimer(int id) throws SQLException {
        String query = "DELETE FROM Acces WHERE id = " + id;
        int result = stmt.executeUpdate(query);
        return result > 0;
    }
    
    // Nouvelle méthode supprimerDAO qui prend un objet Acces
    public boolean supprimerDAO(Acces a) throws SQLException {
        return supprimer(a.getId());
    }
    
    // Méthode pour modifier le statut
    public boolean modifierStatut(int id, String nouveauStatut) throws SQLException {
        String query = "UPDATE Acces SET statut = '" + nouveauStatut + "' WHERE id = " + id;
        int result = stmt.executeUpdate(query);
        return result > 0;
    }
}