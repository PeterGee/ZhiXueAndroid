package com.risenb.studyknowledge.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RadioButton;

/**
 * Created by zhuzh on 2017/9/18.
 */

public class NotifyRadioButton extends RadioButton {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float radius;
    boolean notify;

    public NotifyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
        radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 8.0f, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (notify) {
            Drawable drawable = getCompoundDrawables()[1];
            Rect bounds = drawable.getBounds();
            //float cx, float cy, float radius, @NonNull Paint paint
            float cx = getMeasuredWidth() / 2 + bounds.width() / 2 - radius / 2;
            float cy = getPaddingTop() + bounds.height() / 4;
            canvas.drawCircle(cx, cy, radius, paint);
        }
    }

    /**
     * 新消息提醒
     *
     * @param notify
     */
    public void notify(boolean notify) {
        this.notify = notify;
        invalidate();
    }

}
