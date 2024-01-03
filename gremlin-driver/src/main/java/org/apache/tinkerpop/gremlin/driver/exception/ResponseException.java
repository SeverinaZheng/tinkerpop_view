/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.gremlin.driver.exception;

import org.apache.tinkerpop.gremlin.util.message.ResponseStatusCode;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class ResponseException extends Exception {
    private final ResponseStatusCode responseStatusCode;
    private final String remoteStackTrace;
    private final List<String> remoteExceptionHierarchy;
    private final Map<String,Object> attributes;

    public ResponseException(final ResponseStatusCode responseStatusCode, final String serverMessage) {
        this(responseStatusCode, serverMessage, null, null);
    }

    public ResponseException(final ResponseStatusCode responseStatusCode, final String serverMessage,
                             final List<String> remoteExceptionHierarchy, final String remoteStackTrace) {
        this(responseStatusCode, serverMessage, remoteExceptionHierarchy, remoteStackTrace, null);
    }

    public ResponseException(final ResponseStatusCode responseStatusCode, final String serverMessage,
                             final List<String> remoteExceptionHierarchy, final String remoteStackTrace,
                             final Map<String,Object> statusAttributes) {
        super(serverMessage);
        this.responseStatusCode = responseStatusCode;
        this.remoteExceptionHierarchy = remoteExceptionHierarchy != null ? Collections.unmodifiableList(remoteExceptionHierarchy) : null;
        this.remoteStackTrace = remoteStackTrace;
        this.attributes = statusAttributes != null ? Collections.unmodifiableMap(statusAttributes) : null;
    }

    public ResponseStatusCode getResponseStatusCode() {
        return responseStatusCode;
    }

    /**
     * The stacktrace produced by the remote server.
     */
    public Optional<String> getRemoteStackTrace() {
        return Optional.ofNullable(remoteStackTrace);
    }

    /**
     * The list of exceptions generated by the server starting with the top-most one followed by its "cause". That
     * cause is then followed by its cause and so on down the line.
     */
    public Optional<List<String>> getRemoteExceptionHierarchy() {
        return Optional.ofNullable(remoteExceptionHierarchy);
    }

    /**
     * Gets any status attributes from the response.
     */
    public Optional<Map<String, Object>> getStatusAttributes() {
        return Optional.ofNullable(attributes);
    }
}