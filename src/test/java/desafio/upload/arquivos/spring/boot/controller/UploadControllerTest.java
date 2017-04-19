package desafio.upload.arquivos.spring.boot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import desafio.upload.arquivos.spring.boot.InitApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = InitApplication.class)
public class UploadControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testIniciar() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("carrega"));
	}

	@Test
	public void testVerificarStatusProcessamento() throws Exception {
		this.mockMvc.perform(get("/status")).andExpect(status().isOk()).andExpect(view().name("status"));
	}

	@Test
	public void testCarregarArquivo() throws Exception {
		String conteudoArquivo = "Comprador	descrição	Preço Uniário	Quantidade	Endereço	Fornecedor"
				+ "João Silva	R$10 off R$20 of food	10.0	2	987 Fake St	Bob's Pizza"
				+ "Amy Pond	R$30 of awesome for R$10	10.0	5	456 Unreal Rd	Tom's Awesome Shop"
				+ "Marty McFly	R$20 Sneakers for R$5	5.0	1	123 Fake St	Sneaker Store Emporium"
				+ "Snake Plissken	R$20 Sneakers for R$5	5.0	4	123 Fake St	Sneaker Store Emporium";

		MockMultipartFile arquivo = new MockMultipartFile("arquivo", "dados.txt", "text/plain",
				conteudoArquivo.getBytes());

		mockMvc.perform(fileUpload("/carregar").file(arquivo)).andExpect(redirectedUrl("/status"))
			.andExpect(flash().attributeExists("mensagemSucesso"))
			.andExpect(flash().attributeExists("dadosVendaLista"))
			.andExpect(flash().attributeExists("receitaBruta"));
	}

	@Test
	public void testCarregarArquivoSemArquivo() throws Exception {
		MockMultipartFile arquivo = new MockMultipartFile("arquivo", null, "text/plain", new byte[0]);
		
		mockMvc.perform(fileUpload("/carregar").file(arquivo)).andExpect(redirectedUrl("status"))
			.andExpect(flash().attributeExists("mensagemErro"));
	}

}
