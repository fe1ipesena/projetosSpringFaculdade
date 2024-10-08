package com.roupas.app.services;

import com.roupas.app.entity.Funcionario;
import com.roupas.app.entity.Produto;
import com.roupas.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public String save(Produto produto){
        produtoRepository.save(produto);
        return "Produto salvo com sucesso.";
    }

    public List<Produto> findall(){
        List<Produto> produtos = produtoRepository.findAll();
        if(produtos.isEmpty()){
            throw new RuntimeException("Não há produtos registrados!");
        }else{
            return produtos;
        }
    }

    public Produto findById(long id){
        if(produtoRepository.existsById(id)){
            Optional<Produto> produto = produtoRepository.findById(id);
            return produto.get();
        }else{
            throw new RuntimeException("Produto nao encontrado com id "+id);
        }
    }

    //metodos de filtro automatico
    public List<Produto> findByProduct(String product) {
        return produtoRepository.findByProduct(product);
    }

    public List<Produto> findByValueBetween(BigDecimal minValue, BigDecimal maxValue) {
        return produtoRepository.findByValueBetween(minValue, maxValue);
    }

    public List<Produto> findByValueGreaterThan(BigDecimal value) {
        return produtoRepository.findByValueGreaterThan(value);
    }

    public void deleteById(long id){
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
        }else{
            throw new RuntimeException("Produto nao encontrado com id "+id);
        }
    }

    public Produto updateById(long id, Produto updatedProduto){
                return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setProduct(updatedProduto.getProduct());
                    produto.setValue(updatedProduto.getValue());

                    return produtoRepository.save(produto);
                })
                .orElseThrow(()-> new RuntimeException("Produto não encontrado com id: " + id));
    }


}
