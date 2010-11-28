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


package org.apache.isis.core.progmodel.facets.value;

import org.apache.isis.core.metamodel.facets.FacetHolder;
import org.apache.isis.core.metamodel.facets.MethodRemover;


public class ByteWrapperValueTypeFacetFactory extends ValueUsingValueSemanticsProviderFacetFactory {

    public ByteWrapperValueTypeFacetFactory() {
        super(ByteValueFacet.class);
    }

    @Override
    public boolean process(final Class<?> type, final MethodRemover methodRemover, final FacetHolder holder) {
        if (type != ByteWrapperValueSemanticsProvider.adaptedClass()) {
            return false;
        }
        addFacets(new ByteWrapperValueSemanticsProvider(holder, getConfiguration(), getSpecificationLoader(), getRuntimeContext()));
        return true;
    }

}