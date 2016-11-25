// Copyright (c) 1998-2016 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2016-XX-XX, jerry.wu, creation
// ============================================================================
package org.springframework.samples.mvc.data;

import org.joda.time.LocalDate;
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

    /**
     *
     */
    public Person(final String name,final LocalDate birthday,final Sex gender,final String emailAddress) {
        // TODO Auto-generated constructor stub
      this.name=name;
      this.birthday=birthday;
      this.gender=gender;
      this.emailAddress=emailAddress;
    }


    public void printPerson() {
        System.out.println(getName()+" is a/an "+getGender());
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


