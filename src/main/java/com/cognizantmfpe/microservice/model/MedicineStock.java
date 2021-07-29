package com.cognizantmfpe.microservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





/**
 * Instantiates a new medicine stock.
 *
 * @param mid the mid
 * @param medicineName the medicine name
 * @param chemicalComposition the chemical composition
 * @param targetAilment the target ailment
 * @param pharmacyName the pharmacy name
 * @param dateOfExpiry the date of expiry
 * @param numberOfTabletsInStock the number of tablets in stock
 */
@Data
@Table(name="medicine_stock")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MedicineStock {

	/** The mid. */
	@Id
	@GeneratedValue
	private Integer mid;

	/** The medicine name. */
	@Column
	private String medicineName;

	/**  The chemical composition List. */
	@Column
	private String chemicalComposition;
	
	/** The date of expiry. */
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfExpiry;
	
	/** The number of tablets in stock. */
	@Column
	private Integer numberOfTabletsInStock;
	
	/** The pharmacy name. */
	@Column
	private String pharmacyName;

	/** The target ailment. */
	@Column
	private String targetAilment;
	
	

	

	

}
