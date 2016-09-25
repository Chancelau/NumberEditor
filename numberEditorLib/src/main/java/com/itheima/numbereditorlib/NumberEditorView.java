package com.itheima.numbereditorlib;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by CC on 2016/9/24.
 */
public class NumberEditorView extends LinearLayout {

    /**
     * 大于设定最大值
     */
    public static final int MORE_THAN_MAX = 100;
    /**
     * 小于最设定小值
     */
    public static final int LESS_THAN_MIN = 200;
    public OnNumberEditorClickListener onNumberEditorClickListener;
    protected int mNumber;
    protected boolean mIsNumEditor;
    /**
     * 默认没有指定则为-1
     */
    protected int mMaxValue = -1;
    /**
     * *默认没有指定则为-1
     */
    protected int mMinValue = -1;
    ImageButton BtnSub;
    EditText etNumber;
    ImageButton BtnAdd;

    public NumberEditorView(Context context) {
        this(context, null);
    }

    public NumberEditorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberEditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NumberEditorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        inflate(context, R.layout.number_editor, this);
        initView();


        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberEditorView);
            mIsNumEditor = typedArray.getBoolean(R.styleable.NumberEditorView_isNumEditable, false);
            if (!mIsNumEditor) {
                etNumber.setKeyListener(null);
            }

            mMaxValue = typedArray.getInt(R.styleable.NumberEditorView_maxValue, -1);
            mMinValue = typedArray.getInt(R.styleable.NumberEditorView_minValue, -1);

            typedArray.recycle();


        }

        initListener();

    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
        etNumber.setText(mNumber+"");
    }

    public boolean ismIsNumEditor() {
        return mIsNumEditor;
    }

    public void setmIsNumEditor(boolean mIsNumEditor) {
        this.mIsNumEditor = mIsNumEditor;
    }

    public int getmMaxValue() {
        return mMaxValue;
    }

    public void setmMaxValue(int mMaxValue) {
        this.mMaxValue = mMaxValue;
    }

    public int getmMinValue() {
        return mMinValue;
    }

    public void setmMinValue(int mMinValue) {
        this.mMinValue = mMinValue;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        etNumber = (EditText) findViewById(R.id.tv_number);
        BtnAdd = (ImageButton) findViewById(R.id.btn_add);
        BtnSub = (ImageButton) findViewById(R.id.btn_sub);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        etNumber.setText(mNumber + "");

    }

    private void initListener() {
        etNumber.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onNumberEditorClickListener != null) {
                    onNumberEditorClickListener.onNumberViewClick();
                }
            }
        });
        BtnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //设定了最大值并且当前已经是最大值或者超出最大值
                if (mMaxValue != -1 && mNumber >= mMaxValue) {
                    if (onNumberEditorClickListener != null) {
                        onNumberEditorClickListener.onFailed(MORE_THAN_MAX, mMaxValue);
                        return;
                    }
                }

                ++mNumber;
                etNumber.setText(mNumber + "");
                if (onNumberEditorClickListener != null) {
                    onNumberEditorClickListener.onAddButtonClick(mNumber);
                }

            }
        });
        BtnSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //设定了最大值并且当前已经是最大值或者超出最大值
                if (mMinValue != -1 && mNumber <= mMinValue) {
                    if (onNumberEditorClickListener != null) {
                        onNumberEditorClickListener.onFailed(LESS_THAN_MIN, mMinValue);
                        return;
                    }
                }
                --mNumber;
                etNumber.setText(mNumber + "");
                if (onNumberEditorClickListener != null) {
                    onNumberEditorClickListener.onSubButtonClick(mNumber);
                }
            }
        });
    }

    public void setOnNumberEditorClickListener(OnNumberEditorClickListener onNumberEditorClickListener) {
        this.onNumberEditorClickListener = onNumberEditorClickListener;
    }

    public interface OnNumberEditorClickListener {
        /**
         * @param num 添加
         */
        void onAddButtonClick(int num);

        /**
         * 减少
         *
         * @param num
         */
        void onSubButtonClick(int num);

        void onNumberViewClick();

        /**
         * 错误码：
         * 100：大于设定最大值
         * 200：小于最设定小值
         *
         * @param errorCode
         */
        void onFailed(int errorCode, int number);

    }


}
