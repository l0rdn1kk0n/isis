/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package org.apache.isis.core.commons.ensure;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.apache.isis.core.commons.ensure.Ensure;


public class Ensure_GivenValueThatDoesNotMatchTest {

    @Test
    public void whenCallEnsureThatArgShouldThrowIllegalArgumentException() {
        try {
            Ensure.ensureThatArg("foo", is(nullValue()));
            fail();
        } catch(IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("illegal argument, expected: is null"));
        }
    }

    @Test
    public void whenCallEnsureThatArgOverloadedShouldThrowIllegalArgumentExceptionUsingSuppliedMessage() {
        try {
            Ensure.ensureThatArg("foo", is(nullValue()), "my message");
            fail();
        } catch(IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("my message"));
        }
    }

    @Test
    public void whenCallEnsureThatStateShouldThrowIllegalStateException() {
        try {
            Ensure.ensureThatState("foo", is(nullValue()));
            fail();
        } catch(IllegalStateException ex) {
            assertThat(ex.getMessage(), is("illegal argument, expected: is null"));
        }
    }

    @Test
    public void whenCallEnsureThatStateOverloadedShouldThrowIllegalStateExceptionUsingSuppliedMessage() {
        try {
            Ensure.ensureThatState("foo", is(nullValue()), "my message");
            fail();
        } catch(IllegalStateException ex) {
            assertThat(ex.getMessage(), is("my message"));
        }
    }

    @Test
    public void whenCallEnsureThatContextShouldThrowIllegalThreadStateException() {
        try {
            Ensure.ensureThatContext("foo", is(nullValue()));
            fail();
        } catch(IllegalThreadStateException ex) {
            assertThat(ex.getMessage(), is("illegal argument, expected: is null"));
        }
    }

    @Test
    public void whenCallEnsureThatContextOverloadedShouldThrowIllegalThreadStateExceptionUsingSuppliedMessage() {
        try {
            Ensure.ensureThatContext("foo", is(nullValue()), "my message");
            fail();
        } catch(IllegalThreadStateException ex) {
            assertThat(ex.getMessage(), is("my message"));
        }
    }


}