// Copyright (c) 1998-2016 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2016-XX-XX, jerry.wu, creation
// ============================================================================
package org.springframework.samples.mvc.data.lambda;

import org.springframework.samples.mvc.data.Person;

/**
 * @author jerry.wu
 *
 */
public class CheckPersonEligibleForSelectiveService implements CheckPerson {

    /* (non-Javadoc)
     * @see org.springframework.samples.mvc.data.lambda.CheckPerson#test(java.security.acl.Permission)
     */
    @Override
    public boolean test(final Person p) {
        return p.gender == Person.Sex.MALE &&
                p.getAge() >= 18 &&
                p.getAge() <= 25;
    }

}
