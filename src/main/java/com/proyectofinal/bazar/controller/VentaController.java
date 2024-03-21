package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.DetalleVentaDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    
    @PutMapping("/ventas/editar/{code}")
    public String editarVenta (@PathVariable Long code,
                                @RequestParam (required = false) LocalDate fechaNueva,
                                @RequestParam (required = false) Double totalNuevo,
                                @RequestParam (required = false) List<Producto> productosNuevos,
                                @RequestParam (required = false) Cliente clienteNuevo){
        ventaServ.editVenta(code, fechaNueva, totalNuevo, productosNuevos, clienteNuevo);
        return "Venta editada exitosamente";
    }
    
    @GetMapping("ventas/productos/{code}")
    public List<Producto> traerProductos (@PathVariable Long code){
        return ventaServ.getProductoByIdVenta(code);
    }
    
    @GetMapping("ventas/fecha/{fecha}")
    public String traerInfoByDate (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fecha){
        return ventaServ.getInfoVentasByDate(fecha);
    }
    
    @GetMapping("ventas/mayor_venta")
    public DetalleVentaDTO traerDetalleVentaMayor (){
        return ventaServ.getDetalleVentaMayor();
    }
}
