package com.proyectofinal.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DetalleVentaDTO {
    private Long codigoVenta;
    private Double total;
    private int cantidadProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public DetalleVentaDTO() {
    }    
}
