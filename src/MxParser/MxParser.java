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
		RULE_program = 0, RULE_definition = 1, RULE_funcDef = 2, RULE_conFuncDef = 3, 
		RULE_memberFuncDef = 4, RULE_memberVarDef = 5, RULE_memberDefinition = 6, 
		RULE_classSpace = 7, RULE_classDef = 8, RULE_varDef = 9, RULE_varDeclaration = 10, 
		RULE_parameterList = 11, RULE_expressionList = 12, RULE_suite = 13, RULE_ifStatement = 14, 
		RULE_elseStatement = 15, RULE_ifElseStatement = 16, RULE_forStatement = 17, 
		RULE_whileStatement = 18, RULE_returnStatement = 19, RULE_jumpStatement = 20, 
		RULE_expStatement = 21, RULE_statement = 22, RULE_lambdaExpression = 23, 
		RULE_newSingle = 24, RULE_newArrayExp = 25, RULE_newArray = 26, RULE_indexExpr = 27, 
		RULE_funcExpr = 28, RULE_condition = 29, RULE_leftSelfExprssion = 30, 
		RULE_expression = 31, RULE_parenthes = 32, RULE_label = 33, RULE_primary = 34, 
		RULE_literal = 35, RULE_returnType = 36, RULE_singleType = 37, RULE_type = 38, 
		RULE_builtinType = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "definition", "funcDef", "conFuncDef", "memberFuncDef", "memberVarDef", 
			"memberDefinition", "classSpace", "classDef", "varDef", "varDeclaration", 
			"parameterList", "expressionList", "suite", "ifStatement", "elseStatement", 
			"ifElseStatement", "forStatement", "whileStatement", "returnStatement", 
			"jumpStatement", "expStatement", "statement", "lambdaExpression", "newSingle", 
			"newArrayExp", "newArray", "indexExpr", "funcExpr", "condition", "leftSelfExprssion", 
			"expression", "parenthes", "label", "primary", "literal", "returnType", 
			"singleType", "type", "builtinType"
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
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Class) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
					{
					{
					setState(80);
					definition();
					}
					}
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
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
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
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
		int _la;
		try {
			setState(104);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				funcDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				varDef();
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Semi) {
					{
					{
					setState(91);
					match(Semi);
					}
					}
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				classDef();
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Semi) {
					{
					{
					setState(98);
					match(Semi);
					}
					}
					setState(103);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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
			setState(106);
			returnType();
			setState(107);
			match(Identifier);
			setState(108);
			match(LeftParenthes);
			setState(109);
			parameterList();
			setState(110);
			match(RightParenthes);
			setState(111);
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

	public static class ConFuncDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ConFuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conFuncDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterConFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitConFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitConFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConFuncDefContext conFuncDef() throws RecognitionException {
		ConFuncDefContext _localctx = new ConFuncDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_conFuncDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(Identifier);
			setState(114);
			match(LeftParenthes);
			setState(115);
			parameterList();
			setState(116);
			match(RightParenthes);
			setState(117);
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

	public static class MemberFuncDefContext extends ParserRuleContext {
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public MemberFuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberFuncDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMemberFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberFuncDefContext memberFuncDef() throws RecognitionException {
		MemberFuncDefContext _localctx = new MemberFuncDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_memberFuncDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			funcDef();
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

	public static class MemberVarDefContext extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public MemberVarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberVarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMemberVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberVarDefContext memberVarDef() throws RecognitionException {
		MemberVarDefContext _localctx = new MemberVarDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_memberVarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			varDef();
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

	public static class MemberDefinitionContext extends ParserRuleContext {
		public ConFuncDefContext conFuncDef() {
			return getRuleContext(ConFuncDefContext.class,0);
		}
		public MemberFuncDefContext memberFuncDef() {
			return getRuleContext(MemberFuncDefContext.class,0);
		}
		public MemberVarDefContext memberVarDef() {
			return getRuleContext(MemberVarDefContext.class,0);
		}
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public MemberDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMemberDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberDefinitionContext memberDefinition() throws RecognitionException {
		MemberDefinitionContext _localctx = new MemberDefinitionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_memberDefinition);
		int _la;
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				conFuncDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				memberFuncDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				memberVarDef();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Semi) {
					{
					{
					setState(126);
					match(Semi);
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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
		public List<MemberDefinitionContext> memberDefinition() {
			return getRuleContexts(MemberDefinitionContext.class);
		}
		public MemberDefinitionContext memberDefinition(int i) {
			return getRuleContext(MemberDefinitionContext.class,i);
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
		enterRule(_localctx, 14, RULE_classSpace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(LeftBrace);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				{
				setState(135);
				memberDefinition();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141);
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
		enterRule(_localctx, 16, RULE_classDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(Class);
			setState(144);
			match(Identifier);
			setState(145);
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
		enterRule(_localctx, 18, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			type();
			setState(148);
			varDeclaration();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(149);
				match(Comma);
				setState(150);
				varDeclaration();
				}
				}
				setState(155);
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
		enterRule(_localctx, 20, RULE_varDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(Identifier);
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(157);
				match(Assign);
				setState(158);
				expression(0);
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
		enterRule(_localctx, 22, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(161);
				type();
				setState(162);
				match(Identifier);
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(163);
					match(Comma);
					setState(164);
					type();
					setState(165);
					match(Identifier);
					}
					}
					setState(171);
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
		enterRule(_localctx, 24, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
				{
				setState(174);
				expression(0);
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(175);
					match(Comma);
					setState(176);
					expression(0);
					}
					}
					setState(181);
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
		enterRule(_localctx, 26, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(LeftBrace);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << If) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << While) | (1L << For) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Semi) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
				{
				{
				setState(185);
				statement();
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191);
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

	public static class IfStatementContext extends ParserRuleContext {
		public StatementContext trueStmt;
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(If);
			setState(194);
			match(LeftParenthes);
			setState(195);
			condition();
			setState(196);
			match(RightParenthes);
			setState(197);
			((IfStatementContext)_localctx).trueStmt = statement();
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

	public static class ElseStatementContext extends ParserRuleContext {
		public StatementContext falseStmt;
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(Else);
			setState(200);
			((ElseStatementContext)_localctx).falseStmt = statement();
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

	public static class IfElseStatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public IfElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifElseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIfElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfElseStatementContext ifElseStatement() throws RecognitionException {
		IfElseStatementContext _localctx = new IfElseStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ifElseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			ifStatement();
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(203);
				elseStatement();
				}
				break;
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

	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpStatementContext> expStatement() {
			return getRuleContexts(ExpStatementContext.class);
		}
		public ExpStatementContext expStatement(int i) {
			return getRuleContext(ExpStatementContext.class,i);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(For);
			setState(207);
			match(LeftParenthes);
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(208);
				expStatement();
				}
				break;
			case 2:
				{
				setState(209);
				varDef();
				}
				break;
			}
			setState(212);
			match(Semi);
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
				{
				setState(213);
				condition();
				}
			}

			setState(216);
			match(Semi);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
				{
				setState(217);
				expStatement();
				}
			}

			setState(220);
			match(RightParenthes);
			setState(221);
			statement();
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

	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(While);
			setState(224);
			match(LeftParenthes);
			setState(225);
			condition();
			setState(226);
			match(RightParenthes);
			setState(227);
			statement();
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

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(Return);
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
				{
				setState(230);
				expression(0);
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

	public static class JumpStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitJumpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitJumpStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_jumpStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !(_la==Break || _la==Continue) ) {
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

	public static class ExpStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExpStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpStatementContext expStatement() throws RecognitionException {
		ExpStatementContext _localctx = new ExpStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			expression(0);
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
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public IfElseStatementContext ifElseStatement() {
			return getRuleContext(IfElseStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public ExpStatementContext expStatement() {
			return getRuleContext(ExpStatementContext.class,0);
		}
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
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
		enterRule(_localctx, 44, RULE_statement);
		try {
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				suite();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(238);
				varDef();
				setState(239);
				match(Semi);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				ifElseStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(242);
				forStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(243);
				whileStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(244);
				returnStatement();
				setState(245);
				match(Semi);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(247);
				expStatement();
				setState(248);
				match(Semi);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(250);
				jumpStatement();
				setState(251);
				match(Semi);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(253);
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

	public static class LambdaExpressionContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public TerminalNode RightArrow() { return getToken(MxParser.RightArrow, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLambdaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(LeftBracket);
			setState(257);
			match(And);
			setState(258);
			match(RightBracket);
			setState(259);
			match(LeftParenthes);
			setState(260);
			parameterList();
			setState(261);
			match(RightParenthes);
			setState(262);
			match(RightArrow);
			setState(263);
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

	public static class NewSingleContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public SingleTypeContext singleType() {
			return getRuleContext(SingleTypeContext.class,0);
		}
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public NewSingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newSingle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewSingle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewSingle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewSingle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewSingleContext newSingle() throws RecognitionException {
		NewSingleContext _localctx = new NewSingleContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_newSingle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(New);
			setState(266);
			singleType();
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(267);
				match(LeftParenthes);
				setState(268);
				match(RightParenthes);
				}
				break;
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

	public static class NewArrayExpContext extends ParserRuleContext {
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewArrayExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newArrayExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewArrayExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewArrayExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewArrayExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewArrayExpContext newArrayExp() throws RecognitionException {
		NewArrayExpContext _localctx = new NewArrayExpContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_newArrayExp);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(276); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(271);
					match(LeftBracket);
					setState(273);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << This) | (1L << True) | (1L << False) | (1L << New) | (1L << LeftParenthes) | (1L << LeftBracket) | (1L << SelfPlus) | (1L << SelfMinus) | (1L << Plus) | (1L << Minus) | (1L << Tidle) | (1L << Not) | (1L << Identifier) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) {
						{
						setState(272);
						expression(0);
						}
					}

					setState(275);
					match(RightBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(278); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(280);
					match(LeftBracket);
					setState(281);
					match(RightBracket);
					}
					} 
				}
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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

	public static class NewArrayContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public SingleTypeContext singleType() {
			return getRuleContext(SingleTypeContext.class,0);
		}
		public NewArrayExpContext newArrayExp() {
			return getRuleContext(NewArrayExpContext.class,0);
		}
		public NewArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewArrayContext newArray() throws RecognitionException {
		NewArrayContext _localctx = new NewArrayContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_newArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(New);
			setState(288);
			singleType();
			setState(289);
			newArrayExp();
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

	public static class IndexExprContext extends ParserRuleContext {
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
		public IndexExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIndexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIndexExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIndexExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexExprContext indexExpr() throws RecognitionException {
		IndexExprContext _localctx = new IndexExprContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_indexExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(295); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(291);
					match(LeftBracket);
					setState(292);
					expression(0);
					setState(293);
					match(RightBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(297); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class FuncExprContext extends ParserRuleContext {
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public FuncExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncExprContext funcExpr() throws RecognitionException {
		FuncExprContext _localctx = new FuncExprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_funcExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(LeftParenthes);
			setState(300);
			expressionList();
			setState(301);
			match(RightParenthes);
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

	public static class ConditionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			expression(0);
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

	public static class LeftSelfExprssionContext extends ParserRuleContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Plus() { return getToken(MxParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxParser.Minus, 0); }
		public TerminalNode SelfPlus() { return getToken(MxParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxParser.SelfMinus, 0); }
		public TerminalNode Not() { return getToken(MxParser.Not, 0); }
		public TerminalNode Tidle() { return getToken(MxParser.Tidle, 0); }
		public LeftSelfExprssionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftSelfExprssion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLeftSelfExprssion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLeftSelfExprssion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLeftSelfExprssion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftSelfExprssionContext leftSelfExprssion() throws RecognitionException {
		LeftSelfExprssionContext _localctx = new LeftSelfExprssionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_leftSelfExprssion);
		int _la;
		try {
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Plus:
			case Minus:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				((LeftSelfExprssionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
					((LeftSelfExprssionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(306);
				expression(0);
				}
				break;
			case SelfPlus:
			case SelfMinus:
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				((LeftSelfExprssionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SelfPlus || _la==SelfMinus) ) {
					((LeftSelfExprssionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(308);
				expression(0);
				}
				break;
			case Not:
				enterOuterAlt(_localctx, 3);
				{
				setState(309);
				((LeftSelfExprssionContext)_localctx).op = match(Not);
				setState(310);
				expression(0);
				}
				break;
			case Tidle:
				enterOuterAlt(_localctx, 4);
				{
				setState(311);
				((LeftSelfExprssionContext)_localctx).op = match(Tidle);
				setState(312);
				expression(0);
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

	public static class ExpressionContext extends ParserRuleContext {
		public Token op;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public LeftSelfExprssionContext leftSelfExprssion() {
			return getRuleContext(LeftSelfExprssionContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Dot() { return getToken(MxParser.Dot, 0); }
		public TerminalNode Times() { return getToken(MxParser.Times, 0); }
		public TerminalNode Divide() { return getToken(MxParser.Divide, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode Plus() { return getToken(MxParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxParser.Minus, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode GreaterThan() { return getToken(MxParser.GreaterThan, 0); }
		public TerminalNode LessThan() { return getToken(MxParser.LessThan, 0); }
		public TerminalNode LessEqual() { return getToken(MxParser.LessEqual, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxParser.GreaterEqual, 0); }
		public TerminalNode Equal() { return getToken(MxParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxParser.NotEqual, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode Xor() { return getToken(MxParser.Xor, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public TerminalNode AndAnd() { return getToken(MxParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(MxParser.OrOr, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public IndexExprContext indexExpr() {
			return getRuleContext(IndexExprContext.class,0);
		}
		public FuncExprContext funcExpr() {
			return getRuleContext(FuncExprContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxParser.SelfMinus, 0); }
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
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case This:
			case True:
			case False:
			case New:
			case LeftParenthes:
			case Identifier:
			case StringConst:
			case Integer:
			case Numeric:
				{
				setState(316);
				primary();
				}
				break;
			case LeftBracket:
				{
				setState(317);
				lambdaExpression();
				}
				break;
			case SelfPlus:
			case SelfMinus:
			case Plus:
			case Minus:
			case Tidle:
			case Not:
				{
				setState(318);
				leftSelfExprssion();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(365);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(363);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(321);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(322);
						((ExpressionContext)_localctx).op = match(Dot);
						setState(323);
						expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(324);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(325);
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
						setState(326);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(327);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(328);
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
						setState(329);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(330);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(331);
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
						setState(332);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(333);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(334);
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
						setState(335);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(336);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(337);
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
						setState(338);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(339);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(340);
						((ExpressionContext)_localctx).op = match(And);
						setState(341);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(342);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(343);
						((ExpressionContext)_localctx).op = match(Xor);
						setState(344);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(345);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(346);
						((ExpressionContext)_localctx).op = match(Or);
						setState(347);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(348);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(349);
						((ExpressionContext)_localctx).op = match(AndAnd);
						setState(350);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(351);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(352);
						((ExpressionContext)_localctx).op = match(OrOr);
						setState(353);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(354);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(355);
						((ExpressionContext)_localctx).op = match(Assign);
						setState(356);
						expression(1);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(357);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(358);
						indexExpr();
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(359);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(360);
						funcExpr();
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(361);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(362);
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
				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
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

	public static class ParenthesContext extends ParserRuleContext {
		public TerminalNode LeftParenthes() { return getToken(MxParser.LeftParenthes, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthes() { return getToken(MxParser.RightParenthes, 0); }
		public ParenthesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParenthes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParenthes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParenthes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesContext parenthes() throws RecognitionException {
		ParenthesContext _localctx = new ParenthesContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_parenthes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(LeftParenthes);
			setState(369);
			expression(0);
			setState(370);
			match(RightParenthes);
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

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_label);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			_la = _input.LA(1);
			if ( !(_la==This || _la==Identifier) ) {
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

	public static class PrimaryContext extends ParserRuleContext {
		public ParenthesContext parenthes() {
			return getRuleContext(ParenthesContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public NewArrayContext newArray() {
			return getRuleContext(NewArrayContext.class,0);
		}
		public NewSingleContext newSingle() {
			return getRuleContext(NewSingleContext.class,0);
		}
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
		enterRule(_localctx, 68, RULE_primary);
		try {
			setState(379);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				parenthes();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				label();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
				newArray();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(377);
				newSingle();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(378);
				literal();
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

	public static class LiteralContext extends ParserRuleContext {
		public Token literalType;
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
		enterRule(_localctx, 70, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			((LiteralContext)_localctx).literalType = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << StringConst) | (1L << Integer) | (1L << Numeric))) != 0)) ) {
				((LiteralContext)_localctx).literalType = (Token)_errHandler.recoverInline(this);
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
		enterRule(_localctx, 72, RULE_returnType);
		try {
			setState(385);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Void:
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				match(Void);
				}
				break;
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
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

	public static class SingleTypeContext extends ParserRuleContext {
		public BuiltinTypeContext builtinType() {
			return getRuleContext(BuiltinTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public SingleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSingleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSingleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSingleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleTypeContext singleType() throws RecognitionException {
		SingleTypeContext _localctx = new SingleTypeContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_singleType);
		try {
			setState(389);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				builtinType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
				match(Identifier);
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
		public SingleTypeContext singleType() {
			return getRuleContext(SingleTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxParser.RightBracket, i);
		}
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
		enterRule(_localctx, 76, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			singleType();
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LeftBracket) {
				{
				{
				setState(392);
				match(LeftBracket);
				setState(393);
				match(RightBracket);
				}
				}
				setState(398);
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
		enterRule(_localctx, 78, RULE_builtinType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
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
		case 31:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
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
			return precpred(_ctx, 16);
		case 13:
			return precpred(_ctx, 15);
		case 14:
			return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3E\u0194\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\7\2T\n\2\f"+
		"\2\16\2W\13\2\3\2\5\2Z\n\2\3\3\3\3\3\3\7\3_\n\3\f\3\16\3b\13\3\3\3\3\3"+
		"\7\3f\n\3\f\3\16\3i\13\3\5\3k\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u0082\n\b\f\b\16"+
		"\b\u0085\13\b\5\b\u0087\n\b\3\t\3\t\7\t\u008b\n\t\f\t\16\t\u008e\13\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13\u009a\n\13\f\13\16\13"+
		"\u009d\13\13\3\f\3\f\3\f\5\f\u00a2\n\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00aa"+
		"\n\r\f\r\16\r\u00ad\13\r\5\r\u00af\n\r\3\16\3\16\3\16\7\16\u00b4\n\16"+
		"\f\16\16\16\u00b7\13\16\5\16\u00b9\n\16\3\17\3\17\7\17\u00bd\n\17\f\17"+
		"\16\17\u00c0\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\22\3\22\5\22\u00cf\n\22\3\23\3\23\3\23\3\23\5\23\u00d5\n\23\3\23"+
		"\3\23\5\23\u00d9\n\23\3\23\3\23\5\23\u00dd\n\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\5\25\u00ea\n\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\5\30\u0101\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\5\32\u0110\n\32\3\33\3\33\5\33\u0114\n\33\3"+
		"\33\6\33\u0117\n\33\r\33\16\33\u0118\3\33\3\33\7\33\u011d\n\33\f\33\16"+
		"\33\u0120\13\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\6\35\u012a\n\35"+
		"\r\35\16\35\u012b\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3"+
		" \5 \u013c\n \3!\3!\3!\3!\5!\u0142\n!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3!\7!\u016e\n!\f!\16!\u0171\13!\3\"\3\"\3\"\3\""+
		"\3#\3#\3$\3$\3$\3$\3$\5$\u017e\n$\3%\3%\3&\3&\5&\u0184\n&\3\'\3\'\5\'"+
		"\u0188\n\'\3(\3(\3(\7(\u018d\n(\f(\16(\u0190\13(\3)\3)\3)\2\3@*\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNP\2"+
		"\f\3\2\20\21\3\2$%\3\2\"#\3\2&(\3\2)*\3\2\33\36\3\2\37 \4\2\4\4::\5\2"+
		"\3\3\5\6;=\3\2\n\f\2\u01aa\2Y\3\2\2\2\4j\3\2\2\2\6l\3\2\2\2\bs\3\2\2\2"+
		"\ny\3\2\2\2\f{\3\2\2\2\16\u0086\3\2\2\2\20\u0088\3\2\2\2\22\u0091\3\2"+
		"\2\2\24\u0095\3\2\2\2\26\u009e\3\2\2\2\30\u00ae\3\2\2\2\32\u00b8\3\2\2"+
		"\2\34\u00ba\3\2\2\2\36\u00c3\3\2\2\2 \u00c9\3\2\2\2\"\u00cc\3\2\2\2$\u00d0"+
		"\3\2\2\2&\u00e1\3\2\2\2(\u00e7\3\2\2\2*\u00eb\3\2\2\2,\u00ed\3\2\2\2."+
		"\u0100\3\2\2\2\60\u0102\3\2\2\2\62\u010b\3\2\2\2\64\u0116\3\2\2\2\66\u0121"+
		"\3\2\2\28\u0129\3\2\2\2:\u012d\3\2\2\2<\u0131\3\2\2\2>\u013b\3\2\2\2@"+
		"\u0141\3\2\2\2B\u0172\3\2\2\2D\u0176\3\2\2\2F\u017d\3\2\2\2H\u017f\3\2"+
		"\2\2J\u0183\3\2\2\2L\u0187\3\2\2\2N\u0189\3\2\2\2P\u0191\3\2\2\2RT\5\4"+
		"\3\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VZ\3\2\2\2WU\3\2\2\2XZ\7\2"+
		"\2\3YU\3\2\2\2YX\3\2\2\2Z\3\3\2\2\2[k\5\6\4\2\\`\5\24\13\2]_\7\65\2\2"+
		"^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2ak\3\2\2\2b`\3\2\2\2cg\5\22\n"+
		"\2df\7\65\2\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hk\3\2\2\2ig\3\2"+
		"\2\2j[\3\2\2\2j\\\3\2\2\2jc\3\2\2\2k\5\3\2\2\2lm\5J&\2mn\7:\2\2no\7\25"+
		"\2\2op\5\30\r\2pq\7\26\2\2qr\5\34\17\2r\7\3\2\2\2st\7:\2\2tu\7\25\2\2"+
		"uv\5\30\r\2vw\7\26\2\2wx\5\34\17\2x\t\3\2\2\2yz\5\6\4\2z\13\3\2\2\2{|"+
		"\5\24\13\2|\r\3\2\2\2}\u0087\5\b\5\2~\u0087\5\n\6\2\177\u0083\5\f\7\2"+
		"\u0080\u0082\7\65\2\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0086"+
		"}\3\2\2\2\u0086~\3\2\2\2\u0086\177\3\2\2\2\u0087\17\3\2\2\2\u0088\u008c"+
		"\7\31\2\2\u0089\u008b\5\16\b\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2\2\2"+
		"\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008f\u0090\7\32\2\2\u0090\21\3\2\2\2\u0091\u0092\7\7\2\2\u0092"+
		"\u0093\7:\2\2\u0093\u0094\5\20\t\2\u0094\23\3\2\2\2\u0095\u0096\5N(\2"+
		"\u0096\u009b\5\26\f\2\u0097\u0098\7\64\2\2\u0098\u009a\5\26\f\2\u0099"+
		"\u0097\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\25\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u00a1\7:\2\2\u009f\u00a0"+
		"\7!\2\2\u00a0\u00a2\5@!\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\27\3\2\2\2\u00a3\u00a4\5N(\2\u00a4\u00ab\7:\2\2\u00a5\u00a6\7\64\2\2"+
		"\u00a6\u00a7\5N(\2\u00a7\u00a8\7:\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a5"+
		"\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00a3\3\2\2\2\u00ae\u00af\3\2"+
		"\2\2\u00af\31\3\2\2\2\u00b0\u00b5\5@!\2\u00b1\u00b2\7\64\2\2\u00b2\u00b4"+
		"\5@!\2\u00b3\u00b1\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b0\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\33\3\2\2\2\u00ba\u00be\7\31\2\2\u00bb\u00bd"+
		"\5.\30\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7\32"+
		"\2\2\u00c2\35\3\2\2\2\u00c3\u00c4\7\b\2\2\u00c4\u00c5\7\25\2\2\u00c5\u00c6"+
		"\5<\37\2\u00c6\u00c7\7\26\2\2\u00c7\u00c8\5.\30\2\u00c8\37\3\2\2\2\u00c9"+
		"\u00ca\7\t\2\2\u00ca\u00cb\5.\30\2\u00cb!\3\2\2\2\u00cc\u00ce\5\36\20"+
		"\2\u00cd\u00cf\5 \21\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf#"+
		"\3\2\2\2\u00d0\u00d1\7\17\2\2\u00d1\u00d4\7\25\2\2\u00d2\u00d5\5,\27\2"+
		"\u00d3\u00d5\5\24\13\2\u00d4\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5"+
		"\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\7\65\2\2\u00d7\u00d9\5<\37\2"+
		"\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc"+
		"\7\65\2\2\u00db\u00dd\5,\27\2\u00dc\u00db\3\2\2\2\u00dc\u00dd\3\2\2\2"+
		"\u00dd\u00de\3\2\2\2\u00de\u00df\7\26\2\2\u00df\u00e0\5.\30\2\u00e0%\3"+
		"\2\2\2\u00e1\u00e2\7\16\2\2\u00e2\u00e3\7\25\2\2\u00e3\u00e4\5<\37\2\u00e4"+
		"\u00e5\7\26\2\2\u00e5\u00e6\5.\30\2\u00e6\'\3\2\2\2\u00e7\u00e9\7\22\2"+
		"\2\u00e8\u00ea\5@!\2\u00e9\u00e8\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea)\3"+
		"\2\2\2\u00eb\u00ec\t\2\2\2\u00ec+\3\2\2\2\u00ed\u00ee\5@!\2\u00ee-\3\2"+
		"\2\2\u00ef\u0101\5\34\17\2\u00f0\u00f1\5\24\13\2\u00f1\u00f2\7\65\2\2"+
		"\u00f2\u0101\3\2\2\2\u00f3\u0101\5\"\22\2\u00f4\u0101\5$\23\2\u00f5\u0101"+
		"\5&\24\2\u00f6\u00f7\5(\25\2\u00f7\u00f8\7\65\2\2\u00f8\u0101\3\2\2\2"+
		"\u00f9\u00fa\5,\27\2\u00fa\u00fb\7\65\2\2\u00fb\u0101\3\2\2\2\u00fc\u00fd"+
		"\5*\26\2\u00fd\u00fe\7\65\2\2\u00fe\u0101\3\2\2\2\u00ff\u0101\7\65\2\2"+
		"\u0100\u00ef\3\2\2\2\u0100\u00f0\3\2\2\2\u0100\u00f3\3\2\2\2\u0100\u00f4"+
		"\3\2\2\2\u0100\u00f5\3\2\2\2\u0100\u00f6\3\2\2\2\u0100\u00f9\3\2\2\2\u0100"+
		"\u00fc\3\2\2\2\u0100\u00ff\3\2\2\2\u0101/\3\2\2\2\u0102\u0103\7\27\2\2"+
		"\u0103\u0104\7.\2\2\u0104\u0105\7\30\2\2\u0105\u0106\7\25\2\2\u0106\u0107"+
		"\5\30\r\2\u0107\u0108\7\26\2\2\u0108\u0109\7\67\2\2\u0109\u010a\5\34\17"+
		"\2\u010a\61\3\2\2\2\u010b\u010c\7\23\2\2\u010c\u010f\5L\'\2\u010d\u010e"+
		"\7\25\2\2\u010e\u0110\7\26\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2"+
		"\u0110\63\3\2\2\2\u0111\u0113\7\27\2\2\u0112\u0114\5@!\2\u0113\u0112\3"+
		"\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0117\7\30\2\2\u0116"+
		"\u0111\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2"+
		"\2\2\u0119\u011e\3\2\2\2\u011a\u011b\7\27\2\2\u011b\u011d\7\30\2\2\u011c"+
		"\u011a\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2"+
		"\2\2\u011f\65\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0122\7\23\2\2\u0122\u0123"+
		"\5L\'\2\u0123\u0124\5\64\33\2\u0124\67\3\2\2\2\u0125\u0126\7\27\2\2\u0126"+
		"\u0127\5@!\2\u0127\u0128\7\30\2\2\u0128\u012a\3\2\2\2\u0129\u0125\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"9\3\2\2\2\u012d\u012e\7\25\2\2\u012e\u012f\5\32\16\2\u012f\u0130\7\26"+
		"\2\2\u0130;\3\2\2\2\u0131\u0132\5@!\2\u0132=\3\2\2\2\u0133\u0134\t\3\2"+
		"\2\u0134\u013c\5@!\2\u0135\u0136\t\4\2\2\u0136\u013c\5@!\2\u0137\u0138"+
		"\7\61\2\2\u0138\u013c\5@!\2\u0139\u013a\7/\2\2\u013a\u013c\5@!\2\u013b"+
		"\u0133\3\2\2\2\u013b\u0135\3\2\2\2\u013b\u0137\3\2\2\2\u013b\u0139\3\2"+
		"\2\2\u013c?\3\2\2\2\u013d\u013e\b!\1\2\u013e\u0142\5F$\2\u013f\u0142\5"+
		"\60\31\2\u0140\u0142\5> \2\u0141\u013d\3\2\2\2\u0141\u013f\3\2\2\2\u0141"+
		"\u0140\3\2\2\2\u0142\u016f\3\2\2\2\u0143\u0144\f\20\2\2\u0144\u0145\7"+
		"\24\2\2\u0145\u016e\5@!\21\u0146\u0147\f\r\2\2\u0147\u0148\t\5\2\2\u0148"+
		"\u016e\5@!\16\u0149\u014a\f\f\2\2\u014a\u014b\t\3\2\2\u014b\u016e\5@!"+
		"\r\u014c\u014d\f\13\2\2\u014d\u014e\t\6\2\2\u014e\u016e\5@!\f\u014f\u0150"+
		"\f\n\2\2\u0150\u0151\t\7\2\2\u0151\u016e\5@!\13\u0152\u0153\f\t\2\2\u0153"+
		"\u0154\t\b\2\2\u0154\u016e\5@!\n\u0155\u0156\f\b\2\2\u0156\u0157\7.\2"+
		"\2\u0157\u016e\5@!\t\u0158\u0159\f\7\2\2\u0159\u015a\7\60\2\2\u015a\u016e"+
		"\5@!\b\u015b\u015c\f\6\2\2\u015c\u015d\7-\2\2\u015d\u016e\5@!\7\u015e"+
		"\u015f\f\5\2\2\u015f\u0160\7+\2\2\u0160\u016e\5@!\6\u0161\u0162\f\4\2"+
		"\2\u0162\u0163\7,\2\2\u0163\u016e\5@!\5\u0164\u0165\f\3\2\2\u0165\u0166"+
		"\7!\2\2\u0166\u016e\5@!\3\u0167\u0168\f\22\2\2\u0168\u016e\58\35\2\u0169"+
		"\u016a\f\21\2\2\u016a\u016e\5:\36\2\u016b\u016c\f\16\2\2\u016c\u016e\t"+
		"\4\2\2\u016d\u0143\3\2\2\2\u016d\u0146\3\2\2\2\u016d\u0149\3\2\2\2\u016d"+
		"\u014c\3\2\2\2\u016d\u014f\3\2\2\2\u016d\u0152\3\2\2\2\u016d\u0155\3\2"+
		"\2\2\u016d\u0158\3\2\2\2\u016d\u015b\3\2\2\2\u016d\u015e\3\2\2\2\u016d"+
		"\u0161\3\2\2\2\u016d\u0164\3\2\2\2\u016d\u0167\3\2\2\2\u016d\u0169\3\2"+
		"\2\2\u016d\u016b\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170A\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173\7\25\2\2"+
		"\u0173\u0174\5@!\2\u0174\u0175\7\26\2\2\u0175C\3\2\2\2\u0176\u0177\t\t"+
		"\2\2\u0177E\3\2\2\2\u0178\u017e\5B\"\2\u0179\u017e\5D#\2\u017a\u017e\5"+
		"\66\34\2\u017b\u017e\5\62\32\2\u017c\u017e\5H%\2\u017d\u0178\3\2\2\2\u017d"+
		"\u0179\3\2\2\2\u017d\u017a\3\2\2\2\u017d\u017b\3\2\2\2\u017d\u017c\3\2"+
		"\2\2\u017eG\3\2\2\2\u017f\u0180\t\n\2\2\u0180I\3\2\2\2\u0181\u0184\7\r"+
		"\2\2\u0182\u0184\5N(\2\u0183\u0181\3\2\2\2\u0183\u0182\3\2\2\2\u0184K"+
		"\3\2\2\2\u0185\u0188\5P)\2\u0186\u0188\7:\2\2\u0187\u0185\3\2\2\2\u0187"+
		"\u0186\3\2\2\2\u0188M\3\2\2\2\u0189\u018e\5L\'\2\u018a\u018b\7\27\2\2"+
		"\u018b\u018d\7\30\2\2\u018c\u018a\3\2\2\2\u018d\u0190\3\2\2\2\u018e\u018c"+
		"\3\2\2\2\u018e\u018f\3\2\2\2\u018fO\3\2\2\2\u0190\u018e\3\2\2\2\u0191"+
		"\u0192\t\13\2\2\u0192Q\3\2\2\2$UY`gj\u0083\u0086\u008c\u009b\u00a1\u00ab"+
		"\u00ae\u00b5\u00b8\u00be\u00ce\u00d4\u00d8\u00dc\u00e9\u0100\u010f\u0113"+
		"\u0118\u011e\u012b\u013b\u0141\u016d\u016f\u017d\u0183\u0187\u018e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}