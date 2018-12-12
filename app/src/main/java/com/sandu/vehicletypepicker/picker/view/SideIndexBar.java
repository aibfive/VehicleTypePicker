package com.sandu.vehicletypepicker.picker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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

    private OnIndexSelectChangedListener listener;

    private float textSize = 16;
    private int textNormalColor = Color.BLACK;
    private int textSelectedColor = Color.RED;
    private String[] indexs = new String[]{
            "#", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private int width, height;
    private float divider;
    private Paint normalPaint, selectedPaint;

    private int currentIndex = -1;

    public SideIndexBar(Context context) {
        super(context);
        initAttribute(null, 0);
        initPaint();
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(attrs, 0);
        initPaint();
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(attrs, defStyleAttr);
        initPaint();
    }

    private void initAttribute(AttributeSet attrs, int defStyle){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SideIndexBar, defStyle, 0);
        textSize = array.getDimension(R.styleable.SideIndexBar_textSize, textSize);
        textNormalColor = array.getColor(R.styleable.SideIndexBar_textNormalColor, textNormalColor);
        textSelectedColor = array.getColor(R.styleable.SideIndexBar_textSelectedColor, textSelectedColor);
        String[] indexs = (String[])array.getTextArray(R.styleable.SideIndexBar_indexs);
        if(indexs != null){
            this.indexs = indexs;
        }
        array.recycle();
    }

    private void initPaint(){
        normalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        normalPaint.setTextSize(textSize);
        normalPaint.setColor(textNormalColor);

        selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedPaint.setTextSize(textSize);
        selectedPaint.setColor(textSelectedColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        divider = (height - (textSize * indexs.length)) / (indexs.length + 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int indexLength = indexs.length;

        for(int i = 0; i < indexLength; i++){
            float textWidth = normalPaint.measureText(indexs[i]);
            canvas.drawText(
                    indexs[i], (width - textWidth) / 2.0f,
                    (i + 1) * (divider + textSize), i == currentIndex ? selectedPaint : normalPaint
            );
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index = (int)(y / (divider + textSize));
                if(containYInArea(y, index)){
                    if(index != currentIndex){
                        if(listener != null){
                            listener.onSelectChanged(indexs[index], index);
                        }
                        currentIndex = index;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                currentIndex = -1;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 判断触及点是否在索引区域内
     * @param y
     * @param index
     * @return
     */
    private boolean containYInArea(float y, int index){
        /**
         * (index + 1) * divider + index * textSize--索引字符串的top位置
         * (index + 1) * (divider + textSize)--索引字符串的bottom位置
         * divider / 2.0f--之所以对这个值进行减加是为了扩大索引的点击有效范围
         *
         * 如理解困难，可以绘画矩形RectF显示，这样比较明显，方便。
         */
        if(y >= (index + 1) * divider + index * textSize - divider / 2.0f
                && y <= (index + 1) * (divider + textSize) +  divider / 2.0f){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 索引选择改变监听器
     */
    public interface OnIndexSelectChangedListener{
        /**
         * 索引选择改变触发
         * @param index
         * @param position
         */
        void onSelectChanged(String index, int position);
    }

    /**
     * 设置索引选择改变监听器
     * @param listener
     */
    public void setOnIndexSelectChangedListener(OnIndexSelectChangedListener listener){
        this.listener = listener;
    }

}
