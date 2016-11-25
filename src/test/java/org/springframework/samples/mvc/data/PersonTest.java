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

import org.joda.time.LocalDate;
import org.springframework.samples.mvc.data.lambda.CheckPerson;
import org.springframework.samples.mvc.data.lambda.CheckPersonEligibleForSelectiveService;

/**
 * @author jerry.wu
 *
 */
public class PersonTest {

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
                (final Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);


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


    }


}
