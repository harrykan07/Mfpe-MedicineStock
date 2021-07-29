package com.cognizantmfpe.microservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizantmfpe.microservice.model.MedicineStock;

/**
 * The Interface MedicineStockInterface.
 */
@Repository
public interface MedicineStockInterface extends CrudRepository<MedicineStock, Integer> {
	
	/**
	 * Find List of Stock by target ailment.
	 *
	 * @param targetAilment the target ailment
	 * @return the list
	 */
	List<MedicineStock> findByTargetAilment(String targetAilment);

	/**
	 * Find List of Stock by medicine name.
	 *
	 * @param medicineName the medicine name
	 * @return the medicine stock
	 */
	MedicineStock findByMedicineName(String medicineName);
	
	
	/**
	 * Update the number of tablets in stock by name.
	 *
	 * @param medicineName the medicine name
	 * @param count: the count
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE MEDICINE_STOCK M SET M.NUMBER_OF_TABLETS_IN_STOCK =?2 where M.MEDICINE_NAME =?1", nativeQuery = true)
	Integer updateNumberOfTabletsInStockByName(String medicineName, int count);
}
