package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class FactureServiceImpl implements IFactureService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	OperateurRepository operateurRepository;
	@Autowired
	DetailFactureRepository detailFactureRepository;
	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	ReglementServiceImpl reglementService;

	@Override
	public List<Facture> retrieveAllFactures() {
		return factureRepository.findAll();
	}


	public Facture addFacture(FactureRequestModel f) {
		Facture facture1= new Facture(f.idFacture,f.montantRemise,f.montantFacture,f.dateCreationFacture);
		factureRepository.save(facture1);
		return facture1;
	}


	public Facture updateFacture(FactureRequestModel f) {
		factureRepository.save( new Facture(f.idFacture,f.montantRemise,f.montantFacture,f.dateCreationFacture));
		return  new Facture(f.idFacture,f.montantRemise,f.montantFacture,f.dateCreationFacture);
	}


	public void deleteFacture(Long factureId) {
		factureRepository.deleteById(factureId);

	}
	/*
	 * calculer les montants remise et le montant total d'un détail facture
	 * ainsi que les montants d'une facture
	 */
	private Facture addDetailsFacture(Facture f ){
		return f;
	}

	@Override
	public void cancelFacture(Long factureId) {

		Facture facture = factureRepository.findById(factureId).orElse(new Facture());
		facture.setArchivee(true);
		factureRepository.save(facture);
		//Méthode 02 (Avec JPQL)
		factureRepository.updateFacture(factureId);
	}

	@Override
	public Facture retrieveFacture(Long factureId) {

		Facture facture = factureRepository.findById(factureId).orElse(null);
		log.info("facture :" + facture);
		return facture;
	}

	@Override
	public List<Facture> getFacturesByFournisseur(Long idFacture) {
		Facture facture = factureRepository.findById(idFacture).orElse(null);
		return (List<Facture>) facture;
	}




	@Override
	public float pourcentageRecouvrement(Date startDate, Date endDate) {
		float totalFacturesEntreDeuxDates = factureRepository.getTotalFacturesEntreDeuxDates(startDate,endDate);
		float totalRecouvrementEntreDeuxDates =reglementService.getChiffreAffaireEntreDeuxDate(startDate,endDate);
		float pourcentage=(totalRecouvrementEntreDeuxDates/totalFacturesEntreDeuxDates)*100;
		return pourcentage;
	}


}