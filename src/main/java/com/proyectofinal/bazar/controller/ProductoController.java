package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
    
    @Autowired
    private IProductoService productoServ;
    
    @PostMapping("/productos/crear")
    public String crearProducto(@RequestBody Producto p){
        productoServ.saveProducto(p);
        return "Producto creado exitosamente";
    }
    
    @GetMapping("/productos")
    public List<Producto> traerTodos(){
        return productoServ.getAll();
    }
    
    @GetMapping("/productos/{code}")
    public Producto traerByCode (@PathVariable Long code){
        Producto p = productoServ.getProductoById(code);
        return p;
    }
    
    @DeleteMapping("/productos/eliminar/{code}")
    public String eliminarProducto (@PathVariable Long code){
        productoServ.deleteProducto(code);
        return "Producto eliminado exitosamente";
    }
    
    @PutMapping("/productos/editar/{code}")
    public String editarProducto (@PathVariable Long code, 
                                  @RequestParam(required = false) String nombre,
                                  @RequestParam(required = false) String marca,
                                  @RequestParam(required = false) Double costo,
                                  @RequestParam(required = false) Double cantidad){
        productoServ.editProducto(code, nombre, marca, costo, cantidad);
        return "Producto editado exitosamente";
    }
}
