package com.adityarathi.viewwrapperedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Aditya Rathi on 06-Dec-15.
 */
public class CustomEditText extends EditText {

    int cursorPositionStart =0, cursorPositionEnd = 0;

    public CustomEditText(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);

    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomEditText(Context context) {
        super(context);

    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        cursorPositionStart = selStart;
        cursorPositionEnd = selEnd;
    }

    public int getCursorPositionStart(){
        return cursorPositionStart;
    }

    public int getCursorPositionEnd(){
        return cursorPositionEnd;
    }


}
