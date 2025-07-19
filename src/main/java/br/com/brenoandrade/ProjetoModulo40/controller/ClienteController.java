package br.com.brenoandrade.ProjetoModulo40.controller;

import br.com.brenoandrade.ProjetoModulo40.model.Cliente;
import br.com.brenoandrade.ProjetoModulo40.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST e seus métodos retornam dados diretamente
@RequestMapping("/clientes") // Define o caminho base para todos os endpoints deste controlador
public class ClienteController {

    @Autowired // Injeta uma instância do ClienteRepository
    private ClienteRepository clienteRepository;

    // Endpoint para adicionar um novo cliente
    @PostMapping // Mapeia requisições POST para /clientes
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteRepository.save(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED); // Retorna o cliente salvo e status 201 (Created)
    }

    // Endpoint para listar todos os clientes
    @GetMapping // Mapeia requisições GET para /clientes
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK); // Retorna a lista de clientes e status 200 (OK)
    }

    // Endpoint para buscar cliente por ID
    @GetMapping("/{id}") // Mapeia requisições GET para /clientes/{id}
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK)) // Se encontrar, retorna 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Se não encontrar, retorna 404 Not Found
    }

    // Endpoint para atualizar um cliente
    @PutMapping("/{id}") // Mapeia requisições PUT para /clientes/{id}
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNome(clienteAtualizado.getNome());
                    clienteExistente.setEmail(clienteAtualizado.getEmail());
                    clienteExistente.setTelefone(clienteAtualizado.getTelefone());
                    clienteExistente.setEndereco(clienteAtualizado.getEndereco());
                    Cliente clienteSalvo = clienteRepository.save(clienteExistente);
                    return new ResponseEntity<>(clienteSalvo, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para deletar um cliente
    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /clientes/{id}
    public ResponseEntity<HttpStatus> deletarCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content para deleção bem-sucedida
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se não encontrar, retorna 404 Not Found
        }
    }
}
