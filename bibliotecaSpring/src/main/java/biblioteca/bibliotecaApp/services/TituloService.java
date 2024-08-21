package biblioteca.bibliotecaApp.services;

import biblioteca.bibliotecaApp.entity.Titulo;
import biblioteca.bibliotecaApp.repository.TituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TituloService {

    @Autowired
    private TituloRepository tituloRepository;

    public String save (Titulo titulo){
        tituloRepository.save(titulo);
        return "Salvo com sucesso";
    }

    public void deleteById(long id){
        if(tituloRepository.existsById(id)){
            tituloRepository.deleteById(id);
        }else{
            throw new RuntimeException("Livro nao encontrado com id "+id);
        }
    }

    public Titulo findById (long id) {
        Optional<Titulo> titulo = tituloRepository.findById(id);
        return titulo.get();
    }

    public List<Titulo> findAll(){
        return tituloRepository.findAll();
    }

    public Titulo updateById(long id, Titulo updatedTitulo){
        return tituloRepository.findById(id)
                .map(titulo -> {
                    titulo.setProduto(updatedTitulo.getProduto());
                    titulo.setAutor(updatedTitulo.getAutor());
                    titulo.setEditora(updatedTitulo.getEditora());
                    titulo.setData(updatedTitulo.getData());

                    return tituloRepository.save(titulo);
                })
                .orElseThrow(()-> new RuntimeException("Titulo não encontrado com id: " + id));
    }
}