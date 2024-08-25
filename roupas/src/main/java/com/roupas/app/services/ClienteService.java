package com.roupas.app.services;


import com.roupas.app.entity.Cliente;
import com.roupas.app.entity.Venda;
import com.roupas.app.repository.ClienteRepository;
import com.roupas.app.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendaRepository vendaRepository;

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
        // verifica se o cliente existe
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id " + id));

        // removendo todas as vendas associadas ao cliente
        List<Venda> vendas = vendaRepository.findByCliente(cliente);
        if (!vendas.isEmpty()) {
            vendaRepository.deleteAll(vendas);
        }

        // agora pode deletar o cliente
        clienteRepository.deleteById(id);
    }

    public Cliente updateById(long id, Cliente updatedCliente){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setName(updatedCliente.getName());
                    cliente.setCpf(updatedCliente.getCpf());
                    cliente.setAge(updatedCliente.getAge());
                    cliente.setPhone(updatedCliente.getPhone());

                    return clienteRepository.save(cliente);
                })
                .orElseThrow(()-> new RuntimeException("cliente não encontrado com id: " + id));
    }

}
