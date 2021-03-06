/**
 * Author: dnj, Hank Huang
 * Date: March 7, 2009
 * 6.005 Elements of Software Construction
 * (c) 2007-2009, MIT 6.005 Staff
 */
package sat.formula;

import immutable.EmptyImList;
import immutable.ImList;
import immutable.ImListIterator;
import immutable.NonEmptyImList;

import java.util.Iterator;

import sat.env.Variable;

/**
 * Formula represents an immutable boolean formula in
 * conjunctive normal form, intended to be solved by a
 * SAT solver.
 */
public class Formula {
	private final ImList<Clause> clauses;
    // Rep invariant:
    //      clauses != null
    //      clauses contains no null elements (ensured by spec of ImList)
    //
    // Note: although a formula is intended to be a set,  
    // the list may include duplicate clauses without any problems. 
    // The cost of ensuring that the list has no duplicates is not worth paying.
    //
    //    
    //    Abstraction function:
    //        The list of clauses c1,c2,...,cn represents 
    //        the boolean formula (c1 and c2 and ... and cn)
    //        
    //        For example, if the list contains the two clauses (a,b) and (!c,d), then the
    //        corresponding formula is (a or b) and (!c or d).

    void checkRep() {
        assert this.clauses != null : "SATProblem, Rep invariant: clauses non-null";
    }

    /**
     * Create a new problem for solving that contains no clauses (that is the
     * vacuously true problem)
     * 
     * @return the true problem
     */
    public Formula() {
    	this.clauses = new EmptyImList<Clause> ();
        // TODO: implement this.
        // throw new RuntimeException("not yet implemented.");
    }

    /**
     * Create a new problem for solving that contains a single clause with a
     * single literal
     * 
     * @return the problem with a single clause containing the literal l
     */
    public Formula(Variable l) {
        Literal literal = PosLiteral.make(l);
        Clause clause = new Clause(literal);
        this.clauses = new NonEmptyImList<Clause> (clause);
    	// TODO: implement this.
        // throw new RuntimeException("not yet implemented.");
    }
    
    /**
     * Create a new problem for solving that contains a set of clauses
     * 
     * @return the problem with the clauses l
     */
    private Formula(ImList<Clause> l) {
    	this.clauses = l;
    }

    /**
     * Create a new problem for solving that contains a single clause
     * 
     * @return the problem with a single clause c
     */
    public Formula(Clause c) {
        this.clauses = new NonEmptyImList<Clause> (c);
    	// TODO: implement this.
        // throw new RuntimeException("not yet implemented.");
    }

    /**
     * Add a clause to this problem
     * 
     * @return a new problem with the clauses of this, but c added
     */
    public Formula addClause(Clause c) {
        ImList<Clause> clauses = this.getClauses();
        ImList<Clause> newClauses = clauses.add(c);
        return new Formula(newClauses);
    	// TODO: implement this.
        // throw new RuntimeException("not yet implemented.");
    }

    /**
     * Get the clauses of the formula.
     * 
     * @return list of clauses
     */
    public ImList<Clause> getClauses() {
        return clauses;
    	// TODO: implement this.
        //throw new RuntimeException("not yet implemented.");
    }

    /**
     * Iterator over clauses
     * 
     * @return an iterator that yields each clause of this in some arbitrary
     *         order
     */
    public Iterator<Clause> iterator() {
        return new ImListIterator<Clause>(this.clauses);
    	// TODO: implement this.
        //throw new RuntimeException("not yet implemented.");
    }

    /**
     * @return a new problem corresponding to the conjunction of this and p
     */
    public Formula and(Formula p) {
        ImList<Clause> finalClauses = this.getClauses();
        Iterator<Clause> formIter = p.iterator();
        
        while (formIter.hasNext()) {
        	Clause nextClause = formIter.next();
        	finalClauses = finalClauses.add(nextClause);
        }
        
        return new Formula(finalClauses);
    	// TODO: implement this.
        // throw new RuntimeException("not yet implemented.");
    }

    /**
     * @return a new problem corresponding to the disjunction of this and p
     */
    public Formula or(Formula p) {
        // TODO: implement this.
        // Hint: you'll need to use the distributive law to preserve conjunctive normal form, i.e.:
        //   to do (a & b) .or (c & d),
        //   you'll need to make (a | b) & (a | c) & (b | c) & (b | d)        
    	
    	if (this.getSize() == 0) {
            return p;
        }
    	
        if (p.getSize() == 0) {
            return this;
        }
        
        Formula result = new Formula();
        for (Clause c1 : this.clauses) {
            for (Clause c2 : p.clauses) {
                result = result.addClause(c1.merge(c2));
            }
        }
        
        return result;
    	
    	//throw new RuntimeException("not yet implemented.");
    }

    /**
     * @return a new problem corresponding to the negation of this
     */
    public Formula not() {
        // TODO: implement this.
        // Hint: you'll need to apply DeMorgan's Laws (http://en.wikipedia.org/wiki/De_Morgan's_laws)
        // to move the negation down to the literals, and the distributive law to preserve 
        // conjunctive normal form, i.e.:
        //   if you start with (a | b) & c,
        //   you'll need to make !((a | b) & c) 
        //                       => (!a & !b) | !c            (moving negation down to the literals)
        //                       => (!a | !c) & (!b | !c)    (conjunctive normal form)
    	
    	Formula result = new Formula();
        
    	for (Clause c : this.clauses) {
            Formula temp = new Formula();
            
            for (Literal l : c) {
                temp = temp.addClause(new Clause(l.getNegation()));
            }
            
            result = temp.or(result);
        }
        
    	return result;
    	
    	//throw new RuntimeException("not yet implemented.");
    }

    /**
     * 
     * @return number of clauses in this
     */
    public int getSize() {
        return clauses.size();
    	// TODO: implement this.
        //throw new RuntimeException("not yet implemented.");
    }

    /**
     * @return string representation of this formula
     */
    public String toString() {
        String result = "Problem[";
        for (Clause c : clauses)
            result += "\n" + c;
        return result + "]";
    }
}
