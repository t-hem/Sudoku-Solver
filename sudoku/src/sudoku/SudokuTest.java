package sudoku;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import sudoku.Sudoku.ParseException;


public class SudokuTest {
    

    // make sure assertions are turned on!  
    // we don't want to run test cases without assertions too.
    // see the handout to find out how to turn them on.
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void testReader() {
        try {
            Sudoku sudoku = Sudoku.fromFile(3, "sudoku_easy.txt");
        } catch (Exception e) {
            fail();
        }
        
    }
    
    @Test(expected=Sudoku.ParseException.class)
    public void testIllegalDimension() throws IOException, Sudoku.ParseException {
        
        Sudoku sudoku = Sudoku.fromFile(4, "sudoku_easy.txt");
    }
    
    @Test
    public void test1by1() throws IOException, ParseException {
    	Sudoku sudoku1 = Sudoku.fromFile(1, "sudoku1x1.txt");
        System.out.println("1x1:");
        System.out.println(sudoku1);
        
        Sudoku sudoku12 = Sudoku.fromFile(1, "sudoku1x1two.txt");
        System.out.println("1x1:");
        System.out.println(sudoku12);
    }
    
    @Test
    public void test2by2() throws IOException, ParseException {
    	 Sudoku sudoku2 = Sudoku.fromFile(2, "sudoku2x2.txt");
         System.out.println("2x2:");
         System.out.println(sudoku2);
    }
    
    @Test
    public void test3by3() throws IOException, ParseException {
    	Sudoku sudoku3 = Sudoku.fromFile(3, "sudoku_easy.txt");
        System.out.println("3x3:");
        System.out.println(sudoku3);
    	
    }
    
}