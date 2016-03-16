/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.api.promises.client.js;

import elemental.js.util.JsArrayOf;
import elemental.util.ArrayOf;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayMixed;

import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.api.promises.client.PromiseError;
import org.eclipse.che.api.promises.client.PromiseProvider;

/**
 * @author Vlad Zhukovskyi
 */
public class JsPromiseProvider implements PromiseProvider {
    @Override
    public native <V> Promise<V> create(Executor<V> conclusion) /*-{
        return new Promise(conclusion);
    }-*/;

    @Override
    public native Promise<JsArrayMixed> all(ArrayOf<Promise<?>> promises) /*-{
        return Promise.all(promises);
    }-*/;

    @Override
    public Promise<JsArrayMixed> all(Promise<?>... promises) {
        final JsArrayOf<Promise<?>> promisesArray = JavaScriptObject.createArray().cast();
        for (final Promise<?> promise : promises) {
            promisesArray.push(promise);
        }
        return all(promisesArray);
    }

    @Override
    public native <U> Promise<U> reject(String message) /*-{
        return Promise.reject(new Error(message));
    }-*/;

    @Override
    public native <U> Promise<U> resolve(U value) /*-{
        return Promise.resolve(value);
    }-*/;

    @Override
    public <U> Promise<U> reject(Throwable reason) {
        return reject(JsPromiseError.create(reason));
    }

    public final native <U> JsPromise<U> reject(PromiseError reason) /*-{
        return Promise.reject(reason);
    }-*/;
}
