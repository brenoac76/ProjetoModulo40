package br.com.brenoandrade.ProjetoModulo40.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Indica que esta classe é uma entidade JPA e será mapeada para uma tabela no banco de dados
@Table(name = "clientes") // Especifica o nome da tabela no banco de dados (opcional, se o nome da classe for o mesmo)
@Data // Anotação do Lombok para gerar getters, setters, toString, equals e hashCode
@NoArgsConstructor // Anotação do Lombok para gerar um construtor sem argumentos
@AllArgsConstructor // Anotação do Lombok para gerar um construtor com todos os argumentos
public class Cliente {

    @Id // Indica que este campo é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a estratégia de geração de valor para a chave primária (IDENTITY para auto-incremento no PostgreSQL)
    private Long id;

    @Column(nullable = false) // Indica que a coluna não pode ser nula no banco de dados
    private String nome;

    @Column(nullable = false, unique = true) // Não pode ser nulo e deve ser único
    private String email;

    private String telefone;

    private String endereco;
}