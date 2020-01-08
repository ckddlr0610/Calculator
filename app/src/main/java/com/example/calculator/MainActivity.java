package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView mTextDisplay;
    private int result = 0;
    private String mMathExpr1 = "";
    private String mOpeExpr = null;
    private String mMathExpr2 = "";

    int state = 0; // 초기화 state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingView();
    }

    /*
     * binding a param with view contents
     */
    public void bindingView() {
        mTextDisplay = (TextView)findViewById(R.id.textDisplay);
    }

    /*
     * 버튼을 클릭했을 때 실행되는 메소드입니다.
     */
    public void clickbutton(View v) {
        switch (v.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                numClick(v);
                break;
            case R.id.btnAdd:
            case R.id.btnSub:
            case R.id.btnMul:
            case R.id.btnDiv:
            case R.id.btnPer:
            case R.id.btnPow:
                operatorClick(v);
                break;
            case R.id.btnDel:
                reset(v);
                break;
            case R.id.btnEql:
                result = calc(result);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        switch (state){
            case 0:
                mTextDisplay.setText(""+result);
                break;
            case 1:
                mTextDisplay.setText(mMathExpr1);
                break;
            case 2:
                mTextDisplay.setText(mOpeExpr);
                break;
            case 3:
                mTextDisplay.setText(mMathExpr2);
                break;
        }
    }

    /*
     * state의 상태에 따라 입력되는 변수가 다릅니다.
     */
    public void numClick(View v) {
        if(state == 0 || state == 1){
            state = 1;
            mMathExpr1 += ((Button) v).getText();
        }
        else if(state == 2 || state == 3){
            state = 3;
            mMathExpr2 += ((Button)v).getText();
        }
    }

    /*
     * operator는 두개 이상 입력될 수 없습니다.
     * 입력되는 순간 state는 3이 됩니다.
     */
    public void operatorClick(View v){
        if(state == 0){
            return;
        }
        else if(state == 1){
            state = 2;
            mOpeExpr = (String) ((Button)v).getText();
        }
        else if(state == 2){
            mOpeExpr = (String) ((Button)v).getText();
        }
        else if (state == 3){
            state = 2;
            calc(result);
            mOpeExpr = (String) ((Button)v).getText();
        }
    }

    /*
     * state와 result값을 0으로 만들고 모든 변수를 초기화시킵니다.
     */
    public void reset(View v){
        state = 0;
        result = 0;
        mMathExpr1 = "";
        mMathExpr2 = "";
        mOpeExpr = null;
    }

    /*
     * 연산자에 따라 다르게 계산 결과값을 리턴합니다.
     */

    public int calc(int res){
        state = 0;
        int num1;
        int num2;
        num1 = Integer.parseInt(mMathExpr1);
        num2 = Integer.parseInt(mMathExpr2);

        switch (mOpeExpr)
        {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
        }
        return res;
    }

}