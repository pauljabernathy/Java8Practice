/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.*;
import java.util.function.BiFunction;
import java.util.List;

/**
 *
 * @author pabernathy
 */
public class LambdasTest {
    
    public LambdasTest() {
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
    public void testPredicate() {
        IntPredicate evens = (int i) -> i % 2 == 0;
        Predicate<Integer> odds = (Integer i) -> i % 2 == 1;
        assert(evens.test(2));
        assert(odds.test(1));
        System.out.println(evens.test(2));
        //assert(evens.test(2) == false) : "was false";
        //assert(odds.test(2)) : "was false";
        System.out.println(odds.test(2));
        
        assertEquals(true, evens.test(2));
        assertEquals(false, evens.test(1));
    }
    
    @Test
    public void getListOfApples() {
        List<Integer> weights = Arrays.asList(1, 2, 3, 4);
        List<Apple> apples = Lambdas.getListOfApples(weights, Apple::new);
        assertEquals(4, apples.size());
        System.out.println(apples);
        //checkApples(1, apples.get(0).getWeight(), (int expected, int actual) -> assertEquals(expected, actual)); does not work with checkApples(int expected, int actual, BiFunction<Integer, Integer, Void>  test)
        assertEquals(Integer.valueOf(1), apples.get(0).getWeight());
        assertEquals(Integer.valueOf(2), apples.get(1).getWeight());
        assertEquals(Integer.valueOf(3), apples.get(2).getWeight());
        assertEquals(Integer.valueOf(4), apples.get(3).getWeight());
        
        assertEquals(true, checkApples(1, apples.get(0).getWeight(), (Integer exp, Integer actual) -> { return exp == actual; }));
        //checkApples(1, apples.get(0).getWeight(), (Integer exp, Integer actual, Boolean.valueOf(true)) -> exp actual ))
        checkApples((Integer exp, Integer actual) -> { return exp == actual; } );
        BiFunction<Integer, Integer, Boolean>  test = (Integer exp, Integer actual) -> true;
    }
    
    private boolean checkApples(BiFunction<Integer, Integer, Boolean>  test) {
        return test.apply(1, 1);
    }
    private boolean checkApples(int expected, int actual, BiFunction<Integer, Integer, Boolean>  test) {
        return test.apply(expected, actual);
    }
}
