package br.com.brenoandrade.ProjetoModulo40.repository;

import br.com.brenoandrade.ProjetoModulo40.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interface é um componente de repositório do Spring
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // O Spring Data JPA já fornece métodos CRUD básicos (save, findById, findAll, delete, etc.)
    // Você pode adicionar métodos personalizados aqui, se precisar, como:
    // Cliente findByEmail(String email);
    // List<Cliente> findByNomeContaining(String nome);
}