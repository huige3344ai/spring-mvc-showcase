// Copyright (c) 1998-2016 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2016-XX-XX, jerry.wu, creation
// ============================================================================
package org.springframework.samples.mvc.data;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.samples.mvc.data.lambda.CheckPerson;
/**
 * @author jerry.wu
 *
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    public String name;
    LocalDate birthday;
    public Sex gender;
    String emailAddress;

    public void printPerson() {
        System.out.println(getName()+" is a/an"+getGender());
    }

    public static void printPersonsOlderThan(final List<Person> roster, final int age) {
        for (final Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public static void printPersons(
            final List<Person> roster, final CheckPerson tester) {
            for (final Person p : roster) {
                if (tester.test(p)) {
                    p.printPerson();
                }
            }
        }

    /**
     * @return
     */
    public int getAge() {
        final LocalDate toDay = LocalDate.now();
        final int nowYear = toDay.getYear();
        return getBirthday()!=null?nowYear-getBirthday().getYear():0;
    }


    public String getName() {
        return name;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public Sex getGender() {
        return gender;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }
    public void setGender(final Sex gender) {
        this.gender = gender;
    }
    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }


}
