package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.AutiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController(value = "/versement")
class VersementController {

    public static final int MONTANT_MAXIMAL = 10000;

    Logger LOGGER = LoggerFactory.getLogger(VersementController.class);

    @Autowired
    private CompteRepository rep1;
    @Autowired
    private VersementRepository rep2;
    @Autowired
    private AutiService monservice;
    @Autowired
    private UtilisateurRepository rep3;

    @GetMapping("lister_versements")
    List<Versement> loadAll() {
        List<Versement> all = rep2.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("lister_comptes")
    List<Compte> loadAllCompte() {
        List<Compte> all = rep1.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("lister_utilisateurs")
    List<Utilisateur> loadAllUtilisateur() {
        List<Utilisateur> all = rep3.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @PostMapping("/executerVersements")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody VersementDto versementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
        String c1 = versementDto.getNomEmetteur();
        Compte f12 = rep1
                .findByRib(versementDto.getRibCompteBeneficiaire());

        if (c1 == null) {
            System.out.println("Emetteur Non existant");
            throw new CompteNonExistantException("Emetteur Non existant");
        }

        if (f12 == null) {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (versementDto.getMontantVersement().equals(null)) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (versementDto.getMontantVersement().intValue() == 0) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (versementDto.getMontantVersement().intValue() < 10) {
            System.out.println("Montant minimal de versement non atteint");
            throw new TransactionException("Montant minimal de versement non atteint");
        } else if (versementDto.getMontantVersement().intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de versement dépassé");
            throw new TransactionException("Montant maximal de versement dépassé");
        }

        if (versementDto.getMotif().length() < 0) {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }

        

       

        
        

        f12.setSolde(new BigDecimal(f12.getSolde().intValue() + versementDto.getMontantVersement().intValue()));
        rep1.save(f12);

        Versement versement = new Versement();
        versement.setDateExecution(versementDto.getDate());
        versement.setCompteBeneficiaire(f12);
        versement.setNom_prenom_emetteur(c1);
        versement.setMontantVersement(versementDto.getMontantVersement());

        rep2.save(versement);

        monservice.auditVersement("Versement depuis " + versementDto.getNomEmetteur() + " vers " + versementDto
                        .getRibCompteBeneficiaire() + " d'un montant de " + versementDto.getMontantVersement()
                        .toString());
    }

    private void save(Versement Versement) {
        rep2.save(Versement);
    }
}
