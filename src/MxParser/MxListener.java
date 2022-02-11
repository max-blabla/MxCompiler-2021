// Generated from D:/JavaCoding/MxComplier/ParserG4\Mx.g4 by ANTLR 4.9.2
package MxParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(MxParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(MxParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classSpace}.
	 * @param ctx the parse tree
	 */
	void enterClassSpace(MxParser.ClassSpaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classSpace}.
	 * @param ctx the parse tree
	 */
	void exitClassSpace(MxParser.ClassSpaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MxParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MxParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MxParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MxParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(MxParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(MxParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MxParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MxParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MxParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MxParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MxParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MxParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(MxParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(MxParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varStatement}.
	 * @param ctx the parse tree
	 */
	void enterVarStatement(MxParser.VarStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varStatement}.
	 * @param ctx the parse tree
	 */
	void exitVarStatement(MxParser.VarStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void enterExprStatement(MxParser.ExprStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void exitExprStatement(MxParser.ExprStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(MxParser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(MxParser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newErrorArray}.
	 * @param ctx the parse tree
	 */
	void enterNewErrorArray(MxParser.NewErrorArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newErrorArray}.
	 * @param ctx the parse tree
	 */
	void exitNewErrorArray(MxParser.NewErrorArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newType}.
	 * @param ctx the parse tree
	 */
	void enterNewType(MxParser.NewTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newType}.
	 * @param ctx the parse tree
	 */
	void exitNewType(MxParser.NewTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(MxParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(MxParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#indexExpr}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(MxParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#indexExpr}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(MxParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parenthes}.
	 * @param ctx the parse tree
	 */
	void enterParenthes(MxParser.ParenthesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parenthes}.
	 * @param ctx the parse tree
	 */
	void exitParenthes(MxParser.ParenthesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(MxParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(MxParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#singleType}.
	 * @param ctx the parse tree
	 */
	void enterSingleType(MxParser.SingleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#singleType}.
	 * @param ctx the parse tree
	 */
	void exitSingleType(MxParser.SingleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(MxParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(MxParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#builtinType}.
	 * @param ctx the parse tree
	 */
	void enterBuiltinType(MxParser.BuiltinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#builtinType}.
	 * @param ctx the parse tree
	 */
	void exitBuiltinType(MxParser.BuiltinTypeContext ctx);
}