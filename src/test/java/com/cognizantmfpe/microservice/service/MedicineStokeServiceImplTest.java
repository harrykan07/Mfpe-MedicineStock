package com.cognizantmfpe.microservice.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizantmfpe.microservice.exception.MedicineNotFoundException;
import com.cognizantmfpe.microservice.exception.TreatingAilmentNotFoundException;
import com.cognizantmfpe.microservice.model.MedicineStock;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MedicineStokeServiceImplTest {

	@Autowired
	private MedicineStockImpl medicineStockService;

	@Mock
	private MedicineStock medicineStock;

	@Test
	public void testGetAllMedicines() {
		log.info("Start");
		List<MedicineStock> medicineList = medicineStockService.getAllMedicines();
		assertNotNull(medicineList);
		log.info("End");
	}


	@Test
	public void testGetByMedicineName() {
		log.info("Start");
		MedicineStock numberOfTabletsInStockByName = medicineStockService
				.getByMedicineName("Crocin");
		assertNotNull(numberOfTabletsInStockByName);
		log.info("End");
	}

	@Test
	public void testGetByMedicineNameFail() {
		log.info("Start");
		MedicineStock numberOfTabletsInStockByName  =null;
		try
		{ 
			numberOfTabletsInStockByName = medicineStockService.getByMedicineName("Crgocin");
		}
		catch(MedicineNotFoundException m)
		{
			assertNull(numberOfTabletsInStockByName);
		}
		log.info("End");
	}
	

	@Test
	public void testGetByTargetAilment() {
		log.info("Start");
		List<MedicineStock> medicinesByAilment = medicineStockService
				.getByTargetAilment("Orthopaedics");
		assertNotNull(medicinesByAilment);
		log.info("End");
	}
	
	@Test
	public void testGetByTargetAilmentFail() {
		log.info("Start");
		List<MedicineStock> medicinesByAilment = null;
		try
		{
			medicinesByAilment = medicineStockService
				.getByTargetAilment("Orthopfaedics");
		}
		catch(TreatingAilmentNotFoundException e)
		{
			assertNull(medicinesByAilment);
		}
		log.info("End");
	}
	
	@Test
	public void testUpdateNumberOfTabletsInStockByMedicineName()
	{
		log.info("Start");
		Boolean updatedOrNot=false;
		MedicineStock med  = medicineStockService.getByMedicineName("Crocin");
		updatedOrNot=medicineStockService.updateNumberOfTabletsInStockByMedicineName("Crocin", med.getNumberOfTabletsInStock());
		assertTrue(updatedOrNot);
		log.info("End");
	}
	
	@Test
	public void testUpdateNumberOfTabletsInStockByMedicineNameFail()
	{
		log.info("Start");
		Boolean updatedOrNot=true;
		MedicineStock med  = medicineStockService.getByMedicineName("Crocin");
		updatedOrNot=medicineStockService.updateNumberOfTabletsInStockByMedicineName("Clrocin", med.getNumberOfTabletsInStock());
		assertFalse(updatedOrNot);
		log.info("End");
	}
	

	
	
}
