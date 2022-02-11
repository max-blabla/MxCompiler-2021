// Generated from D:/JavaCoding/MxComplier/ParserG4\Mx.g4 by ANTLR 4.9.2
package MxParser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Null=1, This=2, True=3, False=4, Class=5, If=6, Else=7, Int=8, Bool=9, 
		String=10, Void=11, While=12, For=13, Break=14, Continue=15, Return=16, 
		New=17, Dot=18, LeftParenthes=19, RightParenthes=20, LeftBracket=21, RightBracket=22, 
		LeftBrace=23, RightBrace=24, GreaterThan=25, LessThan=26, GreaterEqual=27, 
		LessEqual=28, NotEqual=29, Equal=30, Assign=31, SelfPlus=32, SelfMinus=33, 
		Plus=34, Minus=35, Divide=36, Mod=37, Times=38, LeftShift=39, RightShift=40, 
		AndAnd=41, OrOr=42, Or=43, And=44, Tidle=45, Xor=46, Not=47, Colon=48, 
		Quot=49, Comma=50, Semi=51, Question=52, RightArrow=53, BackSlash=54, 
		DbQuotation=55, Identifier=56, StringConst=57, Integer=58, Numeric=59, 
		WhiteSpace=60, Newline=61, BlockComment=62, LineComment=63, UCLTR=64, 
		ZERO=65, LCLTR=66, UNDERLINE=67;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Null", "This", "True", "False", "Class", "If", "Else", "Int", "Bool", 
			"String", "Void", "While", "For", "Break", "Continue", "Return", "New", 
			"Dot", "LeftParenthes", "RightParenthes", "LeftBracket", "RightBracket", 
			"LeftBrace", "RightBrace", "GreaterThan", "LessThan", "GreaterEqual", 
			"LessEqual", "NotEqual", "Equal", "Assign", "SelfPlus", "SelfMinus", 
			"Plus", "Minus", "Divide", "Mod", "Times", "LeftShift", "RightShift", 
			"AndAnd", "OrOr", "Or", "And", "Tidle", "Xor", "Not", "Colon", "Quot", 
			"Comma", "Semi", "Question", "RightArrow", "BackSlash", "DbQuotation", 
			"Identifier", "StringConst", "Integer", "Numeric", "WhiteSpace", "Newline", 
			"BlockComment", "LineComment", "DIGIT", "UCLTR", "ZERO", "LCLTR", "UNDERLINE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'null'", "'this'", "'true'", "'false'", "'class'", "'if'", "'else'", 
			"'int'", "'bool'", "'string'", "'void'", "'while'", "'for'", "'break'", 
			"'continue'", "'return'", "'new'", "'.'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'>'", "'<'", "'>='", "'<='", "'!='", "'=='", "'='", "'++'", 
			"'--'", "'+'", "'-'", "'/'", "'%'", "'*'", "'<<'", "'>>'", "'&&'", "'||'", 
			"'|'", "'&'", "'~'", "'^'", "'!'", "':'", "'\"'", "','", "';'", "'?'", 
			"'->'", "'\\\\'", "'\\\"'", null, null, null, null, null, null, null, 
			null, null, "'0'", null, "'_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Null", "This", "True", "False", "Class", "If", "Else", "Int", 
			"Bool", "String", "Void", "While", "For", "Break", "Continue", "Return", 
			"New", "Dot", "LeftParenthes", "RightParenthes", "LeftBracket", "RightBracket", 
			"LeftBrace", "RightBrace", "GreaterThan", "LessThan", "GreaterEqual", 
			"LessEqual", "NotEqual", "Equal", "Assign", "SelfPlus", "SelfMinus", 
			"Plus", "Minus", "Divide", "Mod", "Times", "LeftShift", "RightShift", 
			"AndAnd", "OrOr", "Or", "And", "Tidle", "Xor", "Not", "Colon", "Quot", 
			"Comma", "Semi", "Question", "RightArrow", "BackSlash", "DbQuotation", 
			"Identifier", "StringConst", "Integer", "Numeric", "WhiteSpace", "Newline", 
			"BlockComment", "LineComment", "UCLTR", "ZERO", "LCLTR", "UNDERLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2E\u019c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3!\3\"\3\"\3"+
		"\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3"+
		"+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64"+
		"\3\64\3\65\3\65\3\66\3\66\3\66\3\67\3\67\3\67\38\38\38\39\39\59\u0143"+
		"\n9\39\39\39\39\79\u0149\n9\f9\169\u014c\139\3:\3:\3:\3:\7:\u0152\n:\f"+
		":\16:\u0155\13:\3:\3:\3;\3;\3;\7;\u015c\n;\f;\16;\u015f\13;\5;\u0161\n"+
		";\3<\3<\3<\6<\u0166\n<\r<\16<\u0167\3=\6=\u016b\n=\r=\16=\u016c\3=\3="+
		"\3>\3>\5>\u0173\n>\3>\5>\u0176\n>\3>\3>\3?\3?\3?\3?\7?\u017e\n?\f?\16"+
		"?\u0181\13?\3?\3?\3?\3?\3?\3@\3@\3@\3@\7@\u018c\n@\f@\16@\u018f\13@\3"+
		"@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\4\u0153\u017f\2F\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081\2\u0083B\u0085C\u0087D\u0089E\3\2\b\3\2\63;\4\2\13\13\"\"\4\2"+
		"\f\f\17\17\3\2\62;\3\2C\\\3\2c|\2\u01aa\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2"+
		"\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2"+
		"g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3"+
		"\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3"+
		"\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2"+
		"\3\u008b\3\2\2\2\5\u0090\3\2\2\2\7\u0095\3\2\2\2\t\u009a\3\2\2\2\13\u00a0"+
		"\3\2\2\2\r\u00a6\3\2\2\2\17\u00a9\3\2\2\2\21\u00ae\3\2\2\2\23\u00b2\3"+
		"\2\2\2\25\u00b7\3\2\2\2\27\u00be\3\2\2\2\31\u00c3\3\2\2\2\33\u00c9\3\2"+
		"\2\2\35\u00cd\3\2\2\2\37\u00d3\3\2\2\2!\u00dc\3\2\2\2#\u00e3\3\2\2\2%"+
		"\u00e7\3\2\2\2\'\u00e9\3\2\2\2)\u00eb\3\2\2\2+\u00ed\3\2\2\2-\u00ef\3"+
		"\2\2\2/\u00f1\3\2\2\2\61\u00f3\3\2\2\2\63\u00f5\3\2\2\2\65\u00f7\3\2\2"+
		"\2\67\u00f9\3\2\2\29\u00fc\3\2\2\2;\u00ff\3\2\2\2=\u0102\3\2\2\2?\u0105"+
		"\3\2\2\2A\u0107\3\2\2\2C\u010a\3\2\2\2E\u010d\3\2\2\2G\u010f\3\2\2\2I"+
		"\u0111\3\2\2\2K\u0113\3\2\2\2M\u0115\3\2\2\2O\u0117\3\2\2\2Q\u011a\3\2"+
		"\2\2S\u011d\3\2\2\2U\u0120\3\2\2\2W\u0123\3\2\2\2Y\u0125\3\2\2\2[\u0127"+
		"\3\2\2\2]\u0129\3\2\2\2_\u012b\3\2\2\2a\u012d\3\2\2\2c\u012f\3\2\2\2e"+
		"\u0131\3\2\2\2g\u0133\3\2\2\2i\u0135\3\2\2\2k\u0137\3\2\2\2m\u013a\3\2"+
		"\2\2o\u013d\3\2\2\2q\u0142\3\2\2\2s\u014d\3\2\2\2u\u0160\3\2\2\2w\u0162"+
		"\3\2\2\2y\u016a\3\2\2\2{\u0175\3\2\2\2}\u0179\3\2\2\2\177\u0187\3\2\2"+
		"\2\u0081\u0192\3\2\2\2\u0083\u0194\3\2\2\2\u0085\u0196\3\2\2\2\u0087\u0198"+
		"\3\2\2\2\u0089\u019a\3\2\2\2\u008b\u008c\7p\2\2\u008c\u008d\7w\2\2\u008d"+
		"\u008e\7n\2\2\u008e\u008f\7n\2\2\u008f\4\3\2\2\2\u0090\u0091\7v\2\2\u0091"+
		"\u0092\7j\2\2\u0092\u0093\7k\2\2\u0093\u0094\7u\2\2\u0094\6\3\2\2\2\u0095"+
		"\u0096\7v\2\2\u0096\u0097\7t\2\2\u0097\u0098\7w\2\2\u0098\u0099\7g\2\2"+
		"\u0099\b\3\2\2\2\u009a\u009b\7h\2\2\u009b\u009c\7c\2\2\u009c\u009d\7n"+
		"\2\2\u009d\u009e\7u\2\2\u009e\u009f\7g\2\2\u009f\n\3\2\2\2\u00a0\u00a1"+
		"\7e\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7c\2\2\u00a3\u00a4\7u\2\2\u00a4"+
		"\u00a5\7u\2\2\u00a5\f\3\2\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7h\2\2\u00a8"+
		"\16\3\2\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7u\2\2\u00ac"+
		"\u00ad\7g\2\2\u00ad\20\3\2\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7p\2\2\u00b0"+
		"\u00b1\7v\2\2\u00b1\22\3\2\2\2\u00b2\u00b3\7d\2\2\u00b3\u00b4\7q\2\2\u00b4"+
		"\u00b5\7q\2\2\u00b5\u00b6\7n\2\2\u00b6\24\3\2\2\2\u00b7\u00b8\7u\2\2\u00b8"+
		"\u00b9\7v\2\2\u00b9\u00ba\7t\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7p\2\2"+
		"\u00bc\u00bd\7i\2\2\u00bd\26\3\2\2\2\u00be\u00bf\7x\2\2\u00bf\u00c0\7"+
		"q\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7f\2\2\u00c2\30\3\2\2\2\u00c3\u00c4"+
		"\7y\2\2\u00c4\u00c5\7j\2\2\u00c5\u00c6\7k\2\2\u00c6\u00c7\7n\2\2\u00c7"+
		"\u00c8\7g\2\2\u00c8\32\3\2\2\2\u00c9\u00ca\7h\2\2\u00ca\u00cb\7q\2\2\u00cb"+
		"\u00cc\7t\2\2\u00cc\34\3\2\2\2\u00cd\u00ce\7d\2\2\u00ce\u00cf\7t\2\2\u00cf"+
		"\u00d0\7g\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7m\2\2\u00d2\36\3\2\2\2\u00d3"+
		"\u00d4\7e\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7v\2\2"+
		"\u00d7\u00d8\7k\2\2\u00d8\u00d9\7p\2\2\u00d9\u00da\7w\2\2\u00da\u00db"+
		"\7g\2\2\u00db \3\2\2\2\u00dc\u00dd\7t\2\2\u00dd\u00de\7g\2\2\u00de\u00df"+
		"\7v\2\2\u00df\u00e0\7w\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2\7p\2\2\u00e2"+
		"\"\3\2\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7y\2\2\u00e6"+
		"$\3\2\2\2\u00e7\u00e8\7\60\2\2\u00e8&\3\2\2\2\u00e9\u00ea\7*\2\2\u00ea"+
		"(\3\2\2\2\u00eb\u00ec\7+\2\2\u00ec*\3\2\2\2\u00ed\u00ee\7]\2\2\u00ee,"+
		"\3\2\2\2\u00ef\u00f0\7_\2\2\u00f0.\3\2\2\2\u00f1\u00f2\7}\2\2\u00f2\60"+
		"\3\2\2\2\u00f3\u00f4\7\177\2\2\u00f4\62\3\2\2\2\u00f5\u00f6\7@\2\2\u00f6"+
		"\64\3\2\2\2\u00f7\u00f8\7>\2\2\u00f8\66\3\2\2\2\u00f9\u00fa\7@\2\2\u00fa"+
		"\u00fb\7?\2\2\u00fb8\3\2\2\2\u00fc\u00fd\7>\2\2\u00fd\u00fe\7?\2\2\u00fe"+
		":\3\2\2\2\u00ff\u0100\7#\2\2\u0100\u0101\7?\2\2\u0101<\3\2\2\2\u0102\u0103"+
		"\7?\2\2\u0103\u0104\7?\2\2\u0104>\3\2\2\2\u0105\u0106\7?\2\2\u0106@\3"+
		"\2\2\2\u0107\u0108\7-\2\2\u0108\u0109\7-\2\2\u0109B\3\2\2\2\u010a\u010b"+
		"\7/\2\2\u010b\u010c\7/\2\2\u010cD\3\2\2\2\u010d\u010e\7-\2\2\u010eF\3"+
		"\2\2\2\u010f\u0110\7/\2\2\u0110H\3\2\2\2\u0111\u0112\7\61\2\2\u0112J\3"+
		"\2\2\2\u0113\u0114\7\'\2\2\u0114L\3\2\2\2\u0115\u0116\7,\2\2\u0116N\3"+
		"\2\2\2\u0117\u0118\7>\2\2\u0118\u0119\7>\2\2\u0119P\3\2\2\2\u011a\u011b"+
		"\7@\2\2\u011b\u011c\7@\2\2\u011cR\3\2\2\2\u011d\u011e\7(\2\2\u011e\u011f"+
		"\7(\2\2\u011fT\3\2\2\2\u0120\u0121\7~\2\2\u0121\u0122\7~\2\2\u0122V\3"+
		"\2\2\2\u0123\u0124\7~\2\2\u0124X\3\2\2\2\u0125\u0126\7(\2\2\u0126Z\3\2"+
		"\2\2\u0127\u0128\7\u0080\2\2\u0128\\\3\2\2\2\u0129\u012a\7`\2\2\u012a"+
		"^\3\2\2\2\u012b\u012c\7#\2\2\u012c`\3\2\2\2\u012d\u012e\7<\2\2\u012eb"+
		"\3\2\2\2\u012f\u0130\7$\2\2\u0130d\3\2\2\2\u0131\u0132\7.\2\2\u0132f\3"+
		"\2\2\2\u0133\u0134\7=\2\2\u0134h\3\2\2\2\u0135\u0136\7A\2\2\u0136j\3\2"+
		"\2\2\u0137\u0138\7/\2\2\u0138\u0139\7@\2\2\u0139l\3\2\2\2\u013a\u013b"+
		"\7^\2\2\u013b\u013c\7^\2\2\u013cn\3\2\2\2\u013d\u013e\7^\2\2\u013e\u013f"+
		"\7$\2\2\u013fp\3\2\2\2\u0140\u0143\5\u0083B\2\u0141\u0143\5\u0087D\2\u0142"+
		"\u0140\3\2\2\2\u0142\u0141\3\2\2\2\u0143\u014a\3\2\2\2\u0144\u0149\5\u0081"+
		"A\2\u0145\u0149\5\u0083B\2\u0146\u0149\5\u0087D\2\u0147\u0149\5\u0089"+
		"E\2\u0148\u0144\3\2\2\2\u0148\u0145\3\2\2\2\u0148\u0146\3\2\2\2\u0148"+
		"\u0147\3\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2"+
		"\2\2\u014br\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u0153\5c\62\2\u014e\u0152"+
		"\5m\67\2\u014f\u0152\5o8\2\u0150\u0152\13\2\2\2\u0151\u014e\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0151\u0150\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0154\3\2"+
		"\2\2\u0153\u0151\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156"+
		"\u0157\5c\62\2\u0157t\3\2\2\2\u0158\u0161\5\u0085C\2\u0159\u015d\t\2\2"+
		"\2\u015a\u015c\5\u0081A\2\u015b\u015a\3\2\2\2\u015c\u015f\3\2\2\2\u015d"+
		"\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2"+
		"\2\2\u0160\u0158\3\2\2\2\u0160\u0159\3\2\2\2\u0161v\3\2\2\2\u0162\u0163"+
		"\5u;\2\u0163\u0165\5%\23\2\u0164\u0166\5\u0081A\2\u0165\u0164\3\2\2\2"+
		"\u0166\u0167\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168x\3"+
		"\2\2\2\u0169\u016b\t\3\2\2\u016a\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c"+
		"\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\b="+
		"\2\2\u016fz\3\2\2\2\u0170\u0172\7\17\2\2\u0171\u0173\7\f\2\2\u0172\u0171"+
		"\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0176\3\2\2\2\u0174\u0176\7\f\2\2\u0175"+
		"\u0170\3\2\2\2\u0175\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178\b>"+
		"\2\2\u0178|\3\2\2\2\u0179\u017a\7\61\2\2\u017a\u017b\7,\2\2\u017b\u017f"+
		"\3\2\2\2\u017c\u017e\13\2\2\2\u017d\u017c\3\2\2\2\u017e\u0181\3\2\2\2"+
		"\u017f\u0180\3\2\2\2\u017f\u017d\3\2\2\2\u0180\u0182\3\2\2\2\u0181\u017f"+
		"\3\2\2\2\u0182\u0183\7,\2\2\u0183\u0184\7\61\2\2\u0184\u0185\3\2\2\2\u0185"+
		"\u0186\b?\2\2\u0186~\3\2\2\2\u0187\u0188\7\61\2\2\u0188\u0189\7\61\2\2"+
		"\u0189\u018d\3\2\2\2\u018a\u018c\n\4\2\2\u018b\u018a\3\2\2\2\u018c\u018f"+
		"\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u0190\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u0190\u0191\b@\2\2\u0191\u0080\3\2\2\2\u0192\u0193\t\5"+
		"\2\2\u0193\u0082\3\2\2\2\u0194\u0195\t\6\2\2\u0195\u0084\3\2\2\2\u0196"+
		"\u0197\7\62\2\2\u0197\u0086\3\2\2\2\u0198\u0199\t\7\2\2\u0199\u0088\3"+
		"\2\2\2\u019a\u019b\7a\2\2\u019b\u008a\3\2\2\2\20\2\u0142\u0148\u014a\u0151"+
		"\u0153\u015d\u0160\u0167\u016c\u0172\u0175\u017f\u018d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}