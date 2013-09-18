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

import org.apache.wicket.authroles.authentication.panel.SignInPanel;

/**
 * An extension of wicket-auth-roles that provides custom markup
 * based on Twitter Bootstrap 3
 */
public class WicketSignInPanel extends SignInPanel {

    public WicketSignInPanel(String id, boolean includeRememberMe) {
        super(id, includeRememberMe);
    }
}
