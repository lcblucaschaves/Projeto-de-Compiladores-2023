package main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import exceptions.IsiSemanticException;
import parser.IsiLangLexer;
import parser.IsiLangParser;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			// leio o arquivo "input.isi" e isso é entrada para o Analisador Lexico
			
			lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			
			// crio um "fluxo de tokens" para passar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// crio meu parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();			
			parser.printCommand();
			parser.verificaUso();;
			parser.generateCode();
			System.out.println("Compilation Successful");
			
		}
		catch(IsiSemanticException ex) {
			System.err.println("Erro Semantico - " + ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("ERROR " + ex.getMessage());
		}
		
	}

}
