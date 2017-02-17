package test;
// Generated from test.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link testParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface testVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Init}
	 * labeled alternative in {@link testParser#equationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(@NotNull testParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Times}
	 * labeled alternative in {@link testParser#equationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimes(@NotNull testParser.TimesContext ctx);
	/**
	 * Visit a parse tree produced by {@link testParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull testParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link testParser#equationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(@NotNull testParser.PlusContext ctx);
}