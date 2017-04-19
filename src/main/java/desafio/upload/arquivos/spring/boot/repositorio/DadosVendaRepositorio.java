package desafio.upload.arquivos.spring.boot.repositorio;

import org.springframework.data.repository.CrudRepository;

import desafio.upload.arquivos.spring.boot.modelo.DadosVenda;

public interface DadosVendaRepositorio extends CrudRepository<DadosVenda, Long> {

}
