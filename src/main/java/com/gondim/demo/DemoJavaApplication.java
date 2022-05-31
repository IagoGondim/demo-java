package com.gondim.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gondim.demo.domain.Categoria;
import com.gondim.demo.domain.Cidade;
import com.gondim.demo.domain.Cliente;
import com.gondim.demo.domain.Endereco;
import com.gondim.demo.domain.Estado;
import com.gondim.demo.domain.Produto;
import com.gondim.demo.domain.enums.TipoCliente;
import com.gondim.demo.repositories.CategoriaRepository;
import com.gondim.demo.repositories.CidadeRepository;
import com.gondim.demo.repositories.ClienteRepository;
import com.gondim.demo.repositories.EnderecoRepository;
import com.gondim.demo.repositories.EstadoRepository;
import com.gondim.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class DemoJavaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Ceará");
		Estado est2 = new Estado(null, "Pernambuco");

		Cidade c1 = new Cidade(null, "Juazeiro do Norte", est1);
		Cidade c2 = new Cidade(null, "Salgueiro", est2);
		Cidade c3 = new Cidade(null, "Recife", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Iago Gondim", "iago@gmail.com", "12345678900", TipoCliente.PESSOAFISICA, null, null);

		cli1.getTelefones().addAll(Arrays.asList("88888888","99999999"));
		
		Endereco e1 = new Endereco(null, "Ailton Gomes", "1480", "Casa", "Pirajá", "63034012", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
