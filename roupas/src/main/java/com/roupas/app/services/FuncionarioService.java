package com.roupas.app.services;


import com.roupas.app.entity.Funcionario;
import com.roupas.app.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save(Funcionario funcionario){
        funcionario.setRegistration(generateUniqueRegistrationNumber());
        funcionarioRepository.save(funcionario);
        return "Funcionario salvo com sucesso.";
    }

    private String generateUniqueRegistrationNumber() {
        String registrationNumber;
        do {
            registrationNumber = generateRegistrationNumber();
        } while (funcionarioRepository.existsByRegistration(registrationNumber));
        return registrationNumber;
    }

    private String generateRegistrationNumber() {
        Random random = new Random();
        int number = random.nextInt((int) Math.pow(10, 5));
        String formattedNumber = String.format("%0" + 5 + "d", number);
        return "RN" + formattedNumber;
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

    // metodos de filtro automatico
    public List<Funcionario> findByName(String name) {
        return funcionarioRepository.findByName(name);
    }

    public List<Funcionario> findByRegistration(String registration) {
        return funcionarioRepository.findByRegistration(registration);
    }

    public List<Funcionario> findByAgeGreaterThanEqual(int age) {
        return funcionarioRepository.findByAgeGreaterThanEqual(age);
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

                    return funcionarioRepository.save(funcionario);
                })
                .orElseThrow(()-> new RuntimeException("Funcionario não encontrado com id: " + id));
    }

}
