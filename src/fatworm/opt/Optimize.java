package fatworm.opt;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fatworm.absyn.BinaryExpr;
import fatworm.absyn.BinaryOp;
import fatworm.absyn.Expr;
import fatworm.absyn.Id;
import fatworm.absyn.QueryCall;
import fatworm.absyn.InCall;
import fatworm.logicplan.*;
import fatworm.util.Util;

public class Optimize {

	public static Set<String> orderOnField = new HashSet<String>();
	public static Plan optimize(Plan plan){
		Util.warn("Before opt:"+plan.toString());
		cleanupSelect(plan);
		Util.warn("After cleanup:"+plan.toString());
		plan = decomposeAnd(plan);
//		Util.warn("Before push:"+plan.toString());
		plan = pushSelect(plan);
//		Util.warn("After push:"+plan.toString());
		// after the push we transform into theta-join and merge-join
		// note that by now there shouldn't have been any ThetaJoin plan
		transformTheta(plan);
		orderOnField = new HashSet<String>();
		plan = clearInnerOrders(plan);
		// TODO next push all single orders inwards to fetchTable
//		Util.warn("After opt:"+plan.toString());
		return plan;
	}

	private static void cleanupSelect(Plan plan) {
		if(plan instanceof Distinct){
			Distinct p = (Distinct)plan;
			cleanupSelect(p.src);
		}else if(plan instanceof FetchTable){
		}else if(plan instanceof Group){
			Group p = (Group)plan;
			cleanupSelect(p.src);
		}else if(plan instanceof Join){
			Join p = (Join)plan;
			cleanupSelect(p.left);
			cleanupSelect(p.right);
		}else if(plan instanceof None){
		}else if(plan instanceof One){
		}else if(plan instanceof Order){
			Order p = (Order)plan;
			cleanupSelect(p.src);
		}else if(plan instanceof Project){
			Project p = (Project)plan;
			List<Expr> expr = p.expr;
			for(int i=0;i<expr.size();i++){
				Expr e = expr.get(i);
				if(e instanceof QueryCall && ((QueryCall)e).src instanceof Project){
					Project tmp = (Project) ((QueryCall)e).src;
					if(tmp.src instanceof One){
						expr.set(i, tmp.expr.get(0));
					}
				}
			}
			cleanupSelect(p.src);
		}else if(plan instanceof Rename){
			Rename p = (Rename)plan;
			cleanupSelect(p.src);
		}else if(plan instanceof RenameTable){
			RenameTable p = (RenameTable)plan;
			cleanupSelect(p.src);
		}else if(plan instanceof Select){
			Select p = (Select)plan;
			cleanupSelect(p.src);
			if(p.pred instanceof InCall){
				InCall tmp1 = (InCall) p.pred;
				if(tmp1.src instanceof Project){
					Project tmp2 = (Project) tmp1.src;
					if(tmp2.src instanceof FetchTable && 
							tmp2.expr.get(0) instanceof Id && 
							(!Util.getAttr(tmp2.expr.get(0).toString()).equalsIgnoreCase(Util.getAttr(tmp1.expr.toString()))||
									tmp1.expr.toString().contains("."))){
						
						String tbl = Util.tablePrefix+tmp2.getSchema().tableName;
						p.src = new Join(p.src, new RenameTable(tmp2, tbl));
						p.src.parent = p;
						String col0 = tbl + "." + Util.getAttr(tmp2.expr.get(0).toString());
						p.pred = new BinaryExpr(tmp1.expr, BinaryOp.EQ, new Id(col0));
					}
				}
			}
		} else if(plan instanceof ThetaJoin){
			ThetaJoin p = (ThetaJoin)plan;
			cleanupSelect(p.left);
			cleanupSelect(p.right);
		} else {
			Util.warn("cleanupSelect:meow!!!");
		}
	}

	private static Plan clearInnerOrders(Plan plan) {
		if(plan instanceof Distinct){
			Distinct p = (Distinct)plan;
			p.src = clearInnerOrders(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof FetchTable){
			return plan;
		}else if(plan instanceof Group){
			Group p = (Group)plan;
			p.src = clearInnerOrders(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Join){
			Join p = (Join)plan;
			p.left = clearInnerOrders(p.left);
			p.right = clearInnerOrders(p.right);
			p.left.parent = p;
			p.right.parent = p;
			return p;
		}else if(plan instanceof None){
			return plan;
		}else if(plan instanceof One){
			return plan;
		}else if(plan instanceof Order){
			Order p = (Order)plan;
			boolean dup = !orderOnField.isEmpty();
			for(String x:p.orderField){
				//FIXME should check here and do rename on Rename & RenameTable instead 
				if(!orderOnField.contains(Util.getAttr(x).toLowerCase())){
//					dup = false;
//					Util.warn("Non-Duplicate Order detected and staying."+Util.deepToString(orderOnField)+", "+Util.getAttr(x).toLowerCase());
					orderOnField.add(Util.getAttr(x).toLowerCase());
				}
			}
			if(dup)
				Util.warn("Duplicate Order detected and eliminating.");
			p.src = clearInnerOrders(p.src);
			p.src.parent = dup?p.parent:p;
			return dup?p.src:p;
		}else if(plan instanceof Project){
			Project p = (Project)plan;
			p.src = clearInnerOrders(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Rename){
			Rename p = (Rename)plan;
			p.src = clearInnerOrders(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof RenameTable){
			RenameTable p = (RenameTable)plan;
			p.src = clearInnerOrders(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Select){
			Select p = (Select)plan;
			p.src = clearInnerOrders(p.src);
			p.src.parent = p;
			return p;
		} else if(plan instanceof ThetaJoin){
			ThetaJoin p = (ThetaJoin)plan;
			p.left = clearInnerOrders(p.left);
			p.right = clearInnerOrders(p.right);
			p.left.parent = p;
			p.right.parent = p;
			return p;
		} else {
			Util.warn("ClearInnerOrders:meow!!!");
		}
		return null;
	}

	private static Plan pushSelect(Plan plan) {
		if(plan instanceof Distinct){
			Distinct p = (Distinct)plan;
			p.src = pushSelect(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof FetchTable){
			return plan;
		}else if(plan instanceof Group){
			Group p = (Group)plan;
			p.src = pushSelect(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Join){
			Join p = (Join)plan;
			p.left = pushSelect(p.left);
			p.right = pushSelect(p.right);
			p.left.parent = p;
			p.right.parent = p;
			return p;
		}else if(plan instanceof None){
			return plan;
		}else if(plan instanceof One){
			return plan;
		}else if(plan instanceof Order){
			Order p = (Order)plan;
			p.src = pushSelect(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Project){
			Project p = (Project)plan;
			p.src = pushSelect(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Rename){
			Rename p = (Rename)plan;
			p.src = pushSelect(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof RenameTable){
			RenameTable p = (RenameTable)plan;
			p.src = pushSelect(p.src);
			p.src.parent = p;
			return p;
		}else if(plan instanceof Select){
			Select p = (Select)plan;
			if(p.hasPushed){
				p.src = pushSelect(p.src);
				p.src.parent = p;
				return p;
			}
			Plan head = p;
			Plan child = p.src;
			Select cur = p;
			
			while(cur.isPushable()){
//				Util.warn("Pushing Select down!!!"+cur.toString()+", hasPushed="+cur.hasPushed);
				if(cur.src instanceof RenameTable){
					RenameTable curChild = (RenameTable) cur.src;
					String tbl = curChild.alias;
					for(String newName : curChild.src.getColumns()){
						String oldName = tbl + "." + Util.getAttr(newName);
						cur.pred.rename(oldName, newName);
					}
				}else if(cur.src instanceof Rename){
					Rename curChild = (Rename) cur.src;
					for(int i=0;i<curChild.as.size();i++){
						String oldName = curChild.as.get(i);
						String newName = curChild.src.getColumns().get(i);
						cur.pred.rename(oldName, newName);
					}
				}
				push(cur);
				if(head == p)
					head = child;
			}
			cur.hasPushed=true;
			head = pushSelect(head);
			return head;
		} else if(plan instanceof ThetaJoin){
			ThetaJoin p = (ThetaJoin)plan;
			p.left = pushSelect(p.left);
			p.right = pushSelect(p.right);
			p.left.parent = p;
			p.right.parent = p;
			return p;
		} else {
			Util.warn("Optimize:pushSelect:meow!!!");
		}
		return plan;
	}

	private static void push(Select cur) {
		Plan parent = cur.parent;
		Plan child = cur.src;
		if(parent!=null)
			parent.setSrc(cur, child);
		else{
			child.parent=null;
		}
		if(child instanceof Distinct){
			Distinct p = (Distinct)child;
			cur.setSrc(child, p.src);
			child.setSrc(p.src, cur);
		}else if(child instanceof Join){
			Join p = (Join)child;
			if(Util.subsetof(cur.pred.getRequestedColumns(), p.left.getColumns())){
				cur.setSrc(cur.src, p.left);
				child.setSrc(p.left, cur);
			}else if(Util.subsetof(cur.pred.getRequestedColumns(), p.right.getColumns())){
				cur.setSrc(cur.src, p.right);
				child.setSrc(p.right, cur);
			}else{
				Util.warn("select pushing meow@Join!");
			}
		}else if(child instanceof Order){
			Order p = (Order)child;
			cur.setSrc(child, p.src);
			child.setSrc(p.src, cur);
		}else if(child instanceof Project){
			Project p = (Project)child;
			cur.setSrc(child, p.src);
			child.setSrc(p.src, cur);
		}else if(child instanceof Rename){
			Rename p = (Rename)child;
			cur.setSrc(child, p.src);
			child.setSrc(p.src, cur);
		}else if(child instanceof RenameTable){
			RenameTable p = (RenameTable)child;
			cur.setSrc(child, p.src);
			child.setSrc(p.src, cur);
		}else if(child instanceof Select){
			Select p = (Select)child;
			cur.setSrc(child, p.src);
			child.setSrc(p.src, cur);
		}else{
			Util.warn("select pushing meow!");
		}
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
			Util.warn("transformTheta:meow!!!");
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
			// infer all equivalent equivalent relations to some closure(least fixed point).
//			Util.warn("collected "+Util.deepToString(exprList));
			boolean closed = false;
			while(!closed){
				closed = true;
				Set<Expr> toAdd = new HashSet<Expr>();
				for(int i=0;i<exprList.size();i++){
					Expr xxx = exprList.get(i);
					if(!(xxx instanceof BinaryExpr) || ((BinaryExpr)xxx).op!=BinaryOp.EQ)continue;
					BinaryExpr xx = (BinaryExpr) xxx;
					Id x1 = (xx.l instanceof Id)?(Id)xx.l:null;
					Id x2 = (xx.r instanceof Id)?(Id)xx.r:null;
					if(x1==null && x2==null)continue;
					for(int j=i+1;j<exprList.size();j++){
						Expr yyy = exprList.get(j);
						if(!(yyy instanceof BinaryExpr) || ((BinaryExpr)yyy).op!=BinaryOp.EQ)continue;
						BinaryExpr yy = (BinaryExpr) yyy;
						Id y1 = (yy.l instanceof Id)?(Id)yy.l:null;
						Id y2 = (yy.r instanceof Id)?(Id)yy.r:null;
						if(y1==null && y2==null)continue;
						if(x1!=null&&y1!=null&&x1.name.equalsIgnoreCase(y1.name)){
							toAdd.add(new BinaryExpr(x1, BinaryOp.EQ, yy.r));
							toAdd.add(new BinaryExpr(y1, BinaryOp.EQ, xx.r));
							toAdd.add(new BinaryExpr(yy.r, BinaryOp.EQ, xx.r));
						}else if(x2!=null&&y1!=null&&x2.name.equalsIgnoreCase(y1.name)){
							toAdd.add(new BinaryExpr(x2, BinaryOp.EQ, yy.r));
							toAdd.add(new BinaryExpr(y1, BinaryOp.EQ, xx.l));
							toAdd.add(new BinaryExpr(yy.r, BinaryOp.EQ, xx.l));
						}else if(x1!=null&&y2!=null&&x1.name.equalsIgnoreCase(y2.name)){
							toAdd.add(new BinaryExpr(x1, BinaryOp.EQ, yy.l));
							toAdd.add(new BinaryExpr(y2, BinaryOp.EQ, xx.r));
							toAdd.add(new BinaryExpr(yy.l, BinaryOp.EQ, xx.r));
						}else if(x2!=null&&y2!=null&&x2.name.equalsIgnoreCase(y2.name)){
							toAdd.add(new BinaryExpr(x2, BinaryOp.EQ, yy.l));
							toAdd.add(new BinaryExpr(y2, BinaryOp.EQ, xx.l));
							toAdd.add(new BinaryExpr(yy.r, BinaryOp.EQ, xx.l));
						}
					}
				}
				toAdd.addAll(exprList);
				closed = exprList.size()>=toAdd.size();
				if(!closed)
					Util.warn("Generating new expressions!!!from "+Util.deepToString(exprList)+" to "+ Util.deepToString(toAdd));
				exprList = new LinkedList<Expr>(toAdd);
			}
//			Util.warn("propogated to "+Util.deepToString(exprList));
			Plan ret = p.src;
			for (Expr e : exprList){
				ret = new Select(ret, e);
			}
			ret.parent = p.parent;
			return ret;
		} else if(plan instanceof ThetaJoin){
			ThetaJoin p = (ThetaJoin)plan;
			p.left = decomposeAnd(p.left);
			p.right = decomposeAnd(p.right);
			p.left.parent = p;
			p.right.parent = p;
			return p;
		} else {
			Util.warn("decomposeAnd:meow!!!");
		}
		return null;
	}
}
