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

package org.apache.isis.core.metamodel.facets.object.paged;

import org.apache.isis.core.metamodel.facetapi.Facet;
import org.apache.isis.core.metamodel.facetapi.FacetAbstract;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;

public abstract class PagedFacetAbstract extends FacetAbstract implements PagedFacet {

    public static Class<? extends Facet> type() {
        return PagedFacet.class;
    }

    private final int parented;
    private final int standalone;

    public PagedFacetAbstract(final FacetHolder holder, int parented, int standalone) {
        super(type(), holder, Derivation.DERIVED);
        this.standalone = standalone;
        this.parented = parented;
    }

    @Override
    public int value() {
        return parented;
    }

    @Override
    public int standalone() {
        return standalone;
    }

    
}