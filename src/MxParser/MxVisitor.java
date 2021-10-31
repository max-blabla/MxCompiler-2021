// Generated from D:/JavaCoding/Compiler/ParserG4\Mx.g4 by ANTLR 4.9.1
package MxParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(MxParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#conFuncDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConFuncDef(MxParser.ConFuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#memberFuncDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberFuncDef(MxParser.MemberFuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#memberVarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberVarDef(MxParser.MemberVarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#memberDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDefinition(MxParser.MemberDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classSpace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassSpace(MxParser.ClassSpaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MxParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(MxParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MxParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(MxParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#ifElseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseStatement(MxParser.IfElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MxParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MxParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MxParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(MxParser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpStatement(MxParser.ExpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MxParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpression(MxParser.LambdaExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#newSingle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewSingle(MxParser.NewSingleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#newArrayExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArrayExp(MxParser.NewArrayExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#newArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArray(MxParser.NewArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#indexExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexExpr(MxParser.IndexExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncExpr(MxParser.FuncExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(MxParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#leftSelfExprssion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftSelfExprssion(MxParser.LeftSelfExprssionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parenthes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthes(MxParser.ParenthesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(MxParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#singleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleType(MxParser.SingleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#builtinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltinType(MxParser.BuiltinTypeContext ctx);
}