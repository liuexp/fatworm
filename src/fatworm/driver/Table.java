package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import fatworm.absyn.Expr;

public class Table {
	public Schema schema;
	public List<Record> records = new ArrayList<Record>();
	public Table(CommonTree t){
		schema = new Schema(t);
	}
	
	public void delete(Expr e){
		for
	}
}
