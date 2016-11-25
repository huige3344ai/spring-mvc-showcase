// Copyright (c) 1998-2016 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2016-XX-XX, jerry.wu, creation
// ============================================================================
package org.springframework.samples.mvc.data.lambda;

/**
 * @author jerry.wu
 *
 */
public interface Predicate<T> {

    public boolean test(T t);
}
