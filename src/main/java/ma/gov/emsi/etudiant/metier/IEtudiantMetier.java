package ma.gov.emsi.etudiant.metier;

import ma.gov.emsi.etudiant.model.Etudiant;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IEtudiantMetier extends Remote {

    Etudiant searchEtudiantByCin(String cin) throws RemoteException;
    boolean addEtudiant(Etudiant etudiant) throws RemoteException;
    boolean deleteEtudiant(String cin) throws RemoteException;
}