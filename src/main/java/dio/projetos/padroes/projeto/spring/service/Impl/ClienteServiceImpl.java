package dio.projetos.padroes.projeto.spring.service.Impl;

import dio.projetos.padroes.projeto.spring.model.Cliente;
import dio.projetos.padroes.projeto.spring.model.ClienteRepository;
import dio.projetos.padroes.projeto.spring.model.Endereco;
import dio.projetos.padroes.projeto.spring.model.EnderecoRepository;
import dio.projetos.padroes.projeto.spring.service.ClienteService;
import dio.projetos.padroes.projeto.spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual
 * pode ser injetada pelo Spring (via {@link Autowired}). Com isso, como essa
 * classe é um {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 * @author Hayane
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    //Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    //Strategy: Implementar os métodos definidos na interface.
    //Facade: Abstrair integrações com subsistemas, provendo uma interface simples.
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }
    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        //Buscar cliente por ID, caso exista.
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    //Verificar se o Endereco do Cliente já existe (pelo Cep)
    //Caso não exista, integrar com o ViaCep e persistir o retorno.
    //Alterar cliente, vinculando o Endereco (novo ou existente)
    //esses 3 últimos, serão feitas pelo metodo privado salvarClienteComCep

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        //Verificar se o Endereco do Cliente já existe (pelo Cep)
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            //Caso não exista, integrar com o ViaCep e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        //Inserir Cliente, vinculando o Endereco (novo ou existente)
        clienteRepository.save(cliente);
    }
}
