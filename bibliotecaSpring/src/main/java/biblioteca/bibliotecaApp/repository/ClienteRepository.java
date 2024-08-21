package biblioteca.bibliotecaApp.repository;

import biblioteca.bibliotecaApp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
