package com.cognizantmfpe.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizantmfpe.microservice.exception.MedicineNotFoundException;
import com.cognizantmfpe.microservice.exception.TreatingAilmentNotFoundException;
import com.cognizantmfpe.microservice.model.MedicineStock;
import com.cognizantmfpe.microservice.repo.MedicineStockInterface;

import lombok.extern.slf4j.Slf4j;

/**
 * The Service Class MedicineStockImpl.
 */
@Service
@Slf4j
public class MedicineStockImpl implements IMedicineStock {

	/** The autowired mrepo. */
	@Autowired
	private MedicineStockInterface mrepo;

	/**
	 * Gets all medicines.
	 *
	 * @return the all medicines
	 */
	@Override
	public List<MedicineStock> getAllMedicines() {
		log.info("START");
		log.info("END");
		return (List<MedicineStock>) mrepo.findAll();
	}

	/**
	 * Gets by target ailment.
	 *
	 * @param ailment: the ailment
	 * @return the by target ailment
	 */
	@Override
	public List<MedicineStock> getByTargetAilment(String ailment) {
		log.info("START");
		List<MedicineStock> ms = null;
		ms = mrepo.findByTargetAilment(ailment);
		if (ms.isEmpty())
			throw new TreatingAilmentNotFoundException("TREATING AILMENT NOT FOUND IN THE DATABASE");
		log.info("END");

		return ms;
	}

	/**
	 * Gets by medicine name.
	 *
	 * @param medicineName: the medicine name
	 * @return the by medicine name
	 */
	@Override
	public MedicineStock getByMedicineName(String medicineName) {
		log.info("START");
		MedicineStock stockByName = null;
		stockByName = mrepo.findByMedicineName(medicineName);
		if (stockByName == null)
			throw new MedicineNotFoundException("GIVEN MEDICINE NOT FOUND IN THE DATABASE");
		log.debug("NUMBER OF TABLETS IN STOCK BY NAME {}:", stockByName);
		log.info("END");

		return stockByName;
	}

	/**
	 * Update number of tablets in stock by medicine name.
	 *
	 * @param medicineName the medicine name
	 * @param updatedCount the updated count
	 * @return the boolean if update success or not
	 */
	@Override
	public Boolean updateNumberOfTabletsInStockByMedicineName(String medicineName, int updatedCount) {
		log.info("START");
		log.info(medicineName + " ############# " + updatedCount);
		log.info("END");
		Integer updatedRows = mrepo.updateNumberOfTabletsInStockByName(medicineName, updatedCount);
		if (updatedRows > 0)
			return true;
		else
			return false;
	}
}