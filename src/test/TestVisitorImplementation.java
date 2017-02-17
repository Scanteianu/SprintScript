package test;


public class TestVisitorImplementation extends testBaseVisitor {

	

	@Override
	public Object visitTimes(testParser.TimesContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Times");
		for(testParser.EquationRuleContext e: ctx.equationRule()){
			System.out.println("equationrule "+visit(e));
		}
		System.out.println();
		return 5;
	}

	@Override
	public Object visitPlus(testParser.PlusContext ctx) {
		System.out.println("Plus");
		for(testParser.EquationRuleContext e: ctx.equationRule()){
			System.out.println("equationrule "+visit(e));
		}
		System.out.println();
		return (Integer)visit(ctx.equationRule(0))+(Integer)visit(ctx.equationRule(1));
	}

	@Override
	public Object visitInit(testParser.InitContext ctx) {
		System.out.println("visited int: "+ctx.NUM().getText()+" context text :"+ctx.getText());
		return Integer.parseInt(ctx.NUM().getText());
	}
	@Override
	public Object visitStatement(testParser.StatementContext ctx){
		System.out.println(visitChildren(ctx));
		return null;
	}

	
	

}
