package biblioteca.bibliotecaApp.services;

import biblioteca.bibliotecaApp.entity.Cliente;
import biblioteca.bibliotecaApp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import biblioteca.bibliotecaApp.entity.Titulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String save (Cliente cliente){
        clienteRepository.save(cliente);
        return "Salvo com sucesso";
    }

    public void deleteById(long id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }else{
            throw new RuntimeException("cliente nao encontrado com id "+id);
        }
    }

    public Cliente findById (long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente updateById(long id, Cliente updatedCliente){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(updatedCliente.getNome());
                    cliente.setCpf(updatedCliente.getCpf());
                    cliente.setEmail(updatedCliente.getEmail());
                    cliente.setCep(updatedCliente.getCep());
                    cliente.setEndereco(updatedCliente.getEndereco());
                    cliente.setTelefone(updatedCliente.getTelefone());
                    cliente.setBirth(updatedCliente.getBirth());

                    return clienteRepository.save(cliente);
                })
                .orElseThrow(()-> new RuntimeException("cliente não encontrado com id: " + id));
    }

}
