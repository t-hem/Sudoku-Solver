package sat;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.Environment;

import sat.env.Bool;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.PosLiteral;

public class SATSolverTest {
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();

    // make sure assertions are turned on!  
    // we don't want to run test cases without assertions too.
    // see the handout to find out how to turn them on.
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void testHasNoSolution() {
        Clause aClause = make(a);
        Clause bClause = make(b);
        Clause notbClause = make(nb);
        
        Formula fOne = make(aClause);
        Formula fTwo = make(bClause);
        Formula fThree = make(notbClause);
        Formula completeFormula = (fOne.and(fTwo)).and((fOne.and(fThree)));
        
        sat.env.Environment env = SATSolver.solve(completeFormula);
        assertEquals(null, env);
    }
    
    @Test
    public void testHasSolution() {
        Clause aClause = make(a);
        Clause bClause = make(b);
        Clause secondClause = make(nb, c);
        
        Formula fOne = make(aClause);
        Formula fTwo = make(bClause);
        Formula completeFirst = fOne.and(fTwo);
        Formula completeFormula = completeFirst.and(make(secondClause));
        
        sat.env.Environment env = SATSolver.solve(completeFormula);
        
        assertEquals(Bool.TRUE, env.get(a.getVariable()));
        assertEquals(Bool.TRUE, env.get(b.getVariable()));
        assertEquals(Bool.TRUE, env.get(c.getVariable()));
    }
        
    private Clause make(Literal... e) {
        Clause c = new Clause();
        for (int i = 0; i < e.length; ++i) {
            c = c.add(e[i]);
        }
        return c;
    }
    
    private Formula make(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
            f = f.addClause(c);
        }
        return f;
    }
}