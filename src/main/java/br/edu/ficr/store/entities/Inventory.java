package br.edu.ficr.store.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ficr.store.entities.enums.StockStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", position = 1)
	private Long id;

	@ApiModelProperty(value = "quantity", position = 2)
	private Integer unitQt;

	@ApiModelProperty(value = "quantity", position = 3)
	private Instant updateAt;

	@ApiModelProperty(value = "status", position = 4)
	private StockStatus status;

	@ApiModelProperty(value = "product", position = 5, hidden = true)
	@OneToOne(mappedBy = "inventory")
	@JsonIgnore
	private Product product;

}
