package tn.esprit.rh.achat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

public class FactureRequestModel {

    public FactureRequestModel() {
        super();
    }
    public Long idFacture;
    public float montantRemise;
    public float montantFacture;
    public Date dateCreationFacture;
    public Date dateDerniereModificationFacture;
    public Boolean archivee;
    public Set<DetailFacture> detailsFacture;
    public Fournisseur fournisseur;
    public Set<Reglement> reglements;

    public FactureRequestModel(Long idFacture, float montantRemise, float montantFacture, Date dateCreationFacture) {
        this.idFacture = idFacture;
        this.montantRemise = montantRemise;
        this.montantFacture = montantFacture;
        this.dateCreationFacture = dateCreationFacture;
    }
}