package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "entradaAlmacen")
public class EntradaAlmacen implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    private String numeroPedido;
    @Id
    private String fechaEntAlm;
    private int piezasEntregadas;
    @Id
    private String horaEntrega;
}
