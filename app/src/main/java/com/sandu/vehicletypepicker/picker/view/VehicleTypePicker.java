package com.sandu.vehicletypepicker.picker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sandu.vehicletypepicker.R;

/**
 * VehicleTypePicker
 *
 * @author lizewu
 * @date 2018/12/11
 */
public class VehicleTypePicker extends FrameLayout{

    private float indexBarWidth;
    private float indexSize;
    private int indexNormalColor;
    private int indexSelectedColor;
    private String[] indexs = new String[]{
            "#", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public VehicleTypePicker(@NonNull Context context) {
        super(context);
        initAttribute(null, 0);
    }

    public VehicleTypePicker(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(attrs, 0);
    }

    public VehicleTypePicker(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(attrs, defStyleAttr);
    }

    private void initAttribute(AttributeSet attrs, int defStyle){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.VehicleTypePicker, defStyle, 0);
        indexBarWidth = array.getDimension(R.styleable.VehicleTypePicker_indexBarWidth, indexBarWidth);
        indexSize = array.getDimension(R.styleable.VehicleTypePicker_indexSize, indexSize);
        indexNormalColor = array.getColor(R.styleable.VehicleTypePicker_indexNormalColor, indexNormalColor);
        indexSelectedColor = array.getColor(R.styleable.VehicleTypePicker_indexSelectedColor, indexSelectedColor);
        /*String[] indexs = (String[])array.getTextArray(R.styleable.VehicleTypePicker_indexs);
        if(indexs != null){
            this.indexs = indexs;
        }*/
        array.recycle();
    }


}
