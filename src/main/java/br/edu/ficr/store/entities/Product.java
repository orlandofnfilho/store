package br.edu.ficr.store.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

@Entity
@Table(name = "product")
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
	@ApiModelProperty(value = "category", position = 8)
	private Category category;

	@ManyToMany
	@JoinTable(name = "Products_Suppliers", uniqueConstraints = @UniqueConstraint(columnNames = { "product_id",
			"supplier_id" }), joinColumns = @JoinColumn(name = "supplier_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	@ApiModelProperty(value = "suppliers", position = 9, required = false)
	private Set<Supplier> suppliers = new HashSet<>();

	public Product() {

	}

	public Product(String name, BigDecimal price, BigDecimal weight, String brand, String sku, String description,
			Category category) {
		super();
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.brand = brand;
		this.sku = sku;
		this.description = description;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", weight=" + weight + ", brand=" + brand
				+ ", sku=" + sku + ", description=" + description + ", category=" + category + ", suppliers="
				+ suppliers + "]";
	}

	
}
