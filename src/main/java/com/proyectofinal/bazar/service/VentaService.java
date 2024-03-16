package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;
    
    @Override
    public void saveVenta(Venta v) {
        ventaRepo.save(v);
    }

    @Override
    public List<Venta> getAll() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta getVentaById(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        return v;
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }

    @Override
    public void editVenta(Long id, LocalDate fechaNueva, Double totalNuevo, List<Producto> productosNuevos, Cliente clienteNuevo) {
        Venta v = this.getVentaById(id);
        if (v != null){
           if (fechaNueva != null) v.setFecha_venta(fechaNueva);
           if (totalNuevo != null) v.setTotal(totalNuevo);
           if (productosNuevos != null) v.setListaProductos(productosNuevos);
           if (clienteNuevo != null) v.setCliente(clienteNuevo);
       }
       this.saveVenta(v);
    }
    
}
