package com.ifce.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifce.br.model.Pessoa;
import com.ifce.br.repository.PessoaRepository;
import com.ifce.br.util.AulaFileUtils;


@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepo;
	
	public void cadastrarPessoa(Pessoa pessoa, MultipartFile imagem) {
		String caminho = "images/"+pessoa.getNome()+".png";
		AulaFileUtils.salvarImagem(caminho, imagem);
		
		pessoaRepo.save(pessoa);
	}
	
	public List<Pessoa> listarPessoas(){
		
		return pessoaRepo.findAll();
	}
	
	public void excluirPessoaPorId(Long codigo) {
		pessoaRepo.deleteById(codigo);
		
	}
	
	public Optional<Pessoa> buscarPessoaPorId(Long codigo) {
		
		return pessoaRepo.findById(codigo);
	}
	
}
