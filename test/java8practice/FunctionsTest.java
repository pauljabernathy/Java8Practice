/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java8practice;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.*;

/**
 *
 * @author paul
 */
public class FunctionsTest {
    
    public FunctionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testBasicMathFunctions() {
        System.out.println("\ntestBasicFunctions");
        Function<Integer, Integer> plusOne = y -> y + 1;
        assertEquals(2, plusOne.apply(1).intValue());
        Function<Integer, Integer> plusTwo = x -> x + 2;
        assertEquals(3, plusTwo.apply(1).intValue());
        
        assertEquals(4, plusOne.andThen(plusTwo).apply(1).intValue());
        assertEquals(4, (plusOne.andThen(plusTwo)).apply(1).intValue());
        
        Function<Integer, Integer> timesTwo = z -> z * 2;
        assertEquals(4, plusOne.andThen(timesTwo).apply(1).intValue());
        assertEquals(3, timesTwo.andThen(plusOne).apply(1).intValue());     //timeTwo is executed first, them plusOne;  (1 * 2) + 1; like plusOne.apply(timesTwo.apply(1)).intValue()
        assertEquals(3, plusOne.apply(timesTwo.apply(1)).intValue());     
        
        assertEquals(4, timesTwo.compose(plusOne).apply(1).intValue());     //plusOne is executed first, then timesTwo
        assertEquals(3, plusOne.compose(timesTwo).apply(1).intValue());
        
        //book's example
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        assertEquals(4, h.apply(1).intValue());             //like g(f(x)) in math
        
        h = f.compose(g);
        assertEquals(3, h.apply(1).intValue());             //like f(g(x)) in math
    }
    
    @Test
    public void testIntegrate() {
        System.out.println("\ntesting integrate");
        double epsilon = .01;
        
        DoubleFunction<Double> f = x -> 5.0;
        assertEquals(5.0, Functions.integrate(f, 0.0, 1.0), epsilon);
        
        f = x -> x;
        assertEquals(0.5, Functions.integrate(f, 0.0, 1.0), epsilon);
        
        f = x -> x * x;
        assertEquals(0.33333333, Functions.integrate(f, 0.0, 1.0), epsilon);
    }
    
    @Test
    public void testDifferentiate() {
        System.out.println("\ntesting differentiate");
        
        double epsilon = 0.01;
        DoubleFunction<Double> f = x -> 5.0;
        assertEquals(0.0, Functions.differentiate(f, 1.0), 0.0);
        
        f = x -> x;
        assertEquals(1.0, Functions.differentiate(f, 1.0), epsilon);
        
        f = x -> x * x;
        assertEquals(2.0, Functions.differentiate(f, 1.0), epsilon);
        assertEquals(2.0, Functions.differentiate(x -> x * x, 1.0), epsilon);
        
        assertEquals(3.0, Functions.differentiate(x -> Math.pow(x, 3), 1.0), epsilon);
    }
    
}
