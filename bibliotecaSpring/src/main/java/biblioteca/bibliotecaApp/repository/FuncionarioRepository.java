package biblioteca.bibliotecaApp.repository;

import biblioteca.bibliotecaApp.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
