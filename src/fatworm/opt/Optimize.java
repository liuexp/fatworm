package fatworm.opt;

import java.util.LinkedList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.logicplan.*;
import fatworm.util.Util;

public class Optimize {

	public static Plan optimize(Plan plan){
		plan = pushSelect(plan);
		// after the push we transform into theta-join and merge-join
		// note that by now there shouldn't have been any ThetaJoin plan
		transformTheta(plan);
		// next push all single orders inwards
		return plan;
	}

	private static Plan pushSelect(Plan plan) {
		plan = decomposeAnd(plan);
		// TODO figure out how to push select through Rename(renaming expressions)
		// be more careful about requested & provided columns
		return plan;
	}

	private static void transformTheta(Plan plan) {
		if(plan instanceof Distinct){
			Distinct p = (Distinct)plan;
			transformTheta(p.src);
		}else if(plan instanceof FetchTable){
		}else if(plan instanceof Group){
			Group p = (Group)plan;
			transformTheta(p.src);
		}else if(plan instanceof Join){
			Join p = (Join)plan;
			transformTheta(p.left);
			transformTheta(p.right);
			Plan parent = p.parent;
			Plan cur = p;
			if(!(parent instanceof Select))
				return;
			ThetaJoin ret = new ThetaJoin(p);
			while(parent instanceof Select){
				ret.add(((Select)parent).pred);
				cur = parent;
				parent = parent.parent;
			}
			if(parent!=null){
				parent.setSrc(cur, ret);
			}
			ret.parent = parent;
		}else if(plan instanceof None){
		}else if(plan instanceof One){
		}else if(plan instanceof Order){
			Order p = (Order)plan;
			transformTheta(p.src);
		}else if(plan instanceof Project){
			Project p = (Project)plan;
			transformTheta(p.src);
		}else if(plan instanceof Rename){
			Rename p = (Rename)plan;
			transformTheta(p.src);
		}else if(plan instanceof RenameTable){
			RenameTable p = (RenameTable)plan;
			transformTheta(p.src);
		}else if(plan instanceof Select){
			Select p = (Select)plan;
			transformTheta(p.src);
		} else if(plan instanceof ThetaJoin){
			ThetaJoin p = (ThetaJoin)plan;
			transformTheta(p.left);
			transformTheta(p.right);
		} else {
			Util.warn("Optimize:meow!!!");
		}
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
			Util.warn("Optimize:meow!!!");
		}
		return null;
	}
}
