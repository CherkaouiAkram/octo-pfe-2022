/* pour la couche de securite j'ai augmente la couche de securite de high vers veryhigh a partir 
  du fichier
  configure java
 */

package ma.octo.assignement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.octo.assignement.domain.Virement;

import java.util.ArrayList;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class VirementRepositoryTest {

	@Autowired
	private VirementRepository virementRepository;

	@Test
	public Virement findOne(Long id) {
		return virementRepository.findOne(id);
	}

	@Test
	public ArrayList<Virement> findAll(ArrayList<Long> ids) {
		ArrayList<Virement> lsVirement = new ArrayList<>();
		for(int i=0;i<ids.size();i++) {
			lsVirement.add(findOne(ids.get(i)));
		}
		return lsVirement;
	}

	@Test
	public void save() {
		virementRepository.save();
	}

	@Test
	public void delete(Long id) {
		virementRepository.delete(findOne(id));
	}
}
