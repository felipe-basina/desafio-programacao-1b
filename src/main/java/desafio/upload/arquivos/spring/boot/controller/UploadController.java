package desafio.upload.arquivos.spring.boot.controller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import desafio.upload.arquivos.spring.boot.modelo.DadosVenda;

@Controller
public class UploadController {

    @GetMapping("/")
    public String iniciar() {
        return "carrega";
    }

    @GetMapping("/status")
    public String verificarStatusProcessamento() {
        return "status";
    }

    @PostMapping("/carregar")
    public String carregarArquivo(@RequestParam("arquivo") MultipartFile arquivo,
                                   RedirectAttributes redirectAttributes) {

        if (arquivo.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Um arquivo deve ser selecionado");
            return "redirect:status";
        }

        try {
            
            InputStream arquivoStream =  new BufferedInputStream(arquivo.getInputStream());
            
            StringWriter writer = new StringWriter();
            IOUtils.copy(arquivoStream, writer, "UTF-8");
            String conteudoArquivo = writer.toString();
            
            String[] linhas = conteudoArquivo.split("\n");
            
            List<DadosVenda> dadosVendaLista = new ArrayList<DadosVenda>();
            
            // Não é necessário analisar a primeria posição, pois trata-se do cabeçalho
            for (int indice = 1; indice < linhas.length; indice++) {
            	
            	// Recupera o conteúdo que está separado por 'tab' = '\t'
            	String[] conteudo = linhas[indice].split("\t");
            	
            	try {
            		
            		dadosVendaLista.add(new DadosVenda(conteudo[0], 
            				conteudo[1], new Double(conteudo[2]), Integer.parseInt(conteudo[3]), 
            				conteudo[4], conteudo[5]));
            		
            	} catch (Exception ex) {
                    redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao ler arquivo [ " +
                    		ex.getMessage() + " ]");
                    return "redirect:status";
            	}
            	
            }
            
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Conteúdo do arquivo");
            redirectAttributes.addFlashAttribute("dadosVendaLista", dadosVendaLista);
            
            Double receitaBruta = 0.0;
            
            for (DadosVenda dadosVenda : dadosVendaLista) {
            	receitaBruta += (dadosVenda.getPrecoUnitario() * dadosVenda.getQuantidade());
            }
            
            DecimalFormat df = new DecimalFormat("#.00"); 
            redirectAttributes.addFlashAttribute("receitaBruta", df.format(receitaBruta));
            
        } catch (Exception ex) {
        	redirectAttributes.addFlashAttribute("mensagemErro", "Algum erro aconteceu: " 
        			+ ex.getMessage());
        }

        return "redirect:/status";
    }

}