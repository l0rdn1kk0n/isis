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


package org.apache.isis.core.metamodel.adapter.oid.stringable.directly;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.isis.core.metamodel.adapter.oid.Oid;
import org.apache.isis.core.metamodel.adapter.oid.stringable.directly.DirectlyStringableOid;
import org.apache.isis.core.metamodel.adapter.oid.stringable.directly.OidStringifierDirect;

public class GivenOidStringifierDirectCanRoundtripTest {
	
	private OidStringifierDirect oidStringifier;
	private DirectlyStringableOid directlyStringableOid;
	
	@Before
	public void setUp() {
		directlyStringableOid = new OidConformant();
		oidStringifier = new OidStringifierDirect(OidConformant.class);
	}
	
	@After
	public void tearDown() {
		directlyStringableOid = null;
		oidStringifier = null;
	}
	
	@Test
	public void withConformantOidClassThenCorrectlyInitialized() {
		String enString = oidStringifier.enString(directlyStringableOid);
		Oid deString = oidStringifier.deString(enString);
		assertThat(deString, is(not(nullValue())));
	}
	

}