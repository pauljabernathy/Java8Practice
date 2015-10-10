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
    
}
