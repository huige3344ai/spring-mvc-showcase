// Copyright (c) 1998-2016 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2016-XX-XX, jerry.wu, creation
// ============================================================================
package org.springframework.samples.mvc.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.xml.ws.spi.Invoker;

import org.joda.time.LocalDate;
import org.springframework.samples.mvc.data.Calculator.IntegerMath;
import org.springframework.samples.mvc.data.lambda.CheckPerson;
import org.springframework.samples.mvc.data.lambda.CheckPersonEligibleForSelectiveService;

/**
 * @author jerry.wu
 *
 */
public class PersonTest {

	  public int x = 0;
	  
	  class FirstLevel {

	        public int x = 1;

	        void methodInFirstLevel(int x) {
	            
	            // The following statement causes the compiler to generate
	            // the error "local variables referenced from a lambda expression
	            // must be final or effectively final" in statement A:
	            //
	            // x = 99;

	        	
	        	//Lambda expression's parameter x cannot redeclare another
	        	//local variable defined in an enclosing scope. 
	        	//Consumer<Integer> myConsumer = (x)->
	        	// ...		
	        	//};
	        	
	        	Consumer<Integer> myConsumer = (y)->
	        	{
	                System.out.println("x = " + x); // Statement A
	                System.out.println("y = " + y);
	                System.out.println("this.x = " + this.x);
	                System.out.println("LambdaScopeTest.this.x = " +
	                		PersonTest.this.x);	        		
	        	};
	        	
	        	myConsumer.accept(x);
	        	

	        }
	    }
	
	
    @org.junit.Test
    public void easyTest(){
	
        final PersonServices pServices =  new PersonServices();
        final Person person = new Person("Jim", new LocalDate(1992, 12, 12), Person.Sex.MALE,"test@test.com");
        final Person person1 = new Person("Jim1", new LocalDate(1992, 12, 12), Person.Sex.FEMALE,"Jim1@test.com");
        final Person person2 = new Person("Jim2", new LocalDate(1992, 12, 12), Person.Sex.MALE,"Jim2@test.com");
        final Person person3 = new Person("Jim3", new LocalDate(1992, 12, 12), Person.Sex.MALE,"Jim3@test.com");
        final List<Person> persons = new ArrayList<Person>();
        System.out.println(person.getAge());
        persons.add(person);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        System.out.println("Approach 1: Create Methods That Search for Members That Match One Characteristic");
        pServices.printPersonsOlderThan(persons, 18);

        System.out.println("Approach 2: Create More Generalized Search Methods");
        pServices.printPersonsWithinAgeRange(persons, 18, 25);

        System.out.println("Approach 3: Specify Search Criteria Code in a Local Class");
        pServices.printPersons(
                persons, new CheckPersonEligibleForSelectiveService());


        System.out.println("Approach 4: Specify Search Criteria Code in an Anonymous Class");
        pServices.printPersons(
                persons,
                new CheckPerson() {
                    @Override
                    public boolean test(final Person p) {
                        return p.getGender() == Person.Sex.MALE
                            && p.getAge() >= 18
                            && p.getAge() <= 25;
                    }
                }
            );

        System.out.println("Approach 5: Specify Search Criteria Code with a Lambda Expression");
        pServices.printPersons(persons,
                 p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);


        System.out.println("Approach 6: Use Standard Functional Interfaces with Lambda Expressions");
        pServices.printPersonsWithPredicate(persons,
                 p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

        System.out.println("Approach 7: Use Lambda Expressions Throughout Your Application");
        pServices.processPersons(persons
                ,p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25
                ,p -> p.printPerson());


        pServices.processPersonsWithFunction(persons
                ,p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25
                ,p -> p.getEmailAddress()
                ,email -> System.out.println(email)
                );

        System.out.println("Approach 8: Use Generics More Extensively");
        pServices.processElements(persons
                ,p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25
                ,p -> p.getEmailAddress()
                ,email -> System.out.println(email)
                );

        System.out.println("Approach 9: Use Aggregate Operations That Accept Lambda Expressions as Parameters");
        persons
        .stream()
        .filter(p->p.getGender() == Person.Sex.MALE &&  p.getAge() >= 18&&  p.getAge() <=25)
        .map(p -> p.getEmailAddress())
        .forEach(email -> System.out.println(email));
        
        /**
         * Syntax of Lambda Expressions
         */

        Calculator myApp = new Calculator();
        IntegerMath addAtion = (a,b)->a+b;
        IntegerMath subAction = (a,b)->a-b;
        System.out.println("40 + 2 = " +
        		myApp.operateBinary(40,2,addAtion));     
        System.out.println("40 - 2 = " +
        		myApp.operateBinary(40, 2, subAction));
 
    	
    	PersonTest personTest = new PersonTest();
    	PersonTest.FirstLevel pFirstLevel = personTest.new FirstLevel();
    	pFirstLevel.methodInFirstLevel(23);
    	
    	try {
			String s = pServices.invoke(()->"done");
			System.out.println("work is "+s);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        
    }
    


}
