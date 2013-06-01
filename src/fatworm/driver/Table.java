package fatworm.driver;

import java.io.Serializable;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;

public abstract class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5595620741840746862L;
	public Schema schema;
	public abstract int delete(Expr e) ;
	public abstract int update(List<String> colName, List<Expr> expr, Expr e);
	public abstract int insert(Tree child);
	public abstract int insert(CommonTree t, Tree child);
	public abstract void addRecord(Record r);
	public abstract Schema getSchema() ;

}
