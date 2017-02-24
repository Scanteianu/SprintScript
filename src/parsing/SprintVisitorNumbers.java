package parsing;

import org.antlr.v4.runtime.misc.NotNull;

public class SprintVisitorNumbers extends SprintParserBaseVisitor<Object> {
	//do I need to take care of paren promotion or level switching?
	@Override
	public Object visitIntOrigin(@NotNull SprintParserParser.IntOriginContext ctx){
		return Long.parseLong(ctx.INT().getText());
	}
	public Object visitMult(@NotNull SprintParserParser.MultContext ctx){
		Object numA=visit(ctx.mathLvl2(0));
		Object numB=visit(ctx.mathLvl2(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return (Long)numA * (Long)numB;
			}
			else{
				return (Long)numA * (Double)numB;
			}
		}
		else{
			if(numB instanceof Long){
				return (Double)numA * (Long)numB;
			}
			else{
				return (Double)numA * (Double)numB;
			}
		}
	}
	public Object visitMod(@NotNull SprintParserParser.ModContext ctx){
		Object numA=visit(ctx.mathLvl2(0));
		Object numB=visit(ctx.mathLvl2(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return (Long)numA % (Long)numB;
			}
			else{
				return (Long)numA % (Double)numB;
			}
		}
		else{
			if(numB instanceof Long){
				return (Double)numA % (Long)numB;
			}
			else{
				return (Double)numA % (Double)numB;
			}
		}
	}
	@Override
	public Object visitDiv(@NotNull SprintParserParser.DivContext ctx)
	{
		Object numA=visit(ctx.mathLvl2(0));
		Object numB=visit(ctx.mathLvl2(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return (Long) numA / (Long) numB;
			}
			else{
				return (Long) numA / (Double) numB;
			}
		}
		else{
			if(numB instanceof Long){
				return (Double) numA / (Long) numB;
			}
			else{
				return (Double) numA / (Double) numB;
			}
		}
	}
	@Override
	public Object visitAnd(@NotNull SprintParserParser.AndContext ctx)
	{
		return this.bool2Num(num2Bool(ctx.mathLvl4(0))&&num2Bool(ctx.mathLvl4(1)));
	}
	@Override
	public Object visitOr(@NotNull SprintParserParser.OrContext ctx)
	{
		return this.bool2Num(num2Bool(ctx.mathLvl4(0))||num2Bool(ctx.mathLvl4(1)));
	}
	@Override
	public Object visitNot(@NotNull SprintParserParser.NotContext ctx)
	{
		Object numA=visit(ctx.mathLvl3());
		return bool2Num(!num2Bool(numA));
	}
	@Override
	public Object visitPlus(@NotNull SprintParserParser.PlusContext ctx)
	{
		Object numA=visit(ctx.mathLvl3(0));
		Object numB=visit(ctx.mathLvl3(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return (Long) numA + (Long) numB;
			}
			else{
				return (Long) numA + (Double) numB;
			}
		}
		else{
			if(numB instanceof Long){
				return (Double) numA + (Long) numB;
			}
			else{
				return (Double) numA + (Double) numB;
			}
		}
	}
	@Override
	public Object visitMinus(@NotNull SprintParserParser.MinusContext ctx)
	{
		Object numA=visit(ctx.mathLvl3(0));
		Object numB=visit(ctx.mathLvl3(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return (Long) numA - (Long) numB;
			}
			else{
				return (Long) numA - (Double) numB;
			}
		}
		else{
			if(numB instanceof Long){
				return (Double) numA - (Long) numB;
			}
			else{
				return (Double) numA - (Double) numB;
			}
		}
	}
	@Override
	public Object visitLogicEqual(@NotNull SprintParserParser.LogicEqualContext ctx)
	{
		Object numA=visit(ctx.mathLvl3(0));
		Object numB=visit(ctx.mathLvl3(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return bool2Num((long) numA == (long) numB);
			}
			else{
				return bool2Num(((long) numA) == ((double) numB));
			}
		}
		else{
			if(numB instanceof Long){
				return bool2Num((double) numA == (long) numB);
			}
			else{
				return bool2Num((double) numA == (double) numB);
			}
		}
	}
	@Override
	public Object visitLess(@NotNull SprintParserParser.LessContext ctx)
	{
		Object numA=visit(ctx.mathLvl3(0));
		Object numB=visit(ctx.mathLvl3(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return bool2Num((long) numA < (long) numB);
			}
			else{
				return bool2Num(((long) numA) < ((double) numB));
			}
		}
		else{
			if(numB instanceof Long){
				return bool2Num((double) numA < (long) numB);
			}
			else{
				return bool2Num((double) numA < (double) numB);
			}
		}
	}
	@Override
	public Object visitLeq(@NotNull SprintParserParser.LeqContext ctx)
	{
		Object numA=visit(ctx.mathLvl3(0));
		Object numB=visit(ctx.mathLvl3(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return bool2Num((long) numA <= (long) numB);
			}
			else{
				return bool2Num(((long) numA) <= ((double) numB));
			}
		}
		else{
			if(numB instanceof Long){
				return bool2Num((double) numA <= (long) numB);
			}
			else{
				return bool2Num((double) numA <= (double) numB);
			}
		}
	}
	@Override
	public Object visitGreater(@NotNull SprintParserParser.GreaterContext ctx)
	{
		Object numA=visit(ctx.mathLvl3(0));
		Object numB=visit(ctx.mathLvl3(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return bool2Num((long) numA > (long) numB);
			}
			else{
				return bool2Num(((long) numA) > ((double) numB));
			}
		}
		else{
			if(numB instanceof Long){
				return bool2Num((double) numA > (long) numB);
			}
			else{
				return bool2Num((double) numA > (double) numB);
			}
		}
	}
	@Override
	public Object visitGeq(@NotNull SprintParserParser.GeqContext ctx)
	{
		Object numA=visit(ctx.mathLvl3(0));
		Object numB=visit(ctx.mathLvl3(1));
		if(numA instanceof Long){
			if(numB instanceof Long){
				return bool2Num((long) numA >= (long) numB);
			}
			else{
				return bool2Num(((long) numA) >= ((double) numB));
			}
		}
		else{
			if(numB instanceof Long){
				return bool2Num((double) numA >= (long) numB);
			}
			else{
				return bool2Num((double) numA >= (double) numB);
			}
		}
	}
	@Override
	public Object visitLvl1toLvl2(@NotNull SprintParserParser.Lvl1toLvl2Context ctx){
		return visit(ctx.mathLvl1());
	}
	@Override
	public Object visitParenPromote(@NotNull SprintParserParser.ParenPromoteContext ctx){
		return visit(ctx.mathLvl4());
	}
	@Override
	public Object visitFloatOrigin(@NotNull SprintParserParser.FloatOriginContext ctx){
		//ParsingDebug.println("vfo: "+ctx.FLOAT().getText());
		return Double.parseDouble(ctx.FLOAT().getText());
	}
	public Object visitExponent(@NotNull SprintParserParser.ExponentContext ctx){
		return Math.pow(Double.parseDouble(visit(ctx.mathLvl1(0))+" "),Double.parseDouble(visit(ctx.mathLvl1(1))+" "));
	}
	public Object visitPrintData(@NotNull SprintParserParser.PrintDataContext ctx){
		System.out.println(visit(ctx.data()));
		return null;
	}
	private long bool2Num(boolean b){
		if(b)
			return 1;
		else return 0;
	}
	private boolean num2Bool(Object a){
		if(a instanceof Double){
			if((Double)a==0)
				return false;
			return true;
		}
		else{
			if((Long)a==0)
				return false;
			return true;
		}
	}
	public Object visitConvFloat(@NotNull SprintParserParser.ConvFloatContext ctx) { 
		Object a =visit(ctx.mathLvl4());
		if(a instanceof Double){
			return a;
		}
		else{
			return (double) ((long)a);
		} 
		
	}
	public Object visitConvInt(@NotNull SprintParserParser.ConvIntContext ctx) { 
		Object a =visit(ctx.mathLvl4());
		if(a instanceof Double){
			return (long) ((double)a);
		}
		else{
			return a;
		} 
		
	
	}
	public Object visitStringCat(@NotNull SprintParserParser.StringCatContext ctx){
		String s1 = (String) visit(ctx.string(0));
		String s2 = (String) visit(ctx.string(1));
		return s1+s2;
	}
	public Object visitStringOrigin(@NotNull SprintParserParser.StringOriginContext ctx){
		return ctx.STRING().getText().substring(1, ctx.STRING().getText().length()-1);
	}
	
	
}
