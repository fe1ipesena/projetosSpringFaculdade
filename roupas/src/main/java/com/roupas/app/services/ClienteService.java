package com.roupas.app.services;


import com.roupas.app.entity.Cliente;
import com.roupas.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private String save (Cliente cliente){
        clienteRepository.save(cliente);
        return "Cliente salvo com sucesso";
    }

    public List<Cliente> findall(){
        return clienteRepository.findAll();
    }

}
