package tn.esprit.rh.achat;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Facture;

import tn.esprit.rh.achat.entities.FactureRequestModel;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FactureServiceImplMock {
	@Mock
	FactureRepository factureRepository;
	@InjectMocks
	FactureServiceImpl FactureService;
	Facture p=new Facture((long) 1,10,500,new Date());
	Facture p1=new Facture((long) 2,5,100,new Date());
	Facture p2=new Facture((long) 3,2,4000,new Date());
	List<Facture> listUsers = new ArrayList<Facture>() {
		{
			add(p1);
			add(p1);
			add(p2);
		}
	};

	@Test
	public void createFactureTest()
	{
		FactureRequestModel facture1=new FactureRequestModel((long) 1,10,500,new Date());
		FactureService.addFacture(facture1);
	}
	@Test
	public void testRetrieveUser() {
		Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
		Facture Facture1 = FactureService.retrieveFacture((long)(2));
		Assertions.assertNotNull(Facture1);
	}

	@Test
	public void updateFactureTest(){
		p.setMontantFacture(500);
		FactureRequestModel prm=new FactureRequestModel(p.getIdFacture(),p.getMontantRemise(),p.getMontantFacture(),p.getDateCreationFacture());
		Facture updatedFacture = FactureService.updateFacture(prm);
	}

	@Test
	public void deleteFactureTest(){
		willDoNothing().given(factureRepository).deleteById(p1.getIdFacture());
		FactureService.deleteFacture(p1.getIdFacture());
	}

}