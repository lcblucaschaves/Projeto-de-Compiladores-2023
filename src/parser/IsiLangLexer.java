// Generated from IsiLang.g4 by ANTLR 4.7.1
package parser;

	import ast.*;
	import datastructures.*;
	import exceptions.IsiSemanticException;	
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, AP=12, FP=13, SC=14, OP=15, ATTR=16, VIR=17, ACH=18, 
		FCH=19, OPREL=20, ID=21, NUMBER=22, WS=23, TEXT=24, BOOLEAN=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", 
		"OPREL", "ID", "NUMBER", "WS", "TEXT", "BOOLEAN"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'booleano'", 
		"'leia'", "'escreva'", "'se'", "'senao'", "'enquanto'", "'faca'", "'('", 
		"')'", "';'", null, "'='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", 
		"WS", "TEXT", "BOOLEAN"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00ce\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u009b\n\25\3\26\3\26\7\26\u009f"+
		"\n\26\f\26\16\26\u00a2\13\26\3\27\6\27\u00a5\n\27\r\27\16\27\u00a6\3\27"+
		"\3\27\6\27\u00ab\n\27\r\27\16\27\u00ac\5\27\u00af\n\27\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\7\31\u00b7\n\31\f\31\16\31\u00ba\13\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\5\32\u00cd\n\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\3\2\n\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5\2\13\f\17"+
		"\17\"\"\3\2$$\6\2\"\"\62;C\\c|\2\u00d7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65"+
		"\3\2\2\2\5>\3\2\2\2\7G\3\2\2\2\tN\3\2\2\2\13T\3\2\2\2\r]\3\2\2\2\17b\3"+
		"\2\2\2\21j\3\2\2\2\23m\3\2\2\2\25s\3\2\2\2\27|\3\2\2\2\31\u0081\3\2\2"+
		"\2\33\u0083\3\2\2\2\35\u0085\3\2\2\2\37\u0087\3\2\2\2!\u0089\3\2\2\2#"+
		"\u008b\3\2\2\2%\u008d\3\2\2\2\'\u008f\3\2\2\2)\u009a\3\2\2\2+\u009c\3"+
		"\2\2\2-\u00a4\3\2\2\2/\u00b0\3\2\2\2\61\u00b4\3\2\2\2\63\u00cc\3\2\2\2"+
		"\65\66\7r\2\2\66\67\7t\2\2\678\7q\2\289\7i\2\29:\7t\2\2:;\7c\2\2;<\7o"+
		"\2\2<=\7c\2\2=\4\3\2\2\2>?\7h\2\2?@\7k\2\2@A\7o\2\2AB\7r\2\2BC\7t\2\2"+
		"CD\7q\2\2DE\7i\2\2EF\7=\2\2F\6\3\2\2\2GH\7p\2\2HI\7w\2\2IJ\7o\2\2JK\7"+
		"g\2\2KL\7t\2\2LM\7q\2\2M\b\3\2\2\2NO\7v\2\2OP\7g\2\2PQ\7z\2\2QR\7v\2\2"+
		"RS\7q\2\2S\n\3\2\2\2TU\7d\2\2UV\7q\2\2VW\7q\2\2WX\7n\2\2XY\7g\2\2YZ\7"+
		"c\2\2Z[\7p\2\2[\\\7q\2\2\\\f\3\2\2\2]^\7n\2\2^_\7g\2\2_`\7k\2\2`a\7c\2"+
		"\2a\16\3\2\2\2bc\7g\2\2cd\7u\2\2de\7e\2\2ef\7t\2\2fg\7g\2\2gh\7x\2\2h"+
		"i\7c\2\2i\20\3\2\2\2jk\7u\2\2kl\7g\2\2l\22\3\2\2\2mn\7u\2\2no\7g\2\2o"+
		"p\7p\2\2pq\7c\2\2qr\7q\2\2r\24\3\2\2\2st\7g\2\2tu\7p\2\2uv\7s\2\2vw\7"+
		"w\2\2wx\7c\2\2xy\7p\2\2yz\7v\2\2z{\7q\2\2{\26\3\2\2\2|}\7h\2\2}~\7c\2"+
		"\2~\177\7e\2\2\177\u0080\7c\2\2\u0080\30\3\2\2\2\u0081\u0082\7*\2\2\u0082"+
		"\32\3\2\2\2\u0083\u0084\7+\2\2\u0084\34\3\2\2\2\u0085\u0086\7=\2\2\u0086"+
		"\36\3\2\2\2\u0087\u0088\t\2\2\2\u0088 \3\2\2\2\u0089\u008a\7?\2\2\u008a"+
		"\"\3\2\2\2\u008b\u008c\7.\2\2\u008c$\3\2\2\2\u008d\u008e\7}\2\2\u008e"+
		"&\3\2\2\2\u008f\u0090\7\177\2\2\u0090(\3\2\2\2\u0091\u009b\t\3\2\2\u0092"+
		"\u0093\7@\2\2\u0093\u009b\7?\2\2\u0094\u0095\7>\2\2\u0095\u009b\7?\2\2"+
		"\u0096\u0097\7?\2\2\u0097\u009b\7?\2\2\u0098\u0099\7#\2\2\u0099\u009b"+
		"\7?\2\2\u009a\u0091\3\2\2\2\u009a\u0092\3\2\2\2\u009a\u0094\3\2\2\2\u009a"+
		"\u0096\3\2\2\2\u009a\u0098\3\2\2\2\u009b*\3\2\2\2\u009c\u00a0\t\4\2\2"+
		"\u009d\u009f\t\5\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1,\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3"+
		"\u00a5\t\6\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00ae\3\2\2\2\u00a8\u00aa\7\60\2\2\u00a9"+
		"\u00ab\t\6\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00a8\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af.\3\2\2\2\u00b0\u00b1\t\7\2\2\u00b1\u00b2\3\2\2\2"+
		"\u00b2\u00b3\b\30\2\2\u00b3\60\3\2\2\2\u00b4\u00b8\t\b\2\2\u00b5\u00b7"+
		"\t\t\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8"+
		"\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00bc\t\b"+
		"\2\2\u00bc\62\3\2\2\2\u00bd\u00be\7X\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0"+
		"\7t\2\2\u00c0\u00c1\7f\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3\7f\2\2\u00c3"+
		"\u00c4\7g\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7t\2\2\u00c6\u00cd\7q\2\2"+
		"\u00c7\u00c8\7H\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb"+
		"\7u\2\2\u00cb\u00cd\7q\2\2\u00cc\u00bd\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cd"+
		"\64\3\2\2\2\f\2\u009a\u009e\u00a0\u00a6\u00ac\u00ae\u00b6\u00b8\u00cc"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}