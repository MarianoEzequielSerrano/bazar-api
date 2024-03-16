package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.service.IClienteService;
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
public class ClienteController {
    
    @Autowired
    private IClienteService clienteServ;
    
    @PostMapping("/clientes/crear")
    public String crearCliente(@RequestBody Cliente c){
        clienteServ.saveCliente(c);
        return "Cliente creado exitosamente";
    }
    
    @GetMapping("/clientes")
    public List<Cliente> traerTodos(){
        return clienteServ.getAll();
    }
    
    @GetMapping("/clientes/{id}")
    public Cliente traerById(@PathVariable Long id){
        Cliente c = clienteServ.getClienteById(id);
        return c;
    }
    
    @DeleteMapping("/clientes/eliminar/{id}")
    public String eliminarCliente (@PathVariable Long id){
        clienteServ.deleteCliente(id);
        return "Cliente eliminado exitosamente";
    }
    
    @PutMapping("/clientes/editar/{id}")
    public String editarCliente (@PathVariable Long id,
                                @RequestParam(required = false) String nombre,
                                @RequestParam (required = false) String apellido,
                                @RequestParam (required = false) String dni){
        clienteServ.editCliente(id, nombre, apellido, dni);
        return "Cliente editado exitosamente";
    }
}
