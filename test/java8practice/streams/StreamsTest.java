/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice.streams;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
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
    
    @Test
    public void filterNumbers() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 3, 1, 2, 4);
        assertEquals(7, nums.stream().count());
        assertEquals(2, nums.stream().filter(i -> i % 2 == 0).distinct().count());
        nums.stream().filter(i -> i % 2 == 0).distinct().forEach(n -> System.out.println(n));
        nums.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        int sum = 0;
        //nums.stream().forEach(n -> sum += n); does not compile
        
        assertEquals(4, nums.stream().skip(3).count());
        assertEquals(3, nums.stream().limit(3).count());
    }
    
    @Test
    public void mapping() {
        List<Dish> menu = Streams.getMenu();
        //print out each name
        menu.stream().map(Dish::getName).forEach(System.out::println);
        //menu.stream().forEach(dish -> System.out.println(dish.getName()));  //System.out.println(Dish::getName) isn't working, not sure why.
        //The above two statements print out the same thing.
        
        //print out the length of each name
        //menu.stream().map(dish -> dish.getName().length()).forEach(System.out::println);
        //menu.stream().map(dish -> dish.getName()).map(String::length).forEach(System.out::println);  //You can chain .map() statements.
        menu.stream().map(Dish::getName).map(String::length).forEach(System.out::println);
        //The above three statements print out the same thing.
        menu.stream().forEach(dish -> System.out.println(dish.getName() + " " + dish.getName().length()));
        
        //print out the list of distinct words
        List<String> words = Arrays.asList("word1", "word2", "word1");
        words.stream().distinct().forEach(System.out::println);
        
        //print out the list of distinct letters
        words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
        assertEquals(6, words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().count());
        //some wrong ways to do it
        words.stream().map(w -> w.split("")).forEach(System.out::println);              //object hashes
        System.out.println(words.stream().map(w -> w.split("")).count());               //3
        System.out.println(words.stream().map(w -> w.split("")).distinct().count());    //3
        System.out.println(words.stream().map(w -> w.split("")).map(Arrays::stream).distinct().count());//3
    }
    
    @Test
    public void sorting() {
        List<String> words = new ArrayList<String>();
        words.addAll(Arrays.asList("abc", "def", "aaa", "blob"));
        //words.stream().sorted().forEach(System.out::println);
        words.stream().sorted(new java.util.Comparator<String>() {
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });//.forEach(System.out::println);
        
        words.stream().sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
        words.stream().sorted((a, b) -> a.compareTo(b)).forEach(System.out::println);
    }
}
