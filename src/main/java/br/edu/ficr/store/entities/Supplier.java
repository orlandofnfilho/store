package br.edu.ficr.store.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="supplier")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", position = 1)
	private Long id;
	@ApiModelProperty(value = "name", position = 2)
	private String name;
	@ApiModelProperty(value = "phone", position = 3)
	private String phone;
	@ApiModelProperty(value = "email", position = 4)
	private String email;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Products_Suppliers", uniqueConstraints = @UniqueConstraint(columnNames = { "product_id",
			"supplier_id" }), joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
	@ApiModelProperty(value = "products", position = 5)
	private List<Product> products = new ArrayList<>();

}
