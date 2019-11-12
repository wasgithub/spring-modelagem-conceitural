package com.was.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.was.cursomc.domain.Categoria;
import com.was.cursomc.domain.Cidade;
import com.was.cursomc.domain.Cliente;
import com.was.cursomc.domain.Endereco;
import com.was.cursomc.domain.Estado;
import com.was.cursomc.domain.Produto;
import com.was.cursomc.domain.enums.TipoCliente;
import com.was.cursomc.repositories.CategoriaRepository;
import com.was.cursomc.repositories.CidadeRepository;
import com.was.cursomc.repositories.ClienteRepository;
import com.was.cursomc.repositories.EnderecoRepository;
import com.was.cursomc.repositories.EstadoRepository;
import com.was.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 =  new Produto(null, "Computador", 1000.00);
		Produto prod2 =  new Produto(null, "Impressora", 500.00);
		Produto prod3 =  new Produto(null, "Mouse", 50.00);
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "Uberândia", est2);
		Cidade c2 = new Cidade(null, "São Paulo", est1);
		Cidade c3 = new Cidade(null, "Campinas", est1);		
		
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36046575800", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("979728931", "1141946706"));
		
		Endereco end1 = new Endereco(null, "Rua barra bonita", "153", "casa", "Parque Viana", "06449130", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Brás Leme", "1000", "prédio", "Santana", "222222222", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
