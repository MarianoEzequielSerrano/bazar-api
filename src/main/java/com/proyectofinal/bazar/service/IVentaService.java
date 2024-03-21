package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    
    public void saveVenta(Venta v);
    public List<Venta> getAll();
    public Venta getVentaById(Long id);
    public void deleteVenta(Long id);
    public void editVenta(Long id, LocalDate fechaNueva, Double totalNuevo, List<Producto> productosNuevos, Cliente clienteNuevo);
    public List<Producto> getProductoByIdVenta (Long id);
    public String getInfoVentasByDate(LocalDate fecha);
}
