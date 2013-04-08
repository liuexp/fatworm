// $ANTLR 3.5 fatworm/parser/Fatworm.g 2013-04-07 20:02:27
 package fatworm.parser; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class FatwormParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "ALL", "AND", "ANY", "AS", 
		"ASC", "AUTO_INCREMENT", "AVG", "B", "BOOLEAN", "BY", "C", "CHAR", "COUNT", 
		"CREATE", "CREATE_DATABASE", "CREATE_DEFINITION", "CREATE_INDEX", "CREATE_TABLE", 
		"CREATE_UNIQUE_INDEX", "D", "DATABASE", "DATETIME", "DECIMAL", "DEFAULT", 
		"DELETE", "DESC", "DIGIT", "DISTINCT", "DROP", "DROP_DATABASE", "DROP_INDEX", 
		"DROP_TABLE", "E", "EXISTS", "F", "FALSE", "FLOAT", "FLOAT_LITERAL", "FROM", 
		"G", "GROUP", "H", "HAVING", "I", "ID", "IN", "INDEX", "INSERT", "INSERT_COLUMNS", 
		"INSERT_SUBQUERY", "INSERT_VALUES", "INT", "INTEGER_LITERAL", "INTO", 
		"J", "K", "KEY", "L", "LETTER", "M", "MAX", "MIN", "N", "NOT", "NOT_EXISTS", 
		"NULL", "O", "ON", "OR", "ORDER", "P", "PRIMARY", "PRIMARY_KEY", "Q", 
		"R", "S", "SELECT", "SELECT_DISTINCT", "SET", "STRING_LITERAL", "SUM", 
		"T", "TABLE", "TIMESTAMP", "TRUE", "U", "UNIQUE", "UPDATE", "UPDATE_PAIR", 
		"USE", "USE_DATABASE", "V", "VALUES", "VARCHAR", "W", "WHERE", "WS", "X", 
		"Y", "Z", "'%'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", 
		"'<'", "'<='", "'<>'", "'='", "'>'", "'>='"
	};
	public static final int EOF=-1;
	public static final int T__105=105;
	public static final int T__106=106;
	public static final int T__107=107;
	public static final int T__108=108;
	public static final int T__109=109;
	public static final int T__110=110;
	public static final int T__111=111;
	public static final int T__112=112;
	public static final int T__113=113;
	public static final int T__114=114;
	public static final int T__115=115;
	public static final int T__116=116;
	public static final int T__117=117;
	public static final int T__118=118;
	public static final int T__119=119;
	public static final int A=4;
	public static final int ALL=5;
	public static final int AND=6;
	public static final int ANY=7;
	public static final int AS=8;
	public static final int ASC=9;
	public static final int AUTO_INCREMENT=10;
	public static final int AVG=11;
	public static final int B=12;
	public static final int BOOLEAN=13;
	public static final int BY=14;
	public static final int C=15;
	public static final int CHAR=16;
	public static final int COUNT=17;
	public static final int CREATE=18;
	public static final int CREATE_DATABASE=19;
	public static final int CREATE_DEFINITION=20;
	public static final int CREATE_INDEX=21;
	public static final int CREATE_TABLE=22;
	public static final int CREATE_UNIQUE_INDEX=23;
	public static final int D=24;
	public static final int DATABASE=25;
	public static final int DATETIME=26;
	public static final int DECIMAL=27;
	public static final int DEFAULT=28;
	public static final int DELETE=29;
	public static final int DESC=30;
	public static final int DIGIT=31;
	public static final int DISTINCT=32;
	public static final int DROP=33;
	public static final int DROP_DATABASE=34;
	public static final int DROP_INDEX=35;
	public static final int DROP_TABLE=36;
	public static final int E=37;
	public static final int EXISTS=38;
	public static final int F=39;
	public static final int FALSE=40;
	public static final int FLOAT=41;
	public static final int FLOAT_LITERAL=42;
	public static final int FROM=43;
	public static final int G=44;
	public static final int GROUP=45;
	public static final int H=46;
	public static final int HAVING=47;
	public static final int I=48;
	public static final int ID=49;
	public static final int IN=50;
	public static final int INDEX=51;
	public static final int INSERT=52;
	public static final int INSERT_COLUMNS=53;
	public static final int INSERT_SUBQUERY=54;
	public static final int INSERT_VALUES=55;
	public static final int INT=56;
	public static final int INTEGER_LITERAL=57;
	public static final int INTO=58;
	public static final int J=59;
	public static final int K=60;
	public static final int KEY=61;
	public static final int L=62;
	public static final int LETTER=63;
	public static final int M=64;
	public static final int MAX=65;
	public static final int MIN=66;
	public static final int N=67;
	public static final int NOT=68;
	public static final int NOT_EXISTS=69;
	public static final int NULL=70;
	public static final int O=71;
	public static final int ON=72;
	public static final int OR=73;
	public static final int ORDER=74;
	public static final int P=75;
	public static final int PRIMARY=76;
	public static final int PRIMARY_KEY=77;
	public static final int Q=78;
	public static final int R=79;
	public static final int S=80;
	public static final int SELECT=81;
	public static final int SELECT_DISTINCT=82;
	public static final int SET=83;
	public static final int STRING_LITERAL=84;
	public static final int SUM=85;
	public static final int T=86;
	public static final int TABLE=87;
	public static final int TIMESTAMP=88;
	public static final int TRUE=89;
	public static final int U=90;
	public static final int UNIQUE=91;
	public static final int UPDATE=92;
	public static final int UPDATE_PAIR=93;
	public static final int USE=94;
	public static final int USE_DATABASE=95;
	public static final int V=96;
	public static final int VALUES=97;
	public static final int VARCHAR=98;
	public static final int W=99;
	public static final int WHERE=100;
	public static final int WS=101;
	public static final int X=102;
	public static final int Y=103;
	public static final int Z=104;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public FatwormParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public FatwormParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		this.state.ruleMemo = new HashMap[131+1];


	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return FatwormParser.tokenNames; }
	@Override public String getGrammarFileName() { return "fatworm/parser/Fatworm.g"; }


	public static class statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// fatworm/parser/Fatworm.g:32:1: statement : ( database_statement | table_statement | insert_statement | delete_statement | update_statement | index_statement | select_statement );
	public final FatwormParser.statement_return statement() throws RecognitionException {
		FatwormParser.statement_return retval = new FatwormParser.statement_return();
		retval.start = input.LT(1);
		int statement_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope database_statement1 =null;
		ParserRuleReturnScope table_statement2 =null;
		ParserRuleReturnScope insert_statement3 =null;
		ParserRuleReturnScope delete_statement4 =null;
		ParserRuleReturnScope update_statement5 =null;
		ParserRuleReturnScope index_statement6 =null;
		ParserRuleReturnScope select_statement7 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

			// fatworm/parser/Fatworm.g:33:3: ( database_statement | table_statement | insert_statement | delete_statement | update_statement | index_statement | select_statement )
			int alt1=7;
			switch ( input.LA(1) ) {
			case CREATE:
			case DROP:
				{
				int LA1_1 = input.LA(2);
				if ( (synpred1_Fatworm()) ) {
					alt1=1;
				}
				else if ( (synpred2_Fatworm()) ) {
					alt1=2;
				}
				else if ( (synpred6_Fatworm()) ) {
					alt1=6;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case USE:
				{
				alt1=1;
				}
				break;
			case INSERT:
				{
				alt1=3;
				}
				break;
			case DELETE:
				{
				alt1=4;
				}
				break;
			case UPDATE:
				{
				alt1=5;
				}
				break;
			case SELECT:
				{
				alt1=7;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// fatworm/parser/Fatworm.g:33:5: database_statement
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_database_statement_in_statement151);
					database_statement1=database_statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, database_statement1.getTree());

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:34:5: table_statement
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_table_statement_in_statement157);
					table_statement2=table_statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, table_statement2.getTree());

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:35:5: insert_statement
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_insert_statement_in_statement163);
					insert_statement3=insert_statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, insert_statement3.getTree());

					}
					break;
				case 4 :
					// fatworm/parser/Fatworm.g:36:5: delete_statement
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_delete_statement_in_statement169);
					delete_statement4=delete_statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, delete_statement4.getTree());

					}
					break;
				case 5 :
					// fatworm/parser/Fatworm.g:37:5: update_statement
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_update_statement_in_statement175);
					update_statement5=update_statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, update_statement5.getTree());

					}
					break;
				case 6 :
					// fatworm/parser/Fatworm.g:38:5: index_statement
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_index_statement_in_statement181);
					index_statement6=index_statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, index_statement6.getTree());

					}
					break;
				case 7 :
					// fatworm/parser/Fatworm.g:39:5: select_statement
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_select_statement_in_statement187);
					select_statement7=select_statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, select_statement7.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 1, statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class database_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "database_statement"
	// fatworm/parser/Fatworm.g:42:1: database_statement : ( CREATE DATABASE db_name -> ^( CREATE_DATABASE db_name ) | USE db_name -> ^( USE_DATABASE db_name ) | DROP DATABASE db_name -> ^( DROP_DATABASE db_name ) );
	public final FatwormParser.database_statement_return database_statement() throws RecognitionException {
		FatwormParser.database_statement_return retval = new FatwormParser.database_statement_return();
		retval.start = input.LT(1);
		int database_statement_StartIndex = input.index();

		Object root_0 = null;

		Token CREATE8=null;
		Token DATABASE9=null;
		Token USE11=null;
		Token DROP13=null;
		Token DATABASE14=null;
		ParserRuleReturnScope db_name10 =null;
		ParserRuleReturnScope db_name12 =null;
		ParserRuleReturnScope db_name15 =null;

		Object CREATE8_tree=null;
		Object DATABASE9_tree=null;
		Object USE11_tree=null;
		Object DROP13_tree=null;
		Object DATABASE14_tree=null;
		RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
		RewriteRuleTokenStream stream_USE=new RewriteRuleTokenStream(adaptor,"token USE");
		RewriteRuleTokenStream stream_DATABASE=new RewriteRuleTokenStream(adaptor,"token DATABASE");
		RewriteRuleTokenStream stream_DROP=new RewriteRuleTokenStream(adaptor,"token DROP");
		RewriteRuleSubtreeStream stream_db_name=new RewriteRuleSubtreeStream(adaptor,"rule db_name");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

			// fatworm/parser/Fatworm.g:43:3: ( CREATE DATABASE db_name -> ^( CREATE_DATABASE db_name ) | USE db_name -> ^( USE_DATABASE db_name ) | DROP DATABASE db_name -> ^( DROP_DATABASE db_name ) )
			int alt2=3;
			switch ( input.LA(1) ) {
			case CREATE:
				{
				alt2=1;
				}
				break;
			case USE:
				{
				alt2=2;
				}
				break;
			case DROP:
				{
				alt2=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// fatworm/parser/Fatworm.g:43:5: CREATE DATABASE db_name
					{
					CREATE8=(Token)match(input,CREATE,FOLLOW_CREATE_in_database_statement200); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_CREATE.add(CREATE8);

					DATABASE9=(Token)match(input,DATABASE,FOLLOW_DATABASE_in_database_statement202); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DATABASE.add(DATABASE9);

					pushFollow(FOLLOW_db_name_in_database_statement204);
					db_name10=db_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_db_name.add(db_name10.getTree());
					// AST REWRITE
					// elements: db_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 44:5: -> ^( CREATE_DATABASE db_name )
					{
						// fatworm/parser/Fatworm.g:44:8: ^( CREATE_DATABASE db_name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CREATE_DATABASE, "CREATE_DATABASE"), root_1);
						adaptor.addChild(root_1, stream_db_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:45:5: USE db_name
					{
					USE11=(Token)match(input,USE,FOLLOW_USE_in_database_statement222); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_USE.add(USE11);

					pushFollow(FOLLOW_db_name_in_database_statement224);
					db_name12=db_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_db_name.add(db_name12.getTree());
					// AST REWRITE
					// elements: db_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 46:5: -> ^( USE_DATABASE db_name )
					{
						// fatworm/parser/Fatworm.g:46:8: ^( USE_DATABASE db_name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(USE_DATABASE, "USE_DATABASE"), root_1);
						adaptor.addChild(root_1, stream_db_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:47:5: DROP DATABASE db_name
					{
					DROP13=(Token)match(input,DROP,FOLLOW_DROP_in_database_statement242); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DROP.add(DROP13);

					DATABASE14=(Token)match(input,DATABASE,FOLLOW_DATABASE_in_database_statement244); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DATABASE.add(DATABASE14);

					pushFollow(FOLLOW_db_name_in_database_statement246);
					db_name15=db_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_db_name.add(db_name15.getTree());
					// AST REWRITE
					// elements: db_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 48:5: -> ^( DROP_DATABASE db_name )
					{
						// fatworm/parser/Fatworm.g:48:8: ^( DROP_DATABASE db_name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DROP_DATABASE, "DROP_DATABASE"), root_1);
						adaptor.addChild(root_1, stream_db_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 2, database_statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "database_statement"


	public static class table_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "table_statement"
	// fatworm/parser/Fatworm.g:51:1: table_statement : ( CREATE TABLE tbl_name '(' create_definition ( ',' create_definition )* ')' -> ^( CREATE_TABLE tbl_name ( create_definition )* ) | DROP TABLE tbl_name ( ',' tbl_name )* -> ^( DROP_TABLE ( tbl_name )* ) );
	public final FatwormParser.table_statement_return table_statement() throws RecognitionException {
		FatwormParser.table_statement_return retval = new FatwormParser.table_statement_return();
		retval.start = input.LT(1);
		int table_statement_StartIndex = input.index();

		Object root_0 = null;

		Token CREATE16=null;
		Token TABLE17=null;
		Token char_literal19=null;
		Token char_literal21=null;
		Token char_literal23=null;
		Token DROP24=null;
		Token TABLE25=null;
		Token char_literal27=null;
		ParserRuleReturnScope tbl_name18 =null;
		ParserRuleReturnScope create_definition20 =null;
		ParserRuleReturnScope create_definition22 =null;
		ParserRuleReturnScope tbl_name26 =null;
		ParserRuleReturnScope tbl_name28 =null;

		Object CREATE16_tree=null;
		Object TABLE17_tree=null;
		Object char_literal19_tree=null;
		Object char_literal21_tree=null;
		Object char_literal23_tree=null;
		Object DROP24_tree=null;
		Object TABLE25_tree=null;
		Object char_literal27_tree=null;
		RewriteRuleTokenStream stream_TABLE=new RewriteRuleTokenStream(adaptor,"token TABLE");
		RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_DROP=new RewriteRuleTokenStream(adaptor,"token DROP");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleSubtreeStream stream_create_definition=new RewriteRuleSubtreeStream(adaptor,"rule create_definition");
		RewriteRuleSubtreeStream stream_tbl_name=new RewriteRuleSubtreeStream(adaptor,"rule tbl_name");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

			// fatworm/parser/Fatworm.g:52:3: ( CREATE TABLE tbl_name '(' create_definition ( ',' create_definition )* ')' -> ^( CREATE_TABLE tbl_name ( create_definition )* ) | DROP TABLE tbl_name ( ',' tbl_name )* -> ^( DROP_TABLE ( tbl_name )* ) )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==CREATE) ) {
				alt5=1;
			}
			else if ( (LA5_0==DROP) ) {
				alt5=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// fatworm/parser/Fatworm.g:52:5: CREATE TABLE tbl_name '(' create_definition ( ',' create_definition )* ')'
					{
					CREATE16=(Token)match(input,CREATE,FOLLOW_CREATE_in_table_statement271); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_CREATE.add(CREATE16);

					TABLE17=(Token)match(input,TABLE,FOLLOW_TABLE_in_table_statement273); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_TABLE.add(TABLE17);

					pushFollow(FOLLOW_tbl_name_in_table_statement275);
					tbl_name18=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name18.getTree());
					char_literal19=(Token)match(input,106,FOLLOW_106_in_table_statement277); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_106.add(char_literal19);

					pushFollow(FOLLOW_create_definition_in_table_statement279);
					create_definition20=create_definition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_create_definition.add(create_definition20.getTree());
					// fatworm/parser/Fatworm.g:52:49: ( ',' create_definition )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==110) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// fatworm/parser/Fatworm.g:52:50: ',' create_definition
							{
							char_literal21=(Token)match(input,110,FOLLOW_110_in_table_statement282); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_110.add(char_literal21);

							pushFollow(FOLLOW_create_definition_in_table_statement284);
							create_definition22=create_definition();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_create_definition.add(create_definition22.getTree());
							}
							break;

						default :
							break loop3;
						}
					}

					char_literal23=(Token)match(input,107,FOLLOW_107_in_table_statement288); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_107.add(char_literal23);

					// AST REWRITE
					// elements: tbl_name, create_definition
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 53:5: -> ^( CREATE_TABLE tbl_name ( create_definition )* )
					{
						// fatworm/parser/Fatworm.g:53:8: ^( CREATE_TABLE tbl_name ( create_definition )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CREATE_TABLE, "CREATE_TABLE"), root_1);
						adaptor.addChild(root_1, stream_tbl_name.nextTree());
						// fatworm/parser/Fatworm.g:53:32: ( create_definition )*
						while ( stream_create_definition.hasNext() ) {
							adaptor.addChild(root_1, stream_create_definition.nextTree());
						}
						stream_create_definition.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:54:5: DROP TABLE tbl_name ( ',' tbl_name )*
					{
					DROP24=(Token)match(input,DROP,FOLLOW_DROP_in_table_statement309); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DROP.add(DROP24);

					TABLE25=(Token)match(input,TABLE,FOLLOW_TABLE_in_table_statement311); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_TABLE.add(TABLE25);

					pushFollow(FOLLOW_tbl_name_in_table_statement313);
					tbl_name26=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name26.getTree());
					// fatworm/parser/Fatworm.g:54:25: ( ',' tbl_name )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==110) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// fatworm/parser/Fatworm.g:54:26: ',' tbl_name
							{
							char_literal27=(Token)match(input,110,FOLLOW_110_in_table_statement316); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_110.add(char_literal27);

							pushFollow(FOLLOW_tbl_name_in_table_statement318);
							tbl_name28=tbl_name();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name28.getTree());
							}
							break;

						default :
							break loop4;
						}
					}

					// AST REWRITE
					// elements: tbl_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 55:5: -> ^( DROP_TABLE ( tbl_name )* )
					{
						// fatworm/parser/Fatworm.g:55:8: ^( DROP_TABLE ( tbl_name )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DROP_TABLE, "DROP_TABLE"), root_1);
						// fatworm/parser/Fatworm.g:55:21: ( tbl_name )*
						while ( stream_tbl_name.hasNext() ) {
							adaptor.addChild(root_1, stream_tbl_name.nextTree());
						}
						stream_tbl_name.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 3, table_statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "table_statement"


	public static class create_definition_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "create_definition"
	// fatworm/parser/Fatworm.g:58:1: create_definition : ( col_name data_type ( column_definition_suffix )* -> ^( CREATE_DEFINITION col_name data_type ( column_definition_suffix )* ) | PRIMARY KEY '(' col_name ')' -> ^( PRIMARY_KEY col_name ) );
	public final FatwormParser.create_definition_return create_definition() throws RecognitionException {
		FatwormParser.create_definition_return retval = new FatwormParser.create_definition_return();
		retval.start = input.LT(1);
		int create_definition_StartIndex = input.index();

		Object root_0 = null;

		Token PRIMARY32=null;
		Token KEY33=null;
		Token char_literal34=null;
		Token char_literal36=null;
		ParserRuleReturnScope col_name29 =null;
		ParserRuleReturnScope data_type30 =null;
		ParserRuleReturnScope column_definition_suffix31 =null;
		ParserRuleReturnScope col_name35 =null;

		Object PRIMARY32_tree=null;
		Object KEY33_tree=null;
		Object char_literal34_tree=null;
		Object char_literal36_tree=null;
		RewriteRuleTokenStream stream_PRIMARY=new RewriteRuleTokenStream(adaptor,"token PRIMARY");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_KEY=new RewriteRuleTokenStream(adaptor,"token KEY");
		RewriteRuleSubtreeStream stream_col_name=new RewriteRuleSubtreeStream(adaptor,"rule col_name");
		RewriteRuleSubtreeStream stream_data_type=new RewriteRuleSubtreeStream(adaptor,"rule data_type");
		RewriteRuleSubtreeStream stream_column_definition_suffix=new RewriteRuleSubtreeStream(adaptor,"rule column_definition_suffix");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

			// fatworm/parser/Fatworm.g:59:3: ( col_name data_type ( column_definition_suffix )* -> ^( CREATE_DEFINITION col_name data_type ( column_definition_suffix )* ) | PRIMARY KEY '(' col_name ')' -> ^( PRIMARY_KEY col_name ) )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==ID) ) {
				alt7=1;
			}
			else if ( (LA7_0==PRIMARY) ) {
				alt7=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// fatworm/parser/Fatworm.g:59:5: col_name data_type ( column_definition_suffix )*
					{
					pushFollow(FOLLOW_col_name_in_create_definition346);
					col_name29=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_col_name.add(col_name29.getTree());
					pushFollow(FOLLOW_data_type_in_create_definition348);
					data_type30=data_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_data_type.add(data_type30.getTree());
					// fatworm/parser/Fatworm.g:59:24: ( column_definition_suffix )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==AUTO_INCREMENT||LA6_0==DEFAULT||LA6_0==NOT||LA6_0==NULL) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// fatworm/parser/Fatworm.g:59:24: column_definition_suffix
							{
							pushFollow(FOLLOW_column_definition_suffix_in_create_definition350);
							column_definition_suffix31=column_definition_suffix();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_column_definition_suffix.add(column_definition_suffix31.getTree());
							}
							break;

						default :
							break loop6;
						}
					}

					// AST REWRITE
					// elements: column_definition_suffix, col_name, data_type
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 60:5: -> ^( CREATE_DEFINITION col_name data_type ( column_definition_suffix )* )
					{
						// fatworm/parser/Fatworm.g:60:8: ^( CREATE_DEFINITION col_name data_type ( column_definition_suffix )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CREATE_DEFINITION, "CREATE_DEFINITION"), root_1);
						adaptor.addChild(root_1, stream_col_name.nextTree());
						adaptor.addChild(root_1, stream_data_type.nextTree());
						// fatworm/parser/Fatworm.g:60:47: ( column_definition_suffix )*
						while ( stream_column_definition_suffix.hasNext() ) {
							adaptor.addChild(root_1, stream_column_definition_suffix.nextTree());
						}
						stream_column_definition_suffix.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:61:5: PRIMARY KEY '(' col_name ')'
					{
					PRIMARY32=(Token)match(input,PRIMARY,FOLLOW_PRIMARY_in_create_definition374); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_PRIMARY.add(PRIMARY32);

					KEY33=(Token)match(input,KEY,FOLLOW_KEY_in_create_definition376); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KEY.add(KEY33);

					char_literal34=(Token)match(input,106,FOLLOW_106_in_create_definition378); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_106.add(char_literal34);

					pushFollow(FOLLOW_col_name_in_create_definition380);
					col_name35=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_col_name.add(col_name35.getTree());
					char_literal36=(Token)match(input,107,FOLLOW_107_in_create_definition382); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_107.add(char_literal36);

					// AST REWRITE
					// elements: col_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 62:5: -> ^( PRIMARY_KEY col_name )
					{
						// fatworm/parser/Fatworm.g:62:8: ^( PRIMARY_KEY col_name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PRIMARY_KEY, "PRIMARY_KEY"), root_1);
						adaptor.addChild(root_1, stream_col_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 4, create_definition_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "create_definition"


	public static class data_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "data_type"
	// fatworm/parser/Fatworm.g:65:1: data_type : ( INT ^| FLOAT ^| CHAR ^ '(' ! INTEGER_LITERAL ')' !| DATETIME ^| BOOLEAN ^| DECIMAL ^ '(' ! INTEGER_LITERAL ( ',' ! INTEGER_LITERAL )? ')' !| TIMESTAMP ^| VARCHAR ^ '(' ! INTEGER_LITERAL ')' !);
	public final FatwormParser.data_type_return data_type() throws RecognitionException {
		FatwormParser.data_type_return retval = new FatwormParser.data_type_return();
		retval.start = input.LT(1);
		int data_type_StartIndex = input.index();

		Object root_0 = null;

		Token INT37=null;
		Token FLOAT38=null;
		Token CHAR39=null;
		Token char_literal40=null;
		Token INTEGER_LITERAL41=null;
		Token char_literal42=null;
		Token DATETIME43=null;
		Token BOOLEAN44=null;
		Token DECIMAL45=null;
		Token char_literal46=null;
		Token INTEGER_LITERAL47=null;
		Token char_literal48=null;
		Token INTEGER_LITERAL49=null;
		Token char_literal50=null;
		Token TIMESTAMP51=null;
		Token VARCHAR52=null;
		Token char_literal53=null;
		Token INTEGER_LITERAL54=null;
		Token char_literal55=null;

		Object INT37_tree=null;
		Object FLOAT38_tree=null;
		Object CHAR39_tree=null;
		Object char_literal40_tree=null;
		Object INTEGER_LITERAL41_tree=null;
		Object char_literal42_tree=null;
		Object DATETIME43_tree=null;
		Object BOOLEAN44_tree=null;
		Object DECIMAL45_tree=null;
		Object char_literal46_tree=null;
		Object INTEGER_LITERAL47_tree=null;
		Object char_literal48_tree=null;
		Object INTEGER_LITERAL49_tree=null;
		Object char_literal50_tree=null;
		Object TIMESTAMP51_tree=null;
		Object VARCHAR52_tree=null;
		Object char_literal53_tree=null;
		Object INTEGER_LITERAL54_tree=null;
		Object char_literal55_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

			// fatworm/parser/Fatworm.g:66:3: ( INT ^| FLOAT ^| CHAR ^ '(' ! INTEGER_LITERAL ')' !| DATETIME ^| BOOLEAN ^| DECIMAL ^ '(' ! INTEGER_LITERAL ( ',' ! INTEGER_LITERAL )? ')' !| TIMESTAMP ^| VARCHAR ^ '(' ! INTEGER_LITERAL ')' !)
			int alt9=8;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt9=1;
				}
				break;
			case FLOAT:
				{
				alt9=2;
				}
				break;
			case CHAR:
				{
				alt9=3;
				}
				break;
			case DATETIME:
				{
				alt9=4;
				}
				break;
			case BOOLEAN:
				{
				alt9=5;
				}
				break;
			case DECIMAL:
				{
				alt9=6;
				}
				break;
			case TIMESTAMP:
				{
				alt9=7;
				}
				break;
			case VARCHAR:
				{
				alt9=8;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// fatworm/parser/Fatworm.g:66:5: INT ^
					{
					root_0 = (Object)adaptor.nil();


					INT37=(Token)match(input,INT,FOLLOW_INT_in_data_type407); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INT37_tree = (Object)adaptor.create(INT37);
					root_0 = (Object)adaptor.becomeRoot(INT37_tree, root_0);
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:67:5: FLOAT ^
					{
					root_0 = (Object)adaptor.nil();


					FLOAT38=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_data_type414); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FLOAT38_tree = (Object)adaptor.create(FLOAT38);
					root_0 = (Object)adaptor.becomeRoot(FLOAT38_tree, root_0);
					}

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:68:5: CHAR ^ '(' ! INTEGER_LITERAL ')' !
					{
					root_0 = (Object)adaptor.nil();


					CHAR39=(Token)match(input,CHAR,FOLLOW_CHAR_in_data_type421); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CHAR39_tree = (Object)adaptor.create(CHAR39);
					root_0 = (Object)adaptor.becomeRoot(CHAR39_tree, root_0);
					}

					char_literal40=(Token)match(input,106,FOLLOW_106_in_data_type424); if (state.failed) return retval;
					INTEGER_LITERAL41=(Token)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_data_type427); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INTEGER_LITERAL41_tree = (Object)adaptor.create(INTEGER_LITERAL41);
					adaptor.addChild(root_0, INTEGER_LITERAL41_tree);
					}

					char_literal42=(Token)match(input,107,FOLLOW_107_in_data_type429); if (state.failed) return retval;
					}
					break;
				case 4 :
					// fatworm/parser/Fatworm.g:69:5: DATETIME ^
					{
					root_0 = (Object)adaptor.nil();


					DATETIME43=(Token)match(input,DATETIME,FOLLOW_DATETIME_in_data_type436); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DATETIME43_tree = (Object)adaptor.create(DATETIME43);
					root_0 = (Object)adaptor.becomeRoot(DATETIME43_tree, root_0);
					}

					}
					break;
				case 5 :
					// fatworm/parser/Fatworm.g:70:5: BOOLEAN ^
					{
					root_0 = (Object)adaptor.nil();


					BOOLEAN44=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_data_type443); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					BOOLEAN44_tree = (Object)adaptor.create(BOOLEAN44);
					root_0 = (Object)adaptor.becomeRoot(BOOLEAN44_tree, root_0);
					}

					}
					break;
				case 6 :
					// fatworm/parser/Fatworm.g:71:5: DECIMAL ^ '(' ! INTEGER_LITERAL ( ',' ! INTEGER_LITERAL )? ')' !
					{
					root_0 = (Object)adaptor.nil();


					DECIMAL45=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_data_type450); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DECIMAL45_tree = (Object)adaptor.create(DECIMAL45);
					root_0 = (Object)adaptor.becomeRoot(DECIMAL45_tree, root_0);
					}

					char_literal46=(Token)match(input,106,FOLLOW_106_in_data_type453); if (state.failed) return retval;
					INTEGER_LITERAL47=(Token)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_data_type456); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INTEGER_LITERAL47_tree = (Object)adaptor.create(INTEGER_LITERAL47);
					adaptor.addChild(root_0, INTEGER_LITERAL47_tree);
					}

					// fatworm/parser/Fatworm.g:71:35: ( ',' ! INTEGER_LITERAL )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==110) ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// fatworm/parser/Fatworm.g:71:36: ',' ! INTEGER_LITERAL
							{
							char_literal48=(Token)match(input,110,FOLLOW_110_in_data_type459); if (state.failed) return retval;
							INTEGER_LITERAL49=(Token)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_data_type462); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							INTEGER_LITERAL49_tree = (Object)adaptor.create(INTEGER_LITERAL49);
							adaptor.addChild(root_0, INTEGER_LITERAL49_tree);
							}

							}
							break;

					}

					char_literal50=(Token)match(input,107,FOLLOW_107_in_data_type466); if (state.failed) return retval;
					}
					break;
				case 7 :
					// fatworm/parser/Fatworm.g:72:5: TIMESTAMP ^
					{
					root_0 = (Object)adaptor.nil();


					TIMESTAMP51=(Token)match(input,TIMESTAMP,FOLLOW_TIMESTAMP_in_data_type473); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TIMESTAMP51_tree = (Object)adaptor.create(TIMESTAMP51);
					root_0 = (Object)adaptor.becomeRoot(TIMESTAMP51_tree, root_0);
					}

					}
					break;
				case 8 :
					// fatworm/parser/Fatworm.g:73:5: VARCHAR ^ '(' ! INTEGER_LITERAL ')' !
					{
					root_0 = (Object)adaptor.nil();


					VARCHAR52=(Token)match(input,VARCHAR,FOLLOW_VARCHAR_in_data_type480); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					VARCHAR52_tree = (Object)adaptor.create(VARCHAR52);
					root_0 = (Object)adaptor.becomeRoot(VARCHAR52_tree, root_0);
					}

					char_literal53=(Token)match(input,106,FOLLOW_106_in_data_type483); if (state.failed) return retval;
					INTEGER_LITERAL54=(Token)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_data_type486); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INTEGER_LITERAL54_tree = (Object)adaptor.create(INTEGER_LITERAL54);
					adaptor.addChild(root_0, INTEGER_LITERAL54_tree);
					}

					char_literal55=(Token)match(input,107,FOLLOW_107_in_data_type488); if (state.failed) return retval;
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 5, data_type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "data_type"


	public static class column_definition_suffix_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "column_definition_suffix"
	// fatworm/parser/Fatworm.g:76:1: column_definition_suffix : ( ( NOT )? NULL ^| DEFAULT ^ const_value | AUTO_INCREMENT ^);
	public final FatwormParser.column_definition_suffix_return column_definition_suffix() throws RecognitionException {
		FatwormParser.column_definition_suffix_return retval = new FatwormParser.column_definition_suffix_return();
		retval.start = input.LT(1);
		int column_definition_suffix_StartIndex = input.index();

		Object root_0 = null;

		Token NOT56=null;
		Token NULL57=null;
		Token DEFAULT58=null;
		Token AUTO_INCREMENT60=null;
		ParserRuleReturnScope const_value59 =null;

		Object NOT56_tree=null;
		Object NULL57_tree=null;
		Object DEFAULT58_tree=null;
		Object AUTO_INCREMENT60_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

			// fatworm/parser/Fatworm.g:77:3: ( ( NOT )? NULL ^| DEFAULT ^ const_value | AUTO_INCREMENT ^)
			int alt11=3;
			switch ( input.LA(1) ) {
			case NOT:
			case NULL:
				{
				alt11=1;
				}
				break;
			case DEFAULT:
				{
				alt11=2;
				}
				break;
			case AUTO_INCREMENT:
				{
				alt11=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// fatworm/parser/Fatworm.g:77:5: ( NOT )? NULL ^
					{
					root_0 = (Object)adaptor.nil();


					// fatworm/parser/Fatworm.g:77:5: ( NOT )?
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==NOT) ) {
						alt10=1;
					}
					switch (alt10) {
						case 1 :
							// fatworm/parser/Fatworm.g:77:5: NOT
							{
							NOT56=(Token)match(input,NOT,FOLLOW_NOT_in_column_definition_suffix502); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NOT56_tree = (Object)adaptor.create(NOT56);
							adaptor.addChild(root_0, NOT56_tree);
							}

							}
							break;

					}

					NULL57=(Token)match(input,NULL,FOLLOW_NULL_in_column_definition_suffix505); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NULL57_tree = (Object)adaptor.create(NULL57);
					root_0 = (Object)adaptor.becomeRoot(NULL57_tree, root_0);
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:78:5: DEFAULT ^ const_value
					{
					root_0 = (Object)adaptor.nil();


					DEFAULT58=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_column_definition_suffix512); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DEFAULT58_tree = (Object)adaptor.create(DEFAULT58);
					root_0 = (Object)adaptor.becomeRoot(DEFAULT58_tree, root_0);
					}

					pushFollow(FOLLOW_const_value_in_column_definition_suffix515);
					const_value59=const_value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, const_value59.getTree());

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:79:5: AUTO_INCREMENT ^
					{
					root_0 = (Object)adaptor.nil();


					AUTO_INCREMENT60=(Token)match(input,AUTO_INCREMENT,FOLLOW_AUTO_INCREMENT_in_column_definition_suffix521); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AUTO_INCREMENT60_tree = (Object)adaptor.create(AUTO_INCREMENT60);
					root_0 = (Object)adaptor.becomeRoot(AUTO_INCREMENT60_tree, root_0);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 6, column_definition_suffix_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "column_definition_suffix"


	public static class insert_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "insert_statement"
	// fatworm/parser/Fatworm.g:82:1: insert_statement : ( INSERT INTO tbl_name values_clause -> ^( INSERT_VALUES tbl_name values_clause ) | INSERT INTO tbl_name '(' col_name ( ',' col_name )* ')' values_clause -> ^( INSERT_COLUMNS tbl_name ( col_name )* values_clause ) | INSERT INTO tbl_name '(' subquery ')' -> ^( INSERT_SUBQUERY tbl_name subquery ) );
	public final FatwormParser.insert_statement_return insert_statement() throws RecognitionException {
		FatwormParser.insert_statement_return retval = new FatwormParser.insert_statement_return();
		retval.start = input.LT(1);
		int insert_statement_StartIndex = input.index();

		Object root_0 = null;

		Token INSERT61=null;
		Token INTO62=null;
		Token INSERT65=null;
		Token INTO66=null;
		Token char_literal68=null;
		Token char_literal70=null;
		Token char_literal72=null;
		Token INSERT74=null;
		Token INTO75=null;
		Token char_literal77=null;
		Token char_literal79=null;
		ParserRuleReturnScope tbl_name63 =null;
		ParserRuleReturnScope values_clause64 =null;
		ParserRuleReturnScope tbl_name67 =null;
		ParserRuleReturnScope col_name69 =null;
		ParserRuleReturnScope col_name71 =null;
		ParserRuleReturnScope values_clause73 =null;
		ParserRuleReturnScope tbl_name76 =null;
		ParserRuleReturnScope subquery78 =null;

		Object INSERT61_tree=null;
		Object INTO62_tree=null;
		Object INSERT65_tree=null;
		Object INTO66_tree=null;
		Object char_literal68_tree=null;
		Object char_literal70_tree=null;
		Object char_literal72_tree=null;
		Object INSERT74_tree=null;
		Object INTO75_tree=null;
		Object char_literal77_tree=null;
		Object char_literal79_tree=null;
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
		RewriteRuleTokenStream stream_INSERT=new RewriteRuleTokenStream(adaptor,"token INSERT");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleTokenStream stream_INTO=new RewriteRuleTokenStream(adaptor,"token INTO");
		RewriteRuleSubtreeStream stream_col_name=new RewriteRuleSubtreeStream(adaptor,"rule col_name");
		RewriteRuleSubtreeStream stream_values_clause=new RewriteRuleSubtreeStream(adaptor,"rule values_clause");
		RewriteRuleSubtreeStream stream_subquery=new RewriteRuleSubtreeStream(adaptor,"rule subquery");
		RewriteRuleSubtreeStream stream_tbl_name=new RewriteRuleSubtreeStream(adaptor,"rule tbl_name");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

			// fatworm/parser/Fatworm.g:83:3: ( INSERT INTO tbl_name values_clause -> ^( INSERT_VALUES tbl_name values_clause ) | INSERT INTO tbl_name '(' col_name ( ',' col_name )* ')' values_clause -> ^( INSERT_COLUMNS tbl_name ( col_name )* values_clause ) | INSERT INTO tbl_name '(' subquery ')' -> ^( INSERT_SUBQUERY tbl_name subquery ) )
			int alt13=3;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==INSERT) ) {
				int LA13_1 = input.LA(2);
				if ( (synpred25_Fatworm()) ) {
					alt13=1;
				}
				else if ( (synpred27_Fatworm()) ) {
					alt13=2;
				}
				else if ( (true) ) {
					alt13=3;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// fatworm/parser/Fatworm.g:83:5: INSERT INTO tbl_name values_clause
					{
					INSERT61=(Token)match(input,INSERT,FOLLOW_INSERT_in_insert_statement535); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INSERT.add(INSERT61);

					INTO62=(Token)match(input,INTO,FOLLOW_INTO_in_insert_statement537); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INTO.add(INTO62);

					pushFollow(FOLLOW_tbl_name_in_insert_statement539);
					tbl_name63=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name63.getTree());
					pushFollow(FOLLOW_values_clause_in_insert_statement541);
					values_clause64=values_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_values_clause.add(values_clause64.getTree());
					// AST REWRITE
					// elements: values_clause, tbl_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 84:5: -> ^( INSERT_VALUES tbl_name values_clause )
					{
						// fatworm/parser/Fatworm.g:84:8: ^( INSERT_VALUES tbl_name values_clause )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INSERT_VALUES, "INSERT_VALUES"), root_1);
						adaptor.addChild(root_1, stream_tbl_name.nextTree());
						adaptor.addChild(root_1, stream_values_clause.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:85:5: INSERT INTO tbl_name '(' col_name ( ',' col_name )* ')' values_clause
					{
					INSERT65=(Token)match(input,INSERT,FOLLOW_INSERT_in_insert_statement561); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INSERT.add(INSERT65);

					INTO66=(Token)match(input,INTO,FOLLOW_INTO_in_insert_statement563); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INTO.add(INTO66);

					pushFollow(FOLLOW_tbl_name_in_insert_statement565);
					tbl_name67=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name67.getTree());
					char_literal68=(Token)match(input,106,FOLLOW_106_in_insert_statement567); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_106.add(char_literal68);

					pushFollow(FOLLOW_col_name_in_insert_statement569);
					col_name69=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_col_name.add(col_name69.getTree());
					// fatworm/parser/Fatworm.g:85:39: ( ',' col_name )*
					loop12:
					while (true) {
						int alt12=2;
						int LA12_0 = input.LA(1);
						if ( (LA12_0==110) ) {
							alt12=1;
						}

						switch (alt12) {
						case 1 :
							// fatworm/parser/Fatworm.g:85:40: ',' col_name
							{
							char_literal70=(Token)match(input,110,FOLLOW_110_in_insert_statement572); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_110.add(char_literal70);

							pushFollow(FOLLOW_col_name_in_insert_statement574);
							col_name71=col_name();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_col_name.add(col_name71.getTree());
							}
							break;

						default :
							break loop12;
						}
					}

					char_literal72=(Token)match(input,107,FOLLOW_107_in_insert_statement578); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_107.add(char_literal72);

					pushFollow(FOLLOW_values_clause_in_insert_statement580);
					values_clause73=values_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_values_clause.add(values_clause73.getTree());
					// AST REWRITE
					// elements: tbl_name, values_clause, col_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 86:5: -> ^( INSERT_COLUMNS tbl_name ( col_name )* values_clause )
					{
						// fatworm/parser/Fatworm.g:86:8: ^( INSERT_COLUMNS tbl_name ( col_name )* values_clause )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INSERT_COLUMNS, "INSERT_COLUMNS"), root_1);
						adaptor.addChild(root_1, stream_tbl_name.nextTree());
						// fatworm/parser/Fatworm.g:86:34: ( col_name )*
						while ( stream_col_name.hasNext() ) {
							adaptor.addChild(root_1, stream_col_name.nextTree());
						}
						stream_col_name.reset();

						adaptor.addChild(root_1, stream_values_clause.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:87:5: INSERT INTO tbl_name '(' subquery ')'
					{
					INSERT74=(Token)match(input,INSERT,FOLLOW_INSERT_in_insert_statement603); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INSERT.add(INSERT74);

					INTO75=(Token)match(input,INTO,FOLLOW_INTO_in_insert_statement605); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INTO.add(INTO75);

					pushFollow(FOLLOW_tbl_name_in_insert_statement607);
					tbl_name76=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name76.getTree());
					char_literal77=(Token)match(input,106,FOLLOW_106_in_insert_statement609); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_106.add(char_literal77);

					pushFollow(FOLLOW_subquery_in_insert_statement611);
					subquery78=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_subquery.add(subquery78.getTree());
					char_literal79=(Token)match(input,107,FOLLOW_107_in_insert_statement613); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_107.add(char_literal79);

					// AST REWRITE
					// elements: tbl_name, subquery
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 88:5: -> ^( INSERT_SUBQUERY tbl_name subquery )
					{
						// fatworm/parser/Fatworm.g:88:8: ^( INSERT_SUBQUERY tbl_name subquery )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INSERT_SUBQUERY, "INSERT_SUBQUERY"), root_1);
						adaptor.addChild(root_1, stream_tbl_name.nextTree());
						adaptor.addChild(root_1, stream_subquery.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 7, insert_statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "insert_statement"


	public static class values_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "values_clause"
	// fatworm/parser/Fatworm.g:91:1: values_clause : VALUES ^ '(' ! value ( ',' ! value )* ')' !;
	public final FatwormParser.values_clause_return values_clause() throws RecognitionException {
		FatwormParser.values_clause_return retval = new FatwormParser.values_clause_return();
		retval.start = input.LT(1);
		int values_clause_StartIndex = input.index();

		Object root_0 = null;

		Token VALUES80=null;
		Token char_literal81=null;
		Token char_literal83=null;
		Token char_literal85=null;
		ParserRuleReturnScope value82 =null;
		ParserRuleReturnScope value84 =null;

		Object VALUES80_tree=null;
		Object char_literal81_tree=null;
		Object char_literal83_tree=null;
		Object char_literal85_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

			// fatworm/parser/Fatworm.g:92:3: ( VALUES ^ '(' ! value ( ',' ! value )* ')' !)
			// fatworm/parser/Fatworm.g:92:5: VALUES ^ '(' ! value ( ',' ! value )* ')' !
			{
			root_0 = (Object)adaptor.nil();


			VALUES80=(Token)match(input,VALUES,FOLLOW_VALUES_in_values_clause640); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			VALUES80_tree = (Object)adaptor.create(VALUES80);
			root_0 = (Object)adaptor.becomeRoot(VALUES80_tree, root_0);
			}

			char_literal81=(Token)match(input,106,FOLLOW_106_in_values_clause643); if (state.failed) return retval;
			pushFollow(FOLLOW_value_in_values_clause646);
			value82=value();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, value82.getTree());

			// fatworm/parser/Fatworm.g:92:24: ( ',' ! value )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==110) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// fatworm/parser/Fatworm.g:92:25: ',' ! value
					{
					char_literal83=(Token)match(input,110,FOLLOW_110_in_values_clause649); if (state.failed) return retval;
					pushFollow(FOLLOW_value_in_values_clause652);
					value84=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value84.getTree());

					}
					break;

				default :
					break loop14;
				}
			}

			char_literal85=(Token)match(input,107,FOLLOW_107_in_values_clause656); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 8, values_clause_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "values_clause"


	public static class delete_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "delete_statement"
	// fatworm/parser/Fatworm.g:95:1: delete_statement : DELETE ^ FROM ! tbl_name ( where_clause )? ;
	public final FatwormParser.delete_statement_return delete_statement() throws RecognitionException {
		FatwormParser.delete_statement_return retval = new FatwormParser.delete_statement_return();
		retval.start = input.LT(1);
		int delete_statement_StartIndex = input.index();

		Object root_0 = null;

		Token DELETE86=null;
		Token FROM87=null;
		ParserRuleReturnScope tbl_name88 =null;
		ParserRuleReturnScope where_clause89 =null;

		Object DELETE86_tree=null;
		Object FROM87_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

			// fatworm/parser/Fatworm.g:96:3: ( DELETE ^ FROM ! tbl_name ( where_clause )? )
			// fatworm/parser/Fatworm.g:96:5: DELETE ^ FROM ! tbl_name ( where_clause )?
			{
			root_0 = (Object)adaptor.nil();


			DELETE86=(Token)match(input,DELETE,FOLLOW_DELETE_in_delete_statement670); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DELETE86_tree = (Object)adaptor.create(DELETE86);
			root_0 = (Object)adaptor.becomeRoot(DELETE86_tree, root_0);
			}

			FROM87=(Token)match(input,FROM,FOLLOW_FROM_in_delete_statement673); if (state.failed) return retval;
			pushFollow(FOLLOW_tbl_name_in_delete_statement676);
			tbl_name88=tbl_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, tbl_name88.getTree());

			// fatworm/parser/Fatworm.g:96:28: ( where_clause )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==WHERE) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// fatworm/parser/Fatworm.g:96:28: where_clause
					{
					pushFollow(FOLLOW_where_clause_in_delete_statement678);
					where_clause89=where_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, where_clause89.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 9, delete_statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "delete_statement"


	public static class update_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "update_statement"
	// fatworm/parser/Fatworm.g:99:1: update_statement : UPDATE ^ tbl_name SET ! update_pair ( ',' ! update_pair )* ( where_clause )? ;
	public final FatwormParser.update_statement_return update_statement() throws RecognitionException {
		FatwormParser.update_statement_return retval = new FatwormParser.update_statement_return();
		retval.start = input.LT(1);
		int update_statement_StartIndex = input.index();

		Object root_0 = null;

		Token UPDATE90=null;
		Token SET92=null;
		Token char_literal94=null;
		ParserRuleReturnScope tbl_name91 =null;
		ParserRuleReturnScope update_pair93 =null;
		ParserRuleReturnScope update_pair95 =null;
		ParserRuleReturnScope where_clause96 =null;

		Object UPDATE90_tree=null;
		Object SET92_tree=null;
		Object char_literal94_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

			// fatworm/parser/Fatworm.g:100:3: ( UPDATE ^ tbl_name SET ! update_pair ( ',' ! update_pair )* ( where_clause )? )
			// fatworm/parser/Fatworm.g:100:5: UPDATE ^ tbl_name SET ! update_pair ( ',' ! update_pair )* ( where_clause )?
			{
			root_0 = (Object)adaptor.nil();


			UPDATE90=(Token)match(input,UPDATE,FOLLOW_UPDATE_in_update_statement692); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			UPDATE90_tree = (Object)adaptor.create(UPDATE90);
			root_0 = (Object)adaptor.becomeRoot(UPDATE90_tree, root_0);
			}

			pushFollow(FOLLOW_tbl_name_in_update_statement695);
			tbl_name91=tbl_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, tbl_name91.getTree());

			SET92=(Token)match(input,SET,FOLLOW_SET_in_update_statement697); if (state.failed) return retval;
			pushFollow(FOLLOW_update_pair_in_update_statement700);
			update_pair93=update_pair();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, update_pair93.getTree());

			// fatworm/parser/Fatworm.g:100:39: ( ',' ! update_pair )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==110) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// fatworm/parser/Fatworm.g:100:40: ',' ! update_pair
					{
					char_literal94=(Token)match(input,110,FOLLOW_110_in_update_statement703); if (state.failed) return retval;
					pushFollow(FOLLOW_update_pair_in_update_statement706);
					update_pair95=update_pair();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, update_pair95.getTree());

					}
					break;

				default :
					break loop16;
				}
			}

			// fatworm/parser/Fatworm.g:100:59: ( where_clause )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==WHERE) ) {
				alt17=1;
			}
			switch (alt17) {
				case 1 :
					// fatworm/parser/Fatworm.g:100:59: where_clause
					{
					pushFollow(FOLLOW_where_clause_in_update_statement710);
					where_clause96=where_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, where_clause96.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 10, update_statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "update_statement"


	public static class update_pair_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "update_pair"
	// fatworm/parser/Fatworm.g:103:1: update_pair : col_name '=' value -> ^( UPDATE_PAIR col_name value ) ;
	public final FatwormParser.update_pair_return update_pair() throws RecognitionException {
		FatwormParser.update_pair_return retval = new FatwormParser.update_pair_return();
		retval.start = input.LT(1);
		int update_pair_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal98=null;
		ParserRuleReturnScope col_name97 =null;
		ParserRuleReturnScope value99 =null;

		Object char_literal98_tree=null;
		RewriteRuleTokenStream stream_117=new RewriteRuleTokenStream(adaptor,"token 117");
		RewriteRuleSubtreeStream stream_col_name=new RewriteRuleSubtreeStream(adaptor,"rule col_name");
		RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

			// fatworm/parser/Fatworm.g:104:3: ( col_name '=' value -> ^( UPDATE_PAIR col_name value ) )
			// fatworm/parser/Fatworm.g:104:5: col_name '=' value
			{
			pushFollow(FOLLOW_col_name_in_update_pair724);
			col_name97=col_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_col_name.add(col_name97.getTree());
			char_literal98=(Token)match(input,117,FOLLOW_117_in_update_pair726); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_117.add(char_literal98);

			pushFollow(FOLLOW_value_in_update_pair728);
			value99=value();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_value.add(value99.getTree());
			// AST REWRITE
			// elements: value, col_name
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 105:5: -> ^( UPDATE_PAIR col_name value )
			{
				// fatworm/parser/Fatworm.g:105:8: ^( UPDATE_PAIR col_name value )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(UPDATE_PAIR, "UPDATE_PAIR"), root_1);
				adaptor.addChild(root_1, stream_col_name.nextTree());
				adaptor.addChild(root_1, stream_value.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 11, update_pair_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "update_pair"


	public static class index_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "index_statement"
	// fatworm/parser/Fatworm.g:108:1: index_statement : ( CREATE INDEX index_name ON tbl_name '(' col_name ')' -> ^( CREATE_INDEX index_name tbl_name col_name ) | CREATE UNIQUE INDEX index_name ON tbl_name '(' col_name ')' -> ^( CREATE_UNIQUE_INDEX index_name tbl_name col_name ) | DROP INDEX index_name ON tbl_name -> ^( DROP_INDEX index_name tbl_name ) );
	public final FatwormParser.index_statement_return index_statement() throws RecognitionException {
		FatwormParser.index_statement_return retval = new FatwormParser.index_statement_return();
		retval.start = input.LT(1);
		int index_statement_StartIndex = input.index();

		Object root_0 = null;

		Token CREATE100=null;
		Token INDEX101=null;
		Token ON103=null;
		Token char_literal105=null;
		Token char_literal107=null;
		Token CREATE108=null;
		Token UNIQUE109=null;
		Token INDEX110=null;
		Token ON112=null;
		Token char_literal114=null;
		Token char_literal116=null;
		Token DROP117=null;
		Token INDEX118=null;
		Token ON120=null;
		ParserRuleReturnScope index_name102 =null;
		ParserRuleReturnScope tbl_name104 =null;
		ParserRuleReturnScope col_name106 =null;
		ParserRuleReturnScope index_name111 =null;
		ParserRuleReturnScope tbl_name113 =null;
		ParserRuleReturnScope col_name115 =null;
		ParserRuleReturnScope index_name119 =null;
		ParserRuleReturnScope tbl_name121 =null;

		Object CREATE100_tree=null;
		Object INDEX101_tree=null;
		Object ON103_tree=null;
		Object char_literal105_tree=null;
		Object char_literal107_tree=null;
		Object CREATE108_tree=null;
		Object UNIQUE109_tree=null;
		Object INDEX110_tree=null;
		Object ON112_tree=null;
		Object char_literal114_tree=null;
		Object char_literal116_tree=null;
		Object DROP117_tree=null;
		Object INDEX118_tree=null;
		Object ON120_tree=null;
		RewriteRuleTokenStream stream_INDEX=new RewriteRuleTokenStream(adaptor,"token INDEX");
		RewriteRuleTokenStream stream_ON=new RewriteRuleTokenStream(adaptor,"token ON");
		RewriteRuleTokenStream stream_UNIQUE=new RewriteRuleTokenStream(adaptor,"token UNIQUE");
		RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_DROP=new RewriteRuleTokenStream(adaptor,"token DROP");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleSubtreeStream stream_col_name=new RewriteRuleSubtreeStream(adaptor,"rule col_name");
		RewriteRuleSubtreeStream stream_index_name=new RewriteRuleSubtreeStream(adaptor,"rule index_name");
		RewriteRuleSubtreeStream stream_tbl_name=new RewriteRuleSubtreeStream(adaptor,"rule tbl_name");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

			// fatworm/parser/Fatworm.g:109:3: ( CREATE INDEX index_name ON tbl_name '(' col_name ')' -> ^( CREATE_INDEX index_name tbl_name col_name ) | CREATE UNIQUE INDEX index_name ON tbl_name '(' col_name ')' -> ^( CREATE_UNIQUE_INDEX index_name tbl_name col_name ) | DROP INDEX index_name ON tbl_name -> ^( DROP_INDEX index_name tbl_name ) )
			int alt18=3;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==CREATE) ) {
				int LA18_1 = input.LA(2);
				if ( (synpred32_Fatworm()) ) {
					alt18=1;
				}
				else if ( (synpred33_Fatworm()) ) {
					alt18=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 18, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA18_0==DROP) ) {
				alt18=3;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// fatworm/parser/Fatworm.g:109:5: CREATE INDEX index_name ON tbl_name '(' col_name ')'
					{
					CREATE100=(Token)match(input,CREATE,FOLLOW_CREATE_in_index_statement755); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_CREATE.add(CREATE100);

					INDEX101=(Token)match(input,INDEX,FOLLOW_INDEX_in_index_statement757); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INDEX.add(INDEX101);

					pushFollow(FOLLOW_index_name_in_index_statement759);
					index_name102=index_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_index_name.add(index_name102.getTree());
					ON103=(Token)match(input,ON,FOLLOW_ON_in_index_statement761); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ON.add(ON103);

					pushFollow(FOLLOW_tbl_name_in_index_statement763);
					tbl_name104=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name104.getTree());
					char_literal105=(Token)match(input,106,FOLLOW_106_in_index_statement765); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_106.add(char_literal105);

					pushFollow(FOLLOW_col_name_in_index_statement767);
					col_name106=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_col_name.add(col_name106.getTree());
					char_literal107=(Token)match(input,107,FOLLOW_107_in_index_statement769); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_107.add(char_literal107);

					// AST REWRITE
					// elements: index_name, col_name, tbl_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 110:5: -> ^( CREATE_INDEX index_name tbl_name col_name )
					{
						// fatworm/parser/Fatworm.g:110:8: ^( CREATE_INDEX index_name tbl_name col_name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CREATE_INDEX, "CREATE_INDEX"), root_1);
						adaptor.addChild(root_1, stream_index_name.nextTree());
						adaptor.addChild(root_1, stream_tbl_name.nextTree());
						adaptor.addChild(root_1, stream_col_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:111:5: CREATE UNIQUE INDEX index_name ON tbl_name '(' col_name ')'
					{
					CREATE108=(Token)match(input,CREATE,FOLLOW_CREATE_in_index_statement791); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_CREATE.add(CREATE108);

					UNIQUE109=(Token)match(input,UNIQUE,FOLLOW_UNIQUE_in_index_statement793); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_UNIQUE.add(UNIQUE109);

					INDEX110=(Token)match(input,INDEX,FOLLOW_INDEX_in_index_statement795); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INDEX.add(INDEX110);

					pushFollow(FOLLOW_index_name_in_index_statement797);
					index_name111=index_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_index_name.add(index_name111.getTree());
					ON112=(Token)match(input,ON,FOLLOW_ON_in_index_statement799); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ON.add(ON112);

					pushFollow(FOLLOW_tbl_name_in_index_statement801);
					tbl_name113=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name113.getTree());
					char_literal114=(Token)match(input,106,FOLLOW_106_in_index_statement803); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_106.add(char_literal114);

					pushFollow(FOLLOW_col_name_in_index_statement805);
					col_name115=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_col_name.add(col_name115.getTree());
					char_literal116=(Token)match(input,107,FOLLOW_107_in_index_statement807); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_107.add(char_literal116);

					// AST REWRITE
					// elements: index_name, tbl_name, col_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 112:5: -> ^( CREATE_UNIQUE_INDEX index_name tbl_name col_name )
					{
						// fatworm/parser/Fatworm.g:112:8: ^( CREATE_UNIQUE_INDEX index_name tbl_name col_name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CREATE_UNIQUE_INDEX, "CREATE_UNIQUE_INDEX"), root_1);
						adaptor.addChild(root_1, stream_index_name.nextTree());
						adaptor.addChild(root_1, stream_tbl_name.nextTree());
						adaptor.addChild(root_1, stream_col_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:113:5: DROP INDEX index_name ON tbl_name
					{
					DROP117=(Token)match(input,DROP,FOLLOW_DROP_in_index_statement829); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DROP.add(DROP117);

					INDEX118=(Token)match(input,INDEX,FOLLOW_INDEX_in_index_statement831); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INDEX.add(INDEX118);

					pushFollow(FOLLOW_index_name_in_index_statement833);
					index_name119=index_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_index_name.add(index_name119.getTree());
					ON120=(Token)match(input,ON,FOLLOW_ON_in_index_statement835); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ON.add(ON120);

					pushFollow(FOLLOW_tbl_name_in_index_statement837);
					tbl_name121=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_tbl_name.add(tbl_name121.getTree());
					// AST REWRITE
					// elements: tbl_name, index_name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 114:5: -> ^( DROP_INDEX index_name tbl_name )
					{
						// fatworm/parser/Fatworm.g:114:8: ^( DROP_INDEX index_name tbl_name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DROP_INDEX, "DROP_INDEX"), root_1);
						adaptor.addChild(root_1, stream_index_name.nextTree());
						adaptor.addChild(root_1, stream_tbl_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 12, index_statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "index_statement"


	public static class select_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "select_statement"
	// fatworm/parser/Fatworm.g:117:1: select_statement : ( SELECT select_expr ( ',' select_expr )* ( select_suffix )? -> ^( SELECT ( select_expr )* ( select_suffix )? ) | SELECT DISTINCT select_expr ( ',' select_expr )* ( select_suffix )? -> ^( SELECT_DISTINCT ( select_expr )* ( select_suffix )? ) );
	public final FatwormParser.select_statement_return select_statement() throws RecognitionException {
		FatwormParser.select_statement_return retval = new FatwormParser.select_statement_return();
		retval.start = input.LT(1);
		int select_statement_StartIndex = input.index();

		Object root_0 = null;

		Token SELECT122=null;
		Token char_literal124=null;
		Token SELECT127=null;
		Token DISTINCT128=null;
		Token char_literal130=null;
		ParserRuleReturnScope select_expr123 =null;
		ParserRuleReturnScope select_expr125 =null;
		ParserRuleReturnScope select_suffix126 =null;
		ParserRuleReturnScope select_expr129 =null;
		ParserRuleReturnScope select_expr131 =null;
		ParserRuleReturnScope select_suffix132 =null;

		Object SELECT122_tree=null;
		Object char_literal124_tree=null;
		Object SELECT127_tree=null;
		Object DISTINCT128_tree=null;
		Object char_literal130_tree=null;
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
		RewriteRuleTokenStream stream_DISTINCT=new RewriteRuleTokenStream(adaptor,"token DISTINCT");
		RewriteRuleTokenStream stream_SELECT=new RewriteRuleTokenStream(adaptor,"token SELECT");
		RewriteRuleSubtreeStream stream_select_suffix=new RewriteRuleSubtreeStream(adaptor,"rule select_suffix");
		RewriteRuleSubtreeStream stream_select_expr=new RewriteRuleSubtreeStream(adaptor,"rule select_expr");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

			// fatworm/parser/Fatworm.g:118:3: ( SELECT select_expr ( ',' select_expr )* ( select_suffix )? -> ^( SELECT ( select_expr )* ( select_suffix )? ) | SELECT DISTINCT select_expr ( ',' select_expr )* ( select_suffix )? -> ^( SELECT_DISTINCT ( select_expr )* ( select_suffix )? ) )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==SELECT) ) {
				int LA23_1 = input.LA(2);
				if ( (synpred36_Fatworm()) ) {
					alt23=1;
				}
				else if ( (true) ) {
					alt23=2;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// fatworm/parser/Fatworm.g:118:5: SELECT select_expr ( ',' select_expr )* ( select_suffix )?
					{
					SELECT122=(Token)match(input,SELECT,FOLLOW_SELECT_in_select_statement864); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SELECT.add(SELECT122);

					pushFollow(FOLLOW_select_expr_in_select_statement866);
					select_expr123=select_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_select_expr.add(select_expr123.getTree());
					// fatworm/parser/Fatworm.g:118:24: ( ',' select_expr )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0==110) ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// fatworm/parser/Fatworm.g:118:25: ',' select_expr
							{
							char_literal124=(Token)match(input,110,FOLLOW_110_in_select_statement869); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_110.add(char_literal124);

							pushFollow(FOLLOW_select_expr_in_select_statement871);
							select_expr125=select_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_select_expr.add(select_expr125.getTree());
							}
							break;

						default :
							break loop19;
						}
					}

					// fatworm/parser/Fatworm.g:118:43: ( select_suffix )?
					int alt20=2;
					int LA20_0 = input.LA(1);
					if ( (LA20_0==FROM) ) {
						alt20=1;
					}
					switch (alt20) {
						case 1 :
							// fatworm/parser/Fatworm.g:118:43: select_suffix
							{
							pushFollow(FOLLOW_select_suffix_in_select_statement875);
							select_suffix126=select_suffix();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_select_suffix.add(select_suffix126.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: select_suffix, select_expr, SELECT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 119:5: -> ^( SELECT ( select_expr )* ( select_suffix )? )
					{
						// fatworm/parser/Fatworm.g:119:8: ^( SELECT ( select_expr )* ( select_suffix )? )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_SELECT.nextNode(), root_1);
						// fatworm/parser/Fatworm.g:119:17: ( select_expr )*
						while ( stream_select_expr.hasNext() ) {
							adaptor.addChild(root_1, stream_select_expr.nextTree());
						}
						stream_select_expr.reset();

						// fatworm/parser/Fatworm.g:119:30: ( select_suffix )?
						if ( stream_select_suffix.hasNext() ) {
							adaptor.addChild(root_1, stream_select_suffix.nextTree());
						}
						stream_select_suffix.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:120:5: SELECT DISTINCT select_expr ( ',' select_expr )* ( select_suffix )?
					{
					SELECT127=(Token)match(input,SELECT,FOLLOW_SELECT_in_select_statement898); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SELECT.add(SELECT127);

					DISTINCT128=(Token)match(input,DISTINCT,FOLLOW_DISTINCT_in_select_statement900); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DISTINCT.add(DISTINCT128);

					pushFollow(FOLLOW_select_expr_in_select_statement902);
					select_expr129=select_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_select_expr.add(select_expr129.getTree());
					// fatworm/parser/Fatworm.g:120:33: ( ',' select_expr )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( (LA21_0==110) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// fatworm/parser/Fatworm.g:120:34: ',' select_expr
							{
							char_literal130=(Token)match(input,110,FOLLOW_110_in_select_statement905); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_110.add(char_literal130);

							pushFollow(FOLLOW_select_expr_in_select_statement907);
							select_expr131=select_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_select_expr.add(select_expr131.getTree());
							}
							break;

						default :
							break loop21;
						}
					}

					// fatworm/parser/Fatworm.g:120:52: ( select_suffix )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==FROM) ) {
						alt22=1;
					}
					switch (alt22) {
						case 1 :
							// fatworm/parser/Fatworm.g:120:52: select_suffix
							{
							pushFollow(FOLLOW_select_suffix_in_select_statement911);
							select_suffix132=select_suffix();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_select_suffix.add(select_suffix132.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: select_suffix, select_expr
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 121:5: -> ^( SELECT_DISTINCT ( select_expr )* ( select_suffix )? )
					{
						// fatworm/parser/Fatworm.g:121:8: ^( SELECT_DISTINCT ( select_expr )* ( select_suffix )? )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECT_DISTINCT, "SELECT_DISTINCT"), root_1);
						// fatworm/parser/Fatworm.g:121:26: ( select_expr )*
						while ( stream_select_expr.hasNext() ) {
							adaptor.addChild(root_1, stream_select_expr.nextTree());
						}
						stream_select_expr.reset();

						// fatworm/parser/Fatworm.g:121:39: ( select_suffix )?
						if ( stream_select_suffix.hasNext() ) {
							adaptor.addChild(root_1, stream_select_suffix.nextTree());
						}
						stream_select_suffix.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 13, select_statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "select_statement"


	public static class select_suffix_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "select_suffix"
	// fatworm/parser/Fatworm.g:124:1: select_suffix : from_clause ( where_clause )? ( group_by_clause )? ( having_clause )? ( order_by_clause )? ;
	public final FatwormParser.select_suffix_return select_suffix() throws RecognitionException {
		FatwormParser.select_suffix_return retval = new FatwormParser.select_suffix_return();
		retval.start = input.LT(1);
		int select_suffix_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope from_clause133 =null;
		ParserRuleReturnScope where_clause134 =null;
		ParserRuleReturnScope group_by_clause135 =null;
		ParserRuleReturnScope having_clause136 =null;
		ParserRuleReturnScope order_by_clause137 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

			// fatworm/parser/Fatworm.g:125:3: ( from_clause ( where_clause )? ( group_by_clause )? ( having_clause )? ( order_by_clause )? )
			// fatworm/parser/Fatworm.g:125:5: from_clause ( where_clause )? ( group_by_clause )? ( having_clause )? ( order_by_clause )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_from_clause_in_select_suffix941);
			from_clause133=from_clause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, from_clause133.getTree());

			// fatworm/parser/Fatworm.g:125:17: ( where_clause )?
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==WHERE) ) {
				alt24=1;
			}
			switch (alt24) {
				case 1 :
					// fatworm/parser/Fatworm.g:125:17: where_clause
					{
					pushFollow(FOLLOW_where_clause_in_select_suffix943);
					where_clause134=where_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, where_clause134.getTree());

					}
					break;

			}

			// fatworm/parser/Fatworm.g:125:31: ( group_by_clause )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==GROUP) ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// fatworm/parser/Fatworm.g:125:31: group_by_clause
					{
					pushFollow(FOLLOW_group_by_clause_in_select_suffix946);
					group_by_clause135=group_by_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, group_by_clause135.getTree());

					}
					break;

			}

			// fatworm/parser/Fatworm.g:125:48: ( having_clause )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==HAVING) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// fatworm/parser/Fatworm.g:125:48: having_clause
					{
					pushFollow(FOLLOW_having_clause_in_select_suffix949);
					having_clause136=having_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, having_clause136.getTree());

					}
					break;

			}

			// fatworm/parser/Fatworm.g:125:63: ( order_by_clause )?
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==ORDER) ) {
				alt27=1;
			}
			switch (alt27) {
				case 1 :
					// fatworm/parser/Fatworm.g:125:63: order_by_clause
					{
					pushFollow(FOLLOW_order_by_clause_in_select_suffix952);
					order_by_clause137=order_by_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, order_by_clause137.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 14, select_suffix_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "select_suffix"


	public static class from_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "from_clause"
	// fatworm/parser/Fatworm.g:128:1: from_clause : FROM ^ tbl_ref ( ',' ! tbl_ref )* ;
	public final FatwormParser.from_clause_return from_clause() throws RecognitionException {
		FatwormParser.from_clause_return retval = new FatwormParser.from_clause_return();
		retval.start = input.LT(1);
		int from_clause_StartIndex = input.index();

		Object root_0 = null;

		Token FROM138=null;
		Token char_literal140=null;
		ParserRuleReturnScope tbl_ref139 =null;
		ParserRuleReturnScope tbl_ref141 =null;

		Object FROM138_tree=null;
		Object char_literal140_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

			// fatworm/parser/Fatworm.g:129:3: ( FROM ^ tbl_ref ( ',' ! tbl_ref )* )
			// fatworm/parser/Fatworm.g:129:5: FROM ^ tbl_ref ( ',' ! tbl_ref )*
			{
			root_0 = (Object)adaptor.nil();


			FROM138=(Token)match(input,FROM,FOLLOW_FROM_in_from_clause966); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FROM138_tree = (Object)adaptor.create(FROM138);
			root_0 = (Object)adaptor.becomeRoot(FROM138_tree, root_0);
			}

			pushFollow(FOLLOW_tbl_ref_in_from_clause969);
			tbl_ref139=tbl_ref();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, tbl_ref139.getTree());

			// fatworm/parser/Fatworm.g:129:19: ( ',' ! tbl_ref )*
			loop28:
			while (true) {
				int alt28=2;
				int LA28_0 = input.LA(1);
				if ( (LA28_0==110) ) {
					alt28=1;
				}

				switch (alt28) {
				case 1 :
					// fatworm/parser/Fatworm.g:129:20: ',' ! tbl_ref
					{
					char_literal140=(Token)match(input,110,FOLLOW_110_in_from_clause972); if (state.failed) return retval;
					pushFollow(FOLLOW_tbl_ref_in_from_clause975);
					tbl_ref141=tbl_ref();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, tbl_ref141.getTree());

					}
					break;

				default :
					break loop28;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 15, from_clause_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "from_clause"


	public static class where_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "where_clause"
	// fatworm/parser/Fatworm.g:132:1: where_clause : WHERE ^ where_condition ;
	public final FatwormParser.where_clause_return where_clause() throws RecognitionException {
		FatwormParser.where_clause_return retval = new FatwormParser.where_clause_return();
		retval.start = input.LT(1);
		int where_clause_StartIndex = input.index();

		Object root_0 = null;

		Token WHERE142=null;
		ParserRuleReturnScope where_condition143 =null;

		Object WHERE142_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

			// fatworm/parser/Fatworm.g:133:3: ( WHERE ^ where_condition )
			// fatworm/parser/Fatworm.g:133:5: WHERE ^ where_condition
			{
			root_0 = (Object)adaptor.nil();


			WHERE142=(Token)match(input,WHERE,FOLLOW_WHERE_in_where_clause990); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WHERE142_tree = (Object)adaptor.create(WHERE142);
			root_0 = (Object)adaptor.becomeRoot(WHERE142_tree, root_0);
			}

			pushFollow(FOLLOW_where_condition_in_where_clause993);
			where_condition143=where_condition();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, where_condition143.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 16, where_clause_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "where_clause"


	public static class group_by_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "group_by_clause"
	// fatworm/parser/Fatworm.g:136:1: group_by_clause : GROUP ^ BY ! col_name ;
	public final FatwormParser.group_by_clause_return group_by_clause() throws RecognitionException {
		FatwormParser.group_by_clause_return retval = new FatwormParser.group_by_clause_return();
		retval.start = input.LT(1);
		int group_by_clause_StartIndex = input.index();

		Object root_0 = null;

		Token GROUP144=null;
		Token BY145=null;
		ParserRuleReturnScope col_name146 =null;

		Object GROUP144_tree=null;
		Object BY145_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

			// fatworm/parser/Fatworm.g:137:3: ( GROUP ^ BY ! col_name )
			// fatworm/parser/Fatworm.g:137:5: GROUP ^ BY ! col_name
			{
			root_0 = (Object)adaptor.nil();


			GROUP144=(Token)match(input,GROUP,FOLLOW_GROUP_in_group_by_clause1006); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GROUP144_tree = (Object)adaptor.create(GROUP144);
			root_0 = (Object)adaptor.becomeRoot(GROUP144_tree, root_0);
			}

			BY145=(Token)match(input,BY,FOLLOW_BY_in_group_by_clause1009); if (state.failed) return retval;
			pushFollow(FOLLOW_col_name_in_group_by_clause1012);
			col_name146=col_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, col_name146.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 17, group_by_clause_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "group_by_clause"


	public static class having_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "having_clause"
	// fatworm/parser/Fatworm.g:140:1: having_clause : HAVING ^ having_condition ;
	public final FatwormParser.having_clause_return having_clause() throws RecognitionException {
		FatwormParser.having_clause_return retval = new FatwormParser.having_clause_return();
		retval.start = input.LT(1);
		int having_clause_StartIndex = input.index();

		Object root_0 = null;

		Token HAVING147=null;
		ParserRuleReturnScope having_condition148 =null;

		Object HAVING147_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

			// fatworm/parser/Fatworm.g:141:3: ( HAVING ^ having_condition )
			// fatworm/parser/Fatworm.g:141:5: HAVING ^ having_condition
			{
			root_0 = (Object)adaptor.nil();


			HAVING147=(Token)match(input,HAVING,FOLLOW_HAVING_in_having_clause1025); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			HAVING147_tree = (Object)adaptor.create(HAVING147);
			root_0 = (Object)adaptor.becomeRoot(HAVING147_tree, root_0);
			}

			pushFollow(FOLLOW_having_condition_in_having_clause1028);
			having_condition148=having_condition();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, having_condition148.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 18, having_clause_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "having_clause"


	public static class order_by_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "order_by_clause"
	// fatworm/parser/Fatworm.g:144:1: order_by_clause : ORDER ^ BY ! order_by_col_name ( ',' ! order_by_col_name )* ;
	public final FatwormParser.order_by_clause_return order_by_clause() throws RecognitionException {
		FatwormParser.order_by_clause_return retval = new FatwormParser.order_by_clause_return();
		retval.start = input.LT(1);
		int order_by_clause_StartIndex = input.index();

		Object root_0 = null;

		Token ORDER149=null;
		Token BY150=null;
		Token char_literal152=null;
		ParserRuleReturnScope order_by_col_name151 =null;
		ParserRuleReturnScope order_by_col_name153 =null;

		Object ORDER149_tree=null;
		Object BY150_tree=null;
		Object char_literal152_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

			// fatworm/parser/Fatworm.g:145:3: ( ORDER ^ BY ! order_by_col_name ( ',' ! order_by_col_name )* )
			// fatworm/parser/Fatworm.g:145:5: ORDER ^ BY ! order_by_col_name ( ',' ! order_by_col_name )*
			{
			root_0 = (Object)adaptor.nil();


			ORDER149=(Token)match(input,ORDER,FOLLOW_ORDER_in_order_by_clause1041); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ORDER149_tree = (Object)adaptor.create(ORDER149);
			root_0 = (Object)adaptor.becomeRoot(ORDER149_tree, root_0);
			}

			BY150=(Token)match(input,BY,FOLLOW_BY_in_order_by_clause1044); if (state.failed) return retval;
			pushFollow(FOLLOW_order_by_col_name_in_order_by_clause1047);
			order_by_col_name151=order_by_col_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, order_by_col_name151.getTree());

			// fatworm/parser/Fatworm.g:145:34: ( ',' ! order_by_col_name )*
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0==110) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// fatworm/parser/Fatworm.g:145:35: ',' ! order_by_col_name
					{
					char_literal152=(Token)match(input,110,FOLLOW_110_in_order_by_clause1050); if (state.failed) return retval;
					pushFollow(FOLLOW_order_by_col_name_in_order_by_clause1053);
					order_by_col_name153=order_by_col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, order_by_col_name153.getTree());

					}
					break;

				default :
					break loop29;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 19, order_by_clause_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "order_by_clause"


	public static class order_by_col_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "order_by_col_name"
	// fatworm/parser/Fatworm.g:148:1: order_by_col_name : ( col_name ASC ^| col_name DESC ^| col_name );
	public final FatwormParser.order_by_col_name_return order_by_col_name() throws RecognitionException {
		FatwormParser.order_by_col_name_return retval = new FatwormParser.order_by_col_name_return();
		retval.start = input.LT(1);
		int order_by_col_name_StartIndex = input.index();

		Object root_0 = null;

		Token ASC155=null;
		Token DESC157=null;
		ParserRuleReturnScope col_name154 =null;
		ParserRuleReturnScope col_name156 =null;
		ParserRuleReturnScope col_name158 =null;

		Object ASC155_tree=null;
		Object DESC157_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

			// fatworm/parser/Fatworm.g:149:3: ( col_name ASC ^| col_name DESC ^| col_name )
			int alt30=3;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==ID) ) {
				int LA30_1 = input.LA(2);
				if ( (synpred45_Fatworm()) ) {
					alt30=1;
				}
				else if ( (synpred46_Fatworm()) ) {
					alt30=2;
				}
				else if ( (true) ) {
					alt30=3;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}

			switch (alt30) {
				case 1 :
					// fatworm/parser/Fatworm.g:149:5: col_name ASC ^
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_col_name_in_order_by_col_name1068);
					col_name154=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, col_name154.getTree());

					ASC155=(Token)match(input,ASC,FOLLOW_ASC_in_order_by_col_name1070); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASC155_tree = (Object)adaptor.create(ASC155);
					root_0 = (Object)adaptor.becomeRoot(ASC155_tree, root_0);
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:150:5: col_name DESC ^
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_col_name_in_order_by_col_name1077);
					col_name156=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, col_name156.getTree());

					DESC157=(Token)match(input,DESC,FOLLOW_DESC_in_order_by_col_name1079); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DESC157_tree = (Object)adaptor.create(DESC157);
					root_0 = (Object)adaptor.becomeRoot(DESC157_tree, root_0);
					}

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:151:5: col_name
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_col_name_in_order_by_col_name1086);
					col_name158=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, col_name158.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 20, order_by_col_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "order_by_col_name"


	public static class select_expr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "select_expr"
	// fatworm/parser/Fatworm.g:154:1: select_expr : ( value ( AS ^ alias )? | '*' ^);
	public final FatwormParser.select_expr_return select_expr() throws RecognitionException {
		FatwormParser.select_expr_return retval = new FatwormParser.select_expr_return();
		retval.start = input.LT(1);
		int select_expr_StartIndex = input.index();

		Object root_0 = null;

		Token AS160=null;
		Token char_literal162=null;
		ParserRuleReturnScope value159 =null;
		ParserRuleReturnScope alias161 =null;

		Object AS160_tree=null;
		Object char_literal162_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

			// fatworm/parser/Fatworm.g:155:3: ( value ( AS ^ alias )? | '*' ^)
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==AVG||LA32_0==COUNT||LA32_0==DEFAULT||LA32_0==FALSE||LA32_0==FLOAT_LITERAL||LA32_0==ID||LA32_0==INTEGER_LITERAL||(LA32_0 >= MAX && LA32_0 <= MIN)||LA32_0==NULL||(LA32_0 >= STRING_LITERAL && LA32_0 <= SUM)||LA32_0==TRUE||LA32_0==106||LA32_0==111) ) {
				alt32=1;
			}
			else if ( (LA32_0==108) ) {
				alt32=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}

			switch (alt32) {
				case 1 :
					// fatworm/parser/Fatworm.g:155:5: value ( AS ^ alias )?
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_value_in_select_expr1099);
					value159=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value159.getTree());

					// fatworm/parser/Fatworm.g:155:11: ( AS ^ alias )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==AS) ) {
						alt31=1;
					}
					switch (alt31) {
						case 1 :
							// fatworm/parser/Fatworm.g:155:12: AS ^ alias
							{
							AS160=(Token)match(input,AS,FOLLOW_AS_in_select_expr1102); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							AS160_tree = (Object)adaptor.create(AS160);
							root_0 = (Object)adaptor.becomeRoot(AS160_tree, root_0);
							}

							pushFollow(FOLLOW_alias_in_select_expr1105);
							alias161=alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, alias161.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:156:5: '*' ^
					{
					root_0 = (Object)adaptor.nil();


					char_literal162=(Token)match(input,108,FOLLOW_108_in_select_expr1113); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal162_tree = (Object)adaptor.create(char_literal162);
					root_0 = (Object)adaptor.becomeRoot(char_literal162_tree, root_0);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 21, select_expr_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "select_expr"


	public static class func_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "func"
	// fatworm/parser/Fatworm.g:159:1: func : ( AVG | COUNT | MIN | MAX | SUM );
	public final FatwormParser.func_return func() throws RecognitionException {
		FatwormParser.func_return retval = new FatwormParser.func_return();
		retval.start = input.LT(1);
		int func_StartIndex = input.index();

		Object root_0 = null;

		Token set163=null;

		Object set163_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

			// fatworm/parser/Fatworm.g:160:3: ( AVG | COUNT | MIN | MAX | SUM )
			// fatworm/parser/Fatworm.g:
			{
			root_0 = (Object)adaptor.nil();


			set163=input.LT(1);
			if ( input.LA(1)==AVG||input.LA(1)==COUNT||(input.LA(1) >= MAX && input.LA(1) <= MIN)||input.LA(1)==SUM ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set163));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 22, func_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "func"


	public static class col_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "col_name"
	// fatworm/parser/Fatworm.g:163:1: col_name : ( tbl_name '.' ^)? name ;
	public final FatwormParser.col_name_return col_name() throws RecognitionException {
		FatwormParser.col_name_return retval = new FatwormParser.col_name_return();
		retval.start = input.LT(1);
		int col_name_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal165=null;
		ParserRuleReturnScope tbl_name164 =null;
		ParserRuleReturnScope name166 =null;

		Object char_literal165_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

			// fatworm/parser/Fatworm.g:164:3: ( ( tbl_name '.' ^)? name )
			// fatworm/parser/Fatworm.g:164:5: ( tbl_name '.' ^)? name
			{
			root_0 = (Object)adaptor.nil();


			// fatworm/parser/Fatworm.g:164:5: ( tbl_name '.' ^)?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==ID) ) {
				int LA33_1 = input.LA(2);
				if ( (synpred53_Fatworm()) ) {
					alt33=1;
				}
			}
			switch (alt33) {
				case 1 :
					// fatworm/parser/Fatworm.g:164:6: tbl_name '.' ^
					{
					pushFollow(FOLLOW_tbl_name_in_col_name1157);
					tbl_name164=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, tbl_name164.getTree());

					char_literal165=(Token)match(input,112,FOLLOW_112_in_col_name1159); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal165_tree = (Object)adaptor.create(char_literal165);
					root_0 = (Object)adaptor.becomeRoot(char_literal165_tree, root_0);
					}

					}
					break;

			}

			pushFollow(FOLLOW_name_in_col_name1164);
			name166=name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name166.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 23, col_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "col_name"


	public static class tbl_ref_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "tbl_ref"
	// fatworm/parser/Fatworm.g:167:1: tbl_ref : ( tbl_name ( AS ^ alias )? | '(' ! subquery ')' ! AS ^ alias );
	public final FatwormParser.tbl_ref_return tbl_ref() throws RecognitionException {
		FatwormParser.tbl_ref_return retval = new FatwormParser.tbl_ref_return();
		retval.start = input.LT(1);
		int tbl_ref_StartIndex = input.index();

		Object root_0 = null;

		Token AS168=null;
		Token char_literal170=null;
		Token char_literal172=null;
		Token AS173=null;
		ParserRuleReturnScope tbl_name167 =null;
		ParserRuleReturnScope alias169 =null;
		ParserRuleReturnScope subquery171 =null;
		ParserRuleReturnScope alias174 =null;

		Object AS168_tree=null;
		Object char_literal170_tree=null;
		Object char_literal172_tree=null;
		Object AS173_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

			// fatworm/parser/Fatworm.g:168:3: ( tbl_name ( AS ^ alias )? | '(' ! subquery ')' ! AS ^ alias )
			int alt35=2;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==ID) ) {
				alt35=1;
			}
			else if ( (LA35_0==106) ) {
				alt35=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 35, 0, input);
				throw nvae;
			}

			switch (alt35) {
				case 1 :
					// fatworm/parser/Fatworm.g:168:5: tbl_name ( AS ^ alias )?
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_tbl_name_in_tbl_ref1177);
					tbl_name167=tbl_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, tbl_name167.getTree());

					// fatworm/parser/Fatworm.g:168:14: ( AS ^ alias )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==AS) ) {
						alt34=1;
					}
					switch (alt34) {
						case 1 :
							// fatworm/parser/Fatworm.g:168:15: AS ^ alias
							{
							AS168=(Token)match(input,AS,FOLLOW_AS_in_tbl_ref1180); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							AS168_tree = (Object)adaptor.create(AS168);
							root_0 = (Object)adaptor.becomeRoot(AS168_tree, root_0);
							}

							pushFollow(FOLLOW_alias_in_tbl_ref1183);
							alias169=alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, alias169.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:169:5: '(' ! subquery ')' ! AS ^ alias
					{
					root_0 = (Object)adaptor.nil();


					char_literal170=(Token)match(input,106,FOLLOW_106_in_tbl_ref1191); if (state.failed) return retval;
					pushFollow(FOLLOW_subquery_in_tbl_ref1194);
					subquery171=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery171.getTree());

					char_literal172=(Token)match(input,107,FOLLOW_107_in_tbl_ref1196); if (state.failed) return retval;
					AS173=(Token)match(input,AS,FOLLOW_AS_in_tbl_ref1199); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AS173_tree = (Object)adaptor.create(AS173);
					root_0 = (Object)adaptor.becomeRoot(AS173_tree, root_0);
					}

					pushFollow(FOLLOW_alias_in_tbl_ref1202);
					alias174=alias();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, alias174.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 24, tbl_ref_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tbl_ref"


	public static class tbl_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "tbl_name"
	// fatworm/parser/Fatworm.g:172:1: tbl_name : name ;
	public final FatwormParser.tbl_name_return tbl_name() throws RecognitionException {
		FatwormParser.tbl_name_return retval = new FatwormParser.tbl_name_return();
		retval.start = input.LT(1);
		int tbl_name_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope name175 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }

			// fatworm/parser/Fatworm.g:173:3: ( name )
			// fatworm/parser/Fatworm.g:173:5: name
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_name_in_tbl_name1215);
			name175=name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name175.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 25, tbl_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tbl_name"


	public static class alias_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "alias"
	// fatworm/parser/Fatworm.g:176:1: alias : ( name | func );
	public final FatwormParser.alias_return alias() throws RecognitionException {
		FatwormParser.alias_return retval = new FatwormParser.alias_return();
		retval.start = input.LT(1);
		int alias_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope name176 =null;
		ParserRuleReturnScope func177 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }

			// fatworm/parser/Fatworm.g:177:3: ( name | func )
			int alt36=2;
			int LA36_0 = input.LA(1);
			if ( (LA36_0==ID) ) {
				alt36=1;
			}
			else if ( (LA36_0==AVG||LA36_0==COUNT||(LA36_0 >= MAX && LA36_0 <= MIN)||LA36_0==SUM) ) {
				alt36=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}

			switch (alt36) {
				case 1 :
					// fatworm/parser/Fatworm.g:177:5: name
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_name_in_alias1228);
					name176=name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, name176.getTree());

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:178:5: func
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_func_in_alias1234);
					func177=func();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, func177.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 26, alias_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "alias"


	public static class index_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "index_name"
	// fatworm/parser/Fatworm.g:181:1: index_name : name ;
	public final FatwormParser.index_name_return index_name() throws RecognitionException {
		FatwormParser.index_name_return retval = new FatwormParser.index_name_return();
		retval.start = input.LT(1);
		int index_name_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope name178 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }

			// fatworm/parser/Fatworm.g:182:3: ( name )
			// fatworm/parser/Fatworm.g:182:5: name
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_name_in_index_name1247);
			name178=name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name178.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 27, index_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "index_name"


	public static class db_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "db_name"
	// fatworm/parser/Fatworm.g:185:1: db_name : name ;
	public final FatwormParser.db_name_return db_name() throws RecognitionException {
		FatwormParser.db_name_return retval = new FatwormParser.db_name_return();
		retval.start = input.LT(1);
		int db_name_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope name179 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }

			// fatworm/parser/Fatworm.g:186:3: ( name )
			// fatworm/parser/Fatworm.g:186:5: name
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_name_in_db_name1260);
			name179=name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name179.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 28, db_name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "db_name"


	public static class name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "name"
	// fatworm/parser/Fatworm.g:189:1: name : ID ;
	public final FatwormParser.name_return name() throws RecognitionException {
		FatwormParser.name_return retval = new FatwormParser.name_return();
		retval.start = input.LT(1);
		int name_StartIndex = input.index();

		Object root_0 = null;

		Token ID180=null;

		Object ID180_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }

			// fatworm/parser/Fatworm.g:190:3: ( ID )
			// fatworm/parser/Fatworm.g:190:5: ID
			{
			root_0 = (Object)adaptor.nil();


			ID180=(Token)match(input,ID,FOLLOW_ID_in_name1273); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID180_tree = (Object)adaptor.create(ID180);
			adaptor.addChild(root_0, ID180_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 29, name_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "name"


	public static class where_condition_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "where_condition"
	// fatworm/parser/Fatworm.g:193:1: where_condition : bool_expr ;
	public final FatwormParser.where_condition_return where_condition() throws RecognitionException {
		FatwormParser.where_condition_return retval = new FatwormParser.where_condition_return();
		retval.start = input.LT(1);
		int where_condition_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope bool_expr181 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }

			// fatworm/parser/Fatworm.g:194:3: ( bool_expr )
			// fatworm/parser/Fatworm.g:194:5: bool_expr
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_bool_expr_in_where_condition1286);
			bool_expr181=bool_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, bool_expr181.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 30, where_condition_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "where_condition"


	public static class having_condition_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "having_condition"
	// fatworm/parser/Fatworm.g:197:1: having_condition : bool_expr ;
	public final FatwormParser.having_condition_return having_condition() throws RecognitionException {
		FatwormParser.having_condition_return retval = new FatwormParser.having_condition_return();
		retval.start = input.LT(1);
		int having_condition_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope bool_expr182 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }

			// fatworm/parser/Fatworm.g:198:3: ( bool_expr )
			// fatworm/parser/Fatworm.g:198:5: bool_expr
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_bool_expr_in_having_condition1299);
			bool_expr182=bool_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, bool_expr182.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 31, having_condition_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "having_condition"


	public static class subquery_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "subquery"
	// fatworm/parser/Fatworm.g:201:1: subquery : select_statement ;
	public final FatwormParser.subquery_return subquery() throws RecognitionException {
		FatwormParser.subquery_return retval = new FatwormParser.subquery_return();
		retval.start = input.LT(1);
		int subquery_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope select_statement183 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }

			// fatworm/parser/Fatworm.g:202:3: ( select_statement )
			// fatworm/parser/Fatworm.g:202:5: select_statement
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_select_statement_in_subquery1312);
			select_statement183=select_statement();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, select_statement183.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 32, subquery_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "subquery"


	public static class bool_expr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "bool_expr"
	// fatworm/parser/Fatworm.g:205:1: bool_expr : logical_or ;
	public final FatwormParser.bool_expr_return bool_expr() throws RecognitionException {
		FatwormParser.bool_expr_return retval = new FatwormParser.bool_expr_return();
		retval.start = input.LT(1);
		int bool_expr_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope logical_or184 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }

			// fatworm/parser/Fatworm.g:206:3: ( logical_or )
			// fatworm/parser/Fatworm.g:206:5: logical_or
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_logical_or_in_bool_expr1325);
			logical_or184=logical_or();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, logical_or184.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 33, bool_expr_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "bool_expr"


	public static class logical_or_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_or"
	// fatworm/parser/Fatworm.g:209:1: logical_or : ( logical_and ( OR ^ logical_and )+ | logical_and );
	public final FatwormParser.logical_or_return logical_or() throws RecognitionException {
		FatwormParser.logical_or_return retval = new FatwormParser.logical_or_return();
		retval.start = input.LT(1);
		int logical_or_StartIndex = input.index();

		Object root_0 = null;

		Token OR186=null;
		ParserRuleReturnScope logical_and185 =null;
		ParserRuleReturnScope logical_and187 =null;
		ParserRuleReturnScope logical_and188 =null;

		Object OR186_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }

			// fatworm/parser/Fatworm.g:210:3: ( logical_and ( OR ^ logical_and )+ | logical_and )
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==AVG||LA38_0==COUNT||LA38_0==DEFAULT||LA38_0==EXISTS||LA38_0==FALSE||LA38_0==FLOAT_LITERAL||LA38_0==ID||LA38_0==INTEGER_LITERAL||(LA38_0 >= MAX && LA38_0 <= MIN)||LA38_0==NOT||LA38_0==NULL||(LA38_0 >= STRING_LITERAL && LA38_0 <= SUM)||LA38_0==TRUE||LA38_0==106||LA38_0==111) ) {
				int LA38_1 = input.LA(2);
				if ( (synpred58_Fatworm()) ) {
					alt38=1;
				}
				else if ( (true) ) {
					alt38=2;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}

			switch (alt38) {
				case 1 :
					// fatworm/parser/Fatworm.g:210:5: logical_and ( OR ^ logical_and )+
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_logical_and_in_logical_or1338);
					logical_and185=logical_and();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, logical_and185.getTree());

					// fatworm/parser/Fatworm.g:210:17: ( OR ^ logical_and )+
					int cnt37=0;
					loop37:
					while (true) {
						int alt37=2;
						int LA37_0 = input.LA(1);
						if ( (LA37_0==OR) ) {
							alt37=1;
						}

						switch (alt37) {
						case 1 :
							// fatworm/parser/Fatworm.g:210:18: OR ^ logical_and
							{
							OR186=(Token)match(input,OR,FOLLOW_OR_in_logical_or1341); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							OR186_tree = (Object)adaptor.create(OR186);
							root_0 = (Object)adaptor.becomeRoot(OR186_tree, root_0);
							}

							pushFollow(FOLLOW_logical_and_in_logical_or1344);
							logical_and187=logical_and();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, logical_and187.getTree());

							}
							break;

						default :
							if ( cnt37 >= 1 ) break loop37;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(37, input);
							throw eee;
						}
						cnt37++;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:211:5: logical_and
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_logical_and_in_logical_or1352);
					logical_and188=logical_and();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, logical_and188.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 34, logical_or_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "logical_or"


	public static class logical_and_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_and"
	// fatworm/parser/Fatworm.g:214:1: logical_and : ( primary ( AND ^ primary )+ | primary );
	public final FatwormParser.logical_and_return logical_and() throws RecognitionException {
		FatwormParser.logical_and_return retval = new FatwormParser.logical_and_return();
		retval.start = input.LT(1);
		int logical_and_StartIndex = input.index();

		Object root_0 = null;

		Token AND190=null;
		ParserRuleReturnScope primary189 =null;
		ParserRuleReturnScope primary191 =null;
		ParserRuleReturnScope primary192 =null;

		Object AND190_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }

			// fatworm/parser/Fatworm.g:215:3: ( primary ( AND ^ primary )+ | primary )
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==AVG||LA40_0==COUNT||LA40_0==DEFAULT||LA40_0==EXISTS||LA40_0==FALSE||LA40_0==FLOAT_LITERAL||LA40_0==ID||LA40_0==INTEGER_LITERAL||(LA40_0 >= MAX && LA40_0 <= MIN)||LA40_0==NOT||LA40_0==NULL||(LA40_0 >= STRING_LITERAL && LA40_0 <= SUM)||LA40_0==TRUE||LA40_0==106||LA40_0==111) ) {
				int LA40_1 = input.LA(2);
				if ( (synpred60_Fatworm()) ) {
					alt40=1;
				}
				else if ( (true) ) {
					alt40=2;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 40, 0, input);
				throw nvae;
			}

			switch (alt40) {
				case 1 :
					// fatworm/parser/Fatworm.g:215:5: primary ( AND ^ primary )+
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_primary_in_logical_and1365);
					primary189=primary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, primary189.getTree());

					// fatworm/parser/Fatworm.g:215:13: ( AND ^ primary )+
					int cnt39=0;
					loop39:
					while (true) {
						int alt39=2;
						int LA39_0 = input.LA(1);
						if ( (LA39_0==AND) ) {
							alt39=1;
						}

						switch (alt39) {
						case 1 :
							// fatworm/parser/Fatworm.g:215:14: AND ^ primary
							{
							AND190=(Token)match(input,AND,FOLLOW_AND_in_logical_and1368); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							AND190_tree = (Object)adaptor.create(AND190);
							root_0 = (Object)adaptor.becomeRoot(AND190_tree, root_0);
							}

							pushFollow(FOLLOW_primary_in_logical_and1371);
							primary191=primary();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, primary191.getTree());

							}
							break;

						default :
							if ( cnt39 >= 1 ) break loop39;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(39, input);
							throw eee;
						}
						cnt39++;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:216:5: primary
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_primary_in_logical_and1379);
					primary192=primary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, primary192.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 35, logical_and_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "logical_and"


	public static class primary_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "primary"
	// fatworm/parser/Fatworm.g:219:1: primary : ( value cop ^ value | NOT EXISTS '(' subquery ')' -> ^( NOT_EXISTS subquery ) | EXISTS ^ '(' ! subquery ')' !| value IN ^ '(' ! subquery ')' !| value cop ANY ^ '(' ! subquery ')' !| value cop ALL ^ '(' ! subquery ')' !| '(' ! bool_expr ')' !| const_value );
	public final FatwormParser.primary_return primary() throws RecognitionException {
		FatwormParser.primary_return retval = new FatwormParser.primary_return();
		retval.start = input.LT(1);
		int primary_StartIndex = input.index();

		Object root_0 = null;

		Token NOT196=null;
		Token EXISTS197=null;
		Token char_literal198=null;
		Token char_literal200=null;
		Token EXISTS201=null;
		Token char_literal202=null;
		Token char_literal204=null;
		Token IN206=null;
		Token char_literal207=null;
		Token char_literal209=null;
		Token ANY212=null;
		Token char_literal213=null;
		Token char_literal215=null;
		Token ALL218=null;
		Token char_literal219=null;
		Token char_literal221=null;
		Token char_literal222=null;
		Token char_literal224=null;
		ParserRuleReturnScope value193 =null;
		ParserRuleReturnScope cop194 =null;
		ParserRuleReturnScope value195 =null;
		ParserRuleReturnScope subquery199 =null;
		ParserRuleReturnScope subquery203 =null;
		ParserRuleReturnScope value205 =null;
		ParserRuleReturnScope subquery208 =null;
		ParserRuleReturnScope value210 =null;
		ParserRuleReturnScope cop211 =null;
		ParserRuleReturnScope subquery214 =null;
		ParserRuleReturnScope value216 =null;
		ParserRuleReturnScope cop217 =null;
		ParserRuleReturnScope subquery220 =null;
		ParserRuleReturnScope bool_expr223 =null;
		ParserRuleReturnScope const_value225 =null;

		Object NOT196_tree=null;
		Object EXISTS197_tree=null;
		Object char_literal198_tree=null;
		Object char_literal200_tree=null;
		Object EXISTS201_tree=null;
		Object char_literal202_tree=null;
		Object char_literal204_tree=null;
		Object IN206_tree=null;
		Object char_literal207_tree=null;
		Object char_literal209_tree=null;
		Object ANY212_tree=null;
		Object char_literal213_tree=null;
		Object char_literal215_tree=null;
		Object ALL218_tree=null;
		Object char_literal219_tree=null;
		Object char_literal221_tree=null;
		Object char_literal222_tree=null;
		Object char_literal224_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleTokenStream stream_EXISTS=new RewriteRuleTokenStream(adaptor,"token EXISTS");
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
		RewriteRuleSubtreeStream stream_subquery=new RewriteRuleSubtreeStream(adaptor,"rule subquery");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }

			// fatworm/parser/Fatworm.g:220:3: ( value cop ^ value | NOT EXISTS '(' subquery ')' -> ^( NOT_EXISTS subquery ) | EXISTS ^ '(' ! subquery ')' !| value IN ^ '(' ! subquery ')' !| value cop ANY ^ '(' ! subquery ')' !| value cop ALL ^ '(' ! subquery ')' !| '(' ! bool_expr ')' !| const_value )
			int alt41=8;
			switch ( input.LA(1) ) {
			case 106:
				{
				int LA41_1 = input.LA(2);
				if ( (synpred61_Fatworm()) ) {
					alt41=1;
				}
				else if ( (synpred64_Fatworm()) ) {
					alt41=4;
				}
				else if ( (synpred65_Fatworm()) ) {
					alt41=5;
				}
				else if ( (synpred66_Fatworm()) ) {
					alt41=6;
				}
				else if ( (synpred67_Fatworm()) ) {
					alt41=7;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 41, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case NOT:
				{
				alt41=2;
				}
				break;
			case EXISTS:
				{
				alt41=3;
				}
				break;
			case DEFAULT:
			case FALSE:
			case FLOAT_LITERAL:
			case INTEGER_LITERAL:
			case NULL:
			case STRING_LITERAL:
			case TRUE:
				{
				int LA41_9 = input.LA(2);
				if ( (synpred61_Fatworm()) ) {
					alt41=1;
				}
				else if ( (synpred64_Fatworm()) ) {
					alt41=4;
				}
				else if ( (synpred65_Fatworm()) ) {
					alt41=5;
				}
				else if ( (synpred66_Fatworm()) ) {
					alt41=6;
				}
				else if ( (true) ) {
					alt41=8;
				}

				}
				break;
			case AVG:
			case COUNT:
			case ID:
			case MAX:
			case MIN:
			case SUM:
			case 111:
				{
				int LA41_11 = input.LA(2);
				if ( (synpred61_Fatworm()) ) {
					alt41=1;
				}
				else if ( (synpred64_Fatworm()) ) {
					alt41=4;
				}
				else if ( (synpred65_Fatworm()) ) {
					alt41=5;
				}
				else if ( (synpred66_Fatworm()) ) {
					alt41=6;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 41, 11, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}
			switch (alt41) {
				case 1 :
					// fatworm/parser/Fatworm.g:220:5: value cop ^ value
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_value_in_primary1392);
					value193=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value193.getTree());

					pushFollow(FOLLOW_cop_in_primary1394);
					cop194=cop();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(cop194.getTree(), root_0);
					pushFollow(FOLLOW_value_in_primary1397);
					value195=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value195.getTree());

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:221:5: NOT EXISTS '(' subquery ')'
					{
					NOT196=(Token)match(input,NOT,FOLLOW_NOT_in_primary1403); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_NOT.add(NOT196);

					EXISTS197=(Token)match(input,EXISTS,FOLLOW_EXISTS_in_primary1405); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_EXISTS.add(EXISTS197);

					char_literal198=(Token)match(input,106,FOLLOW_106_in_primary1407); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_106.add(char_literal198);

					pushFollow(FOLLOW_subquery_in_primary1409);
					subquery199=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_subquery.add(subquery199.getTree());
					char_literal200=(Token)match(input,107,FOLLOW_107_in_primary1411); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_107.add(char_literal200);

					// AST REWRITE
					// elements: subquery
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 222:5: -> ^( NOT_EXISTS subquery )
					{
						// fatworm/parser/Fatworm.g:222:8: ^( NOT_EXISTS subquery )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NOT_EXISTS, "NOT_EXISTS"), root_1);
						adaptor.addChild(root_1, stream_subquery.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:223:5: EXISTS ^ '(' ! subquery ')' !
					{
					root_0 = (Object)adaptor.nil();


					EXISTS201=(Token)match(input,EXISTS,FOLLOW_EXISTS_in_primary1429); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXISTS201_tree = (Object)adaptor.create(EXISTS201);
					root_0 = (Object)adaptor.becomeRoot(EXISTS201_tree, root_0);
					}

					char_literal202=(Token)match(input,106,FOLLOW_106_in_primary1432); if (state.failed) return retval;
					pushFollow(FOLLOW_subquery_in_primary1435);
					subquery203=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery203.getTree());

					char_literal204=(Token)match(input,107,FOLLOW_107_in_primary1437); if (state.failed) return retval;
					}
					break;
				case 4 :
					// fatworm/parser/Fatworm.g:224:5: value IN ^ '(' ! subquery ')' !
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_value_in_primary1444);
					value205=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value205.getTree());

					IN206=(Token)match(input,IN,FOLLOW_IN_in_primary1446); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IN206_tree = (Object)adaptor.create(IN206);
					root_0 = (Object)adaptor.becomeRoot(IN206_tree, root_0);
					}

					char_literal207=(Token)match(input,106,FOLLOW_106_in_primary1449); if (state.failed) return retval;
					pushFollow(FOLLOW_subquery_in_primary1452);
					subquery208=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery208.getTree());

					char_literal209=(Token)match(input,107,FOLLOW_107_in_primary1454); if (state.failed) return retval;
					}
					break;
				case 5 :
					// fatworm/parser/Fatworm.g:225:5: value cop ANY ^ '(' ! subquery ')' !
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_value_in_primary1461);
					value210=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value210.getTree());

					pushFollow(FOLLOW_cop_in_primary1463);
					cop211=cop();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cop211.getTree());

					ANY212=(Token)match(input,ANY,FOLLOW_ANY_in_primary1465); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ANY212_tree = (Object)adaptor.create(ANY212);
					root_0 = (Object)adaptor.becomeRoot(ANY212_tree, root_0);
					}

					char_literal213=(Token)match(input,106,FOLLOW_106_in_primary1468); if (state.failed) return retval;
					pushFollow(FOLLOW_subquery_in_primary1471);
					subquery214=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery214.getTree());

					char_literal215=(Token)match(input,107,FOLLOW_107_in_primary1473); if (state.failed) return retval;
					}
					break;
				case 6 :
					// fatworm/parser/Fatworm.g:226:5: value cop ALL ^ '(' ! subquery ')' !
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_value_in_primary1480);
					value216=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value216.getTree());

					pushFollow(FOLLOW_cop_in_primary1482);
					cop217=cop();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cop217.getTree());

					ALL218=(Token)match(input,ALL,FOLLOW_ALL_in_primary1484); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL218_tree = (Object)adaptor.create(ALL218);
					root_0 = (Object)adaptor.becomeRoot(ALL218_tree, root_0);
					}

					char_literal219=(Token)match(input,106,FOLLOW_106_in_primary1487); if (state.failed) return retval;
					pushFollow(FOLLOW_subquery_in_primary1490);
					subquery220=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery220.getTree());

					char_literal221=(Token)match(input,107,FOLLOW_107_in_primary1492); if (state.failed) return retval;
					}
					break;
				case 7 :
					// fatworm/parser/Fatworm.g:227:5: '(' ! bool_expr ')' !
					{
					root_0 = (Object)adaptor.nil();


					char_literal222=(Token)match(input,106,FOLLOW_106_in_primary1499); if (state.failed) return retval;
					pushFollow(FOLLOW_bool_expr_in_primary1502);
					bool_expr223=bool_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, bool_expr223.getTree());

					char_literal224=(Token)match(input,107,FOLLOW_107_in_primary1504); if (state.failed) return retval;
					}
					break;
				case 8 :
					// fatworm/parser/Fatworm.g:228:5: const_value
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_const_value_in_primary1511);
					const_value225=const_value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, const_value225.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 36, primary_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "primary"


	public static class cop_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cop"
	// fatworm/parser/Fatworm.g:231:1: cop : ( '<' | '>' | '=' | '<=' | '>=' | '<>' );
	public final FatwormParser.cop_return cop() throws RecognitionException {
		FatwormParser.cop_return retval = new FatwormParser.cop_return();
		retval.start = input.LT(1);
		int cop_StartIndex = input.index();

		Object root_0 = null;

		Token set226=null;

		Object set226_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }

			// fatworm/parser/Fatworm.g:232:3: ( '<' | '>' | '=' | '<=' | '>=' | '<>' )
			// fatworm/parser/Fatworm.g:
			{
			root_0 = (Object)adaptor.nil();


			set226=input.LT(1);
			if ( (input.LA(1) >= 114 && input.LA(1) <= 119) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set226));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 37, cop_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "cop"


	public static class value_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "value"
	// fatworm/parser/Fatworm.g:235:1: value : ( multiplicative ( ( '+' | '-' ) ^ multiplicative )+ | multiplicative );
	public final FatwormParser.value_return value() throws RecognitionException {
		FatwormParser.value_return retval = new FatwormParser.value_return();
		retval.start = input.LT(1);
		int value_StartIndex = input.index();

		Object root_0 = null;

		Token set228=null;
		ParserRuleReturnScope multiplicative227 =null;
		ParserRuleReturnScope multiplicative229 =null;
		ParserRuleReturnScope multiplicative230 =null;

		Object set228_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }

			// fatworm/parser/Fatworm.g:236:3: ( multiplicative ( ( '+' | '-' ) ^ multiplicative )+ | multiplicative )
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==AVG||LA43_0==COUNT||LA43_0==DEFAULT||LA43_0==FALSE||LA43_0==FLOAT_LITERAL||LA43_0==ID||LA43_0==INTEGER_LITERAL||(LA43_0 >= MAX && LA43_0 <= MIN)||LA43_0==NULL||(LA43_0 >= STRING_LITERAL && LA43_0 <= SUM)||LA43_0==TRUE||LA43_0==106||LA43_0==111) ) {
				int LA43_1 = input.LA(2);
				if ( (synpred75_Fatworm()) ) {
					alt43=1;
				}
				else if ( (true) ) {
					alt43=2;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// fatworm/parser/Fatworm.g:236:5: multiplicative ( ( '+' | '-' ) ^ multiplicative )+
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_multiplicative_in_value1557);
					multiplicative227=multiplicative();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicative227.getTree());

					// fatworm/parser/Fatworm.g:236:20: ( ( '+' | '-' ) ^ multiplicative )+
					int cnt42=0;
					loop42:
					while (true) {
						int alt42=2;
						int LA42_0 = input.LA(1);
						if ( (LA42_0==109||LA42_0==111) ) {
							alt42=1;
						}

						switch (alt42) {
						case 1 :
							// fatworm/parser/Fatworm.g:236:21: ( '+' | '-' ) ^ multiplicative
							{
							set228=input.LT(1);
							set228=input.LT(1);
							if ( input.LA(1)==109||input.LA(1)==111 ) {
								input.consume();
								if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set228), root_0);
								state.errorRecovery=false;
								state.failed=false;
							}
							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							pushFollow(FOLLOW_multiplicative_in_value1567);
							multiplicative229=multiplicative();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicative229.getTree());

							}
							break;

						default :
							if ( cnt42 >= 1 ) break loop42;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(42, input);
							throw eee;
						}
						cnt42++;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:237:5: multiplicative
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_multiplicative_in_value1575);
					multiplicative230=multiplicative();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicative230.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 38, value_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "value"


	public static class multiplicative_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multiplicative"
	// fatworm/parser/Fatworm.g:240:1: multiplicative : ( atom ( ( '*' | '/' | '%' ) ^ atom )+ | atom );
	public final FatwormParser.multiplicative_return multiplicative() throws RecognitionException {
		FatwormParser.multiplicative_return retval = new FatwormParser.multiplicative_return();
		retval.start = input.LT(1);
		int multiplicative_StartIndex = input.index();

		Object root_0 = null;

		Token set232=null;
		ParserRuleReturnScope atom231 =null;
		ParserRuleReturnScope atom233 =null;
		ParserRuleReturnScope atom234 =null;

		Object set232_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

			// fatworm/parser/Fatworm.g:241:3: ( atom ( ( '*' | '/' | '%' ) ^ atom )+ | atom )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==AVG||LA45_0==COUNT||LA45_0==DEFAULT||LA45_0==FALSE||LA45_0==FLOAT_LITERAL||LA45_0==ID||LA45_0==INTEGER_LITERAL||(LA45_0 >= MAX && LA45_0 <= MIN)||LA45_0==NULL||(LA45_0 >= STRING_LITERAL && LA45_0 <= SUM)||LA45_0==TRUE||LA45_0==106||LA45_0==111) ) {
				int LA45_1 = input.LA(2);
				if ( (synpred79_Fatworm()) ) {
					alt45=1;
				}
				else if ( (true) ) {
					alt45=2;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// fatworm/parser/Fatworm.g:241:5: atom ( ( '*' | '/' | '%' ) ^ atom )+
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_atom_in_multiplicative1588);
					atom231=atom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom231.getTree());

					// fatworm/parser/Fatworm.g:241:10: ( ( '*' | '/' | '%' ) ^ atom )+
					int cnt44=0;
					loop44:
					while (true) {
						int alt44=2;
						int LA44_0 = input.LA(1);
						if ( (LA44_0==105||LA44_0==108||LA44_0==113) ) {
							alt44=1;
						}

						switch (alt44) {
						case 1 :
							// fatworm/parser/Fatworm.g:241:11: ( '*' | '/' | '%' ) ^ atom
							{
							set232=input.LT(1);
							set232=input.LT(1);
							if ( input.LA(1)==105||input.LA(1)==108||input.LA(1)==113 ) {
								input.consume();
								if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set232), root_0);
								state.errorRecovery=false;
								state.failed=false;
							}
							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							pushFollow(FOLLOW_atom_in_multiplicative1600);
							atom233=atom();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, atom233.getTree());

							}
							break;

						default :
							if ( cnt44 >= 1 ) break loop44;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(44, input);
							throw eee;
						}
						cnt44++;
					}

					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:242:5: atom
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_atom_in_multiplicative1608);
					atom234=atom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom234.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 39, multiplicative_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "multiplicative"


	public static class atom_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// fatworm/parser/Fatworm.g:245:1: atom : ( '(' ! value ')' !| col_name | const_value | '(' ! subquery ')' !| func ^ '(' ! col_name ')' !| '-' ^ atom );
	public final FatwormParser.atom_return atom() throws RecognitionException {
		FatwormParser.atom_return retval = new FatwormParser.atom_return();
		retval.start = input.LT(1);
		int atom_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal235=null;
		Token char_literal237=null;
		Token char_literal240=null;
		Token char_literal242=null;
		Token char_literal244=null;
		Token char_literal246=null;
		Token char_literal247=null;
		ParserRuleReturnScope value236 =null;
		ParserRuleReturnScope col_name238 =null;
		ParserRuleReturnScope const_value239 =null;
		ParserRuleReturnScope subquery241 =null;
		ParserRuleReturnScope func243 =null;
		ParserRuleReturnScope col_name245 =null;
		ParserRuleReturnScope atom248 =null;

		Object char_literal235_tree=null;
		Object char_literal237_tree=null;
		Object char_literal240_tree=null;
		Object char_literal242_tree=null;
		Object char_literal244_tree=null;
		Object char_literal246_tree=null;
		Object char_literal247_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }

			// fatworm/parser/Fatworm.g:246:3: ( '(' ! value ')' !| col_name | const_value | '(' ! subquery ')' !| func ^ '(' ! col_name ')' !| '-' ^ atom )
			int alt46=6;
			switch ( input.LA(1) ) {
			case 106:
				{
				int LA46_1 = input.LA(2);
				if ( (synpred80_Fatworm()) ) {
					alt46=1;
				}
				else if ( (synpred83_Fatworm()) ) {
					alt46=4;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 46, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ID:
				{
				alt46=2;
				}
				break;
			case DEFAULT:
			case FALSE:
			case FLOAT_LITERAL:
			case INTEGER_LITERAL:
			case NULL:
			case STRING_LITERAL:
			case TRUE:
				{
				alt46=3;
				}
				break;
			case AVG:
			case COUNT:
			case MAX:
			case MIN:
			case SUM:
				{
				alt46=5;
				}
				break;
			case 111:
				{
				alt46=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}
			switch (alt46) {
				case 1 :
					// fatworm/parser/Fatworm.g:246:5: '(' ! value ')' !
					{
					root_0 = (Object)adaptor.nil();


					char_literal235=(Token)match(input,106,FOLLOW_106_in_atom1621); if (state.failed) return retval;
					pushFollow(FOLLOW_value_in_atom1624);
					value236=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value236.getTree());

					char_literal237=(Token)match(input,107,FOLLOW_107_in_atom1626); if (state.failed) return retval;
					}
					break;
				case 2 :
					// fatworm/parser/Fatworm.g:247:5: col_name
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_col_name_in_atom1633);
					col_name238=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, col_name238.getTree());

					}
					break;
				case 3 :
					// fatworm/parser/Fatworm.g:248:5: const_value
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_const_value_in_atom1639);
					const_value239=const_value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, const_value239.getTree());

					}
					break;
				case 4 :
					// fatworm/parser/Fatworm.g:249:5: '(' ! subquery ')' !
					{
					root_0 = (Object)adaptor.nil();


					char_literal240=(Token)match(input,106,FOLLOW_106_in_atom1645); if (state.failed) return retval;
					pushFollow(FOLLOW_subquery_in_atom1648);
					subquery241=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery241.getTree());

					char_literal242=(Token)match(input,107,FOLLOW_107_in_atom1650); if (state.failed) return retval;
					}
					break;
				case 5 :
					// fatworm/parser/Fatworm.g:250:5: func ^ '(' ! col_name ')' !
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_func_in_atom1657);
					func243=func();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(func243.getTree(), root_0);
					char_literal244=(Token)match(input,106,FOLLOW_106_in_atom1660); if (state.failed) return retval;
					pushFollow(FOLLOW_col_name_in_atom1663);
					col_name245=col_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, col_name245.getTree());

					char_literal246=(Token)match(input,107,FOLLOW_107_in_atom1665); if (state.failed) return retval;
					}
					break;
				case 6 :
					// fatworm/parser/Fatworm.g:251:5: '-' ^ atom
					{
					root_0 = (Object)adaptor.nil();


					char_literal247=(Token)match(input,111,FOLLOW_111_in_atom1672); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal247_tree = (Object)adaptor.create(char_literal247);
					root_0 = (Object)adaptor.becomeRoot(char_literal247_tree, root_0);
					}

					pushFollow(FOLLOW_atom_in_atom1675);
					atom248=atom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom248.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 40, atom_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "atom"


	public static class const_value_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "const_value"
	// fatworm/parser/Fatworm.g:254:1: const_value : ( INTEGER_LITERAL | STRING_LITERAL | FLOAT_LITERAL | TRUE | FALSE | NULL | DEFAULT );
	public final FatwormParser.const_value_return const_value() throws RecognitionException {
		FatwormParser.const_value_return retval = new FatwormParser.const_value_return();
		retval.start = input.LT(1);
		int const_value_StartIndex = input.index();

		Object root_0 = null;

		Token set249=null;

		Object set249_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

			// fatworm/parser/Fatworm.g:255:3: ( INTEGER_LITERAL | STRING_LITERAL | FLOAT_LITERAL | TRUE | FALSE | NULL | DEFAULT )
			// fatworm/parser/Fatworm.g:
			{
			root_0 = (Object)adaptor.nil();


			set249=input.LT(1);
			if ( input.LA(1)==DEFAULT||input.LA(1)==FALSE||input.LA(1)==FLOAT_LITERAL||input.LA(1)==INTEGER_LITERAL||input.LA(1)==NULL||input.LA(1)==STRING_LITERAL||input.LA(1)==TRUE ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set249));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 41, const_value_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "const_value"

	// $ANTLR start synpred1_Fatworm
	public final void synpred1_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:33:5: ( database_statement )
		// fatworm/parser/Fatworm.g:33:5: database_statement
		{
		pushFollow(FOLLOW_database_statement_in_synpred1_Fatworm151);
		database_statement();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_Fatworm

	// $ANTLR start synpred2_Fatworm
	public final void synpred2_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:34:5: ( table_statement )
		// fatworm/parser/Fatworm.g:34:5: table_statement
		{
		pushFollow(FOLLOW_table_statement_in_synpred2_Fatworm157);
		table_statement();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred2_Fatworm

	// $ANTLR start synpred6_Fatworm
	public final void synpred6_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:38:5: ( index_statement )
		// fatworm/parser/Fatworm.g:38:5: index_statement
		{
		pushFollow(FOLLOW_index_statement_in_synpred6_Fatworm181);
		index_statement();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred6_Fatworm

	// $ANTLR start synpred25_Fatworm
	public final void synpred25_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:83:5: ( INSERT INTO tbl_name values_clause )
		// fatworm/parser/Fatworm.g:83:5: INSERT INTO tbl_name values_clause
		{
		match(input,INSERT,FOLLOW_INSERT_in_synpred25_Fatworm535); if (state.failed) return;

		match(input,INTO,FOLLOW_INTO_in_synpred25_Fatworm537); if (state.failed) return;

		pushFollow(FOLLOW_tbl_name_in_synpred25_Fatworm539);
		tbl_name();
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_values_clause_in_synpred25_Fatworm541);
		values_clause();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred25_Fatworm

	// $ANTLR start synpred27_Fatworm
	public final void synpred27_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:85:5: ( INSERT INTO tbl_name '(' col_name ( ',' col_name )* ')' values_clause )
		// fatworm/parser/Fatworm.g:85:5: INSERT INTO tbl_name '(' col_name ( ',' col_name )* ')' values_clause
		{
		match(input,INSERT,FOLLOW_INSERT_in_synpred27_Fatworm561); if (state.failed) return;

		match(input,INTO,FOLLOW_INTO_in_synpred27_Fatworm563); if (state.failed) return;

		pushFollow(FOLLOW_tbl_name_in_synpred27_Fatworm565);
		tbl_name();
		state._fsp--;
		if (state.failed) return;

		match(input,106,FOLLOW_106_in_synpred27_Fatworm567); if (state.failed) return;

		pushFollow(FOLLOW_col_name_in_synpred27_Fatworm569);
		col_name();
		state._fsp--;
		if (state.failed) return;

		// fatworm/parser/Fatworm.g:85:39: ( ',' col_name )*
		loop51:
		while (true) {
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==110) ) {
				alt51=1;
			}

			switch (alt51) {
			case 1 :
				// fatworm/parser/Fatworm.g:85:40: ',' col_name
				{
				match(input,110,FOLLOW_110_in_synpred27_Fatworm572); if (state.failed) return;

				pushFollow(FOLLOW_col_name_in_synpred27_Fatworm574);
				col_name();
				state._fsp--;
				if (state.failed) return;

				}
				break;

			default :
				break loop51;
			}
		}

		match(input,107,FOLLOW_107_in_synpred27_Fatworm578); if (state.failed) return;

		pushFollow(FOLLOW_values_clause_in_synpred27_Fatworm580);
		values_clause();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred27_Fatworm

	// $ANTLR start synpred32_Fatworm
	public final void synpred32_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:109:5: ( CREATE INDEX index_name ON tbl_name '(' col_name ')' )
		// fatworm/parser/Fatworm.g:109:5: CREATE INDEX index_name ON tbl_name '(' col_name ')'
		{
		match(input,CREATE,FOLLOW_CREATE_in_synpred32_Fatworm755); if (state.failed) return;

		match(input,INDEX,FOLLOW_INDEX_in_synpred32_Fatworm757); if (state.failed) return;

		pushFollow(FOLLOW_index_name_in_synpred32_Fatworm759);
		index_name();
		state._fsp--;
		if (state.failed) return;

		match(input,ON,FOLLOW_ON_in_synpred32_Fatworm761); if (state.failed) return;

		pushFollow(FOLLOW_tbl_name_in_synpred32_Fatworm763);
		tbl_name();
		state._fsp--;
		if (state.failed) return;

		match(input,106,FOLLOW_106_in_synpred32_Fatworm765); if (state.failed) return;

		pushFollow(FOLLOW_col_name_in_synpred32_Fatworm767);
		col_name();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred32_Fatworm769); if (state.failed) return;

		}

	}
	// $ANTLR end synpred32_Fatworm

	// $ANTLR start synpred33_Fatworm
	public final void synpred33_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:111:5: ( CREATE UNIQUE INDEX index_name ON tbl_name '(' col_name ')' )
		// fatworm/parser/Fatworm.g:111:5: CREATE UNIQUE INDEX index_name ON tbl_name '(' col_name ')'
		{
		match(input,CREATE,FOLLOW_CREATE_in_synpred33_Fatworm791); if (state.failed) return;

		match(input,UNIQUE,FOLLOW_UNIQUE_in_synpred33_Fatworm793); if (state.failed) return;

		match(input,INDEX,FOLLOW_INDEX_in_synpred33_Fatworm795); if (state.failed) return;

		pushFollow(FOLLOW_index_name_in_synpred33_Fatworm797);
		index_name();
		state._fsp--;
		if (state.failed) return;

		match(input,ON,FOLLOW_ON_in_synpred33_Fatworm799); if (state.failed) return;

		pushFollow(FOLLOW_tbl_name_in_synpred33_Fatworm801);
		tbl_name();
		state._fsp--;
		if (state.failed) return;

		match(input,106,FOLLOW_106_in_synpred33_Fatworm803); if (state.failed) return;

		pushFollow(FOLLOW_col_name_in_synpred33_Fatworm805);
		col_name();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred33_Fatworm807); if (state.failed) return;

		}

	}
	// $ANTLR end synpred33_Fatworm

	// $ANTLR start synpred36_Fatworm
	public final void synpred36_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:118:5: ( SELECT select_expr ( ',' select_expr )* ( select_suffix )? )
		// fatworm/parser/Fatworm.g:118:5: SELECT select_expr ( ',' select_expr )* ( select_suffix )?
		{
		match(input,SELECT,FOLLOW_SELECT_in_synpred36_Fatworm864); if (state.failed) return;

		pushFollow(FOLLOW_select_expr_in_synpred36_Fatworm866);
		select_expr();
		state._fsp--;
		if (state.failed) return;

		// fatworm/parser/Fatworm.g:118:24: ( ',' select_expr )*
		loop52:
		while (true) {
			int alt52=2;
			int LA52_0 = input.LA(1);
			if ( (LA52_0==110) ) {
				alt52=1;
			}

			switch (alt52) {
			case 1 :
				// fatworm/parser/Fatworm.g:118:25: ',' select_expr
				{
				match(input,110,FOLLOW_110_in_synpred36_Fatworm869); if (state.failed) return;

				pushFollow(FOLLOW_select_expr_in_synpred36_Fatworm871);
				select_expr();
				state._fsp--;
				if (state.failed) return;

				}
				break;

			default :
				break loop52;
			}
		}

		// fatworm/parser/Fatworm.g:118:43: ( select_suffix )?
		int alt53=2;
		int LA53_0 = input.LA(1);
		if ( (LA53_0==FROM) ) {
			alt53=1;
		}
		switch (alt53) {
			case 1 :
				// fatworm/parser/Fatworm.g:118:43: select_suffix
				{
				pushFollow(FOLLOW_select_suffix_in_synpred36_Fatworm875);
				select_suffix();
				state._fsp--;
				if (state.failed) return;

				}
				break;

		}

		}

	}
	// $ANTLR end synpred36_Fatworm

	// $ANTLR start synpred45_Fatworm
	public final void synpred45_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:149:5: ( col_name ASC )
		// fatworm/parser/Fatworm.g:149:5: col_name ASC
		{
		pushFollow(FOLLOW_col_name_in_synpred45_Fatworm1068);
		col_name();
		state._fsp--;
		if (state.failed) return;

		match(input,ASC,FOLLOW_ASC_in_synpred45_Fatworm1070); if (state.failed) return;

		}

	}
	// $ANTLR end synpred45_Fatworm

	// $ANTLR start synpred46_Fatworm
	public final void synpred46_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:150:5: ( col_name DESC )
		// fatworm/parser/Fatworm.g:150:5: col_name DESC
		{
		pushFollow(FOLLOW_col_name_in_synpred46_Fatworm1077);
		col_name();
		state._fsp--;
		if (state.failed) return;

		match(input,DESC,FOLLOW_DESC_in_synpred46_Fatworm1079); if (state.failed) return;

		}

	}
	// $ANTLR end synpred46_Fatworm

	// $ANTLR start synpred53_Fatworm
	public final void synpred53_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:164:6: ( tbl_name '.' )
		// fatworm/parser/Fatworm.g:164:6: tbl_name '.'
		{
		pushFollow(FOLLOW_tbl_name_in_synpred53_Fatworm1157);
		tbl_name();
		state._fsp--;
		if (state.failed) return;

		match(input,112,FOLLOW_112_in_synpred53_Fatworm1159); if (state.failed) return;

		}

	}
	// $ANTLR end synpred53_Fatworm

	// $ANTLR start synpred58_Fatworm
	public final void synpred58_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:210:5: ( logical_and ( OR logical_and )+ )
		// fatworm/parser/Fatworm.g:210:5: logical_and ( OR logical_and )+
		{
		pushFollow(FOLLOW_logical_and_in_synpred58_Fatworm1338);
		logical_and();
		state._fsp--;
		if (state.failed) return;

		// fatworm/parser/Fatworm.g:210:17: ( OR logical_and )+
		int cnt56=0;
		loop56:
		while (true) {
			int alt56=2;
			int LA56_0 = input.LA(1);
			if ( (LA56_0==OR) ) {
				alt56=1;
			}

			switch (alt56) {
			case 1 :
				// fatworm/parser/Fatworm.g:210:18: OR logical_and
				{
				match(input,OR,FOLLOW_OR_in_synpred58_Fatworm1341); if (state.failed) return;

				pushFollow(FOLLOW_logical_and_in_synpred58_Fatworm1344);
				logical_and();
				state._fsp--;
				if (state.failed) return;

				}
				break;

			default :
				if ( cnt56 >= 1 ) break loop56;
				if (state.backtracking>0) {state.failed=true; return;}
				EarlyExitException eee = new EarlyExitException(56, input);
				throw eee;
			}
			cnt56++;
		}

		}

	}
	// $ANTLR end synpred58_Fatworm

	// $ANTLR start synpred60_Fatworm
	public final void synpred60_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:215:5: ( primary ( AND primary )+ )
		// fatworm/parser/Fatworm.g:215:5: primary ( AND primary )+
		{
		pushFollow(FOLLOW_primary_in_synpred60_Fatworm1365);
		primary();
		state._fsp--;
		if (state.failed) return;

		// fatworm/parser/Fatworm.g:215:13: ( AND primary )+
		int cnt57=0;
		loop57:
		while (true) {
			int alt57=2;
			int LA57_0 = input.LA(1);
			if ( (LA57_0==AND) ) {
				alt57=1;
			}

			switch (alt57) {
			case 1 :
				// fatworm/parser/Fatworm.g:215:14: AND primary
				{
				match(input,AND,FOLLOW_AND_in_synpred60_Fatworm1368); if (state.failed) return;

				pushFollow(FOLLOW_primary_in_synpred60_Fatworm1371);
				primary();
				state._fsp--;
				if (state.failed) return;

				}
				break;

			default :
				if ( cnt57 >= 1 ) break loop57;
				if (state.backtracking>0) {state.failed=true; return;}
				EarlyExitException eee = new EarlyExitException(57, input);
				throw eee;
			}
			cnt57++;
		}

		}

	}
	// $ANTLR end synpred60_Fatworm

	// $ANTLR start synpred61_Fatworm
	public final void synpred61_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:220:5: ( value cop value )
		// fatworm/parser/Fatworm.g:220:5: value cop value
		{
		pushFollow(FOLLOW_value_in_synpred61_Fatworm1392);
		value();
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_cop_in_synpred61_Fatworm1394);
		cop();
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_value_in_synpred61_Fatworm1397);
		value();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred61_Fatworm

	// $ANTLR start synpred64_Fatworm
	public final void synpred64_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:224:5: ( value IN '(' subquery ')' )
		// fatworm/parser/Fatworm.g:224:5: value IN '(' subquery ')'
		{
		pushFollow(FOLLOW_value_in_synpred64_Fatworm1444);
		value();
		state._fsp--;
		if (state.failed) return;

		match(input,IN,FOLLOW_IN_in_synpred64_Fatworm1446); if (state.failed) return;

		match(input,106,FOLLOW_106_in_synpred64_Fatworm1449); if (state.failed) return;

		pushFollow(FOLLOW_subquery_in_synpred64_Fatworm1452);
		subquery();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred64_Fatworm1454); if (state.failed) return;

		}

	}
	// $ANTLR end synpred64_Fatworm

	// $ANTLR start synpred65_Fatworm
	public final void synpred65_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:225:5: ( value cop ANY '(' subquery ')' )
		// fatworm/parser/Fatworm.g:225:5: value cop ANY '(' subquery ')'
		{
		pushFollow(FOLLOW_value_in_synpred65_Fatworm1461);
		value();
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_cop_in_synpred65_Fatworm1463);
		cop();
		state._fsp--;
		if (state.failed) return;

		match(input,ANY,FOLLOW_ANY_in_synpred65_Fatworm1465); if (state.failed) return;

		match(input,106,FOLLOW_106_in_synpred65_Fatworm1468); if (state.failed) return;

		pushFollow(FOLLOW_subquery_in_synpred65_Fatworm1471);
		subquery();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred65_Fatworm1473); if (state.failed) return;

		}

	}
	// $ANTLR end synpred65_Fatworm

	// $ANTLR start synpred66_Fatworm
	public final void synpred66_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:226:5: ( value cop ALL '(' subquery ')' )
		// fatworm/parser/Fatworm.g:226:5: value cop ALL '(' subquery ')'
		{
		pushFollow(FOLLOW_value_in_synpred66_Fatworm1480);
		value();
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_cop_in_synpred66_Fatworm1482);
		cop();
		state._fsp--;
		if (state.failed) return;

		match(input,ALL,FOLLOW_ALL_in_synpred66_Fatworm1484); if (state.failed) return;

		match(input,106,FOLLOW_106_in_synpred66_Fatworm1487); if (state.failed) return;

		pushFollow(FOLLOW_subquery_in_synpred66_Fatworm1490);
		subquery();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred66_Fatworm1492); if (state.failed) return;

		}

	}
	// $ANTLR end synpred66_Fatworm

	// $ANTLR start synpred67_Fatworm
	public final void synpred67_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:227:5: ( '(' bool_expr ')' )
		// fatworm/parser/Fatworm.g:227:5: '(' bool_expr ')'
		{
		match(input,106,FOLLOW_106_in_synpred67_Fatworm1499); if (state.failed) return;

		pushFollow(FOLLOW_bool_expr_in_synpred67_Fatworm1502);
		bool_expr();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred67_Fatworm1504); if (state.failed) return;

		}

	}
	// $ANTLR end synpred67_Fatworm

	// $ANTLR start synpred75_Fatworm
	public final void synpred75_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:236:5: ( multiplicative ( ( '+' | '-' ) multiplicative )+ )
		// fatworm/parser/Fatworm.g:236:5: multiplicative ( ( '+' | '-' ) multiplicative )+
		{
		pushFollow(FOLLOW_multiplicative_in_synpred75_Fatworm1557);
		multiplicative();
		state._fsp--;
		if (state.failed) return;

		// fatworm/parser/Fatworm.g:236:20: ( ( '+' | '-' ) multiplicative )+
		int cnt58=0;
		loop58:
		while (true) {
			int alt58=2;
			int LA58_0 = input.LA(1);
			if ( (LA58_0==109||LA58_0==111) ) {
				alt58=1;
			}

			switch (alt58) {
			case 1 :
				// fatworm/parser/Fatworm.g:236:21: ( '+' | '-' ) multiplicative
				{
				if ( input.LA(1)==109||input.LA(1)==111 ) {
					input.consume();
					state.errorRecovery=false;
					state.failed=false;
				}
				else {
					if (state.backtracking>0) {state.failed=true; return;}
					MismatchedSetException mse = new MismatchedSetException(null,input);
					throw mse;
				}
				pushFollow(FOLLOW_multiplicative_in_synpred75_Fatworm1567);
				multiplicative();
				state._fsp--;
				if (state.failed) return;

				}
				break;

			default :
				if ( cnt58 >= 1 ) break loop58;
				if (state.backtracking>0) {state.failed=true; return;}
				EarlyExitException eee = new EarlyExitException(58, input);
				throw eee;
			}
			cnt58++;
		}

		}

	}
	// $ANTLR end synpred75_Fatworm

	// $ANTLR start synpred79_Fatworm
	public final void synpred79_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:241:5: ( atom ( ( '*' | '/' | '%' ) atom )+ )
		// fatworm/parser/Fatworm.g:241:5: atom ( ( '*' | '/' | '%' ) atom )+
		{
		pushFollow(FOLLOW_atom_in_synpred79_Fatworm1588);
		atom();
		state._fsp--;
		if (state.failed) return;

		// fatworm/parser/Fatworm.g:241:10: ( ( '*' | '/' | '%' ) atom )+
		int cnt59=0;
		loop59:
		while (true) {
			int alt59=2;
			int LA59_0 = input.LA(1);
			if ( (LA59_0==105||LA59_0==108||LA59_0==113) ) {
				alt59=1;
			}

			switch (alt59) {
			case 1 :
				// fatworm/parser/Fatworm.g:241:11: ( '*' | '/' | '%' ) atom
				{
				if ( input.LA(1)==105||input.LA(1)==108||input.LA(1)==113 ) {
					input.consume();
					state.errorRecovery=false;
					state.failed=false;
				}
				else {
					if (state.backtracking>0) {state.failed=true; return;}
					MismatchedSetException mse = new MismatchedSetException(null,input);
					throw mse;
				}
				pushFollow(FOLLOW_atom_in_synpred79_Fatworm1600);
				atom();
				state._fsp--;
				if (state.failed) return;

				}
				break;

			default :
				if ( cnt59 >= 1 ) break loop59;
				if (state.backtracking>0) {state.failed=true; return;}
				EarlyExitException eee = new EarlyExitException(59, input);
				throw eee;
			}
			cnt59++;
		}

		}

	}
	// $ANTLR end synpred79_Fatworm

	// $ANTLR start synpred80_Fatworm
	public final void synpred80_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:246:5: ( '(' value ')' )
		// fatworm/parser/Fatworm.g:246:5: '(' value ')'
		{
		match(input,106,FOLLOW_106_in_synpred80_Fatworm1621); if (state.failed) return;

		pushFollow(FOLLOW_value_in_synpred80_Fatworm1624);
		value();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred80_Fatworm1626); if (state.failed) return;

		}

	}
	// $ANTLR end synpred80_Fatworm

	// $ANTLR start synpred83_Fatworm
	public final void synpred83_Fatworm_fragment() throws RecognitionException {
		// fatworm/parser/Fatworm.g:249:5: ( '(' subquery ')' )
		// fatworm/parser/Fatworm.g:249:5: '(' subquery ')'
		{
		match(input,106,FOLLOW_106_in_synpred83_Fatworm1645); if (state.failed) return;

		pushFollow(FOLLOW_subquery_in_synpred83_Fatworm1648);
		subquery();
		state._fsp--;
		if (state.failed) return;

		match(input,107,FOLLOW_107_in_synpred83_Fatworm1650); if (state.failed) return;

		}

	}
	// $ANTLR end synpred83_Fatworm

	// Delegated rules

	public final boolean synpred66_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred66_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred1_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred65_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred65_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred80_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred80_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred6_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred6_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred61_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred61_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred46_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred46_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred53_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred53_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred32_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred32_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred75_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred75_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred67_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred67_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred60_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred60_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred58_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred58_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred25_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred25_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred2_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred2_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred33_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred33_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred79_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred79_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred64_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred64_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred45_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred45_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred36_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred36_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred83_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred83_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred27_Fatworm() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred27_Fatworm_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_database_statement_in_statement151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_statement_in_statement157 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insert_statement_in_statement163 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_statement_in_statement169 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_update_statement_in_statement175 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_index_statement_in_statement181 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_statement_in_statement187 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_database_statement200 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_DATABASE_in_database_statement202 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_db_name_in_database_statement204 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_USE_in_database_statement222 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_db_name_in_database_statement224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DROP_in_database_statement242 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_DATABASE_in_database_statement244 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_db_name_in_database_statement246 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_table_statement271 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_TABLE_in_table_statement273 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_table_statement275 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_table_statement277 = new BitSet(new long[]{0x0002000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_create_definition_in_table_statement279 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_110_in_table_statement282 = new BitSet(new long[]{0x0002000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_create_definition_in_table_statement284 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_107_in_table_statement288 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DROP_in_table_statement309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_TABLE_in_table_statement311 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_table_statement313 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_table_statement316 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_table_statement318 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_col_name_in_create_definition346 = new BitSet(new long[]{0x010002000C012000L,0x0000000401000000L});
	public static final BitSet FOLLOW_data_type_in_create_definition348 = new BitSet(new long[]{0x0000000010000402L,0x0000000000000050L});
	public static final BitSet FOLLOW_column_definition_suffix_in_create_definition350 = new BitSet(new long[]{0x0000000010000402L,0x0000000000000050L});
	public static final BitSet FOLLOW_PRIMARY_in_create_definition374 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_KEY_in_create_definition376 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_create_definition378 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_create_definition380 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_create_definition382 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_data_type407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_in_data_type414 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_in_data_type421 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_data_type424 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_INTEGER_LITERAL_in_data_type427 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_data_type429 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DATETIME_in_data_type436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOLEAN_in_data_type443 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_in_data_type450 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_data_type453 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_INTEGER_LITERAL_in_data_type456 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_110_in_data_type459 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_INTEGER_LITERAL_in_data_type462 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_data_type466 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TIMESTAMP_in_data_type473 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARCHAR_in_data_type480 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_data_type483 = new BitSet(new long[]{0x0200000000000000L});
	public static final BitSet FOLLOW_INTEGER_LITERAL_in_data_type486 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_data_type488 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_column_definition_suffix502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NULL_in_column_definition_suffix505 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DEFAULT_in_column_definition_suffix512 = new BitSet(new long[]{0x0200050010000000L,0x0000000002100040L});
	public static final BitSet FOLLOW_const_value_in_column_definition_suffix515 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AUTO_INCREMENT_in_column_definition_suffix521 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSERT_in_insert_statement535 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_INTO_in_insert_statement537 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_insert_statement539 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_values_clause_in_insert_statement541 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSERT_in_insert_statement561 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_INTO_in_insert_statement563 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_insert_statement565 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_insert_statement567 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_insert_statement569 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_110_in_insert_statement572 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_insert_statement574 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_107_in_insert_statement578 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_values_clause_in_insert_statement580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSERT_in_insert_statement603 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_INTO_in_insert_statement605 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_insert_statement607 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_insert_statement609 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_insert_statement611 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_insert_statement613 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VALUES_in_values_clause640 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_values_clause643 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_value_in_values_clause646 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_110_in_values_clause649 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_value_in_values_clause652 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_107_in_values_clause656 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DELETE_in_delete_statement670 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_FROM_in_delete_statement673 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_delete_statement676 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
	public static final BitSet FOLLOW_where_clause_in_delete_statement678 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UPDATE_in_update_statement692 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_update_statement695 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_SET_in_update_statement697 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_update_pair_in_update_statement700 = new BitSet(new long[]{0x0000000000000002L,0x0000401000000000L});
	public static final BitSet FOLLOW_110_in_update_statement703 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_update_pair_in_update_statement706 = new BitSet(new long[]{0x0000000000000002L,0x0000401000000000L});
	public static final BitSet FOLLOW_where_clause_in_update_statement710 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_col_name_in_update_pair724 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
	public static final BitSet FOLLOW_117_in_update_pair726 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_value_in_update_pair728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_index_statement755 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_INDEX_in_index_statement757 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_index_name_in_index_statement759 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_ON_in_index_statement761 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_index_statement763 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_index_statement765 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_index_statement767 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_index_statement769 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_index_statement791 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_UNIQUE_in_index_statement793 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_INDEX_in_index_statement795 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_index_name_in_index_statement797 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_ON_in_index_statement799 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_index_statement801 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_index_statement803 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_index_statement805 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_index_statement807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DROP_in_index_statement829 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_INDEX_in_index_statement831 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_index_name_in_index_statement833 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_ON_in_index_statement835 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_index_statement837 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_select_statement864 = new BitSet(new long[]{0x0202050010020800L,0x0000940002300046L});
	public static final BitSet FOLLOW_select_expr_in_select_statement866 = new BitSet(new long[]{0x0000080000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_select_statement869 = new BitSet(new long[]{0x0202050010020800L,0x0000940002300046L});
	public static final BitSet FOLLOW_select_expr_in_select_statement871 = new BitSet(new long[]{0x0000080000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_select_suffix_in_select_statement875 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_select_statement898 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_DISTINCT_in_select_statement900 = new BitSet(new long[]{0x0202050010020800L,0x0000940002300046L});
	public static final BitSet FOLLOW_select_expr_in_select_statement902 = new BitSet(new long[]{0x0000080000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_select_statement905 = new BitSet(new long[]{0x0202050010020800L,0x0000940002300046L});
	public static final BitSet FOLLOW_select_expr_in_select_statement907 = new BitSet(new long[]{0x0000080000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_select_suffix_in_select_statement911 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_from_clause_in_select_suffix941 = new BitSet(new long[]{0x0000A00000000002L,0x0000001000000400L});
	public static final BitSet FOLLOW_where_clause_in_select_suffix943 = new BitSet(new long[]{0x0000A00000000002L,0x0000000000000400L});
	public static final BitSet FOLLOW_group_by_clause_in_select_suffix946 = new BitSet(new long[]{0x0000800000000002L,0x0000000000000400L});
	public static final BitSet FOLLOW_having_clause_in_select_suffix949 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
	public static final BitSet FOLLOW_order_by_clause_in_select_suffix952 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_from_clause966 = new BitSet(new long[]{0x0002000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_tbl_ref_in_from_clause969 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_from_clause972 = new BitSet(new long[]{0x0002000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_tbl_ref_in_from_clause975 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_WHERE_in_where_clause990 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_where_condition_in_where_clause993 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GROUP_in_group_by_clause1006 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_BY_in_group_by_clause1009 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_group_by_clause1012 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HAVING_in_having_clause1025 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_having_condition_in_having_clause1028 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ORDER_in_order_by_clause1041 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_BY_in_order_by_clause1044 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_order_by_col_name_in_order_by_clause1047 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_order_by_clause1050 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_order_by_col_name_in_order_by_clause1053 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_col_name_in_order_by_col_name1068 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ASC_in_order_by_col_name1070 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_col_name_in_order_by_col_name1077 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_DESC_in_order_by_col_name1079 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_col_name_in_order_by_col_name1086 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_select_expr1099 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_AS_in_select_expr1102 = new BitSet(new long[]{0x0002000000020800L,0x0000000000200006L});
	public static final BitSet FOLLOW_alias_in_select_expr1105 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_108_in_select_expr1113 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tbl_name_in_col_name1157 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
	public static final BitSet FOLLOW_112_in_col_name1159 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_name_in_col_name1164 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tbl_name_in_tbl_ref1177 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_AS_in_tbl_ref1180 = new BitSet(new long[]{0x0002000000020800L,0x0000000000200006L});
	public static final BitSet FOLLOW_alias_in_tbl_ref1183 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_tbl_ref1191 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_tbl_ref1194 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_tbl_ref1196 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_AS_in_tbl_ref1199 = new BitSet(new long[]{0x0002000000020800L,0x0000000000200006L});
	public static final BitSet FOLLOW_alias_in_tbl_ref1202 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_tbl_name1215 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_alias1228 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_func_in_alias1234 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_index_name1247 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_db_name1260 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_name1273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_expr_in_where_condition1286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_expr_in_having_condition1299 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_statement_in_subquery1312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_or_in_bool_expr1325 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_and_in_logical_or1338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_OR_in_logical_or1341 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_logical_and_in_logical_or1344 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
	public static final BitSet FOLLOW_logical_and_in_logical_or1352 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_primary_in_logical_and1365 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_AND_in_logical_and1368 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_primary_in_logical_and1371 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_primary_in_logical_and1379 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_primary1392 = new BitSet(new long[]{0x0000000000000000L,0x00FC000000000000L});
	public static final BitSet FOLLOW_cop_in_primary1394 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_value_in_primary1397 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_primary1403 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_EXISTS_in_primary1405 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_primary1407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_primary1409 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_primary1411 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EXISTS_in_primary1429 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_primary1432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_primary1435 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_primary1437 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_primary1444 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_IN_in_primary1446 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_primary1449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_primary1452 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_primary1454 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_primary1461 = new BitSet(new long[]{0x0000000000000000L,0x00FC000000000000L});
	public static final BitSet FOLLOW_cop_in_primary1463 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_ANY_in_primary1465 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_primary1468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_primary1471 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_primary1473 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_primary1480 = new BitSet(new long[]{0x0000000000000000L,0x00FC000000000000L});
	public static final BitSet FOLLOW_cop_in_primary1482 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ALL_in_primary1484 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_primary1487 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_primary1490 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_primary1492 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_primary1499 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_bool_expr_in_primary1502 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_primary1504 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_const_value_in_primary1511 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multiplicative_in_value1557 = new BitSet(new long[]{0x0000000000000000L,0x0000A00000000000L});
	public static final BitSet FOLLOW_set_in_value1560 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_multiplicative_in_value1567 = new BitSet(new long[]{0x0000000000000002L,0x0000A00000000000L});
	public static final BitSet FOLLOW_multiplicative_in_value1575 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atom_in_multiplicative1588 = new BitSet(new long[]{0x0000000000000000L,0x0002120000000000L});
	public static final BitSet FOLLOW_set_in_multiplicative1591 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_atom_in_multiplicative1600 = new BitSet(new long[]{0x0000000000000002L,0x0002120000000000L});
	public static final BitSet FOLLOW_atom_in_multiplicative1608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_atom1621 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_value_in_atom1624 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_atom1626 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_col_name_in_atom1633 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_const_value_in_atom1639 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_atom1645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_atom1648 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_atom1650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_func_in_atom1657 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_atom1660 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_atom1663 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_atom1665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_111_in_atom1672 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_atom_in_atom1675 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_database_statement_in_synpred1_Fatworm151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_statement_in_synpred2_Fatworm157 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_index_statement_in_synpred6_Fatworm181 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSERT_in_synpred25_Fatworm535 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_INTO_in_synpred25_Fatworm537 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_synpred25_Fatworm539 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_values_clause_in_synpred25_Fatworm541 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSERT_in_synpred27_Fatworm561 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_INTO_in_synpred27_Fatworm563 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_synpred27_Fatworm565 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_synpred27_Fatworm567 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_synpred27_Fatworm569 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_110_in_synpred27_Fatworm572 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_synpred27_Fatworm574 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_107_in_synpred27_Fatworm578 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_values_clause_in_synpred27_Fatworm580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_synpred32_Fatworm755 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_INDEX_in_synpred32_Fatworm757 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_index_name_in_synpred32_Fatworm759 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_ON_in_synpred32_Fatworm761 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_synpred32_Fatworm763 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_synpred32_Fatworm765 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_synpred32_Fatworm767 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred32_Fatworm769 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_synpred33_Fatworm791 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_UNIQUE_in_synpred33_Fatworm793 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_INDEX_in_synpred33_Fatworm795 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_index_name_in_synpred33_Fatworm797 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_ON_in_synpred33_Fatworm799 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_tbl_name_in_synpred33_Fatworm801 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_synpred33_Fatworm803 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_col_name_in_synpred33_Fatworm805 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred33_Fatworm807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_synpred36_Fatworm864 = new BitSet(new long[]{0x0202050010020800L,0x0000940002300046L});
	public static final BitSet FOLLOW_select_expr_in_synpred36_Fatworm866 = new BitSet(new long[]{0x0000080000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_110_in_synpred36_Fatworm869 = new BitSet(new long[]{0x0202050010020800L,0x0000940002300046L});
	public static final BitSet FOLLOW_select_expr_in_synpred36_Fatworm871 = new BitSet(new long[]{0x0000080000000002L,0x0000400000000000L});
	public static final BitSet FOLLOW_select_suffix_in_synpred36_Fatworm875 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_col_name_in_synpred45_Fatworm1068 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ASC_in_synpred45_Fatworm1070 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_col_name_in_synpred46_Fatworm1077 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_DESC_in_synpred46_Fatworm1079 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tbl_name_in_synpred53_Fatworm1157 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
	public static final BitSet FOLLOW_112_in_synpred53_Fatworm1159 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_and_in_synpred58_Fatworm1338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_OR_in_synpred58_Fatworm1341 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_logical_and_in_synpred58_Fatworm1344 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
	public static final BitSet FOLLOW_primary_in_synpred60_Fatworm1365 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_AND_in_synpred60_Fatworm1368 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_primary_in_synpred60_Fatworm1371 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_value_in_synpred61_Fatworm1392 = new BitSet(new long[]{0x0000000000000000L,0x00FC000000000000L});
	public static final BitSet FOLLOW_cop_in_synpred61_Fatworm1394 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_value_in_synpred61_Fatworm1397 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_synpred64_Fatworm1444 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_IN_in_synpred64_Fatworm1446 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_synpred64_Fatworm1449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_synpred64_Fatworm1452 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred64_Fatworm1454 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_synpred65_Fatworm1461 = new BitSet(new long[]{0x0000000000000000L,0x00FC000000000000L});
	public static final BitSet FOLLOW_cop_in_synpred65_Fatworm1463 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_ANY_in_synpred65_Fatworm1465 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_synpred65_Fatworm1468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_synpred65_Fatworm1471 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred65_Fatworm1473 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_synpred66_Fatworm1480 = new BitSet(new long[]{0x0000000000000000L,0x00FC000000000000L});
	public static final BitSet FOLLOW_cop_in_synpred66_Fatworm1482 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ALL_in_synpred66_Fatworm1484 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_synpred66_Fatworm1487 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_synpred66_Fatworm1490 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred66_Fatworm1492 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_synpred67_Fatworm1499 = new BitSet(new long[]{0x0202054010020800L,0x0000840002300056L});
	public static final BitSet FOLLOW_bool_expr_in_synpred67_Fatworm1502 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred67_Fatworm1504 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multiplicative_in_synpred75_Fatworm1557 = new BitSet(new long[]{0x0000000000000000L,0x0000A00000000000L});
	public static final BitSet FOLLOW_set_in_synpred75_Fatworm1560 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_multiplicative_in_synpred75_Fatworm1567 = new BitSet(new long[]{0x0000000000000002L,0x0000A00000000000L});
	public static final BitSet FOLLOW_atom_in_synpred79_Fatworm1588 = new BitSet(new long[]{0x0000000000000000L,0x0002120000000000L});
	public static final BitSet FOLLOW_set_in_synpred79_Fatworm1591 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_atom_in_synpred79_Fatworm1600 = new BitSet(new long[]{0x0000000000000002L,0x0002120000000000L});
	public static final BitSet FOLLOW_106_in_synpred80_Fatworm1621 = new BitSet(new long[]{0x0202050010020800L,0x0000840002300046L});
	public static final BitSet FOLLOW_value_in_synpred80_Fatworm1624 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred80_Fatworm1626 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_synpred83_Fatworm1645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_subquery_in_synpred83_Fatworm1648 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_synpred83_Fatworm1650 = new BitSet(new long[]{0x0000000000000002L});
}
