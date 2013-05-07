package fatworm.opt;

import java.util.LinkedList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.logicplan.*;
import fatworm.util.Util;

public class Optimize {

	public static Plan optimize(Plan plan){
		plan = pushSelect(plan);
		return plan;
	}

	private static Plan pushSelect(Plan plan) {
		plan = decomposeAnd(plan);
		return null;
	}

	private static Plan decomposeAnd(Plan plan) {
		if(plan instanceof Distinct){
			Distinct p = (Distinct)plan;
			p.src = decomposeAnd(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof FetchTable){
			return plan;
		}else if(plan instanceof Group){
			Group p = (Group)plan;
			p.src = decomposeAnd(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Join){
			Join p = (Join)plan;
			p.left = decomposeAnd(p.left);
			p.right = decomposeAnd(p.right);
			p.left.parent = p;
			p.right.parent = p;
			return p;
		}else if(plan instanceof None){
			return plan;
		}else if(plan instanceof One){
			return plan;
		}else if(plan instanceof Order){
			Order p = (Order)plan;
			p.src = decomposeAnd(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Project){
			Project p = (Project)plan;
			p.src = decomposeAnd(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Rename){
			Rename p = (Rename)plan;
			p.src = decomposeAnd(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof RenameTable){
			RenameTable p = (RenameTable)plan;
			p.src = decomposeAnd(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Select){
			Select p = (Select)plan;
			p.src = decomposeAnd(p.src);
			p.src.parent = p;
			if(!p.pred.isAnd())
				return p;
			List<Expr> exprList = new LinkedList<Expr>();
			p.pred.collectCond(exprList);
			Plan ret = p.src;
			for (Expr e : exprList){
				ret = new Select(ret, e);
			}
			ret.parent = p.parent;
			return ret;
		} else {
			Util.error("Optimize:meow!!!");
		}
		return null;
	}
}
