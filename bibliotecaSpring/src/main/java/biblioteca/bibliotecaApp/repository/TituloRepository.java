package biblioteca.bibliotecaApp.repository;

import biblioteca.bibliotecaApp.entity.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
}
