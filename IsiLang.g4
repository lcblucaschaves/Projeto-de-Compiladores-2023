grammar IsiLang;

@header{
	import ast.*;
	import datastructures.*;
	import exceptions.IsiSemanticException;	
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _type;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
	private String _exprWhile;
	private int _exprType;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> commandEnq;
	private ArrayList<AbstractCommand> listaLoop;

	private String[] typeDict = new String[] {"numero", "texto", "booleano"};	
	
	public void printCommand(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}	
	
	public void generateCode(){
		program.generateJavaCode();
	}

	public void verificaDeclaracao(String id) { 
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Simbolo " + id + " nao foi declarado");
		}		
	}
	
	public void verificaInicializacao(String id) {
		IsiVariable var = (IsiVariable)symbolTable.get(id);
		if (var.getValue() == null && var.getAttributed() == false) {
			throw new IsiSemanticException("Simbolo " + id + " foi declarado mas nao possui valor");
			//System.out.println(var.getValue());
		}
	}
	
	public void verificaUso(){ 
		for (IsiSymbol symbol: symbolTable.getAll()) {
			if(symbol instanceof IsiVariable && ((IsiVariable)symbol).getAttributed() == false) {
				System.out.println(String.format("Variavel %s declarada mas nunca utilizada", symbol.getName()));
			}
		}
	}
}	

prog : 'programa' declara bloco  'fimprog;'
		{  program.setVarTable(symbolTable);
			program.setComandos(stack.pop());
		}
	 ;
		
declara : (declaravar)+;        
        
declaravar : tipo ID
				{
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _type, _varValue);
					
					if (!symbolTable.exists(_varName)) {
						symbolTable.add(symbol);	
					} else {
						throw new IsiSemanticException("Simbolo " + _varName + " ja foi declarado");
					}
				} 
			 (VIR ID 
				{
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _type, _varValue);
					if (!symbolTable.exists(_varName)){
						symbolTable.add(symbol);	
					} else {
						throw new IsiSemanticException("Simbolo " + _varName + " ja foi declarado");
					}
				}
			 )* SC
			;
	
tipo : 'numero'   { _type = IsiVariable.NUMBER; }
	 | 'texto'    { _type = IsiVariable.TEXT;   }
	 | 'booleano' { _type = IsiVariable.BOOLEAN;}
	 ;
        
bloco : {
			curThread = new ArrayList<AbstractCommand>(); 
			stack.push(curThread);  
		} 
		(cmd)+
	  ;
		

cmd	:  cmdleitura  
	|  cmdescrita 
	|  cmdattr
	|  cmdselecao
	|  cmdenquanto
	|  cmdfazenquanto  
	;
		
cmdleitura 	: 'leia' AP ID 
						{ 
							verificaDeclaracao(_input.LT(-1).getText());
							_readID = _input.LT(-1).getText();
                        } 
						FP SC 
						{
							IsiVariable var = (IsiVariable)symbolTable.get(_readID);
							CommandLeitura cmd = new CommandLeitura(_readID, var);
							stack.peek().add(cmd);
							var.setAttributed(true);
						}   
			;
			
cmdescrita 	: 'escreva' AP (ID 
						{ 
							verificaDeclaracao(_input.LT(-1).getText());
							verificaInicializacao(_input.LT(-1).getText());
							_writeID = _input.LT(-1).getText();
						} 
						| NUMBER | TEXT | BOOLEAN )
						FP SC
						{
							CommandEscrita cmd = new CommandEscrita(_writeID);
							stack.peek().add(cmd);
							//IsiVariable var = (IsiVariable)symbolTable.get(_writeID);
							//var.setAttributed(true);
						}
			;
			
cmdattr		:  ID
				{ 
					verificaDeclaracao(_input.LT(-1).getText());
					_exprID = _input.LT(-1).getText();
				} 
				ATTR 
				{ 
					_exprContent = "";
				} 
				expr SC
				{	
					IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
					
					if (var.getType() != _exprType) {						
						throw new IsiSemanticException(String.format("Variavel %s esperava um %s mas recebeu um %s no lugar", var.getName(), typeDict[var.getType()], typeDict[_exprType]));
					}
					
					CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
					stack.peek().add(cmd);
					var.setValue(_exprContent);
					var.setAttributed(true);
				}
			;
			
			
cmdselecao  :  'se' AP ID
						{ 
							_exprDecision = _input.LT(-1).getText();
						}
                    OPREL 
                    	{ 
                    		_exprDecision += _input.LT(-1).getText();
                    	}
                    (ID | NUMBER) 
                    	{
                    		_exprDecision += _input.LT(-1).getText();
                    	}
                    FP ACH
                        { 	
                        	curThread = new ArrayList<AbstractCommand>(); 
							stack.push(curThread);
                    	}                    	
                    (cmd)+ FCH 
	                    {
							listaTrue = stack.pop();	
	                    } 	                 
					('senao' ACH
						{
							curThread = new ArrayList<AbstractCommand>();
							stack.push(curThread);
						} 
					(cmd+) FCH
						{
							listaFalse = stack.pop();
							CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
							stack.peek().add(cmd);
						}
					)?
            ;

cmdenquanto  : 'enquanto' AP ID 
							{
								_exprDecision = _input.LT(-1).getText();
							}
						OPREL 
							{ 
								_exprDecision += _input.LT(-1).getText();
							}
						(ID | NUMBER) 
							{
								_exprDecision += _input.LT(-1).getText();
							}
						FP 	ACH 
							{ 
								curThread = new ArrayList<AbstractCommand>();
								stack.push(curThread);
							}
						(cmd)+ 				
						FCH 
							{
								commandEnq = stack.pop();
								CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commandEnq);
								stack.peek().add(cmd);	
							}
				; 

cmdfazenquanto : 'faca' ACH
		                {     
		                	curThread = new ArrayList<AbstractCommand>(); 
		                    stack.push(curThread);
						}
					(cmd)+ FCH 'enquanto' AP expr 
						{
							_exprWhile = _input.LT(-1).getText();
						}
					OPREL
						{
							_exprWhile += _input.LT(-1).getText();
						}
					expr 
						{
							_exprWhile += _input.LT(-1).getText();
						}
					FP
						{   
							listaLoop = stack.pop();
							CommandFacaEnquanto cmd = new CommandFacaEnquanto(_exprWhile, listaLoop);
							stack.peek().add(cmd);
						}
					SC
           		;

expr : TEXT 
		{
	 		_exprContent += _input.LT(-1).getText();
	  		_exprType = 1;
		}
	 | termo (OP  
		{ 	_exprContent += _input.LT(-1).getText();
			_exprType = 0;
		}
	  termo)* | BOOLEAN 
		{ 
			_exprContent += _input.LT(-1).getText();
		 	_exprType = 2;
		}
	;
			
termo : ID 
		{ 
			verificaDeclaracao(_input.LT(-1).getText());
			verificaInicializacao(_input.LT(-1).getText());
			_exprContent += _input.LT(-1).getText();
		} 
	  |	NUMBER
		{
			_exprContent += _input.LT(-1).getText();
			_exprType = 0;
		}
	  ;	


	
AP	: '('
	;
	
FP	: ')'
	;

SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR: '='
	;
	
VIR : ','
	;
	
ACH : '{'
	;
	
FCH : '}'
    ;

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
	  ;

ID : [a-z] ([a-z] | [A-Z] | [0-9])*
   ;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
WS : (' ' | '\t' | '\n' | '\r') -> skip;

TEXT: ["]([a-z] | [A-Z] | [0-9] | ' ')*["];

BOOLEAN : 'Verdadeiro' | 'Falso';