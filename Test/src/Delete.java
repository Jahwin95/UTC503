import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Demander l'ID à l'utilisateur
            System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");
            int id = scanner.nextInt();
            
            // Configuration de la connexion
            String strClassName = "com.mysql.jdbc.Driver";
            String dbName = "exercice";
            String login = "root";
            String motdepasse = "";
            String strUrl = "jdbc:mysql://localhost:3306/exercice?useSSL=false";
            
            // Établir la connexion
            Class.forName(strClassName);
            Connection conn = DriverManager.getConnection(strUrl, login, motdepasse);
            Statement stDelete = conn.createStatement();
            
            // Exécution de la requête de suppression
            int nbLignesSupprimees = stDelete.executeUpdate("DELETE FROM Acces WHERE id = " + id);
            
            // Affichage du résultat
            if (nbLignesSupprimees > 0) {
                System.out.println("L'utilisateur avec l'ID " + id + " a été supprimé avec succès.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID " + id + ".");
            }
            
            // Fermeture de la connexion
            conn.close();
            scanner.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver non chargé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL !");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur de saisie !");
            e.printStackTrace();
        }
    }
}