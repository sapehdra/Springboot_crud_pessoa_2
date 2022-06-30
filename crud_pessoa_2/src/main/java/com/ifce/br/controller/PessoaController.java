package com.ifce.br.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ifce.br.model.Pessoa;
import com.ifce.br.service.PessoaService;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/OlaMundo")
	public String OlaMundo() {
		
		return "ola-mundo";
		
	}
	
	@GetMapping("/pessoa/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("formulario");
		mv.addObject("pessoa", new Pessoa());
		
		return mv;
		
	}
	
	@RequestMapping("/pessoa/salvar")
	public ModelAndView salvar(Pessoa pessoa, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		
		
		ModelAndView mv = new ModelAndView("formulario");
		
		if(result.hasErrors()) {
			
			return mv;
		}
		
		pessoaService.cadastrarPessoa(pessoa, imagem);
		mv.addObject("mensagem", "Pessoa cadastrada com sucesso!");
		
		
		return mv;
		
	}
	
	@GetMapping("/pessoa/listar")
	public ModelAndView listarPessoas() {
		List<Pessoa> pessoas = pessoaService.listarPessoas();
		ModelAndView mv = new ModelAndView("listagem-pessoa");
		
		mv.addObject("listarPessoas", pessoas);
		
		return mv;
		
	}
	
	@RequestMapping("/pessoa/excluir/{codigo}")
	public ModelAndView excluirPessoaPorId (@PathVariable Long codigo) {
		pessoaService.excluirPessoaPorId(codigo);
		ModelAndView mv = new ModelAndView("redirect:/pessoa/listar");
		
		return mv;
	}
	
	@RequestMapping("/pessoa/atualizar/{codigo}")
	public ModelAndView atualizarPessoaPorId (@PathVariable Long codigo) {
		Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorId(codigo);
		ModelAndView mv = new ModelAndView("formulario");
		mv.addObject("pessoa", pessoa);
		
		return mv;
	}

}
