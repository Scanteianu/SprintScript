package test;

public class MyTestListener  extends testBaseListener{
    @Override
    public void enterEquationRule(testParser.EquationRuleContext ctx) {
        System.out.println(ctx.getText());
    }
}
