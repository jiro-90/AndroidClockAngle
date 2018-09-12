package za.co.kva.clock.clockangles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Anesu on 2018/09/11.
 */

class CircleView extends View {

    private float minuteAngle = 0;
    private float hourAngle = 0;
    private float angleIncrement = 2;
    private float tempMinuteAngle = 0;
    private float tempHourAngle = 0;
    private final float ERROR = 0.01f;
    private int radius;
    private Point clock_centre;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint =  new Paint();
        paint.setColor(Color.LTGRAY);
        canvas.drawPaint(paint);

        drawClock(canvas, paint);
        moveAngles();
    }

    private void moveAngles()
    {
        //hours
        if (Math.abs(tempHourAngle - hourAngle) > 0.01)
        {
            if (tempHourAngle < hourAngle)
            {
                tempHourAngle += angleIncrement;
                if (tempHourAngle > hourAngle)
                    tempHourAngle = hourAngle;
            }
            else
            {
                tempHourAngle += angleIncrement;
                if (tempHourAngle > 360)
                    tempHourAngle = 0;
            }
            invalidate();
        }

        //minutes
        if (Math.abs(tempMinuteAngle - minuteAngle) > 0.01)
        {
            if (tempMinuteAngle < minuteAngle)
            {
                tempMinuteAngle += angleIncrement;
                if (tempMinuteAngle > minuteAngle)
                    tempMinuteAngle = minuteAngle;
            }
            else
            {
                tempMinuteAngle += angleIncrement;
                if (tempMinuteAngle > 360)
                    tempMinuteAngle = 0;
            }
            invalidate();
        }
    }

    public void draw()
    {
        invalidate();
        requestLayout();
    }

    public void minuteAngle(float angle)
    {
        minuteAngle = angle;
    }

    public void hourAngle(float angle)
    {
        hourAngle = angle;
    }

    private void drawTimeSeparator(Canvas canvas, Paint paint)
    {
        final int MAIN_TIME_SEPARATOR_LENGTH = 30;
        final int SECONDARY_TIME_SEPARATOR_LENGTH = 10;
        final int TIME_SEPARATOR_WIDTH = 3;

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(TIME_SEPARATOR_WIDTH);

        for (int i=0; i < 360; i+= 30)
        {
            int minorRadius = radius - SECONDARY_TIME_SEPARATOR_LENGTH;
            if ((i % 90) == 0)
                minorRadius = radius - MAIN_TIME_SEPARATOR_LENGTH;

            canvas.drawLine((float) (clock_centre.x + minorRadius * Math.sin(Math.toRadians(i))),
                    (float) (clock_centre.y - minorRadius * Math.cos(Math.toRadians(i))),
                    (float) (clock_centre.x + radius * Math.sin(Math.toRadians(i))),
                    (float) (clock_centre.y - radius * Math.cos(Math.toRadians(i))),
                    paint
            );
        }
    }

    private void drawClock(Canvas canvas, Paint paint)
    {
        final int X_MARGIN = 100;
        radius = (getWidth() - X_MARGIN)/2;
        final int MINUTE_HAND_LENGTH = radius - 30;
        final int MINUTE_HAND_WIDTH = 6;

        final int HOUR_HAND_LENGTH = (int) (0.66 * MINUTE_HAND_LENGTH);
        final int HOUR_HAND_WIDTH = 10;
        clock_centre = new Point(getWidth()/2, getHeight()/2);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(clock_centre.x, clock_centre.y, radius, paint);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(MINUTE_HAND_WIDTH);
        canvas.drawLine(clock_centre.x,
                clock_centre.y,
                (float) (clock_centre.x + MINUTE_HAND_LENGTH * Math.sin(Math.toRadians(tempMinuteAngle))),
                (float) (clock_centre.y - MINUTE_HAND_LENGTH * Math.cos(Math.toRadians(tempMinuteAngle))),
                paint
        );
        paint.setStrokeWidth(HOUR_HAND_WIDTH);
        canvas.drawLine(clock_centre.x,
                clock_centre.y,
                (float) (clock_centre.x + HOUR_HAND_LENGTH * Math.sin(Math.toRadians(tempHourAngle))),
                (float) (clock_centre.y - HOUR_HAND_LENGTH * Math.cos(Math.toRadians(tempHourAngle))),
                paint
        );
        drawTimeSeparator(canvas, paint);
    }
}
