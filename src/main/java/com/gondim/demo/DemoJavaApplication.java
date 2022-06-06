package com.gondim.demo;

import java.text.SimpleDateFormat;
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
import com.gondim.demo.domain.ItemPedido;
import com.gondim.demo.domain.Pagamento;
import com.gondim.demo.domain.PagamentoComBoleto;
import com.gondim.demo.domain.PagamentoComCartao;
import com.gondim.demo.domain.Pedido;
import com.gondim.demo.domain.Produto;
import com.gondim.demo.domain.enums.EstadoPagamento;
import com.gondim.demo.domain.enums.TipoCliente;
import com.gondim.demo.repositories.CategoriaRepository;
import com.gondim.demo.repositories.CidadeRepository;
import com.gondim.demo.repositories.ClienteRepository;
import com.gondim.demo.repositories.EnderecoRepository;
import com.gondim.demo.repositories.EstadoRepository;
import com.gondim.demo.repositories.ItemPedidoRepository;
import com.gondim.demo.repositories.PagamentoRepository;
import com.gondim.demo.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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

		Cliente cli1 = new Cliente(null, "Iago Gondim", "iago@gmail.com", "12345678900", TipoCliente.PESSOAFISICA, null,
				null);

		cli1.getTelefones().addAll(Arrays.asList("88888888", "99999999"));

		Endereco e1 = new Endereco(null, "Ailton Gomes", "1480", "Casa", "Pirajá", "63034012", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("14/03/2014 19:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("04/10/2020 00:00"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/02/2022 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
