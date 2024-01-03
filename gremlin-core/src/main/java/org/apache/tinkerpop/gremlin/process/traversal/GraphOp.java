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
package org.apache.tinkerpop.gremlin.process.traversal;

/**
 * A {@code GraphOp} or "graph operation" is a static {@link Bytecode} form that does not translate to a traversal
 * but instead refers to a specific function to perform on a graph instance.
 */
public enum GraphOp {

    /**
     * Commit a transaction.
     */
    TX_COMMIT(new Bytecode("tx", "commit")),

    /**
     * Rollback a transaction.
     */
    TX_ROLLBACK(new Bytecode("tx", "rollback"));

    private final Bytecode bytecode;

    GraphOp(final Bytecode bc) {
        this.bytecode = bc;
    }

    /**
     * Gets the {@link Bytecode} that represents this graph operation. There is no notion of immutable bytecode
     * instances so it is important that the object returned here is not modified. If they are changed, the operations
     * will no longer be recognized. In a future version, we should probably introduce the concept of immutable
     * bytecode to prevent this possibility - https://issues.apache.org/jira/browse/TINKERPOP-2545
     */
    public Bytecode getBytecode() {
        return bytecode;
    }

    public boolean equals(final Bytecode bc) {
        return bytecode.equals(bc);
    }
}
