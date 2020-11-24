package com.zr.wanandroid.test.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;

public class BgSpan extends ReplacementSpan implements Cloneable {
    @Override
    public BgSpan clone() {
        try {
            return (BgSpan) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_TOP = 1;
    public static final int ALIGN_CENTER = 2;

    private float marginLeft;
    private float marginTop;
    private float marginRight;
    private float marginBottom;

    private float paddingLeft;
    private float paddingRight;

    private float radiusLeftTop;
    private float radiusLeftBottom;
    private float radiusRightTop;
    private float radiusRightBottom;
    private int borderColor;
    private float borderWidth;
    private float borderDashGap;
    private float borderDashLength;
    private int bgColor;
    private int showAlign;
    private int tempTextColor;
    private int textColor;

    private int itemWidth;
    private Rect textRect = new Rect();
    private Path path = new Path();
    private Path clipPath = new Path();
    private RectF pathRect;
    private Paint.FontMetrics fontMetrics;

    public BgSpan() {
        marginLeft = 0;
        marginTop = 0;
        marginRight = 0;
        marginBottom = 0;
        paddingLeft = 0;
        paddingRight = 0;
        radiusLeftTop = 0;
        radiusLeftBottom = 0;
        radiusRightTop = 0;
        radiusRightBottom = 0;
        borderColor = 0;
        borderWidth = 0;
        borderDashGap = 0;
        borderDashLength = 0;
        bgColor = 0;
        showAlign = ALIGN_BOTTOM;
        tempTextColor = 0;
        textColor = 0;

    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        float v = paint.measureText(text, start, end);
        itemWidth = (int) (v + marginLeft + marginRight + paddingLeft + paddingRight);

        return itemWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        if (pathRect == null) {
            pathRect = new RectF();
        }
        tempTextColor = paint.getColor();
        pathRect.left = x + marginLeft;
        pathRect.top = (top + marginTop);
        pathRect.right = x + itemWidth - marginRight;
        pathRect.bottom = bottom - marginBottom;

        int count = -1;
        if (borderWidth > 0) {
            count = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        }
        /*计算背景范围*/
        if (!path.isEmpty()) {
            path.reset();
        }
        path.addRoundRect(pathRect, new float[]{radiusLeftTop, radiusLeftTop, radiusLeftBottom, radiusLeftBottom, radiusRightTop, radiusRightTop, radiusRightBottom, radiusRightBottom,}, Path.Direction.CW);
        /*绘制背景*/
        if (bgColor != Color.TRANSPARENT) {
            paint.setColor(bgColor);
            canvas.drawPath(path, paint);
        }
        if (borderWidth > 0) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderWidth * 2);
            paint.setColor(borderColor);

            if (bgColor != Color.TRANSPARENT) {
                /*清除边框下的背景*/
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                canvas.drawPath(path, paint);
            }
            /*绘制边框*/
            paint.setXfermode(null);
            if (borderDashGap > 0 && borderDashLength > 0) {
                paint.setPathEffect(new DashPathEffect(new float[]{borderDashLength, borderDashGap}, 0));
            }
            canvas.drawPath(path, paint);
            paint.setPathEffect(null);

            /*去掉内容显示区域外部边框*/
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            if (!clipPath.isEmpty()) {
                clipPath.reset();
            }
            clipPath.addRect(new RectF(x - borderWidth - 1, top - borderWidth - 1, x + itemWidth + borderWidth + 1, bottom + borderWidth + 1), Path.Direction.CW);
            clipPath.op(clipPath, path, Path.Op.DIFFERENCE);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(clipPath, paint);
            paint.setXfermode(null);
        }
        if (count != -1) {
            canvas.restoreToCount(count);
        }

        paint.setStrokeWidth(0);

        /*绘制文字*/
        if (textColor == Color.TRANSPARENT) {
            textColor = tempTextColor;
        }
        paint.setColor(textColor);
        if (showAlign == ALIGN_BOTTOM) {
            canvas.drawText(text, start, end, x + paddingLeft + marginLeft, y, paint);
        } else if (showAlign == ALIGN_CENTER) {
            fontMetrics = paint.getFontMetrics();
            float ascent = fontMetrics.ascent;
            float descent = fontMetrics.descent;
            canvas.drawText(text, start, end, x + paddingLeft + marginLeft, pathRect.centerY() - (descent + ascent) * 1f / 2 + 1.5f, paint);
        } else {
            paint.getTextBounds(text.toString(), start, end, textRect);
            int offset = bottom - top - textRect.height();
            canvas.drawText(text, start, end, x + paddingLeft + marginLeft, y - offset / 2, paint);
        }
    }

    /************************************************************************************************************/
    public BgSpan setMargin(float left, float top, float right, float bottom) {
        setMarginLeft(left);
        setMarginTop(top);
        setMarginRight(right);
        setMarginBottom(bottom);
        return this;
    }

    public BgSpan setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public BgSpan setMarginTop(float marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public BgSpan setMarginRight(float marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public BgSpan setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public BgSpan setPadding(float padding) {
        setPadding(padding, padding);
        return this;
    }

    public BgSpan setPadding(float left, float right) {
        setPaddingLeft(left);
        setPaddingRight(right);
        return this;
    }

    public BgSpan setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
        return this;
    }

    public BgSpan setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
        return this;
    }

    public BgSpan setRadius(float radius) {
        setRadiusLeftTop(radius);
        setRadiusRightTop(radius);
        setRadiusLeftBottom(radius);
        setRadiusRightBottom(radius);
        return this;
    }

    public BgSpan setRadiusLeftTop(float radiusLeftTop) {
        this.radiusLeftTop = radiusLeftTop;
        return this;
    }

    public BgSpan setRadiusLeftBottom(float radiusLeftBottom) {
        this.radiusLeftBottom = radiusLeftBottom;
        return this;
    }

    public BgSpan setRadiusRightTop(float radiusRightTop) {
        this.radiusRightTop = radiusRightTop;
        return this;
    }

    public BgSpan setRadiusRightBottom(float radiusRightBottom) {
        this.radiusRightBottom = radiusRightBottom;
        return this;
    }

    public BgSpan setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public BgSpan setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public BgSpan setBorderDashGap(float borderDashGap) {
        this.borderDashGap = borderDashGap;
        return this;
    }

    public BgSpan setBorderDashLength(float borderDashLength) {
        this.borderDashLength = borderDashLength;
        return this;
    }

    public BgSpan setBgColor(@ColorInt int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public BgSpan setShowAlignBottom() {
        this.showAlign = ALIGN_BOTTOM;
        return this;
    }

    public BgSpan setShowAlignTop() {
        this.showAlign = ALIGN_TOP;
        return this;
    }

    public BgSpan setShowAlignCenter() {
        this.showAlign = ALIGN_CENTER;
        return this;
    }

    public BgSpan setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        return this;
    }

}
