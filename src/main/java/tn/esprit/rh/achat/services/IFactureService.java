package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.FactureRequestModel;

import java.util.Date;
import java.util.List;

public interface IFactureService {
	List<Facture> retrieveAllFactures();

	List<Facture> getFacturesByFournisseur(Long idFournisseur);

	Facture addFacture(FactureRequestModel f);
	Facture updateFacture(FactureRequestModel f);

	void deleteFacture(Long id);

	void cancelFacture(Long id);

	Facture retrieveFacture(Long id);


	float pourcentageRecouvrement(Date startDate, Date endDate);

}