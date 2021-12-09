package ma.octo.assignement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.octo.assignement.domain.Versement;

import java.util.ArrayList;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class VersementRepositoryTest {

	@Autowired
	private VersementRepository VersementRepository;

	@Test
	public Versement findOne(Long id) {
		return VersementRepository.findOne(id);
	}

	@Test
	public ArrayList<Versement> findAll(ArrayList<Long> ids) {
		ArrayList<Versement> lsVersement = new ArrayList<>();
		for(int i=0;i<ids.size();i++) {
			lsVersement.add(findOne(ids.get(i)));
		}
		return lsVersement;
	}

	@Test
	public void save() {
		VersementRepository.save();
	}

	@Test
	public void delete(Long id) {
		VersementRepository.delete(findOne(id));
	}
}