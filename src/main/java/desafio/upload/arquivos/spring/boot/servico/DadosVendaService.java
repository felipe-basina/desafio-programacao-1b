package desafio.upload.arquivos.spring.boot.servico;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import desafio.upload.arquivos.spring.boot.modelo.DadosVenda;
import desafio.upload.arquivos.spring.boot.repositorio.DadosVendaRepositorio;

@Service
public class DadosVendaService {

	private DadosVendaRepositorio dadosVendaRepositorio;

	@Autowired
	public DadosVendaService(DadosVendaRepositorio dadosVendaRepositorio) {
		this.dadosVendaRepositorio = dadosVendaRepositorio;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean salvarRegistros(List<DadosVenda> dadosVendaLista) {
		
		try {
			
			for (DadosVenda dadosVenda : dadosVendaLista) {
				
				this.dadosVendaRepositorio.save(dadosVenda);
				
			}
			
			return true;
			
		} catch (Exception ex) {
			return false;
		}
		
	}
	
	public List<DadosVenda> recuperarDadosVenda() {
		
		try {
			
			return (List<DadosVenda>) this.dadosVendaRepositorio.findAll();
			
		} catch (Exception ex) {
			return new ArrayList<DadosVenda>();
		}
		
	}
	
	public String recuperarValorReceitaBruta() {
		
		try {
			
			Double receitaBruta = 0.0;
			
			for (DadosVenda dadosVenda : this.recuperarDadosVenda()) {
				receitaBruta += (dadosVenda.getPrecoUnitario() * dadosVenda.getQuantidade());
			}
			
			DecimalFormat df = new DecimalFormat("#.00"); 
			return df.format(receitaBruta);
			
		} catch (Exception ex) {
			return "0.0";
		}
		
	}
	
	
}
