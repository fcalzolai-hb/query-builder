package autogen;// Generated from Field.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface FieldListener extends ParseTreeListener {
	void enterField(FieldParser.FieldContext ctx);
	void exitField(FieldParser.FieldContext ctx);

	void enterBurial(FieldParser.BurialContext ctx);
	void exitBurial(FieldParser.BurialContext ctx);

	void enterLocation(FieldParser.LocationContext ctx);
	void exitLocation(FieldParser.LocationContext ctx);

	void enterPoints(FieldParser.PointsContext ctx);
	void exitPoints(FieldParser.PointsContext ctx);
}