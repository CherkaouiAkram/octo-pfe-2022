package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;

// Cette classe permet d'insérer les données dans la base de données

public class VersementMapper {

	private static VersementDto versementDto;

	public static VersementDto map(Versement versement) {
		versementDto = new VersementDto();
		versementDto.setNomEmetteur(versement.getNom_prenom_emetteur());
		versementDto.setDate(versement.getDateExecution());
		versementDto.setMotif(versement.getMotifVersement());

		return versementDto;

	}
}
