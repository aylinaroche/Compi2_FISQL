/* Generated By:JavaCC: Do not edit this line. parserXMLTokenManager.java */
package AnalizadorXML;
import fisql.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/** Token Manager. */
public class parserXMLTokenManager implements parserXMLConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x7fff2L) != 0L)
         {
            jjmatchedKind = 31;
            return 1;
         }
         return -1;
      case 1:
         if ((active0 & 0x2L) != 0L)
            return 1;
         if ((active0 & 0x7fff0L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 1;
            return 1;
         }
         return -1;
      case 2:
         if ((active0 & 0xc180L) != 0L)
            return 1;
         if ((active0 & 0x73e70L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               jjmatchedKind = 31;
               jjmatchedPos = 2;
            }
            return 1;
         }
         return -1;
      case 3:
         if ((active0 & 0xe70L) != 0L)
            return 1;
         if ((active0 & 0x73080L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 31;
               jjmatchedPos = 3;
            }
            return 1;
         }
         return -1;
      case 4:
         if ((active0 & 0x610a0L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 4;
            return 1;
         }
         if ((active0 & 0x12000L) != 0L)
            return 1;
         return -1;
      case 5:
         if ((active0 & 0x60020L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 5;
            return 1;
         }
         if ((active0 & 0x1080L) != 0L)
            return 1;
         return -1;
      case 6:
         if ((active0 & 0x40020L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 6;
            return 1;
         }
         if ((active0 & 0x20000L) != 0L)
            return 1;
         return -1;
      case 7:
         if ((active0 & 0x40020L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 7;
            return 1;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 13:
         jjmatchedKind = 38;
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 40:
         return jjStopAtPos(0, 19);
      case 41:
         return jjStopAtPos(0, 20);
      case 58:
         return jjStopAtPos(0, 26);
      case 59:
         return jjStopAtPos(0, 25);
      case 60:
         jjmatchedKind = 28;
         return jjMoveStringLiteralDfa1_0(0xcL);
      case 62:
         return jjStopAtPos(0, 27);
      case 91:
         return jjStopAtPos(0, 23);
      case 93:
         return jjStopAtPos(0, 24);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa1_0(0x40400L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa1_0(0x2L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa1_0(0x4080L);
      case 80:
      case 112:
         return jjMoveStringLiteralDfa1_0(0x1830L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa1_0(0x40L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa1_0(0x10200L);
      case 123:
         return jjStopAtPos(0, 21);
      case 125:
         return jjStopAtPos(0, 22);
      case 160:
         return jjStopAtPos(0, 40);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 10:
         if ((active0 & 0x800000000L) != 0L)
            return jjStopAtPos(1, 35);
         break;
      case 47:
         return jjMoveStringLiteralDfa2_0(active0, 0x8L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x71010L);
      case 66:
      case 98:
         if ((active0 & 0x2L) != 0L)
            return jjStartNfaWithStates_0(1, 1, 1);
         return jjMoveStringLiteralDfa2_0(active0, 0x4080L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x104L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x440L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x2820L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 66:
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x22000L);
      case 74:
      case 106:
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x8L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0xc24L);
      case 82:
      case 114:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(2, 15, 1);
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      case 84:
      case 116:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(2, 8, 1);
         return jjMoveStringLiteralDfa3_0(active0, 0x10L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa3_0(active0, 0x40L);
      case 88:
      case 120:
         return jjMoveStringLiteralDfa3_0(active0, 0x200L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x3000L);
      case 67:
      case 99:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 11;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x20L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x40080L);
      case 72:
      case 104:
         if ((active0 & 0x10L) != 0L)
            return jjStartNfaWithStates_0(3, 4, 1);
         break;
      case 76:
      case 108:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(3, 10, 1);
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x4L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x8L);
      case 83:
      case 115:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(3, 6, 1);
         return jjMoveStringLiteralDfa4_0(active0, 0x20000L);
      case 84:
      case 116:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(3, 9, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(4, 16, 1);
         break;
      case 66:
      case 98:
         return jjMoveStringLiteralDfa5_0(active0, 0x4L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x80L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa5_0(active0, 0x20L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa5_0(active0, 0x1008L);
      case 82:
      case 114:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(4, 13, 1);
         break;
      case 84:
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x40000L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa6_0(active0, 0x8L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x20L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x20004L);
      case 83:
      case 115:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(5, 12, 1);
         break;
      case 84:
      case 116:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(5, 7, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 69:
      case 101:
         return jjMoveStringLiteralDfa7_0(active0, 0x4L);
      case 79:
      case 111:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(6, 17, 1);
         break;
      case 82:
      case 114:
         return jjMoveStringLiteralDfa7_0(active0, 0x8L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa7_0(active0, 0x40000L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x4L) != 0L)
            return jjStopAtPos(7, 2);
         break;
      case 69:
      case 101:
         return jjMoveStringLiteralDfa8_0(active0, 0x8L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa8_0(active0, 0x40000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa8_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x8L) != 0L)
            return jjStopAtPos(8, 3);
         break;
      case 69:
      case 101:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(8, 5, 1);
         break;
      case 83:
      case 115:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(8, 18, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0x8000000200000000L, 0x0L
};
static final long[] jjbitVec1 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 11;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xf000e77a00000000L & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                  }
                  else if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 29)
                        kind = 29;
                     jjCheckNAddStates(0, 2);
                  }
                  else if (curChar == 34)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 2:
                  if ((0xf000e77a00000000L & l) != 0L && kind > 32)
                     kind = 32;
                  break;
               case 3:
                  if (curChar == 34)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 4:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 5:
                  if (curChar == 34 && kind > 33)
                     kind = 33;
                  break;
               case 6:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAddStates(0, 2);
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAdd(7);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(8, 9);
                  break;
               case 9:
                  if (curChar == 46)
                     jjCheckNAdd(10);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 30)
                     kind = 30;
                  jjCheckNAdd(10);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(1);
                  }
                  else if ((0xe8000001L & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                  }
                  break;
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(1);
                  break;
               case 2:
                  if ((0xe8000001L & l) != 0L && kind > 32)
                     kind = 32;
                  break;
               case 4:
                  jjAddStates(3, 4);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((jjbitVec0[i2] & l2) != 0L && kind > 32)
                     kind = 32;
                  break;
               case 4:
                  if ((jjbitVec1[i2] & l2) != 0L)
                     jjAddStates(3, 4);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 11 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   7, 8, 9, 4, 5, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, "\50", "\51", "\173", "\175", "\133", "\135", 
"\73", "\72", "\76", "\74", null, null, null, null, null, null, null, null, null, 
null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x3ffffffffL, 
};
static final long[] jjtoSkip = {
   0x1fc00000000L, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[11];
static private final int[] jjstateSet = new int[22];
static protected char curChar;
/** Constructor. */
public parserXMLTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public parserXMLTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 11; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
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

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100001600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
