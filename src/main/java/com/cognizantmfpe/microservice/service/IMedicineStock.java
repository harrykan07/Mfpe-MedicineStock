package com.cognizantmfpe.microservice.service;

import java.util.List;

import com.cognizantmfpe.microservice.model.MedicineStock;

/**
 * The Interface IMedicineStock.
 */
public interface IMedicineStock {


	/**
	 * Gets all medicines.
	 *
	 * @return the all medicines
	 */
	public List<MedicineStock> getAllMedicines();

	/**
	 * Gets the medicineStockList by target ailment.
	 *
	 * @param ailment the ailment
	 * @return the by target ailment
	 */
	public List<MedicineStock> getByTargetAilment(String ailment);

	/**
	 * Gets the medicineStock by medicine name.
	 *
	 * @param medicineName the medicine name
	 * @return the by medicine name
	 */
	public MedicineStock getByMedicineName(String medicineName);
	
	/**
	 * Update number of tablets in stock by medicine name.
	 *
	 * @param medicine the medicine
	 * @param updatedCount: the count
	 * @return the boolean
	 */
	public Boolean updateNumberOfTabletsInStockByMedicineName(String medicineName, int updatedCount);

}
