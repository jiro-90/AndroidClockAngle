package za.co.kva.clock.clockangles;

import java.text.DecimalFormat;

/**
 * Created by Tatenda on 2018/09/11.
 */

public class Clock {
    private int minutes;
    private int hours;

    public Clock(int min, int h)
    {
        minutes = min;
        hours = h;
        if (minutes > 59 || minutes < 0) minutes = 0;
        if (hours > 11 || hours < 0) hours = 0;
    }

    public Clock()
    {
        minutes = 0;
        hours = 0;
    }

    public void reset()
    {
        minutes = 0;
        hours = 0;
    }

    public void minutes(int min)
    {
        minutes = min;
        if (minutes > 59 || minutes < 0) minutes = 0;
    }

    public void hours(int h)
    {
        hours = h;
        if (hours > 11 || hours < 0) hours = 0;
    }

    public String angle()
    {
        float minuteAngle = minutes/60.0f * 360;
        float hourAngle = hours/12.0f * 360 + minutes/60.0f * 30;
        float diff = Math.abs(hourAngle - minuteAngle);
        if (diff > 180) diff = 360 - diff;
        DecimalFormat df1 = new DecimalFormat("#.#");
        return df1.format(diff);
    }

    public float minuteAngle()
    {
        float minuteAngle = minutes/60.0f * 360;
        return round(minuteAngle, 1);
    }

    public float hourAngle()
    {
        float hourAngle = hours/12.0f * 360 + minutes/60.0f * 30;
        return round(hourAngle, 1);
    }

    private float round (float value, int precision)
    {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }
}
