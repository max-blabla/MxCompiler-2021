// Generated from D:/JavaCoding/Compiler/ParserG4\Mx.g4 by ANTLR 4.9.1
package MxParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

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
	public static final int
		RULE_program = 0, RULE_definition = 1, RULE_funcDef = 2, RULE_memberDef = 3, 
		RULE_classSpace = 4, RULE_classDef = 5, RULE_varDef = 6, RULE_varDeclaration = 7, 
		RULE_parameterList = 8, RULE_expressionList = 9, RULE_suite = 10, RULE_statement = 11, 
		RULE_expression = 12, RULE_primary = 13, RULE_literal = 14, RULE_returnType = 15, 
		RULE_type = 16, RULE_builtinType = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "definition", "funcDef", "memberDef", "classSpace", "classDef", 
			"varDef", "varDeclaration", "parameterList", "expressionList", "suite", 
			"statement", "expression", "primary", "literal", "returnType", "type", 
			"builtinType"
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

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Class) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
					{
					{
					setState(36);
					definition();
					}
					}
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionContext extends ParserRuleContext {
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definition);
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				funcDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				varDef();
				setState(47);
				match(Semi);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(49);
				classDef();
				setState(50);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			returnType();
			setState(55);
			match(Identifier);
			setState(56);
			match(LeftParenthes);
			setState(57);
			parameterList();
			setState(58);
			match(RightParenthes);
			setState(59);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public DefinitionContext definition() {
			return getRuleContext(DefinitionContext.class,0);
		}
		public MemberDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMemberDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberDefContext memberDef() throws RecognitionException {
		MemberDefContext _localctx = new MemberDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_memberDef);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(Identifier);
				setState(62);
				match(LeftParenthes);
				setState(63);
				parameterList();
				setState(64);
				match(RightParenthes);
				setState(65);
				suite();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				definition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassSpaceContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<MemberDefContext> memberDef() {
			return getRuleContexts(MemberDefContext.class);
		}
		public MemberDefContext memberDef(int i) {
			return getRuleContext(MemberDefContext.class,i);
		}
		public ClassSpaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classSpace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassSpace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassSpace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassSpace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassSpaceContext classSpace() throws RecognitionException {
		ClassSpaceContext _localctx = new ClassSpaceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classSpace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(LeftBrace);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Class) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				{
				setState(71);
				memberDef();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ClassSpaceContext classSpace() {
			return getRuleContext(ClassSpaceContext.class,0);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(Class);
			setState(80);
			match(Identifier);
			setState(81);
			classSpace();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			type();
			setState(84);
			varDeclaration();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(85);
				match(Comma);
				setState(86);
				varDeclaration();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDeclaration);
		int _la;
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(Identifier);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Assign) {
					{
					setState(93);
					match(Assign);
					setState(94);
					expression(0);
					}
				}

				}
				break;
			case LeftBracket:
				enterOuterAlt(_localctx, 2);
				{
				setState(99); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(97);
					match(LeftBracket);
					setState(98);
					match(RightBracket);
					}
					}
					setState(101); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LeftBracket );
				setState(103);
				match(Identifier);
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Assign) {
					{
					setState(104);
					match(Assign);
					setState(105);
					expression(0);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(110);
				type();
				setState(111);
				match(Identifier);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(112);
					match(Comma);
					setState(113);
					type();
					setState(114);
					match(Identifier);
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			expression(0);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(124);
				match(Comma);
				setState(125);
				expression(0);
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(LeftBrace);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << If) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << While) | (1L << For) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Semi) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
				{
				{
				setState(132);
				statement();
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(138);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext trueStmt;
		public StatementContext falseStmt;
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statement);
		int _la;
		try {
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				suite();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				varDef();
				setState(142);
				match(Semi);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				match(If);
				setState(145);
				match(LeftParenthes);
				setState(146);
				expression(0);
				setState(147);
				match(RightParenthes);
				setState(148);
				((StatementContext)_localctx).trueStmt = statement();
				setState(151);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(149);
					match(Else);
					setState(150);
					((StatementContext)_localctx).falseStmt = statement();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				match(For);
				setState(154);
				match(LeftParenthes);
				setState(157);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(155);
					expression(0);
					}
					break;
				case 2:
					{
					setState(156);
					varDef();
					}
					break;
				}
				setState(159);
				match(Semi);
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << Break) | (1L << Continue) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
					{
					setState(160);
					expression(0);
					}
				}

				setState(163);
				match(Semi);
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << Break) | (1L << Continue) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
					{
					setState(164);
					expression(0);
					}
				}

				setState(167);
				match(RightParenthes);
				setState(168);
				statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(169);
				match(While);
				setState(170);
				match(LeftParenthes);
				setState(171);
				expression(0);
				setState(172);
				match(RightParenthes);
				setState(173);
				statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(175);
				match(Return);
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << Break) | (1L << Continue) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
					{
					setState(176);
					expression(0);
					}
				}

				setState(179);
				match(Semi);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(180);
				expression(0);
				setState(181);
				match(Semi);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(183);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Token op;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Plus() { return getToken(MxParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxParser.Minus, 0); }
		public TerminalNode SelfPlus() { return getToken(MxParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxParser.SelfMinus, 0); }
		public TerminalNode Not() { return getToken(MxParser.Not, 0); }
		public TerminalNode Tidle() { return getToken(MxParser.Tidle, 0); }
		public TerminalNode Dot() { return getToken(MxParser.Dot, 0); }
		public TerminalNode Times() { return getToken(MxParser.Times, 0); }
		public TerminalNode Divide() { return getToken(MxParser.Divide, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode GreaterThan() { return getToken(MxParser.GreaterThan, 0); }
		public TerminalNode LessThan() { return getToken(MxParser.LessThan, 0); }
		public TerminalNode LessEqual() { return getToken(MxParser.LessEqual, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxParser.GreaterEqual, 0); }
		public TerminalNode Equal() { return getToken(MxParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxParser.NotEqual, 0); }
		public TerminalNode Xor() { return getToken(MxParser.Xor, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public TerminalNode AndAnd() { return getToken(MxParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(MxParser.OrOr, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(187);
				primary();
				}
				break;
			case 2:
				{
				setState(188);
				match(LeftBracket);
				setState(189);
				match(And);
				setState(190);
				match(RightBracket);
				setState(191);
				match(LeftParenthes);
				}
				break;
			case 3:
				{
				setState(192);
				match(New);
				setState(193);
				type();
				setState(196);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(194);
					match(LeftParenthes);
					setState(195);
					match(RightParenthes);
					}
					break;
				}
				}
				break;
			case 4:
				{
				setState(198);
				match(New);
				setState(199);
				type();
				{
				setState(200);
				match(LeftBracket);
				setState(201);
				expression(0);
				setState(202);
				match(RightBracket);
				}
				setState(211);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(204);
						match(LeftBracket);
						setState(206);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << Break) | (1L << Continue) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
							{
							setState(205);
							expression(0);
							}
						}

						setState(208);
						match(RightBracket);
						}
						} 
					}
					setState(213);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				}
				break;
			case 5:
				{
				setState(214);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(215);
				expression(16);
				}
				break;
			case 6:
				{
				setState(216);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SelfPlus || _la==SelfMinus) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(217);
				expression(15);
				}
				break;
			case 7:
				{
				setState(218);
				((ExpressionContext)_localctx).op = match(Not);
				setState(219);
				expression(13);
				}
				break;
			case 8:
				{
				setState(220);
				((ExpressionContext)_localctx).op = match(Tidle);
				setState(221);
				expression(12);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(275);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(273);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(224);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(225);
						((ExpressionContext)_localctx).op = match(Dot);
						setState(226);
						expression(18);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(227);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(228);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Divide) | (1L << Mod) | (1L << Times))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(229);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(230);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(231);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(232);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(233);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(234);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(235);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(236);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(237);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GreaterThan) | (1L << LessThan) | (1L << GreaterEqual) | (1L << LessEqual))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(238);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(239);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(240);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==NotEqual || _la==Equal) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(241);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(242);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(243);
						((ExpressionContext)_localctx).op = match(And);
						setState(244);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(245);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(246);
						((ExpressionContext)_localctx).op = match(Xor);
						setState(247);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(248);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(249);
						((ExpressionContext)_localctx).op = match(Or);
						setState(250);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(251);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(252);
						((ExpressionContext)_localctx).op = match(AndAnd);
						setState(253);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(254);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(255);
						((ExpressionContext)_localctx).op = match(OrOr);
						setState(256);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(257);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(258);
						((ExpressionContext)_localctx).op = match(Assign);
						setState(259);
						expression(1);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(260);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(261);
						match(LeftBracket);
						setState(262);
						expression(0);
						setState(263);
						match(RightBracket);
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(265);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(266);
						match(LeftParenthes);
						setState(268);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << Break) | (1L << Continue) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
							{
							setState(267);
							expressionList();
							}
						}

						setState(270);
						match(RightParenthes);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(272);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SelfPlus || _la==SelfMinus) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(277);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_primary);
		try {
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParenthes:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				match(LeftParenthes);
				setState(279);
				expression(0);
				setState(280);
				match(RightParenthes);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				match(Identifier);
				}
				break;
			case Break:
				enterOuterAlt(_localctx, 3);
				{
				setState(283);
				match(Break);
				}
				break;
			case Continue:
				enterOuterAlt(_localctx, 4);
				{
				setState(284);
				match(Continue);
				}
				break;
			case This:
				enterOuterAlt(_localctx, 5);
				{
				setState(285);
				match(This);
				}
				break;
			case Null:
			case True:
			case False:
			case StringConst:
			case Integer:
			case Numeric:
				enterOuterAlt(_localctx, 6);
				{
				setState(286);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode Integer() { return getToken(MxParser.Integer, 0); }
		public TerminalNode StringConst() { return getToken(MxParser.StringConst, 0); }
		public TerminalNode Numeric() { return getToken(MxParser.Numeric, 0); }
		public TerminalNode True() { return getToken(MxParser.True, 0); }
		public TerminalNode False() { return getToken(MxParser.False, 0); }
		public TerminalNode Null() { return getToken(MxParser.Null, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public TerminalNode Void() { return getToken(MxParser.Void, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnType);
		try {
			setState(293);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Void:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(Void);
				}
				break;
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BuiltinTypeContext builtinType() {
			return getRuleContext(BuiltinTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_type);
		try {
			int _alt;
			setState(311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				builtinType();
				setState(300);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(296);
						match(LeftBracket);
						setState(297);
						match(RightBracket);
						}
						} 
					}
					setState(302);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				match(Identifier);
				setState(308);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(304);
						match(LeftBracket);
						setState(305);
						match(RightBracket);
						}
						} 
					}
					setState(310);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuiltinTypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode String() { return getToken(MxParser.String, 0); }
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public BuiltinTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtinType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBuiltinType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBuiltinType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBuiltinType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuiltinTypeContext builtinType() throws RecognitionException {
		BuiltinTypeContext _localctx = new BuiltinTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_builtinType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 17);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 19);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3E\u013e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\2\5\2.\n\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3\67\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\5G\n\5\3\6\3\6\7\6K\n\6\f\6\16\6N\13\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\7\bZ\n\b\f\b\16\b]\13\b\3\t\3\t\3\t\5\tb\n\t\3\t"+
		"\3\t\6\tf\n\t\r\t\16\tg\3\t\3\t\3\t\5\tm\n\t\5\to\n\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\nw\n\n\f\n\16\nz\13\n\5\n|\n\n\3\13\3\13\3\13\7\13\u0081\n"+
		"\13\f\13\16\13\u0084\13\13\3\f\3\f\7\f\u0088\n\f\f\f\16\f\u008b\13\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u009a\n\r\3\r\3"+
		"\r\3\r\3\r\5\r\u00a0\n\r\3\r\3\r\5\r\u00a4\n\r\3\r\3\r\5\r\u00a8\n\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b4\n\r\3\r\3\r\3\r\3\r\3"+
		"\r\5\r\u00bb\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u00c7\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00d1\n\16\3"+
		"\16\7\16\u00d4\n\16\f\16\16\16\u00d7\13\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\5\16\u00e1\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u010f\n\16\3\16\3\16\3\16"+
		"\7\16\u0114\n\16\f\16\16\16\u0117\13\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u0122\n\17\3\20\3\20\3\21\3\21\5\21\u0128\n\21\3"+
		"\22\3\22\3\22\7\22\u012d\n\22\f\22\16\22\u0130\13\22\3\22\3\22\3\22\7"+
		"\22\u0135\n\22\f\22\16\22\u0138\13\22\5\22\u013a\n\22\3\23\3\23\3\23\2"+
		"\3\32\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\n\3\2$%\3\2\"#\3"+
		"\2&(\3\2)*\3\2\33\36\3\2\37 \5\2\3\3\5\6;=\3\2\n\f\2\u016a\2-\3\2\2\2"+
		"\4\66\3\2\2\2\68\3\2\2\2\bF\3\2\2\2\nH\3\2\2\2\fQ\3\2\2\2\16U\3\2\2\2"+
		"\20n\3\2\2\2\22{\3\2\2\2\24}\3\2\2\2\26\u0085\3\2\2\2\30\u00ba\3\2\2\2"+
		"\32\u00e0\3\2\2\2\34\u0121\3\2\2\2\36\u0123\3\2\2\2 \u0127\3\2\2\2\"\u0139"+
		"\3\2\2\2$\u013b\3\2\2\2&(\5\4\3\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3"+
		"\2\2\2*.\3\2\2\2+)\3\2\2\2,.\7\2\2\3-)\3\2\2\2-,\3\2\2\2.\3\3\2\2\2/\67"+
		"\5\6\4\2\60\61\5\16\b\2\61\62\7\65\2\2\62\67\3\2\2\2\63\64\5\f\7\2\64"+
		"\65\7\65\2\2\65\67\3\2\2\2\66/\3\2\2\2\66\60\3\2\2\2\66\63\3\2\2\2\67"+
		"\5\3\2\2\289\5 \21\29:\7:\2\2:;\7\25\2\2;<\5\22\n\2<=\7\26\2\2=>\5\26"+
		"\f\2>\7\3\2\2\2?@\7:\2\2@A\7\25\2\2AB\5\22\n\2BC\7\26\2\2CD\5\26\f\2D"+
		"G\3\2\2\2EG\5\4\3\2F?\3\2\2\2FE\3\2\2\2G\t\3\2\2\2HL\7\31\2\2IK\5\b\5"+
		"\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\32"+
		"\2\2P\13\3\2\2\2QR\7\7\2\2RS\7:\2\2ST\5\n\6\2T\r\3\2\2\2UV\5\"\22\2V["+
		"\5\20\t\2WX\7\64\2\2XZ\5\20\t\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2"+
		"\2\\\17\3\2\2\2][\3\2\2\2^a\7:\2\2_`\7!\2\2`b\5\32\16\2a_\3\2\2\2ab\3"+
		"\2\2\2bo\3\2\2\2cd\7\27\2\2df\7\30\2\2ec\3\2\2\2fg\3\2\2\2ge\3\2\2\2g"+
		"h\3\2\2\2hi\3\2\2\2il\7:\2\2jk\7!\2\2km\5\32\16\2lj\3\2\2\2lm\3\2\2\2"+
		"mo\3\2\2\2n^\3\2\2\2ne\3\2\2\2o\21\3\2\2\2pq\5\"\22\2qx\7:\2\2rs\7\64"+
		"\2\2st\5\"\22\2tu\7:\2\2uw\3\2\2\2vr\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2"+
		"\2\2y|\3\2\2\2zx\3\2\2\2{p\3\2\2\2{|\3\2\2\2|\23\3\2\2\2}\u0082\5\32\16"+
		"\2~\177\7\64\2\2\177\u0081\5\32\16\2\u0080~\3\2\2\2\u0081\u0084\3\2\2"+
		"\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\25\3\2\2\2\u0084\u0082"+
		"\3\2\2\2\u0085\u0089\7\31\2\2\u0086\u0088\5\30\r\2\u0087\u0086\3\2\2\2"+
		"\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c"+
		"\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\7\32\2\2\u008d\27\3\2\2\2\u008e"+
		"\u00bb\5\26\f\2\u008f\u0090\5\16\b\2\u0090\u0091\7\65\2\2\u0091\u00bb"+
		"\3\2\2\2\u0092\u0093\7\b\2\2\u0093\u0094\7\25\2\2\u0094\u0095\5\32\16"+
		"\2\u0095\u0096\7\26\2\2\u0096\u0099\5\30\r\2\u0097\u0098\7\t\2\2\u0098"+
		"\u009a\5\30\r\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u00bb\3"+
		"\2\2\2\u009b\u009c\7\17\2\2\u009c\u009f\7\25\2\2\u009d\u00a0\5\32\16\2"+
		"\u009e\u00a0\5\16\b\2\u009f\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\7\65\2\2\u00a2\u00a4\5\32\16"+
		"\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7"+
		"\7\65\2\2\u00a6\u00a8\5\32\16\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2"+
		"\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\7\26\2\2\u00aa\u00bb\5\30\r\2\u00ab"+
		"\u00ac\7\16\2\2\u00ac\u00ad\7\25\2\2\u00ad\u00ae\5\32\16\2\u00ae\u00af"+
		"\7\26\2\2\u00af\u00b0\5\30\r\2\u00b0\u00bb\3\2\2\2\u00b1\u00b3\7\22\2"+
		"\2\u00b2\u00b4\5\32\16\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00bb\7\65\2\2\u00b6\u00b7\5\32\16\2\u00b7\u00b8"+
		"\7\65\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00bb\7\65\2\2\u00ba\u008e\3\2\2\2"+
		"\u00ba\u008f\3\2\2\2\u00ba\u0092\3\2\2\2\u00ba\u009b\3\2\2\2\u00ba\u00ab"+
		"\3\2\2\2\u00ba\u00b1\3\2\2\2\u00ba\u00b6\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb"+
		"\31\3\2\2\2\u00bc\u00bd\b\16\1\2\u00bd\u00e1\5\34\17\2\u00be\u00bf\7\27"+
		"\2\2\u00bf\u00c0\7.\2\2\u00c0\u00c1\7\30\2\2\u00c1\u00e1\7\25\2\2\u00c2"+
		"\u00c3\7\23\2\2\u00c3\u00c6\5\"\22\2\u00c4\u00c5\7\25\2\2\u00c5\u00c7"+
		"\7\26\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00e1\3\2\2\2"+
		"\u00c8\u00c9\7\23\2\2\u00c9\u00ca\5\"\22\2\u00ca\u00cb\7\27\2\2\u00cb"+
		"\u00cc\5\32\16\2\u00cc\u00cd\7\30\2\2\u00cd\u00d5\3\2\2\2\u00ce\u00d0"+
		"\7\27\2\2\u00cf\u00d1\5\32\16\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2\2"+
		"\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\7\30\2\2\u00d3\u00ce\3\2\2\2\u00d4"+
		"\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00e1\3\2"+
		"\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\t\2\2\2\u00d9\u00e1\5\32\16\22\u00da"+
		"\u00db\t\3\2\2\u00db\u00e1\5\32\16\21\u00dc\u00dd\7\61\2\2\u00dd\u00e1"+
		"\5\32\16\17\u00de\u00df\7/\2\2\u00df\u00e1\5\32\16\16\u00e0\u00bc\3\2"+
		"\2\2\u00e0\u00be\3\2\2\2\u00e0\u00c2\3\2\2\2\u00e0\u00c8\3\2\2\2\u00e0"+
		"\u00d8\3\2\2\2\u00e0\u00da\3\2\2\2\u00e0\u00dc\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e1\u0115\3\2\2\2\u00e2\u00e3\f\23\2\2\u00e3\u00e4\7\24\2\2\u00e4"+
		"\u0114\5\32\16\24\u00e5\u00e6\f\r\2\2\u00e6\u00e7\t\4\2\2\u00e7\u0114"+
		"\5\32\16\16\u00e8\u00e9\f\f\2\2\u00e9\u00ea\t\2\2\2\u00ea\u0114\5\32\16"+
		"\r\u00eb\u00ec\f\13\2\2\u00ec\u00ed\t\5\2\2\u00ed\u0114\5\32\16\f\u00ee"+
		"\u00ef\f\n\2\2\u00ef\u00f0\t\6\2\2\u00f0\u0114\5\32\16\13\u00f1\u00f2"+
		"\f\t\2\2\u00f2\u00f3\t\7\2\2\u00f3\u0114\5\32\16\n\u00f4\u00f5\f\b\2\2"+
		"\u00f5\u00f6\7.\2\2\u00f6\u0114\5\32\16\t\u00f7\u00f8\f\7\2\2\u00f8\u00f9"+
		"\7\60\2\2\u00f9\u0114\5\32\16\b\u00fa\u00fb\f\6\2\2\u00fb\u00fc\7-\2\2"+
		"\u00fc\u0114\5\32\16\7\u00fd\u00fe\f\5\2\2\u00fe\u00ff\7+\2\2\u00ff\u0114"+
		"\5\32\16\6\u0100\u0101\f\4\2\2\u0101\u0102\7,\2\2\u0102\u0114\5\32\16"+
		"\5\u0103\u0104\f\3\2\2\u0104\u0105\7!\2\2\u0105\u0114\5\32\16\3\u0106"+
		"\u0107\f\25\2\2\u0107\u0108\7\27\2\2\u0108\u0109\5\32\16\2\u0109\u010a"+
		"\7\30\2\2\u010a\u0114\3\2\2\2\u010b\u010c\f\24\2\2\u010c\u010e\7\25\2"+
		"\2\u010d\u010f\5\24\13\2\u010e\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0114\7\26\2\2\u0111\u0112\f\20\2\2\u0112\u0114\t"+
		"\3\2\2\u0113\u00e2\3\2\2\2\u0113\u00e5\3\2\2\2\u0113\u00e8\3\2\2\2\u0113"+
		"\u00eb\3\2\2\2\u0113\u00ee\3\2\2\2\u0113\u00f1\3\2\2\2\u0113\u00f4\3\2"+
		"\2\2\u0113\u00f7\3\2\2\2\u0113\u00fa\3\2\2\2\u0113\u00fd\3\2\2\2\u0113"+
		"\u0100\3\2\2\2\u0113\u0103\3\2\2\2\u0113\u0106\3\2\2\2\u0113\u010b\3\2"+
		"\2\2\u0113\u0111\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116\33\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0119\7\25\2"+
		"\2\u0119\u011a\5\32\16\2\u011a\u011b\7\26\2\2\u011b\u0122\3\2\2\2\u011c"+
		"\u0122\7:\2\2\u011d\u0122\7\20\2\2\u011e\u0122\7\21\2\2\u011f\u0122\7"+
		"\4\2\2\u0120\u0122\5\36\20\2\u0121\u0118\3\2\2\2\u0121\u011c\3\2\2\2\u0121"+
		"\u011d\3\2\2\2\u0121\u011e\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0120\3\2"+
		"\2\2\u0122\35\3\2\2\2\u0123\u0124\t\b\2\2\u0124\37\3\2\2\2\u0125\u0128"+
		"\7\r\2\2\u0126\u0128\5\"\22\2\u0127\u0125\3\2\2\2\u0127\u0126\3\2\2\2"+
		"\u0128!\3\2\2\2\u0129\u012e\5$\23\2\u012a\u012b\7\27\2\2\u012b\u012d\7"+
		"\30\2\2\u012c\u012a\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e"+
		"\u012f\3\2\2\2\u012f\u013a\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0136\7:"+
		"\2\2\u0132\u0133\7\27\2\2\u0133\u0135\7\30\2\2\u0134\u0132\3\2\2\2\u0135"+
		"\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013a\3\2"+
		"\2\2\u0138\u0136\3\2\2\2\u0139\u0129\3\2\2\2\u0139\u0131\3\2\2\2\u013a"+
		"#\3\2\2\2\u013b\u013c\t\t\2\2\u013c%\3\2\2\2\")-\66FL[aglnx{\u0082\u0089"+
		"\u0099\u009f\u00a3\u00a7\u00b3\u00ba\u00c6\u00d0\u00d5\u00e0\u010e\u0113"+
		"\u0115\u0121\u0127\u012e\u0136\u0139";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}