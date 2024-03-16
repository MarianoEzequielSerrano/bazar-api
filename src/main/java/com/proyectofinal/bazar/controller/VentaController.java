package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.service.IVentaService;
import java.time.LocalDate;
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
public class VentaController {
    
    @Autowired
    private IVentaService ventaServ;
    
    @PostMapping("/ventas/crear")
    public String crearVenta(@RequestBody Venta v){
        ventaServ.saveVenta(v);
        return "Venta creada exitosamente";
    }
    
    @GetMapping("/ventas")
    public List<Venta> traerTodas(){
        return ventaServ.getAll();
    }
    
    @GetMapping("/ventas/{code}")
    public Venta traerByCode (@PathVariable Long code){
        Venta v = ventaServ.getVentaById(code);
        return v;
    }
    
    @DeleteMapping("/ventas/eliminar/{code}")
    public String eliminarVenta (@PathVariable Long code){
        ventaServ.deleteVenta(code);
        return "Venta eliminada exitosamente";
    }
    
    @PutMapping("/productos/editar/{code}")
    public String editarVenta (@PathVariable Long code,
                                @RequestParam LocalDate fechaNueva,
                                @RequestParam Double totalNuevo,
                                @RequestParam List<Producto> productosNuevos,
                                @RequestParam Cliente clienteNuevo){
        ventaServ.editVenta(code, fechaNueva, totalNuevo, productosNuevos, clienteNuevo);
        return "Venta editada exitosamente";
    }
}
