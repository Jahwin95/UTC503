import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            DAOAcces dao = new DAOAcces();
            Scanner scanner = new Scanner(System.in);
            int choix = 0;
            
            do {
                System.out.println("\nGestion des accès");
                System.out.println("1. Lister tous les accès");
                System.out.println("2. Ajouter un accès");
                System.out.println("3. Supprimer un accès");
                System.out.println("4. Modifier le statut d'un accès");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                
                choix = scanner.nextInt();
                scanner.nextLine();  // Consommer le retour à la ligne
                
                switch(choix) {
                    case 1:
                        dao.lister();
                        break;
                    case 2:
                        System.out.print("ID : ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nom : ");
                        String nom = scanner.nextLine();
                        System.out.print("Login : ");
                        String login = scanner.nextLine();
                        System.out.print("Mot de passe : ");
                        String password = scanner.nextLine();
                        System.out.print("Statut : ");
                        String statut = scanner.nextLine();
                        System.out.print("Age : ");
                        int age = scanner.nextInt();
                        
                        boolean ajoutReussi = dao.ajouter(id, nom, login, password, statut, age);
                        System.out.println(ajoutReussi ? "Ajout réussi" : "L'utilisateur existe déjà");
                        break;
                    case 3:
                        System.out.print("ID à supprimer : ");
                        int idSuppr = scanner.nextInt();
                        boolean suppressionReussie = dao.supprimer(idSuppr);
                        System.out.println(suppressionReussie ? "Suppression réussie" : "Utilisateur non trouvé");
                        break;
                    case 4:
                        System.out.print("ID à modifier : ");
                        int idMod = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nouveau statut : ");
                        String nouveauStatut = scanner.nextLine();
                        boolean modificationReussie = dao.modifierStatut(idMod, nouveauStatut);
                        System.out.println(modificationReussie ? "Modification réussie" : "Utilisateur non trouvé");
                        break;
                }
            } while (choix != 0);
            
            dao.fermerConnexion();
            scanner.close();
            
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}