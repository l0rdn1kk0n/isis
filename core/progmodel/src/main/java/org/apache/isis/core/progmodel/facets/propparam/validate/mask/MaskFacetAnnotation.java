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


package org.apache.isis.core.progmodel.facets.propparam.validate.mask;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.facets.FacetHolder;


public class MaskFacetAnnotation extends MaskFacetAbstract {
    private final MaskEvaluator evaluator;

    public MaskFacetAnnotation(final String outputMask, String inputMask, final FacetHolder holder) {
        super(outputMask, holder);
        evaluator = inputMask == null ? null : new MaskEvaluator(inputMask);
    }

    public boolean doesNotMatch(final ObjectAdapter adapter) {
        if (evaluator == null) {
            return false;
        } else {
            if (adapter == null) {
                return false;
            }
            final Object object = adapter.getObject();
            if (object == null) {
                return false;
            }
            return !evaluator.evaluate(object.toString());
        }
    }

}
