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
package org.eclipse.che.ide.api.resources.marker.event;

import com.google.common.annotations.Beta;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.eclipse.che.ide.api.resources.Resource;
import org.eclipse.che.ide.api.resources.marker.Marker;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Marker created event. This event fires when marker has been deleted.
 * Deleted, means that marker has unbound from the resource.
 *
 * @author Vlad Zhukovskiy
 * @see Marker
 * @since 4.0.0-RC14
 */
@Beta
public class MarkerDeletedEvent extends GwtEvent<MarkerDeletedEvent.MarkerDeletedHandler> {

    /**
     * A marker create listener is notified of marker removal from the specified resource.
     * <p/>
     * Third party components may implement this interface to handle marker removal event.
     */
    public interface MarkerDeletedHandler extends EventHandler {

        /**
         * Notifies the listener that some marker has been deleted.
         *
         * @param event
         *         instance of {@link MarkerDeletedEvent}
         * @see MarkerDeletedEvent
         * @since 4.0.0-RC14
         */
        void onMarkerDeleted(MarkerDeletedEvent event);
    }

    private static Type<MarkerDeletedHandler> TYPE;

    public static Type<MarkerDeletedHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<>();
        }
        return TYPE;
    }

    private Resource resource;
    private Marker   marker;

    public MarkerDeletedEvent(Resource resource, Marker marker) {
        this.resource = checkNotNull(resource, "Resource should not be a null");
        this.marker = checkNotNull(marker, "Marker should not be a null");
    }

    /**
     * Returns the resource which is the host of specified marker.
     *
     * @return the resource
     * @since 4.0.0-RC14
     */
    public final Resource getResource() {
        return resource;
    }

    /**
     * Returns the marker which is unbounded from resource provided by {@link #getResource()}.
     *
     * @return the marker
     * @see Marker
     * @since 4.0.0-RC14
     */
    public Marker getMarker() {
        return marker;
    }

    /** {@inheritDoc} */
    @Override
    public Type<MarkerDeletedHandler> getAssociatedType() {
        return TYPE;
    }

    /** {@inheritDoc} */
    @Override
    protected void dispatch(MarkerDeletedHandler handler) {
        handler.onMarkerDeleted(this);
    }

}
