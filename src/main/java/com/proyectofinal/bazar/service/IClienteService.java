package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    public void saveCliente(Cliente c);
    public List<Cliente> getAll();
    public Cliente getClienteById(Long id);
    public void deleteCliente(Long id);
    public void editCliente(Long id, String nombreNuevo, String apellidoNuevo, String dni);

}
