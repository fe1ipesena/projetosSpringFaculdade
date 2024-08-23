package com.roupas.app.services;


import com.roupas.app.entity.Cliente;
import com.roupas.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String save(Cliente cliente){
        clienteRepository.save(cliente);
        return "Cliente salvo com sucesso";
    }

    public List<Cliente> findall(){
        List<Cliente> clientes = clienteRepository.findAll();
        if(clientes.isEmpty()){
            throw new RuntimeException("Não há clientes registrados!");
        }else{
            return clientes;
        }
    }

    public Cliente findById(long id){
        if(clienteRepository.existsById(id)){
            Optional<Cliente> cliente = clienteRepository.findById(id);
            return cliente.get();
        }else{
            throw new RuntimeException("Cliente nao encontrado com id "+id);
        }
    }

    public void deleteById(long id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }else{
            throw new RuntimeException("cliente nao encontrado com id "+id);
        }
    }

    public Cliente updateById(long id, Cliente updatedCliente){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setName(updatedCliente.getName());
                    cliente.setCpf(updatedCliente.getCpf());
                    cliente.setAge(updatedCliente.getAge());
                    cliente.setPhone(updatedCliente.getPhone());
                    cliente.setVenda(updatedCliente.getVenda());

                    return clienteRepository.save(cliente);
                })
                .orElseThrow(()-> new RuntimeException("cliente não encontrado com id: " + id));
    }

}
