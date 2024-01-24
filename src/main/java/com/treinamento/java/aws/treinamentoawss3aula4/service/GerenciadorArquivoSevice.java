package com.treinamento.java.aws.treinamentoawss3aula4.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.treinamento.java.aws.treinamentoawss3aula4.controller.model.ArquivoRequest;


@Component
public class GerenciadorArquivoSevice {

	@Value("${app.aws.key.id}")
	private String AccessKeyId;
	
	@Value("${app.aws.secret.access.key}")
	private String secretAccessKey;
	
	@Value("${app.s3.bucket.name}")
	private String bucketName;
	
	public void criar(ArquivoRequest arquivoRequest) {
		//Salvar no bucket um arquivo
		
		try {
			File arquivo = criarArquivoTemp(arquivoRequest);
			String fileName = "ArquivoTeste_"+ new Date().toLocaleString() +".csv";
			System.out.println("Arquivo temporário = " + fileName);
			if(arquivo != null) {
				final AmazonS3 s3 = AmazonS3ClientBuilder
						.standard()
						.withCredentials( new AWSStaticCredentialsProvider(new BasicAWSCredentials(AccessKeyId,secretAccessKey)))
						.withRegion(Regions.US_EAST_1).build();
			    s3.putObject(bucketName, fileName, arquivo);
			    arquivoRequest.setNomeArquivoSalvoBucket(fileName);
			    System.out.format("Arquivo Salvo no Bucket %s \n", bucketName);
			}else {
				System.out.format("Arquivo não foi Salvo no Bucket %s \n", bucketName);
			}
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
	}

	private File criarArquivoTemp(ArquivoRequest arquivo) {
	
		File file;
		BufferedWriter writer;
		try {
			file = File.createTempFile("Arquivo", ".tmp");
		
		
			writer =  new BufferedWriter(
                new FileWriter(file));
            writer.write(
            		"Nome ; Descricao ; Data Criação \n" );
		
            writer.write(
            		arquivo.getNomeArquivo()+ ";" + arquivo.getDescricao() +";"+ arquivo.getDataCriacao() );
            writer.close();
            return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
				 
		
	}
}
