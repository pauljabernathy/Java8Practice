/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice.streams;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

/**
 *
 * @author pabernathy
 */
public class StreamsTest {
    
    public StreamsTest() {
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
    public void testDishStreams() {
        List<Dish> menu = Streams.getMenu();
        int minCalories = 300;
        List<Dish> threeHighCalorieDishes = menu.stream().filter(dish -> dish.getCalories() > minCalories).limit(3).collect(toList());
        assertEquals(3, threeHighCalorieDishes.size());
        System.out.println(threeHighCalorieDishes);
        System.out.println(threeHighCalorieDishes.size());
        for(Dish dish : threeHighCalorieDishes) {
            if(dish.getCalories() <= minCalories) {
                fail(dish.getName() + " had " + dish.getCalories() + " calories, which is fewer than " + minCalories + " calories");
            }
        }
        
        List<String> threeHighCalorieDishNames = menu.stream().filter(dish -> dish.getCalories() > minCalories).map(Dish::getName).limit(3).collect(toList());
        assertEquals(3, threeHighCalorieDishNames.size());
        assertEquals(3l, threeHighCalorieDishNames.stream().count());
        System.out.println(threeHighCalorieDishNames);
        System.out.println(threeHighCalorieDishNames.size());
        threeHighCalorieDishNames.stream().forEach(name -> System.out.println(name));       //forEach is a terminal operation so you can't do a collect after it
        for(String dishName : threeHighCalorieDishNames) {
            if(menu.stream().filter(d -> (d.getName().equals(dishName) && d.getCalories() <= minCalories)).count() > 0) {
                //You can do the && inside a lambda.  Interesting.
                //This whole if statement looks for any dish that has the same name as the current dish name and has a calorie count
                //less than or equal to minCalories.  Of course, if you have multiple dishes with the same name, there could be a problem.
                fail(dishName + " had too few calories");
            }
        }
        //similar to the above but using stream().forEach instead of the regular for loop
        threeHighCalorieDishNames.stream().forEach(dishName -> {
            if(menu.stream().filter(d -> (d.getName().equals(dishName) && d.getCalories() <= minCalories)).count() > 0) {
                fail(dishName + " had too few calories");
            }
        });
        
        threeHighCalorieDishNames = threeHighCalorieDishes.stream().map(d -> d.getName()).collect(toList());
        assertEquals(3, threeHighCalorieDishNames.size());
        System.out.println(threeHighCalorieDishNames);
        System.out.println(threeHighCalorieDishNames.size());
        
        
    }
    
}
