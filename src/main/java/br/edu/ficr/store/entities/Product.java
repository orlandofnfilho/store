package br.edu.ficr.store.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", position = 1)
	private Long id;
	@ApiModelProperty(value = "name", position = 2)
	private String name;
	@ApiModelProperty(value = "price", position = 3)
	private Double price;
	@ApiModelProperty(value = "quantity", position = 4)
	private Integer quantity;
	@ApiModelProperty(value = "weight", position = 5)
	private Double weight;
	@ApiModelProperty(value = "brand", position = 6)
	private String brand;
	@ApiModelProperty(value = "ean", position = 7)
	private String ean;
	@ApiModelProperty(value = "description", position = 8)
	private String description;
	@ApiModelProperty(value = "status", position = 9)
	private StockStatus status;
	@ApiModelProperty(value = "updatedAt", position = 10)
	private Instant updatedAt;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@ApiModelProperty(value = "category", position = 11)
	private Category category;

	@ManyToOne
	@ApiModelProperty(value = "supplier", position = 12)
	private Supplier supplier;

}
