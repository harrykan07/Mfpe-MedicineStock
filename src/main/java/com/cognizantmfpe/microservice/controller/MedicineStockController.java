package com.cognizantmfpe.microservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizantmfpe.microservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.microservice.feign.AuthenticationFeignClient;
import com.cognizantmfpe.microservice.model.JwtResponse;
import com.cognizantmfpe.microservice.model.MedicineStock;
import com.cognizantmfpe.microservice.service.IMedicineStock;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class MedicineStockController.
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping(value="medicine")
public class MedicineStockController {
	
	/** The iservice. */
	@Autowired
	private IMedicineStock iservice;
	
	

	@Autowired
	private AuthenticationFeignClient authFeignClient;
	
	/*
	 * 1. Save a stock
	 * url : http://localhost:8080/saveStock
	 * {
	 * 		"medicineName": "NAIORA",
	 * 		"chemicalComposition": "HCL,HNO2,HNO3"
	 * 		"targetAilment": "Ortho",
	 * 		"dateOfExpiry": "20-12-2050",
	 * 		"numberOfTabletsInStock": "520"
	 * }
	 */
	
	
	/*
	 * 2. Get all stocks
	 * url : http://localhost:8080/medicine/MedicineStockInformation
	*/
	/**
	 * Gets the all stocks.
	 *
	 * @return the all stocks
	 * @throws TokenValidationFailedException 
	 */
	@GetMapping("/MedicineStockInformation")
	public ResponseEntity<List<MedicineStock>> getAllStocks(@RequestHeader(name = "Authorization") String token) throws TokenValidationFailedException {
		log.info("START");
		log.debug("TOKEN {}:", token);
		List<MedicineStock> medicineStocks = null;
		try {
			JwtResponse jwtResponse = authFeignClient.verifyToken(token);
			log.debug("JwtResponse {}:", jwtResponse);
			if (jwtResponse.isValid()) {
				log.info("TOKEN IS VALID");
				medicineStocks = iservice.getAllMedicines();
			}
		} catch (FeignException e) {
			log.error("TOKEN VALIDATION FAILED");
			throw new TokenValidationFailedException("TOKEN EXPIRED");
		}
		log.info("END");
		return ResponseEntity.ok(medicineStocks);

	}
	
	/*
	 * 3. Get all stocks by ailment
	 * url : http://localhost:8080/medicine/getByAilment/ortho
	 */
	/**
	 * Gets the stocks by ailment.
	 *
	 * @param ailment
	 * @return the stocks by ailment
	 */
	@GetMapping("/getByAilment/{ailment}")
	public ResponseEntity<String[]> getStocksByAilment(@RequestHeader(name = "Authorization") String token, @PathVariable String ailment) throws TokenValidationFailedException
	{
		log.info("START");
		log.debug("TOKEN {}:", token);
		log.debug("TREATING AILMENT {}:", ailment);
		List<String> medicineNames = new ArrayList<>();
		try {
			JwtResponse jwtResponse = authFeignClient.verifyToken(token);
			log.debug("JwtResponse {}:", jwtResponse);
			if (jwtResponse.isValid()) {
				log.info("TOKEN IS VALID");
				List<MedicineStock> medicineStockList = iservice.getByTargetAilment(ailment);
				log.debug("MEDICINE BY TARGET AILMENT {}: ", medicineStockList);
				for(MedicineStock medi : medicineStockList)
				{
					medicineNames.add(medi.getMedicineName());
				}
			}
		} catch (FeignException e) {
			log.error("TOKEN VALIDATION FAILED");
			throw new TokenValidationFailedException("TOKEN EXPIRED");
		}
		log.info("END");
		
		return ResponseEntity.ok(medicineNames.toArray(new String[0]));
	}
	
	/*
	 * 4. Get all stocks by medicine
	 * url : http://localhost:8080/medicine/getByMedicine/Orthoherb
	*/
	/**
	 * Gets the stocks by medicine name.
	 *
	 * @param  the medicine name
	 * @return the stocks by medicine name
	 * @throws TokenValidationFailedException 
	 */
	@GetMapping("/getByMedicine/{medicineName}")
	public ResponseEntity<MedicineStock> getStocksByMedicineName(@RequestHeader(name = "Authorization") String token, @PathVariable String medicineName) throws TokenValidationFailedException
	{
		log.info("START");

		MedicineStock medicineStock = null;
		try {
			JwtResponse jwtResponse = authFeignClient.verifyToken(token);
			if (jwtResponse.isValid()) {
				medicineStock =iservice.getByMedicineName(medicineName);
			}
		} catch (FeignException e) {
			log.info("EXCEPTION: TOKEN EXPIRED");
			throw new TokenValidationFailedException("TOKEN EXPIRED");
		}
		log.info("END");
		return new ResponseEntity<>(medicineStock, HttpStatus.OK);
	}
	/*
	 * 5. Update count by medicine
	 * url : http://localhost:8080/medicine/updateCount/
	*/
	
	@GetMapping("/updateCount/{medicineName}/{count}")
	public Boolean updateNumberOfTabletsInStock(@RequestHeader(name = "Authorization") String token, @PathVariable String medicineName,@PathVariable Integer count) throws TokenValidationFailedException
	{
		log.info("START");
		Boolean isUpdated = false;

		try {
			JwtResponse jwtResponse = authFeignClient.verifyToken(token);
			if (jwtResponse.isValid()) {
				isUpdated =iservice.updateNumberOfTabletsInStockByMedicineName(medicineName, count);
			}
			getStocksByMedicineName(token, medicineName);
		} catch (FeignException e) {
			log.info("EXCEPTION : TOKEN EXPIRED");
			throw new TokenValidationFailedException("TOKEN EXPIRED");
		}
		log.info("END");
		return isUpdated;
	}
	
}
