package br.edu.ficr.store.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", position = 1, required = false)
	private Long id;
	@ApiModelProperty(value = "name", position = 2)
	private String name;
	@ApiModelProperty(value = "phone", position = 3)
	private String phone;
	@ApiModelProperty(value = "email", position = 4)
	private String email;

	@JsonIgnore
	@ManyToMany
	@ApiModelProperty(value = "products", position = 5, required = false)
	private List<Product> products;

}
