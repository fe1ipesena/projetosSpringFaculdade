package com.roupas.app.services;

import com.roupas.app.entity.Cliente;
import com.roupas.app.entity.Funcionario;
import com.roupas.app.entity.Produto;
import com.roupas.app.entity.Venda;
import com.roupas.app.repository.ClienteRepository;
import com.roupas.app.repository.FuncionarioRepository;
import com.roupas.app.repository.ProdutoRepository;
import com.roupas.app.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda save(Venda venda) {

        // garantindo que os produtos estejam no estado "managed"
        List<Produto> managedProducts = findProductsById(venda.getProducts());
        venda.setProducts(managedProducts);

        // garantindo que o cliente e o funcionário estejam no estado "managed"
        Cliente managedCliente = findClienteById(venda.getCustomer().getId());
        venda.setCustomer(managedCliente);

        Funcionario managedFuncionario = findFuncionarioById(venda.getEmployee().getId());
        venda.setEmployee(managedFuncionario);

        // calculo do valor total
        BigDecimal totalValue = sumOfValues(managedProducts);
        venda.setTotalValue(totalValue);

        // aqui persiste a venda
        return vendaRepository.save(venda);
    }

    //validar se as entidades chamadas no save existem

    public List<Produto> findProductsById(List<Produto> produtos) {
        List<Produto> validProducts = new ArrayList<>();
        for (Produto produto : produtos) {
            Long id = produto.getId();
            Produto foundProduto = produtoRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Produto não encontrado com id " + id));
            validProducts.add(foundProduto);
        }
        return validProducts;
    }

    public Cliente findClienteById(long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado com id " + id));
    }

    public Funcionario findFuncionarioById(long id) {
        return funcionarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Funcionario não encontrado com id " + id));
    }

    public BigDecimal sumOfValues(List<Produto> produtos) {
        BigDecimal total_value = BigDecimal.ZERO; //tem q usar os metodos do bigdecimal, aqui inicia com zero

        for (Produto produto : produtos) {
            total_value = total_value.add(produto.getValue());//o metodo add soma os valores, assim como se fosse um +=
        }

        return total_value;
    }

    public List<Venda> findall() {
        List<Venda> vendas = vendaRepository.findAll();
        if (vendas.isEmpty()) {
            throw new RuntimeException("Não há sales registradas!");
        } else {
            return vendas;
        }
    }

    public Venda findById(long id) {
        if (vendaRepository.existsById(id)) {
            Optional<Venda> venda = vendaRepository.findById(id);
            return venda.get();
        } else {
            throw new RuntimeException("Venda não encontrada com id " + id);
        }
    }

    //metodos de filtro automatico

    public List<Venda> findByDeliveryAdress(String deliveryAdress) {
        return vendaRepository.findByDeliveryAdress(deliveryAdress);
    }

    public List<Venda> findByTotalValueGreaterThan(BigDecimal totalValue) {
        return vendaRepository.findByTotalValueGreaterThan(totalValue);
    }

    public List<Venda> findByTotalValueBetween(BigDecimal minValue, BigDecimal maxValue) {
        return vendaRepository.findByTotalValueBetween(minValue, maxValue);
    }

    public void deleteById(long id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Venda não encontrado com id " + id);
        }
    }

    public Venda updateById(long id, Venda venda) {
        if (vendaRepository.existsById(id)) {
            // Carregar a venda existente para atualização
            Venda existingVenda = vendaRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Venda não encontrada com id " + id));

            // Atualizar os dados da venda existente
            existingVenda.setDeliveryAdress(venda.getDeliveryAdress());
            existingVenda.setCustomer(findClienteById(venda.getCustomer().getId()));
            existingVenda.setEmployee(findFuncionarioById(venda.getEmployee().getId()));
            existingVenda.setProducts(findProductsById(venda.getProducts()));
            existingVenda.setTotalValue(sumOfValues(existingVenda.getProducts()));

            // Salvar a venda atualizada
            vendaRepository.save(existingVenda);
            return existingVenda;
        } else {
            throw new RuntimeException("Venda não encontrada com id: " + id);
        }
    }


}