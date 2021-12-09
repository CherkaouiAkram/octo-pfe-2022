package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

// Cette classe sert à encapsuler les données pour les faire passer d'un layer de l'application à un autre

public class VersementDto {

	private String nomEmetteur;
	private String ribCompteBeneficiaire;
	private String motif;
	private BigDecimal montantVersement;
	private Date date;

	public String getNomEmetteur() {
		return nomEmetteur;
	}

	public void setNomEmetteur(String nomEmetteur) {
		this.nomEmetteur = nomEmetteur;
	}

	public String getRibCompteBeneficiaire() {
		return ribCompteBeneficiaire;
	}

	public void setRibCompteBeneficiaire(String ribCompteBeneficiaire) {
		this.ribCompteBeneficiaire = ribCompteBeneficiaire;
	}

	public BigDecimal getMontantVersement() {
		return montantVersement;
	}

	public void setMontantVersement(BigDecimal montantVersement) {
		this.montantVersement = montantVersement;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
