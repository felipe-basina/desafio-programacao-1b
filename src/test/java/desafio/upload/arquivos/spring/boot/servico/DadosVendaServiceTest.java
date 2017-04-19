package desafio.upload.arquivos.spring.boot.servico;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import desafio.upload.arquivos.spring.boot.modelo.DadosVenda;
import desafio.upload.arquivos.spring.boot.repositorio.DadosVendaRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DadosVendaServiceTest {

	@Autowired
	private DadosVendaRepositorio dadosVendaRepositorio;
	
	private DadosVendaService dadosVendaService;
	
	private List<DadosVenda> dadosVendaLista;
	
	@Before
	public void setUp() throws Exception {
		this.dadosVendaService = new DadosVendaService(this.dadosVendaRepositorio);
		
		this.dadosVendaLista = new ArrayList<DadosVenda>();
		this.dadosVendaLista.add(new DadosVenda("comprador1", "descricao1", 10.0, 1, "endereco1", "fornecedor1"));
		this.dadosVendaLista.add(new DadosVenda("comprador2", "descricao2", 10.0, 2, "endereco2", "fornecedor2"));
		this.dadosVendaLista.add(new DadosVenda("comprador3", "descricao3", 10.0, 3, "endereco3", "fornecedor3"));
	}

	@Test
	public void testSalvarRegistros() {
		Assert.assertTrue(this.dadosVendaService.salvarRegistros(this.dadosVendaLista));
	}

	@Test
	public void testRecuperarDadosVenda() {
		List<DadosVenda> dadosVendaListaPersistido = this.dadosVendaService.recuperarDadosVenda();
		
		Assert.assertNotNull(dadosVendaListaPersistido);
		Assert.assertTrue(!dadosVendaListaPersistido.isEmpty());
		Assert.assertTrue(dadosVendaListaPersistido.size() == this.dadosVendaLista.size());
	}

	@Test
	public void testRecuperarValorReceitaBruta() {
		String receitaBruta = this.dadosVendaService.recuperarValorReceitaBruta();
		Assert.assertTrue(!receitaBruta.equals("0.0"));
		Assert.assertTrue(receitaBruta.equals("60,00")); // Valor previamente calculado para verificação!
	}

}
