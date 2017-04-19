package desafio.upload.arquivos.spring.boot.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DadosVenda {

	@Id
	@GeneratedValue
	private long id;
	
	private String comprador;
	
	private String descricao;
	
	private Double precoUnitario;
	
	private Integer quantidade;
	
	private String endereco;
	
	private String fornecedor;

	public DadosVenda() {
		super();
	}

	public DadosVenda(String comprador, String descricao, Double precoUnitario, Integer quantidade, String endereco,
			String fornecedor) {
		this.comprador = comprador;
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.endereco = endereco;
		this.fornecedor = fornecedor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getComprador() {
		return comprador;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosVenda other = (DadosVenda) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DadosVenda [id=");
		builder.append(id);
		builder.append(", comprador=");
		builder.append(comprador);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", precoUnitario=");
		builder.append(precoUnitario);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", fornecedor=");
		builder.append(fornecedor);
		builder.append("]");
		return builder.toString();
	}

}
