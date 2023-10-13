package dio.projetos.padroes.projeto.spring.service;

import dio.projetos.padroes.projeto.spring.model.Cliente;

/**
 * Interface que defice o padrão <b>Strategy</b> no domínio de cliente.
 * Assim, se necessário, podemos ter múltiplas implementações dessa mesma interface.
 *
 * @author Hayane
 */

public interface ClienteService {
    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
