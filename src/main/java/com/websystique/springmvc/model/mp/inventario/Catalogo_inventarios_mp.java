package com.websystique.springmvc.model.mp.inventario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_INVENTARIOS_MP")
public class Catalogo_inventarios_mp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_inicio;
	@NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_fin;
	@Size(min = 1, max = 100)
	private String descripcion;
	@Column(updatable=false)
	private Integer usuario_insert;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") @Column(updatable=false)
	private Date fecha_insert;
	private Integer usuario_update;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_update;
	
	/*@Transient
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_inv")
	@Basic(optional = false, fetch = FetchType.EAGER)
	private List<Conteo_inventario_mp> ListaConteos = new ArrayList<Conteo_inventario_mp>(); */
}
