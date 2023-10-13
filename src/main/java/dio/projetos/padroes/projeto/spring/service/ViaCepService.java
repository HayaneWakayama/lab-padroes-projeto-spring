package dio.projetos.padroes.projeto.spring.service;

import dio.projetos.padroes.projeto.spring.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Cliente HTTP, criado via <b>OpenFeign</b>, para consumo da API do <b>ViaCep</b>.
 *
 * @author Hayane
 */

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")

public interface ViaCepService {
    @GetMapping("/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
