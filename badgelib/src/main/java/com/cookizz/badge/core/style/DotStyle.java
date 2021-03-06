package com.cookizz.badge.core.style;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.cookizz.badge.core.mutable.BadgeMutable;

/**
 * Base class for dot badge style (template method)
 * Created by Cookizz on 2015/9/17.
 */
public abstract class DotStyle extends AbsBadgeStyle {

    private final int radius;
    private final Paint paint;

    public DotStyle(Context context) {
        super(context);

        // calculate screen-adapted values
        offset.x *= screenAdaptionScale;
        offset.y *= screenAdaptionScale;
        radius = (int) (getRadius() * screenAdaptionScale);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getBackgroundColor(context));
    }

    @Override
    public final void apply(Canvas canvas, Rect rect, BadgeMutable mutable) {

        if(mutable.isAttached() && mutable.isShown()) {
            final int centerX, centerY;
            if(gravity.x == 0) {
                centerX = offset.x + (rect.right + rect.left) / 2;
            }
            else {
                centerX = offset.x + (gravity.x > 0 ? (rect.right - radius) : (rect.left + radius));
            }
            if(gravity.y == 0) {
                centerY = offset.y + (rect.bottom + rect.top) / 2;
            }
            else {
                centerY = offset.y + (gravity.y > 0 ? (rect.bottom - radius) : (rect.top + radius));
            }
            canvas.drawCircle(centerX, centerY, radius, paint);
        }
    }

    public abstract int getBackgroundColor(Context context);

    public abstract int getRadius();
}
