package com.example.nasa_taskmaster;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawingViewCalendarScreen extends View {
    private Paint paint;

    public DrawingViewCalendarScreen(Context context) {
        super(context);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE); // Set color
        paint.setStrokeWidth(10); // Set thickness
        paint.setStyle(Paint.Style.STROKE); // Outline
        paint.setAntiAlias(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw a circle: x, y, radius, paint
        canvas.drawCircle(300, 300, 100, paint);
        // Draw a line: startX, startY, stopX, stopY, paint
        canvas.drawLine(0, 0, 500, 500, paint);
    }
}
