package ma.gov.emsi.etudiant.dao;

import ma.gov.emsi.etudiant.model.Etudiant;

public interface IEtudiantDao {
    Etudiant getEtudiantByCin(String cin);
    boolean addEtudiant(Etudiant etudiant);
    boolean deleteEtudiant(String cin);
}