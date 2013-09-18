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

package org.apache.isis.viewer.wicket.ui.pages.login;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import org.apache.isis.core.commons.config.IsisConfiguration;
import org.apache.isis.core.runtime.system.context.IsisContext;
import org.apache.isis.viewer.wicket.model.mementos.PageParameterNames;
import org.apache.isis.viewer.wicket.ui.errors.ExceptionModel;
import org.apache.isis.viewer.wicket.ui.errors.ExceptionStackTracePanel;
import org.apache.isis.viewer.wicket.ui.pages.PageAbstract;

/**
 * Boilerplate, pick up our HTML and CSS.
 */
public class WicketSignInPage extends WebPage {
    
    private static final long serialVersionUID = 1L;

    private static final String ID_PAGE_TITLE = "pageTitle";
    private static final String ID_APPLICATION_NAME = "applicationName";

    private static final String ID_EXCEPTION_STACK_TRACE = "exceptionStackTrace";

    /**
     * {@link Inject}ed when {@link #init() initialized}.
     */
    @Inject
    @Named("applicationName")
    private String applicationName;

    /**
     * {@link Inject}ed when {@link #init() initialized}.
     */
    @Inject
    @Named("applicationCss")
    private String applicationCss;
    
    /**
     * {@link Inject}ed when {@link #init() initialized}.
     */
    @Inject
    @Named("applicationJs")
    private String applicationJs;

    /**
     * If set by {@link PageAbstract}. 
     */
    private static ExceptionModel getAndClearExceptionModelIfAny() {
        ExceptionModel exceptionModel = PageAbstract.EXCEPTION.get();
        PageAbstract.EXCEPTION.remove();
        return exceptionModel;
    }

    public WicketSignInPage() {
        this(null);
    }

    public WicketSignInPage(final PageParameters parameters) {
        this(parameters, getAndClearExceptionModelIfAny());
    }

    public WicketSignInPage(final PageParameters parameters, ExceptionModel exceptionModel) {
        addPageTitle(parameters);
        addApplicationName();
        addSignInPanel();
        
        if(exceptionModel != null) {
            add(new ExceptionStackTracePanel(ID_EXCEPTION_STACK_TRACE, exceptionModel));
        } else {
            add(new WebMarkupContainer(ID_EXCEPTION_STACK_TRACE).setVisible(false));
        }
    }

    private void addPageTitle(final PageParameters parameters) {
        add(new Label(ID_PAGE_TITLE, PageParameterNames.PAGE_TITLE.getStringFrom(parameters, applicationName)));
    }

    private void addApplicationName() {
        add(new Label(ID_APPLICATION_NAME, applicationName));
    }

    protected SignInPanel addSignInPanel() {
        final boolean suppressRememberMe = getConfiguration().getBoolean("isis.viewer.wicket.suppressRememberMe", false);
        final boolean rememberMe = !suppressRememberMe;
        return addSignInPanel(rememberMe);
    }

    private SignInPanel addSignInPanel(boolean rememberMe) {
        final SignInPanel signInPanel = new WicketSignInPanel("signInPanel", rememberMe);
        add(signInPanel);
        return signInPanel;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(new PriorityHeaderItem(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference())));
        
        if(applicationCss != null) {
            response.render(CssReferenceHeaderItem.forUrl(applicationCss));
        }
        if(applicationJs != null) {
            response.render(JavaScriptReferenceHeaderItem.forUrl(applicationJs));
        }
    }

 
    // ///////////////////////////////////////////////////
    // System components
    // ///////////////////////////////////////////////////

    protected IsisConfiguration getConfiguration() {
        return IsisContext.getConfiguration();
    }

}
