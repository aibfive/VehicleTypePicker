package com.sandu.vehicletypepicker.picker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.sandu.vehicletypepicker.R;

/**
 * VehicleTypePicker
 *
 * @author lizewu
 * @date 2018/12/11
 */
public class SideIndexBar extends View {

    private int textSize = 14;
    private int textColor = Color.BLACK;
    private int textSelectedColor = Color.RED;
    private String[] indexs = new String[]{
            "#", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public SideIndexBar(Context context) {
        super(context);
        initAttribute(null, 0);
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(attrs, 0);
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(attrs, defStyleAttr);
    }

    private void initAttribute(AttributeSet attrs, int defStyle){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SideIndexBar, defStyle, 0);
        textSize = array.getDimensionPixelSize(R.styleable.SideIndexBar_textSize, textSize);
        textColor = array.getColor(R.styleable.SideIndexBar_textColor, textColor);
        textSelectedColor = array.getColor(R.styleable.SideIndexBar_textSelectedColor, textSelectedColor);
        indexs = (String[])array.getTextArray(R.styleable.SideIndexBar_indexs);
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
