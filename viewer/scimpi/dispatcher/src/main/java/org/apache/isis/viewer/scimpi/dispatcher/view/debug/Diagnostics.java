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


package org.apache.isis.viewer.scimpi.dispatcher.view.debug;

import org.apache.isis.viewer.scimpi.dispatcher.AbstractElementProcessor;
import org.apache.isis.viewer.scimpi.dispatcher.context.RequestContext;
import org.apache.isis.viewer.scimpi.dispatcher.processor.Request;


public class Diagnostics extends AbstractElementProcessor {

    public void process(Request request) {
        if (request.getContext().getDebug() == RequestContext.Debug.ON) {
            RequestContext context = request.getContext();
            request.appendHtml("<div class=\"debug\">");
            request.appendHtml("<a class=\"option\" target=\"debug\" href=\"debug.app\">Object</a>");
            request.appendHtml("<pre>");  
            request.appendHtml("URI:  " + context.getUri());
            request.appendHtml("\n");
            request.appendHtml("File: " + context.fullFilePath(context.getResourceFile()));
            request.appendHtml("\n\n");
            context.append(request, "variables");
            request.appendHtml("</pre>");
            request.appendHtml("</div>");
        }
    }

    public String getName() {
        return "diagnostics";
    }

}
