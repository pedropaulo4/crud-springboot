package com.pedropaulo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pedropaulo.cursomc.domain.Categoria;
import com.pedropaulo.cursomc.domain.Cidade;
import com.pedropaulo.cursomc.domain.Cliente;
import com.pedropaulo.cursomc.domain.Endereco;
import com.pedropaulo.cursomc.domain.Estado;
import com.pedropaulo.cursomc.domain.Pagamento;
import com.pedropaulo.cursomc.domain.PagamentoComBoleto;
import com.pedropaulo.cursomc.domain.PagamentoComCartao;
import com.pedropaulo.cursomc.domain.Pedido;
import com.pedropaulo.cursomc.domain.Produto;
import com.pedropaulo.cursomc.domain.enums.EstadoPagamento;
import com.pedropaulo.cursomc.domain.enums.TipoCliente;
import com.pedropaulo.cursomc.repository.CategoriaRepository;
import com.pedropaulo.cursomc.repository.CidadeRepository;
import com.pedropaulo.cursomc.repository.ClienteRepository;
import com.pedropaulo.cursomc.repository.EnderecoRepository;
import com.pedropaulo.cursomc.repository.EstadoRepository;
import com.pedropaulo.cursomc.repository.PagamentoRepository;
import com.pedropaulo.cursomc.repository.PedidoRepository;
import com.pedropaulo.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { // permite implementar um metodo auxiliar, pra executar alguma ação quando a aplicação iniciar
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	
	@Autowired
	private PagamentoRepository pagamentoRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

@Override
public void run(String... args) throws Exception {
	
	
	Categoria cat1 = new Categoria(null, "Informatica");
	Categoria cat2 = new Categoria(null, "Escritório");
	
	Produto p1 = new Produto(null, "Computador", 2000.00);
	Produto p2 = new Produto(null, "Impressora", 800.00);
	Produto p3 = new Produto(null, "Mouse", 80.00);
	
	Estado est1 = new Estado(null, "Minas Gerais");
	Estado est2 = new Estado(null, "São Paulo");
	
	Cidade c1 = new Cidade(null, "Uberlandia", est1);
	Cidade c2 = new Cidade(null, "São Paulo", est2);
	Cidade c3 = new Cidade(null, "Campinas", est2);
	
	// Associando as cidades com seus estados

	cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	
	cat2.getProdutos().addAll(Arrays.asList(p2));
	
	p1.getCategorias().addAll(Arrays.asList(cat1));
	p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	p3.getCategorias().addAll(Arrays.asList(cat1));
	
	
	
	
	est1.getCidades().addAll(Arrays.asList(c1));
	est2.getCidades().addAll(Arrays.asList(c2,c3));
	
	Cliente cli1 = new Cliente( null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
	
	cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
	
	Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", c1, cli1);
	Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);
	
	cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	
	Pagamento pto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	Pagamento pto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
	
	ped1.setPagamento(pto1);
	ped2.setPagamento(pto2);
	
	cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
	
	
	
	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	estadoRepository.saveAll(Arrays.asList(est1,est2));
	cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	clienteRepository.saveAll(Arrays.asList(cli1));
	enderecoRepository.saveAll(Arrays.asList(e1,e2));
	pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	pagamentoRepository.saveAll(Arrays.asList(pto1, pto2));
	
	
	
	
	
}

}

