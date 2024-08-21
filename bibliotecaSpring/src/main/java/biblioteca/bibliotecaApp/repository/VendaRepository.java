package biblioteca.bibliotecaApp.repository;

import biblioteca.bibliotecaApp.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
