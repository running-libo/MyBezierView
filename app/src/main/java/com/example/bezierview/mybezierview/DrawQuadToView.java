package com.example.bezierview.mybezierview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by libo on 2017/11/28.
 */

public class DrawQuadToView extends View{
    private int eventX,eventY;
    private int centerX,centerY;
    private int startX,startY;
    private int endX,endY;
    private Paint paint;

    public DrawQuadToView(Context context) {
        super(context);
        init();
    }

    public DrawQuadToView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    //测量大小完成以后回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w/2;
        centerY = h/2;
        startX = centerX-250;
        startY = centerY;
        endX = centerX + 250;
        endY = centerY;
        eventX = centerX;
        eventY = centerY - 250;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.GRAY);
        //画3个点
        canvas.drawCircle(startX,startY,8,paint);
        canvas.drawCircle(endX,endY,8,paint);
        canvas.drawCircle(eventX,eventY,8,paint);

        //绘制连线
        paint.setStrokeWidth(3);
        canvas.drawLine(startX,centerY,eventX,eventY,paint);
        canvas.drawLine(endX,centerY,eventX,eventY,paint);

        //画二阶贝塞尔曲线
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(startX,startY);
        path.quadTo(eventX,eventY,endX,endY);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                eventX = (int) event.getX();
                eventY = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }

}
