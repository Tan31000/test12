package fr.orsys.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("INTER")
@Data @AllArgsConstructor @NoArgsConstructor
public class FormationInter extends Formation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Min(1)
	@Max(10)
private Integer nbEntreprise;
}
