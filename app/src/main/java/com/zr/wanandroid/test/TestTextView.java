package com.zr.wanandroid.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TestTextView extends View {

    private float top;
    private float bottom;
    private float ascent;
    private float descent;
    private float leading;

    public TestTextView(Context context) {
        super(context);
        init();
    }


    public TestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private String text;
    private Paint paint;
    private void init() {
        text="王H钟sSDefFjgJgG";
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(120);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        top = fontMetrics.top;
        bottom = fontMetrics.bottom;
        ascent = fontMetrics.ascent;
        descent = fontMetrics.descent;
        leading = fontMetrics.leading;

        Log.i("=====","====top====="+top);
        Log.i("=====","====bottom=="+bottom);
        Log.i("=====","====ascent=="+ascent);
        Log.i("=====","====descent="+descent);
        Log.i("=====","====leading="+leading);

    }

    @Override
    protected void onDraw(Canvas canvas) {
      /*  canvas.translate(100,getHeight()/2);
        canvas.drawText(text,0,0,paint);

        canvas.drawLine(0,top,getWidth()/1 -50,top,paint);
        canvas.drawLine(0,bottom,getWidth()/1 -50,bottom,paint);

        canvas.drawLine(0,ascent,getWidth()/1 -50,ascent,paint);
        canvas.drawLine(0,descent,getWidth()/1 -50,descent,paint);
        canvas.drawLine(0,leading,getWidth()/1-50,leading,paint);

        paint.setColor(Color.RED);
        canvas.drawLine(0,0,getWidth()/4-50,0,paint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(0,(ascent+descent)/2,getWidth()/4-50,(ascent+descent)/2,paint);
*/

        int i = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);
//        canvas.drawBitmap(getDst(),getWidth()/2,getHeight()/2,paint);
//        canvas.drawPath(getDstPath(getWidth()/2,getHeight()/2),paint);
//        canvas.drawBitmap(getSrc(),getWidth()/2,getHeight()/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawRect(new RectF(getWidth()/2,getHeight()/2,getWidth()/2+200,getHeight()/2+200),paint);
//        canvas.drawPath(getSrcPath(getWidth()/2,getHeight()/2),paint);
//        canvas.drawBitmap(getSrc(),getWidth()/2,getHeight()/2,paint);
//        paint.setXfermode(null);
        canvas.restoreToCount(i);
    }


    public Path getDstPath(int centerX,int centerY){
        Path p=new Path();
        p.addCircle(centerX,centerY,100, Path.Direction.CW);
        return p;
    }
    public Path getSrcPath(int centerX,int centerY){
        Path p=new Path();
        p.moveTo(0,0);
        p.moveTo(centerX*2,centerY*2);
        p.addRect(centerX,centerY,200+centerX, 200+centerY,Path.Direction.CW);
        return p;
    }
    public Bitmap getDst(){
        Bitmap bitmap=Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.YELLOW);
        canvas.drawCircle(100,100,100,p);
        return bitmap;
    }
    public Bitmap getSrc(){
        Bitmap bitmap=Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLUE);
        canvas.drawRect(100,100,300,300,p);
        return bitmap;
    }
}
