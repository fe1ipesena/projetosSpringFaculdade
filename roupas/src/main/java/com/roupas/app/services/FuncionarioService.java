package com.roupas.app.services;


import com.roupas.app.entity.Funcionario;
import com.roupas.app.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
        return "Funcionario salvo com sucesso.";
    }

    public List<Funcionario> findall(){
        return funcionarioRepository.findAll();

    }

    public Funcionario findById(long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.get();
    }

    public void deleteById(long id){
        if(funcionarioRepository.existsById(id)){
            funcionarioRepository.deleteById(id);
        }else{
            throw new RuntimeException("Funcionario nao encontrado com id "+id);
        }
    }

    public Funcionario updateById(long id, Funcionario updatedFuncionario){
                return funcionarioRepository.findById(id)
                .map(cliente -> {
                    cliente.setName(updatedFuncionario.getName());
                    cliente.setAge(updatedFuncionario.getAge());
                    cliente.setRegistration(updatedFuncionario.getRegistration());
                    cliente.setVenda(updatedFuncionario.getVenda());

                    return funcionarioRepository.save(cliente);
                })
                .orElseThrow(()-> new RuntimeException("Funcionario não encontrado com id: " + id));
    }

}
