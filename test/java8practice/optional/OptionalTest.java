/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice.optional;

import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pabernathy
 */
public class OptionalTest {
    
    public OptionalTest() {
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
    public void Optionals() {
        Optional<String> o = Optional.empty(); //new Optional<String>("");
        System.out.println(o.isPresent());
        System.out.println(o);
        System.out.println(o.toString());
        //System.out.println(o.get());      //causes a java.util.NoSuchElementException
        o = Optional.of("abcd");
        System.out.println(o.isPresent());
        System.out.println(o);
        System.out.println(o.toString());
        System.out.println(o.get());
        System.out.println(o.get().getClass());
        
        Optional p = Optional.empty();
        p = Optional.of("efgh");
        System.out.println(p);
        System.out.println(p.toString());
        System.out.println(p.get());
        System.out.println(p.get().getClass());
    }
    
    @Test
    public void testCreating() {
        Optional<String> o = Optional.of("whatever");
        assertEquals(true, o.isPresent());
        assertEquals("whatever", o.get());
        System.out.println(o);
        System.out.println(o.get());
        
        o = Optional.ofNullable("whatever");
        assertEquals(true, o.isPresent());
        assertEquals("whatever", o.get());
        System.out.println(o);
        System.out.println(o.get());
        
        //o = Optional.of(null);  causes a NullPointerException
        o = Optional.ofNullable(null);  //does not cause a NullPointerException
        //System.out.println(o.get());    //still causes a java.util.NoSuchElementException
    }
    
    @Test
    public void testOrElse() {
        Optional<String> o = Optional.empty();
        System.out.println(o.orElse("dlkfj"));
        o = Optional.of("something coherent");
        System.out.println(o.orElse("dlkfj"));
    }
    
    @Test
    public void testOrElseGet() {
        Optional<String> o = Optional.empty();
        System.out.println(o.orElse("orElse"));
        //The following do not work.
        /*Supplier<String> s = new Supplier<String> {
            public String get() {
                return "what?";
            }
        };*/
        //Supplier<String> s =() -> "a";
        /*System.out.println(o.orElseGet(new Supplier<String> {
            public String get() {
                return "what?";
            }
        }));*/
        System.out.println(o.orElseGet(() -> "orElseGet"));
        o = Optional.of("something coherent");
        System.out.println(o.orElse("orElse"));
        System.out.println(o.orElseGet(() -> "orElseGet"));
    }
}
