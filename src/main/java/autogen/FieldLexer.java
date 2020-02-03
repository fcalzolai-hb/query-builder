package autogen;// Generated from Field.g4 by ANTLR 4.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FieldLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__5=1, T__4=2, T__3=3, T__2=4, T__1=5, T__0=6, Name=7, Int=8, WS=9, NL=10;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'at'", "'buried'", "'is'", "'scores'", "','", "'points'", "Name", "Int", 
		"WS", "NL"
	};
	public static final String[] ruleNames = {
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "Name", "Int", "WS", "NL"
	};


	public FieldLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Field.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\2\4\fK\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\6\b\67\n\b\r\b\16\b8\3\b\3\b\3\t\6\t>\n\t\r\t\16\t?\3\n\6\nC"+
		"\n\n\r\n\16\nD\3\13\5\13H\n\13\3\13\3\13\2\f\3\3\1\5\4\1\7\5\1\t\6\1\13"+
		"\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\3\2\4\5\"\"C\\c|\4\13\13\"\"N"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2"+
		"\5\32\3\2\2\2\7!\3\2\2\2\t$\3\2\2\2\13+\3\2\2\2\r-\3\2\2\2\17\64\3\2\2"+
		"\2\21=\3\2\2\2\23B\3\2\2\2\25G\3\2\2\2\27\30\7c\2\2\30\31\7v\2\2\31\4"+
		"\3\2\2\2\32\33\7d\2\2\33\34\7w\2\2\34\35\7t\2\2\35\36\7k\2\2\36\37\7g"+
		"\2\2\37 \7f\2\2 \6\3\2\2\2!\"\7k\2\2\"#\7u\2\2#\b\3\2\2\2$%\7u\2\2%&\7"+
		"e\2\2&\'\7q\2\2\'(\7t\2\2()\7g\2\2)*\7u\2\2*\n\3\2\2\2+,\7.\2\2,\f\3\2"+
		"\2\2-.\7r\2\2./\7q\2\2/\60\7k\2\2\60\61\7p\2\2\61\62\7v\2\2\62\63\7u\2"+
		"\2\63\16\3\2\2\2\64\66\7$\2\2\65\67\t\2\2\2\66\65\3\2\2\2\678\3\2\2\2"+
		"8\66\3\2\2\289\3\2\2\29:\3\2\2\2:;\7$\2\2;\20\3\2\2\2<>\4\62;\2=<\3\2"+
		"\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\22\3\2\2\2AC\t\3\2\2BA\3\2\2\2CD\3"+
		"\2\2\2DB\3\2\2\2DE\3\2\2\2E\24\3\2\2\2FH\7\17\2\2GF\3\2\2\2GH\3\2\2\2"+
		"HI\3\2\2\2IJ\7\f\2\2J\26\3\2\2\2\7\28?DG";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}