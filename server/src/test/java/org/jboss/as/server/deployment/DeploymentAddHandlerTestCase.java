/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.server.deployment;

import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.ResultHandler;
import org.jboss.as.server.deployment.api.ContentRepository;
import org.jboss.dmr.ModelNode;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class DeploymentAddHandlerTestCase {
    /**
        {
            "operation" => "add",
            "address" => [("deployment" => "test.war")],
            "content" => [{
                "archive" => true,
                "path" => "${jboss.home}/content/welcome.jar"
            }],
            "runtime-name" => "test-run.war",
            "enabled" => true
        }
     * @throws OperationFailedException
     */
    @Ignore("TODO: JBAS-9020: Archive deployments are not yet implemented")
    @Test
    public void testContent() throws OperationFailedException {
        final ContentRepository contentRepository = null;
        final DeploymentAddHandler handler = new DeploymentAddHandler(contentRepository);
        final OperationContext context = null;
        final ModelNode operation = new ModelNode();
        //operation.get("address").setEmptyList().get(0).get("deployment").set("test.war");
        operation.get("address").get(0).setExpression("deployment", "test.war");
        operation.get("content").get(0).get("archive").set(true);
        operation.get("content").get(0).get("path").set("test.war");
        final ResultHandler resultHandler = null;
        handler.execute(context, operation, resultHandler);
    }

    @Test (expected = OperationFailedException.class)
    public void testTooMuchContent() throws OperationFailedException {
        final ContentRepository contentRepository = null;
        final DeploymentAddHandler handler = new DeploymentAddHandler(contentRepository);
        final OperationContext context = null;
        final ModelNode operation = new ModelNode();
        //operation.get("address").setEmptyList().get(0).get("deployment").set("test.war");
        operation.get("address").get(0).setExpression("deployment", "test.war");
        operation.get("content").get(0).get("archive").set(true);
        operation.get("content").get(0).get("path").set("test.war");
        operation.get("content").add("muck");
        final ResultHandler resultHandler = null;
        handler.execute(context, operation, resultHandler);
    }

    @Test
    public void testValidator() throws OperationFailedException {
        final ContentRepository contentRepository = null;
        final DeploymentAddHandler handler = new DeploymentAddHandler(contentRepository);
        final OperationContext context = null;
        final ModelNode operation = new ModelNode();
        operation.get("content").get(0).get("archive").set("wrong");
        final ResultHandler resultHandler = null;
        try {
            handler.execute(context, operation, resultHandler);
        } catch (OperationFailedException e) {
            // TODO: check exception
        }
    }
}
