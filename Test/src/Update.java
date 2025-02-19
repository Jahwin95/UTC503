import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Demander l'ID et le nouveau statut à l'utilisateur
            System.out.print("Entrez l'identifiant de l'utilisateur à modifier : ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le retour à la ligne
            
            System.out.print("Entrez le nouveau statut : ");
            String nouveauStatut = scanner.nextLine();
            
            // Configuration de la connexion
            String strClassName = "com.mysql.jdbc.Driver";
            String dbName = "exercice";
            String login = "root";
            String motdepasse = "";
            String strUrl = "jdbc:mysql://localhost:3306/exercice?useSSL=false";
            
            // Établir la connexion
            Class.forName(strClassName);
            Connection conn = DriverManager.getConnection(strUrl, login, motdepasse);
            Statement stUpdate = conn.createStatement();
            
            // Exécution de la requête de mise à jour
            String updateQuery = "UPDATE Acces SET statut = '" + nouveauStatut + "' WHERE id = " + id;
            int nbLignesModifiees = stUpdate.executeUpdate(updateQuery);
            
            // Affichage du résultat
            if (nbLignesModifiees > 0) {
                System.out.println("Le statut de l'utilisateur avec l'ID " + id + " a été modifié en '" + nouveauStatut + "'.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID " + id + ".");
            }
            
            // Fermeture des ressources
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