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
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        if(funcionarios.isEmpty()){
            throw new RuntimeException("Não há funcionários registrados!");
        }else{
            return funcionarios;
        }
    }

    public Funcionario findById(long id){
        if(funcionarioRepository.existsById(id)){
            Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
            return funcionario.get();
        }else{
            throw new RuntimeException("Funcionario nao encontrado com id "+id);
        }
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
                .map(funcionario -> {
                    funcionario.setName(updatedFuncionario.getName());
                    funcionario.setAge(updatedFuncionario.getAge());
                    funcionario.setRegistration(updatedFuncionario.getRegistration());
                    funcionario.setVenda(updatedFuncionario.getVenda());

                    return funcionarioRepository.save(funcionario);
                })
                .orElseThrow(()-> new RuntimeException("Funcionario não encontrado com id: " + id));
    }

}
