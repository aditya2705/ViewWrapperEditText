package com.adityarathi.viewwrapperedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Aditya Rathi on 20-Dec-15.
 */
public class ViewTextWrapperLayout extends LinearLayout {

    /**
     * The size of the divider in pixels.
     */
    private int mDividerSpace = 0;

    private ArrayList<CustomEditText> editTextArrayList;
    private ArrayList<View> customViewArrayList;
    private ArrayList<View> viewArrayList;

    private int focusedEditTextPointer = 0;

    private int removePointer;

    private CustomEditText bodyMainEditText;

    private String bodyMainHint="";

    private boolean generateSameTypeOfEditTexts = true;

    public boolean isGenerateSameTypeOfEditTexts() {
        return generateSameTypeOfEditTexts;
    }

    public void setGenerateSameTypeOfEditTexts(boolean generateSameTypeOfEditTexts) {
        this.generateSameTypeOfEditTexts = generateSameTypeOfEditTexts;
    }

    private OnFocusChangeListener focusListener = new OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                focusedEditTextPointer = viewArrayList.indexOf(v);
                Log.d("Pointer4",focusedEditTextPointer+"");
            }
        }
    };

    public ViewTextWrapperLayout(Context context) {
        this(context, null);
    }

    public ViewTextWrapperLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    @Override
    public void setOrientation(int orientation) {
        // enforce VERTICAL orientation; remove if HORIZONTAL support is ever added
        if (LinearLayout.HORIZONTAL == orientation) {
            throw new IllegalArgumentException("ViewTextWrapperLayout must be VERTICAL.");
        }
        super.setOrientation(orientation);
    }

    private void initialize(Context context, AttributeSet attrs){

        viewArrayList = new ArrayList<>();
        customViewArrayList = new ArrayList<>();
        editTextArrayList = new ArrayList<>();
        focusedEditTextPointer =0;

        setOrientation(LinearLayout.VERTICAL);

        if(attrs!=null){

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewTextWrapperLayout);

            if (ta != null) {

                mDividerSpace = ta.getDimensionPixelSize(R.styleable.ViewTextWrapperLayout_arathiDividerSpace, 0);

            }

            ta.recycle();

            bodyMainEditText = new CustomEditText(getContext());
            bodyMainEditText.setOnFocusChangeListener(focusListener);
            bodyMainEditText.setHint(bodyMainHint);
            bodyMainEditText.setGravity(Gravity.TOP);
            viewArrayList.add(bodyMainEditText);
            addView(bodyMainEditText,0);

        }

    }

    public void addViewWithinText(View view){

        CustomEditText nextEditText;
        nextEditText = new CustomEditText(getContext());
        additionOperationOfTheViews(view,nextEditText);

    }

    public void addViewWithinText(View view, CustomEditText nextEditText){

        additionOperationOfTheViews(view,nextEditText);

    }

    private void additionOperationOfTheViews(View view,CustomEditText nextEditText){

        Log.d("Pointer1",focusedEditTextPointer+"");

        int cursorPosition;
        CustomEditText previousEditText;

        if(focusedEditTextPointer >=0&& focusedEditTextPointer <viewArrayList.size()&&viewArrayList.get(focusedEditTextPointer) instanceof CustomEditText)
            previousEditText = (CustomEditText) viewArrayList.get(focusedEditTextPointer);
        else
            return;


        nextEditText.setOnFocusChangeListener(focusListener);

        cursorPosition = previousEditText.getCursorPositionEnd();

        CharSequence cursorToEnd, remainingText, existingText;

        existingText = previousEditText.getText();
        if ((cursorPosition) < existingText.length())
            cursorToEnd = existingText.subSequence(cursorPosition, existingText.length());
        else
            cursorToEnd = "";


        remainingText = existingText.subSequence(0, cursorPosition);
        previousEditText.setText(remainingText);
        nextEditText.setText(cursorToEnd);

        viewArrayList.add(focusedEditTextPointer +1, view);
        addView(view, focusedEditTextPointer +1);

        viewArrayList.add(focusedEditTextPointer + 2, nextEditText);
        addView(nextEditText, focusedEditTextPointer + 2);

        if(previousEditText.getText().toString().equals("")){
            removeView(previousEditText);
            viewArrayList.remove(previousEditText);
            if(nextEditText.isFocused()){
                focusedEditTextPointer = viewArrayList.indexOf(nextEditText);
            }
        }

        Log.d("Pointer2",focusedEditTextPointer+"");


    }

    public void deleteViewWithinText(View view){

        Log.d("Pointer3",focusedEditTextPointer+"");

        removePointer = viewArrayList.indexOf(view);

        Log.d("REMOVE POINTER", removePointer+"");

        boolean mergeEditTexts=false, bodyTopMergeBelow=false;

        if(removePointer>0&&removePointer<viewArrayList.size()&&
                viewArrayList.get(removePointer - 1) instanceof CustomEditText &&
                viewArrayList.get(removePointer + 1) instanceof CustomEditText){

            mergeEditTexts = true;

        }else if(removePointer==0&&viewArrayList.get(removePointer + 1) instanceof CustomEditText){

            bodyTopMergeBelow=true;
        }

        if (mergeEditTexts) {

            CustomEditText editTextAbove = (CustomEditText) viewArrayList.get(removePointer - 1);
            CustomEditText editTextBelow = (CustomEditText) viewArrayList.get(removePointer + 1);
            CharSequence charSequence1 = editTextAbove.getText();
            CharSequence charSequence2 = editTextBelow.getText();
            String combined = charSequence1.toString().concat(charSequence2.toString());
            editTextAbove.setText(combined);
            removeViewAt(removePointer);
            viewArrayList.remove(removePointer);
            removeViewAt(removePointer);
            viewArrayList.remove(removePointer);


        } else {


            if(bodyTopMergeBelow) {

                bodyMainEditText = (CustomEditText) viewArrayList.get(removePointer + 1);
                bodyMainEditText.setHint(bodyMainHint);

            }

            if((removePointer-1)>=0 && viewArrayList.get(removePointer-1) instanceof CustomEditText){
                focusedEditTextPointer = removePointer-1;
            }else{
                focusedEditTextPointer = removePointer;
            }

            removeViewAt(removePointer);
            viewArrayList.remove(removePointer);



        }


        Log.d("Pointer4",focusedEditTextPointer+"");


    }








}
