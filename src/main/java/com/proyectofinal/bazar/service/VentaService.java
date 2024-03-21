package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.DetalleVentaDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public List<Producto> getProductoByIdVenta(Long id) {
        Venta v = this.getVentaById(id);
        return v.getListaProductos();
    }

    @Override
    public String getInfoVentasByDate(LocalDate fecha) {
        List<Venta> todas = this.getAll();
        Double montoTotal = 0.0;
        int cantidadTotal = 0;
        String info = "";
        for(Venta v: todas){
            if(v.getFecha_venta().equals(fecha)){
                montoTotal += v.getTotal();
                cantidadTotal++;
            }
        }
        info = "Cantidad de ventas = " + cantidadTotal + ".\nMonto total = $" + montoTotal +".";
        return info;
    }

    @Override
    public DetalleVentaDTO getDetalleVentaMayor() {
        DetalleVentaDTO detalleVenta = new DetalleVentaDTO();
        Venta mayor = new Venta();
        boolean esPrimera = true;
        List<Venta> todas = this.getAll();
        for(Venta v : todas){
            if(esPrimera){
                mayor = v;
                esPrimera = false;
            }else if (v.getTotal() > mayor.getTotal()){
                mayor = v;
            }
        }
        detalleVenta.setCodigoVenta(mayor.getCodigo_venta());
        detalleVenta.setTotal(mayor.getTotal());
        detalleVenta.setCantidadProductos(mayor.getListaProductos().size());
        detalleVenta.setNombreCliente(mayor.getCliente().getNombre());
        detalleVenta.setApellidoCliente(mayor.getCliente().getApellido());
       
        return detalleVenta;
    }
    
}
