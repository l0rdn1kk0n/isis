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

package org.apache.isis.applib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.services.publish.EventPayload;
import org.apache.isis.applib.services.publish.PublishingService;

/**
 * Indicates that the action should be published.
 * 
 * <p>
 * Requires that an implementation of the {@link PublishingService} is registered with the framework.
 */
@Inherited
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishedAction {
    
    public interface PayloadFactory {

        @Programmatic
        public EventPayload payloadFor(Identifier actionIdentifier, Object target, List<Object> arguments, Object result);
    }
    Class<? extends PayloadFactory> value()  default PayloadFactory.class;
}
