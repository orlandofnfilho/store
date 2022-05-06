package br.edu.ficr.store.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
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
	private BigDecimal price;
	@ApiModelProperty(value = "weight", position = 4)
	private BigDecimal weight;
	@ApiModelProperty(value = "brand", position = 5)
	private String brand;
	@ApiModelProperty(value = "sku", position = 6)
	private String sku;
	@ApiModelProperty(value = "description", position = 7)
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@ApiModelProperty(value = "category", position = 8, required = false)
	private Category category;

	
	@ManyToMany
	@JoinTable(name = "Products_Suppliers", uniqueConstraints = @UniqueConstraint(columnNames = { "product_id",
			"supplier_id" }), joinColumns = @JoinColumn(name = "supplier_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	@ApiModelProperty(value = "supplier", position = 9)
	private List<Supplier> suppliers = new ArrayList<>();

	
	
}
