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


package org.apache.isis.core.progmodel.value;

import java.text.DecimalFormat;

import org.apache.isis.applib.adapters.EncoderDecoder;
import org.apache.isis.applib.adapters.Parser;
import org.apache.isis.applib.value.Color;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.adapter.TextEntryParseException;
import org.apache.isis.core.metamodel.config.IsisConfiguration;
import org.apache.isis.core.metamodel.facets.Facet;
import org.apache.isis.core.metamodel.facets.FacetHolder;
import org.apache.isis.core.metamodel.runtimecontext.RuntimeContext;
import org.apache.isis.core.metamodel.specloader.SpecificationLoader;
import org.apache.isis.core.progmodel.facets.value.ColorValueFacet;


public class ColorValueSemanticsProvider extends ValueSemanticsProviderAbstract implements ColorValueFacet {

    public static Class<? extends Facet> type() {
        return ColorValueFacet.class;
    }

    private static final int TYPICAL_LENGTH = 4;
    private static final boolean IMMUTABLE = true;
    private static final boolean EQUAL_BY_CONTENT = false;
    private static final Object DEFAULT_VALUE = Color.BLACK;

    /**
     * Required because implementation of {@link Parser} and {@link EncoderDecoder}.
     */
    public ColorValueSemanticsProvider() {
        this(null, null, null, null);
    }

    public ColorValueSemanticsProvider(
    		final FacetHolder holder,
            final IsisConfiguration configuration, 
            final SpecificationLoader specificationLoader, 
            final RuntimeContext runtimeContext) {
        super(type(), holder, Color.class, TYPICAL_LENGTH, IMMUTABLE, EQUAL_BY_CONTENT, DEFAULT_VALUE, configuration, specificationLoader, runtimeContext);
    }

    // //////////////////////////////////////////////////////////////////
    // Parser
    // //////////////////////////////////////////////////////////////////

    @Override
    protected Object doParse(final Object original, final String text) {
        try {
            if (text.startsWith("0x")) {
                return new Color(Integer.parseInt(text.substring(2), 16));
            } else if (text.startsWith("#")) {
                return new Color(Integer.parseInt(text.substring(1), 16));
            } else {
                return new Color(Integer.parseInt(text));
            }
        } catch (final NumberFormatException e) {
            throw new TextEntryParseException("Not a number " + text, e);
        }
    }

    @Override
    public String titleString(final Object object) {
        final Color color = (Color) object;
        return color.title();
    }
    
    public String titleStringWithMask(final Object object, final String usingMask) {
        final Color color = (Color) object;
        return titleString(new DecimalFormat(usingMask), color.intValue());
    }

    // //////////////////////////////////////////////////////////////////
    // Encode, Decode
    // //////////////////////////////////////////////////////////////////

    @Override
    protected String doEncode(final Object object) {
        final Color color = (Color) object;
        return Integer.toHexString(color.intValue());
    }

    @Override
    protected Object doRestore(final String data) {
        return new Color(Integer.parseInt(data, 16));
    }

    // //////////////////////////////////////////////////////////////////
    // ColorValueFacet
    // //////////////////////////////////////////////////////////////////

    public int colorValue(final ObjectAdapter object) {
        if (object == null) {
            return 0;
        }
        final Color color = (Color) object.getObject();
        return color.intValue();
    }

    public ObjectAdapter createValue(final ObjectAdapter object, final int colorAsInt) {
        final Color color = new Color(colorAsInt);
        return getRuntimeContext().adapterFor(color);
    }

    @Override
    public String toString() {
        return "ColorValueSemanticsProvider";
    }

    
}