/*
 * Copyright (C) 2013 Marten Gajda <marten@dmfs.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.dmfs.tasks.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import org.dmfs.tasks.utils.MapOpener;


/**
 * A widget that shows the location. When clicked, it opens maps.
 *
 * @author Gabor Keszthelyi
 */
public class LocationFieldView extends TextFieldView
{
    private GestureDetector mGestureDetector;
    private MapOpener mMapOpener;


    public LocationFieldView(Context context)
    {
        super(context);
        init();
    }


    public LocationFieldView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }


    public LocationFieldView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }


    private void init()
    {
        mMapOpener = new MapOpener(getContext());
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                onClicked();
                return false;
            }
        });
    }


    /*
     * Note: Should have been easier with a simple OnClickListener,
     * but that didn't work with any android:clickable and android:focusable variations on the ViewGroup and its children.
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        mGestureDetector.onTouchEvent(event);
        return false;
    }


    public void onClicked()
    {
        mMapOpener.openMapWithLocation(getText());
    }

    // TODO onTouch animation would be nice
}