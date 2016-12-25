// Copyright (c) 1998-2016 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2016-XX-XX, jerry.wu, creation
// ============================================================================
package org.springframework.samples.mvc.data;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.samples.mvc.data.lambda.CheckPerson;
import org.springframework.samples.mvc.data.lambda.Predicate;

/**
 * @author jerry.wu
 *
 */
public class PersonServices {


    public  void printPersonsOlderThan(final List<Person> roster, final int age) {
        for (final Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public  void printPersonsWithinAgeRange(
            final List<Person> roster, final int low, final int high) {
            for (final Person p : roster) {
                if (low <= p.getAge() && p.getAge() < high) {
                    p.printPerson();
                }
            }
        }
    public  void printPersons(
            final List<Person> roster, final CheckPerson tester) {
            for (final Person p : roster) {
                if (tester.test(p)) {
                    p.printPerson();
                }
            }


    }

    public  void  printPersonsWithPredicate( final List<Person> roster, final Predicate<Person> tester) {
        for (final Person p2 : roster) {
            if(tester.test(p2)){
                p2.printPerson();
            }
        }
    }

    public  void processPersons(
            final List<Person> roster,
            final Predicate<Person> tester,
            final Consumer<Person> block) {
                for (final Person p : roster) {
                    if (tester.test(p)) {
                        block.accept(p);
                    }
                }
        }

    public  void processPersonsWithFunction(
            final List<Person> roster,
            final Predicate<Person> tester,
            final Function<Person, String> mapper,
            final Consumer<String> block) {
            for (final Person p : roster) {
                if (tester.test(p)) {
                    final String data = mapper.apply(p);
                    block.accept(data);
                }
            }
        }

    public  <X, Y> void processElements(
            final Iterable<X> source,
            final Predicate<X> tester,
            final Function <X, Y> mapper,
            final Consumer<Y> block) {
            for (final X p : source) {
                if (tester.test(p)) {
                    final Y data = mapper.apply(p);
                    block.accept(data);
                }
            }
        }
    
    void invoke(Runnable r) {
    	System.out.println("invoke(Runnable r)");
        r.run();
    }

    <T> T invoke(Callable<T> c) throws Exception {
    	System.out.println("invoke(Callable<T> c)");
        return c.call();
    }
    
}
