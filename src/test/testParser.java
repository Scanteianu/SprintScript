package test;
// Generated from test.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class testParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUM=1, PLUS=2, TIMES=3, EQUAL=4, WS=5;
	public static final String[] tokenNames = {
		"<INVALID>", "NUM", "'+'", "'*'", "'='", "WS"
	};
	public static final int
		RULE_statement = 0, RULE_equationRule = 1;
	public static final String[] ruleNames = {
		"statement", "equationRule"
	};

	@Override
	public String getGrammarFileName() { return "test.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public testParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementContext extends ParserRuleContext {
		public EquationRuleContext equationRule() {
			return getRuleContext(EquationRuleContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof testVisitor ) return ((testVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4); equationRule(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EquationRuleContext extends ParserRuleContext {
		public EquationRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equationRule; }
	 
		public EquationRuleContext() { }
		public void copyFrom(EquationRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InitContext extends EquationRuleContext {
		public TerminalNode NUM() { return getToken(testParser.NUM, 0); }
		public InitContext(EquationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof testVisitor ) return ((testVisitor<? extends T>)visitor).visitInit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TimesContext extends EquationRuleContext {
		public TerminalNode TIMES() { return getToken(testParser.TIMES, 0); }
		public EquationRuleContext equationRule(int i) {
			return getRuleContext(EquationRuleContext.class,i);
		}
		public List<EquationRuleContext> equationRule() {
			return getRuleContexts(EquationRuleContext.class);
		}
		public TimesContext(EquationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof testVisitor ) return ((testVisitor<? extends T>)visitor).visitTimes(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusContext extends EquationRuleContext {
		public EquationRuleContext equationRule(int i) {
			return getRuleContext(EquationRuleContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(testParser.PLUS, 0); }
		public List<EquationRuleContext> equationRule() {
			return getRuleContexts(EquationRuleContext.class);
		}
		public PlusContext(EquationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof testVisitor ) return ((testVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationRuleContext equationRule() throws RecognitionException {
		return equationRule(0);
	}

	private EquationRuleContext equationRule(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EquationRuleContext _localctx = new EquationRuleContext(_ctx, _parentState);
		EquationRuleContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_equationRule, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new InitContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(7); match(NUM);
			}
			_ctx.stop = _input.LT(-1);
			setState(17);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(15);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						_localctx = new PlusContext(new EquationRuleContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_equationRule);
						setState(9);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(10); match(PLUS);
						setState(11); equationRule(4);
						}
						break;
					case 2:
						{
						_localctx = new TimesContext(new EquationRuleContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_equationRule);
						setState(12);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(13); match(TIMES);
						setState(14); equationRule(3);
						}
						break;
					}
					} 
				}
				setState(19);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1: return equationRule_sempred((EquationRuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean equationRule_sempred(EquationRuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 3);
		case 1: return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\7\27\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\22\n\3\f\3\16\3\25"+
		"\13\3\3\3\2\3\4\4\2\4\2\2\26\2\6\3\2\2\2\4\b\3\2\2\2\6\7\5\4\3\2\7\3\3"+
		"\2\2\2\b\t\b\3\1\2\t\n\7\3\2\2\n\23\3\2\2\2\13\f\f\5\2\2\f\r\7\4\2\2\r"+
		"\22\5\4\3\6\16\17\f\4\2\2\17\20\7\5\2\2\20\22\5\4\3\5\21\13\3\2\2\2\21"+
		"\16\3\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2\24\5\3\2\2\2\25"+
		"\23\3\2\2\2\4\21\23";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}