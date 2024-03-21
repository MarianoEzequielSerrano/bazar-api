package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    private IProductoRepository productoRepo;
    
    @Override
    public void saveProducto(Producto p) {
        productoRepo.save(p);
    }

    @Override
    public List<Producto> getAll() {
        return productoRepo.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        Producto p = productoRepo.findById(id).orElse(null);
        return p;
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public void editProducto(Long id, String nombreNuevo, String marcaNueva, Double costoNuevo, Double cantidadNueva) {
       Producto p = this.getProductoById(id);
       if(p != null){
        if (nombreNuevo != null) p.setNombre(nombreNuevo);
        if (marcaNueva != null) p.setMarca(marcaNueva);
        if (costoNuevo != null) p.setCosto(costoNuevo);
        if (cantidadNueva != null) p.setCantidad_disponible(cantidadNueva);
       }
       this.saveProducto(p);
    }
    
    @Override
    public List<Producto> getFaltantes(){
        List<Producto> faltantes = new ArrayList<>();
        List <Producto> todos = this.getAll();
        for(Producto p : todos){
            if(p.getCantidad_disponible() < 5) faltantes.add(p);
        }
        return faltantes;
    }
}
