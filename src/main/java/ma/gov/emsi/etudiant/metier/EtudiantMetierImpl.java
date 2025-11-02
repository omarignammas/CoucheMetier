package ma.gov.emsi.etudiant.metier;

import ma.gov.emsi.etudiant.dao.EtudiantDaoImpl;
import ma.gov.emsi.etudiant.dao.IEtudiantDao;
import ma.gov.emsi.etudiant.model.Etudiant;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class EtudiantMetierImpl extends UnicastRemoteObject implements IEtudiantMetier {

    private IEtudiantDao dao;


    public EtudiantMetierImpl() throws RemoteException {
        super();
        this.dao = new EtudiantDaoImpl();
    }

    @Override
    public Etudiant searchEtudiantByCin(String cin) throws RemoteException {

        if (cin == null || cin.trim().isEmpty()) {
            System.out.println(" CIN invalide");
            throw new RemoteException("Le CIN ne peut pas être vide");
        }

        Etudiant etudiant = dao.getEtudiantByCin(cin);

        if (etudiant != null) {
            System.out.println(" Étudiant trouvé: " + etudiant);
        } else {
            System.out.println(" Aucun étudiant trouvé");
        }

        return etudiant;
    }
    @Override
    public boolean addEtudiant(Etudiant etudiant) throws RemoteException {
        if (etudiant == null || etudiant.getCin() == null || etudiant.getCin().trim().isEmpty()) {
            throw new RemoteException("Données étudiant invalides");
        }

        boolean result = dao.addEtudiant(etudiant);

        if (result) {
            System.out.println("✓ Étudiant ajouté avec succès: " + etudiant.getCin());
        } else {
            System.out.println("✗ Échec de l'ajout de l'étudiant");
        }

        return result;
    }

    @Override
    public boolean deleteEtudiant(String cin) throws RemoteException {
        if (cin == null || cin.trim().isEmpty()) {
            throw new RemoteException("CIN invalide");
        }

        boolean result = dao.deleteEtudiant(cin);

        if (result) {
            System.out.println("✓ Étudiant supprimé avec succès: " + cin);
        } else {
            System.out.println("✗ Échec de la suppression");
        }

        return result;
    }
}