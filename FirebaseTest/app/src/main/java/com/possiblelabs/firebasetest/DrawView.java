package com.possiblelabs.firebasetest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by possiblelabs on 7/21/15.
 */
public class DrawView extends View {

    private static final String TAG = "DrawView";

    Paint paint = new Paint();

    private float x;
    private float y;

    private float maxY = 1000;
    private float maxX = 1000;

    private int STROKE = 3;
    private int WIDTH = 50;
    private int HEIGHT = 50;

    private int STEP = 10;


    public DrawView(Context context) {
        super(context);
        x = 0;
        y = 0;
    }

    @Override
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(STROKE);
        canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.CYAN);
        canvas.drawRect(x + STROKE, y + STROKE, x + WIDTH - STROKE, y + HEIGHT - STROKE, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(x + STROKE, y + STROKE, x + WIDTH - STROKE, y + (HEIGHT - STROKE) / 2f, paint);

    }

    public void setPosition(float newX, float newY) {
        if (newX > maxX) {
            x = maxX;
        } else if (newX < 0) {
            x = 0;
        } else {
            x = newX;
        }

        if (newY > maxY) {
            y = maxY;
        } else if (newY < 0) {
            y = 0;
        } else {
            y = newY;
        }
        invalidate();
    }

    public void up() {
        if (y - STEP < 0)
            return;

        y -= STEP;
        invalidate();
    }

    public void down() {
        if (y + STEP > maxY)
            return;

        y += STEP;
        invalidate();
    }

    public void left() {
        if (x - STEP < 0)
            return;

        x -= STEP;
        invalidate();
    }

    public void right() {
        if (x + STEP > maxX)
            return;
        x += STEP;
        invalidate();
    }

    public String pointToString() {
        return ((int) x) + "," + ((int) y);
    }

}
