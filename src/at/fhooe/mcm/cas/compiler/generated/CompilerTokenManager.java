package at.fhooe.mcm.cas.compiler.generated;

/* Generated By:JavaCC: Do not edit this line. CompilerTokenManager.java */
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/** Token Manager. */
public class CompilerTokenManager implements CompilerConstants {

	/** Debug output. */
	public java.io.PrintStream debugStream = System.out;

	/** Set debug output. */
	public void setDebugStream(java.io.PrintStream ds) {
		debugStream = ds;
	}

	private final int jjStopStringLiteralDfa_0(int pos, long active0) {
		switch (pos) {
		case 0:
			if ((active0 & 0x80L) != 0L)
				return 2;
			return -1;
		default:
			return -1;
		}
	}

	private final int jjStartNfa_0(int pos, long active0) {
		return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
	}

	private int jjStopAtPos(int pos, int kind) {
		jjmatchedKind = kind;
		jjmatchedPos = pos;
		return pos + 1;
	}

	private int jjMoveStringLiteralDfa0_0() {
		switch (curChar) {
		case 68:
			return jjMoveStringLiteralDfa1_0(0x80L);
		case 69:
			return jjMoveStringLiteralDfa1_0(0x200L);
		case 71:
			return jjMoveStringLiteralDfa1_0(0x800L);
		case 76:
			return jjMoveStringLiteralDfa1_0(0x400L);
		case 80:
			return jjMoveStringLiteralDfa1_0(0x100L);
		case 100:
			return jjMoveStringLiteralDfa1_0(0x1000L);
		case 112:
			return jjMoveStringLiteralDfa1_0(0x2000L);
		default:
			return jjMoveNfa_0(3, 0);
		}
	}

	private int jjMoveStringLiteralDfa1_0(long active0) {
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(0, active0);
			return 1;
		}
		switch (curChar) {
		case 69:
			return jjMoveStringLiteralDfa2_0(active0, 0x400L);
		case 73:
			return jjMoveStringLiteralDfa2_0(active0, 0x80L);
		case 79:
			return jjMoveStringLiteralDfa2_0(active0, 0x100L);
		case 81:
			return jjMoveStringLiteralDfa2_0(active0, 0x200L);
		case 82:
			return jjMoveStringLiteralDfa2_0(active0, 0x800L);
		case 111:
			return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
		case 116:
			return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
		default:
			break;
		}
		return jjStartNfa_0(0, active0);
	}

	private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(0, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(1, active0);
			return 2;
		}
		switch (curChar) {
		case 69:
			return jjMoveStringLiteralDfa3_0(active0, 0x800L);
		case 73:
			return jjMoveStringLiteralDfa3_0(active0, 0x100L);
		case 83:
			return jjMoveStringLiteralDfa3_0(active0, 0x480L);
		case 85:
			return jjMoveStringLiteralDfa3_0(active0, 0x200L);
		case 95:
			return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
		case 115:
			return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
		default:
			break;
		}
		return jjStartNfa_0(1, active0);
	}

	private int jjMoveStringLiteralDfa3_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(1, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(2, active0);
			return 3;
		}
		switch (curChar) {
		case 65:
			return jjMoveStringLiteralDfa4_0(active0, 0xa00L);
		case 78:
			return jjMoveStringLiteralDfa4_0(active0, 0x100L);
		case 83:
			if ((active0 & 0x400L) != 0L)
				return jjStopAtPos(3, 10);
			break;
		case 84:
			return jjMoveStringLiteralDfa4_0(active0, 0x80L);
		case 95:
			return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
		case 110:
			return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
		default:
			break;
		}
		return jjStartNfa_0(2, active0);
	}

	private int jjMoveStringLiteralDfa4_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(2, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(3, active0);
			return 4;
		}
		switch (curChar) {
		case 65:
			return jjMoveStringLiteralDfa5_0(active0, 0x80L);
		case 76:
			return jjMoveStringLiteralDfa5_0(active0, 0x200L);
		case 84:
			if ((active0 & 0x100L) != 0L)
				return jjStopAtPos(4, 8);
			return jjMoveStringLiteralDfa5_0(active0, 0x800L);
		case 110:
			return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
		case 111:
			return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
		default:
			break;
		}
		return jjStartNfa_0(3, active0);
	}

	private int jjMoveStringLiteralDfa5_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(3, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(4, active0);
			return 5;
		}
		switch (curChar) {
		case 69:
			return jjMoveStringLiteralDfa6_0(active0, 0x800L);
		case 78:
			return jjMoveStringLiteralDfa6_0(active0, 0x80L);
		case 83:
			if ((active0 & 0x200L) != 0L)
				return jjStopAtPos(5, 9);
			break;
		case 111:
			return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
		case 119:
			if ((active0 & 0x1000L) != 0L)
				return jjStopAtPos(5, 12);
			break;
		default:
			break;
		}
		return jjStartNfa_0(4, active0);
	}

	private int jjMoveStringLiteralDfa6_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(4, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(5, active0);
			return 6;
		}
		switch (curChar) {
		case 67:
			return jjMoveStringLiteralDfa7_0(active0, 0x80L);
		case 82:
			if ((active0 & 0x800L) != 0L)
				return jjStopAtPos(6, 11);
			break;
		case 119:
			if ((active0 & 0x2000L) != 0L)
				return jjStopAtPos(6, 13);
			break;
		default:
			break;
		}
		return jjStartNfa_0(5, active0);
	}

	private int jjMoveStringLiteralDfa7_0(long old0, long active0) {
		if (((active0 &= old0)) == 0L)
			return jjStartNfa_0(5, old0);
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(6, active0);
			return 7;
		}
		switch (curChar) {
		case 69:
			if ((active0 & 0x80L) != 0L)
				return jjStopAtPos(7, 7);
			break;
		default:
			break;
		}
		return jjStartNfa_0(6, active0);
	}

	private int jjMoveNfa_0(int startState, int curPos) {
		int startsAt = 0;
		jjnewStateCnt = 9;
		int i = 1;
		jjstateSet[0] = startState;
		int kind = 0x7fffffff;
		for (;;) {
			if (++jjround == 0x7fffffff)
				ReInitRounds();
			if (curChar < 64) {
				long l = 1L << curChar;
				do {
					switch (jjstateSet[--i]) {
					case 3:
					case 0:
						if ((0x3ff000000000000L & l) == 0L)
							break;
						kind = 5;
						jjCheckNAdd(0);
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else if (curChar < 128) {
				long l = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					case 3:
						if (curChar == 78)
							jjstateSet[jjnewStateCnt++] = 7;
						else if (curChar == 68)
							jjstateSet[jjnewStateCnt++] = 2;
						break;
					case 1:
						if (curChar == 121)
							kind = 6;
						break;
					case 2:
						if (curChar == 97)
							jjstateSet[jjnewStateCnt++] = 1;
						break;
					case 4:
						if (curChar == 116)
							kind = 6;
						break;
					case 5:
						if (curChar == 104)
							jjstateSet[jjnewStateCnt++] = 4;
						break;
					case 6:
						if (curChar == 103)
							jjstateSet[jjnewStateCnt++] = 5;
						break;
					case 7:
						if (curChar == 105)
							jjstateSet[jjnewStateCnt++] = 6;
						break;
					case 8:
						if (curChar == 78)
							jjstateSet[jjnewStateCnt++] = 7;
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else {
				int i2 = (curChar & 0xff) >> 6;
				long l2 = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					default:
						break;
					}
				} while (i != startsAt);
			}
			if (kind != 0x7fffffff) {
				jjmatchedKind = kind;
				jjmatchedPos = curPos;
				kind = 0x7fffffff;
			}
			++curPos;
			if ((i = jjnewStateCnt) == (startsAt = 9 - (jjnewStateCnt = startsAt)))
				return curPos;
			try {
				curChar = input_stream.readChar();
			} catch (java.io.IOException e) {
				return curPos;
			}
		}
	}

	static final int[] jjnextStates = {};

	/** Token literal values. */
	public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null,
			"\104\111\123\124\101\116\103\105", "\120\117\111\116\124", "\105\121\125\101\114\123", "\114\105\123\123",
			"\107\122\105\101\124\105\122", "\144\164\137\156\157\167", "\160\157\163\137\156\157\167", };

	/** Lexer state names. */
	public static final String[] lexStateNames = { "DEFAULT", };
	static final long[] jjtoToken = { 0x3fe1L, };
	static final long[] jjtoSkip = { 0x1eL, };
	protected SimpleCharStream input_stream;
	private final int[] jjrounds = new int[9];
	private final int[] jjstateSet = new int[18];
	private final StringBuilder jjimage = new StringBuilder();
	private StringBuilder image = jjimage;
	private int jjimageLen;
	private int lengthOfMatch;
	protected char curChar;

	/** Constructor. */
	public CompilerTokenManager(SimpleCharStream stream) {
		if (SimpleCharStream.staticFlag)
			throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
		input_stream = stream;
	}

	/** Constructor. */
	public CompilerTokenManager(SimpleCharStream stream, int lexState) {
		this(stream);
		SwitchTo(lexState);
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream) {
		jjmatchedPos = jjnewStateCnt = 0;
		curLexState = defaultLexState;
		input_stream = stream;
		ReInitRounds();
	}

	private void ReInitRounds() {
		int i;
		jjround = 0x80000001;
		for (i = 9; i-- > 0;)
			jjrounds[i] = 0x80000000;
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream, int lexState) {
		ReInit(stream);
		SwitchTo(lexState);
	}

	/** Switch to specified lex state. */
	public void SwitchTo(int lexState) {
		if (lexState >= 1 || lexState < 0)
			throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.",
					TokenMgrError.INVALID_LEXICAL_STATE);
		else
			curLexState = lexState;
	}

	protected Token jjFillToken() {
		final Token t;
		final String curTokenImage;
		final int beginLine;
		final int endLine;
		final int beginColumn;
		final int endColumn;
		String im = jjstrLiteralImages[jjmatchedKind];
		curTokenImage = (im == null) ? input_stream.GetImage() : im;
		beginLine = input_stream.getBeginLine();
		beginColumn = input_stream.getBeginColumn();
		endLine = input_stream.getEndLine();
		endColumn = input_stream.getEndColumn();
		t = Token.newToken(jjmatchedKind, curTokenImage);

		t.beginLine = beginLine;
		t.endLine = endLine;
		t.beginColumn = beginColumn;
		t.endColumn = endColumn;

		return t;
	}

	int curLexState = 0;
	int defaultLexState = 0;
	int jjnewStateCnt;
	int jjround;
	int jjmatchedPos;
	int jjmatchedKind;

	/** Get the next Token. */
	public Token getNextToken() {
		Token matchedToken;
		int curPos = 0;

		EOFLoop: for (;;) {
			try {
				curChar = input_stream.BeginToken();
			} catch (java.io.IOException e) {
				jjmatchedKind = 0;
				matchedToken = jjFillToken();
				return matchedToken;
			}
			image = jjimage;
			image.setLength(0);
			jjimageLen = 0;

			try {
				input_stream.backup(0);
				while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
					curChar = input_stream.BeginToken();
			} catch (java.io.IOException e1) {
				continue EOFLoop;
			}
			jjmatchedKind = 0x7fffffff;
			jjmatchedPos = 0;
			curPos = jjMoveStringLiteralDfa0_0();
			if (jjmatchedKind != 0x7fffffff) {
				if (jjmatchedPos + 1 < curPos)
					input_stream.backup(curPos - jjmatchedPos - 1);
				if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
					matchedToken = jjFillToken();
					TokenLexicalActions(matchedToken);
					return matchedToken;
				} else {
					continue EOFLoop;
				}
			}
			int error_line = input_stream.getEndLine();
			int error_column = input_stream.getEndColumn();
			String error_after = null;
			boolean EOFSeen = false;
			try {
				input_stream.readChar();
				input_stream.backup(1);
			} catch (java.io.IOException e1) {
				EOFSeen = true;
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
				if (curChar == '\n' || curChar == '\r') {
					error_line++;
					error_column = 0;
				} else
					error_column++;
			}
			if (!EOFSeen) {
				input_stream.backup(1);
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
			}
			throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar,
					TokenMgrError.LEXICAL_ERROR);
		}
	}

	void TokenLexicalActions(Token matchedToken) {
		switch (jjmatchedKind) {
		case 5:
			image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
			System.out.print("number ");
			break;
		case 6:
			image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
			System.out.print("daytime(const)");
			break;
		case 7:
			image.append(jjstrLiteralImages[7]);
			lengthOfMatch = jjstrLiteralImages[7].length();
			System.out.print("DISTANCE ");
			break;
		case 8:
			image.append(jjstrLiteralImages[8]);
			lengthOfMatch = jjstrLiteralImages[8].length();
			System.out.print("POINT ");
			break;
		case 9:
			image.append(jjstrLiteralImages[9]);
			lengthOfMatch = jjstrLiteralImages[9].length();
			System.out.print("EQUALS ");
			break;
		case 10:
			image.append(jjstrLiteralImages[10]);
			lengthOfMatch = jjstrLiteralImages[10].length();
			System.out.print("LESS ");
			break;
		case 11:
			image.append(jjstrLiteralImages[11]);
			lengthOfMatch = jjstrLiteralImages[11].length();
			System.out.print("GREATER ");
			break;
		case 12:
			image.append(jjstrLiteralImages[12]);
			lengthOfMatch = jjstrLiteralImages[12].length();
			System.out.print("daytime (var) ");
			break;
		case 13:
			image.append(jjstrLiteralImages[13]);
			lengthOfMatch = jjstrLiteralImages[13].length();
			System.out.print("position (var) ");
			break;
		default:
			break;
		}
	}

	private void jjCheckNAdd(int state) {
		if (jjrounds[state] != jjround) {
			jjstateSet[jjnewStateCnt++] = state;
			jjrounds[state] = jjround;
		}
	}

	private void jjAddStates(int start, int end) {
		do {
			jjstateSet[jjnewStateCnt++] = jjnextStates[start];
		} while (start++ != end);
	}

	private void jjCheckNAddTwoStates(int state1, int state2) {
		jjCheckNAdd(state1);
		jjCheckNAdd(state2);
	}

}
