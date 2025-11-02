package ma.gov.emsi.etudiant.server;

import ma.gov.emsi.etudiant.metier.EtudiantMetierImpl;
import ma.gov.emsi.etudiant.metier.IEtudiantMetier;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRMI {

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            LocateRegistry.createRegistry(2023);
            IEtudiantMetier metier = new EtudiantMetierImpl();
            Naming.rebind("rmi://127.0.0.1:2023/etudiantService", metier);

            System.out.println("   En attente des clients...");

        } catch (Exception e) {
            System.err.println(" ERREUR lors du démarrage du serveur RMI:");
            e.printStackTrace();
        }
    }
}