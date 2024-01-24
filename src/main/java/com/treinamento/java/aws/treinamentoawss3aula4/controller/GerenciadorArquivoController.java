package com.treinamento.java.aws.treinamentoawss3aula4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.java.aws.treinamentoawss3aula4.controller.model.ArquivoRequest;
import com.treinamento.java.aws.treinamentoawss3aula4.service.GerenciadorArquivoSevice;

@RestController
public class GerenciadorArquivoController {

	@Autowired
	private GerenciadorArquivoSevice service;
	
	@PostMapping("/gerenciarArquivo")
	public void gerenciarArquivo(@RequestBody ArquivoRequest arquivo) {
		service.criar(arquivo);
		
		
	}
}
