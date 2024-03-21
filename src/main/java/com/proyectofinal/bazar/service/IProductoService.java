package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Producto;
import java.util.List;

public interface IProductoService {
    
    public void saveProducto(Producto p);
    public List<Producto> getAll();
    public Producto getProductoById(Long id);
    public void deleteProducto(Long id);
    public void editProducto(Long id, String nombreNuevo, String marcaNueva, Double costoNuevo, Double cantidadNueva);

    public List<Producto> getFaltantes();
}
