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
package org.apache.isis.viewer.restfulobjects.tck.domainservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Response;

import org.apache.isis.viewer.restfulobjects.applib.RepresentationType;
import org.apache.isis.viewer.restfulobjects.applib.client.RestfulClient;
import org.apache.isis.viewer.restfulobjects.applib.client.RestfulResponse;
import org.apache.isis.viewer.restfulobjects.applib.client.RestfulResponse.HttpStatusCode;
import org.apache.isis.viewer.restfulobjects.applib.domainobjects.DomainObjectRepresentation;
import org.apache.isis.viewer.restfulobjects.applib.domainobjects.DomainServiceResource;
import org.apache.isis.viewer.restfulobjects.tck.IsisWebServerRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DomainServiceResourceTest_serviceId_response_headers {

    @Rule
    public IsisWebServerRule webServerRule = new IsisWebServerRule();

    private RestfulClient client;
    private DomainServiceResource resource;

    @Before
    public void setUp() throws Exception {
        client = webServerRule.getClient();
        
        resource = client.getDomainServiceResource();
    }

    @Test
    public void headers() throws Exception {
        // given
        final Response resp = resource.service("JdkValuedEntities");

        // when
        final RestfulResponse<DomainObjectRepresentation> jsonResp = RestfulResponse.ofT(resp);

        
        // then
        assertThat(jsonResp.getStatus(), is(HttpStatusCode.OK));
        assertThat(jsonResp.getHeader(RestfulResponse.Header.CONTENT_TYPE), is(RepresentationType.DOMAIN_OBJECT.getMediaType("x-ro-domain-type", "http://localhost:39393/domain-types/JdkValuedEntities")));
        assertThat(jsonResp.getHeader(RestfulResponse.Header.CACHE_CONTROL).getMaxAge(), is(24 * 60 * 60));
        // update spec so this that an ETag is not required for services
        assertThat(jsonResp.getHeader(RestfulResponse.Header.ETAG), is(nullValue()));
    }

}
