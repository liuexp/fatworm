package fatworm.absyn;

public enum BinaryOp {

	MULTIPLY, DIVIDE, MODULO, PLUS, MINUS, LESS, LESS_EQ, GREATER, GREATER_EQ, EQ, NEQ, AND, OR;
	
	@Override
	  public String toString() {
	    switch(this) {
	      case MULTIPLY: return "*";
	      case DIVIDE: return "/";
	      case MODULO: return "%";
	      case PLUS: return "+";
	      case MINUS: return "-";
	      case LESS: return "<";
	      case LESS_EQ: return "<=";
	      case GREATER: return ">";
	      case GREATER_EQ: return ">=";
	      case EQ: return "=";
	      case NEQ: return "<>";
	      case AND: return "and";
	      case OR: return "or";
	      default: throw new IllegalArgumentException();
	    }
	  }
}
