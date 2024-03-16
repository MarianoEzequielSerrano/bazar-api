package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    
    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public void saveCliente(Cliente c) {
        clienteRepo.save(c);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        Cliente c = clienteRepo.findById(id).orElse(null);
        return c;
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public void editCliente(Long id, String nombreNuevo, String apellidoNuevo, String dni) {
        Cliente c = this.getClienteById(id);
        if (c != null){
           if (nombreNuevo != null) c.setNombre(nombreNuevo);
           if (apellidoNuevo != null) c.setApellido(apellidoNuevo);
           if (dni != null) c.setDni(dni);
       }
       this.saveCliente(c);
    }
    
    
}
