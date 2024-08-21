package biblioteca.bibliotecaApp.services;

import biblioteca.bibliotecaApp.entity.Venda;
import biblioteca.bibliotecaApp.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public String save (Venda venda){
        vendaRepository.save(venda);
        return "Salvo com sucesso";
    }

    public void deleteById(long id){
        if(vendaRepository.existsById(id)){
            vendaRepository.deleteById(id);
        }else{
            throw new RuntimeException("venda nao encontrada com id "+id);
        }
    }

    public Venda findById (long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        return venda.get();
    }

    public List<Venda> findAll(){
        return vendaRepository.findAll();
    }

    public Venda updateById(long id, Venda updatedVenda){
        return vendaRepository.findById(id)
                .map(venda -> {
                    venda.setCliente(updatedVenda.getCliente());
                    venda.setTitulos(updatedVenda.getTitulos());
                    venda.setObservacao(updatedVenda.getObservacao());
                    venda.setValorTotal(updatedVenda.getValorTotal());
                    return vendaRepository.save(venda);
                })
                .orElseThrow(()-> new RuntimeException("venda não encontrada com id: " + id));
    }

}
