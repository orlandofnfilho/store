package br.edu.ficr.store.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", position = 1, required = false)
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inventory_id", referencedColumnName = "id")
	@ApiModelProperty(value = "inventory", position = 9, required = false)
	private Inventory inventory;

	@ManyToMany
	@JoinTable(name = "Products_Suppliers", uniqueConstraints = @UniqueConstraint(columnNames = { "product_id",
			"supplier_id" }), joinColumns = @JoinColumn(name = "supplier_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	@ApiModelProperty(value = "supplier", position = 10, required = false)
	private List<Supplier> suppliers = new ArrayList<>();

}
