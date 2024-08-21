package biblioteca.bibliotecaApp.services;

import biblioteca.bibliotecaApp.entity.Funcionario;
import biblioteca.bibliotecaApp.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save (Funcionario funcionario){
        funcionarioRepository.save(funcionario);
        return "Salvo com sucesso";
    }

    public void deleteById(long id){
        if(funcionarioRepository.existsById(id)){
            funcionarioRepository.deleteById(id);
        }else{
            throw new RuntimeException("funcionario nao encontrado com id "+id);
        }
    }

    public Funcionario findById (long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.get();
    }

    public List<Funcionario> findAll(){
        return funcionarioRepository.findAll();
    }

    public Funcionario updateById(long id, Funcionario updatedFuncionario){
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(updatedFuncionario.getNome());
                    funcionario.setCpf(updatedFuncionario.getCpf());
                    funcionario.setEmail(updatedFuncionario.getEmail());
                    funcionario.setCep(updatedFuncionario.getCep());
                    funcionario.setEndereco(updatedFuncionario.getEndereco());
                    funcionario.setTelefone(updatedFuncionario.getTelefone());
                    funcionario.setBirth(updatedFuncionario.getBirth());

                    return funcionarioRepository.save(funcionario);
                })
                .orElseThrow(()-> new RuntimeException("funcionario não encontrado com id: " + id));
    }


}
