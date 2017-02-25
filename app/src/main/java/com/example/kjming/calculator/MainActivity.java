package com.example.kjming.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mAnswer;
    private Button mBtnZero;
    private Button mBtnOne;
    private Button mBtnTwo;
    private Button mBtnThree;
    private Button mBtnFour;
    private Button mBtnFive;
    private Button mBtnSix;
    private Button mBtnSeven;
    private Button mBtnEight;
    private Button mBtnNight;
    private Button mBtnPoint;
    private Button mBtnPositive;
    private Button mBtnNegative;
    private Button mBtnTimes;
    private Button mBtnDivide;
    private Button mBtnEqual;
    private Button mBtnAC;
    private Button mBtnDEL;
    private Button mBtnPAN;
    private float mLastNumber = 0;
    private float mNewNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.view);
        mAnswer = (TextView) findViewById(R.id.answer);
        mBtnZero = (Button) findViewById(R.id.zero);
        mBtnOne = (Button) findViewById(R.id.one);
        mBtnTwo = (Button) findViewById(R.id.two);
        mBtnThree = (Button) findViewById(R.id.three);
        mBtnFour = (Button) findViewById(R.id.four);
        mBtnFive = (Button) findViewById(R.id.five);
        mBtnSix = (Button) findViewById(R.id.six);
        mBtnSeven = (Button) findViewById(R.id.seven);
        mBtnEight = (Button) findViewById(R.id.eight);
        mBtnNight = (Button) findViewById(R.id.night);
        mBtnPoint = (Button) findViewById(R.id.point);
        mBtnPositive = (Button) findViewById(R.id.positive);
        mBtnNegative = (Button) findViewById(R.id.negative);
        mBtnTimes = (Button) findViewById(R.id.times);
        mBtnDivide = (Button) findViewById(R.id.divide);
        mBtnEqual = (Button) findViewById(R.id.equal);
        mBtnAC = (Button) findViewById(R.id.AC);
        mBtnDEL = (Button) findViewById(R.id.DEL);
        mBtnPAN = (Button) findViewById(R.id.PAN);

        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof Button) {
                    String btnText = ((Button) v).getText().toString();
                    String text = mTextView.getText().toString();
                    if(text.equals("") && TextUtils.isDigitsOnly(((Button) v).getText())) {
                        mTextView.setText(btnText);
                    }else if(text != "") {
                        ArrayList<String> tmpArray = new ArrayList<>(Arrays.asList(mTextView.getText().toString().split(" ")));
                        switch(v.getId()) {
                            case R.id.zero:
//                                String[] value = mTextView.getText().toString().split("\\+|\\-|÷|×");
                                if(getCurrentInput(mTextView.getText().toString()).equals("0")) {
                                }else {
                                    mTextView.setText(mTextView.getText() + btnText);
                                }
                                break;
                            case R.id.one:
                            case R.id.two:
                            case R.id.three:
                            case R.id.four:
                            case R.id.five:
                            case R.id.six:
                            case R.id.seven:
                            case R.id.eight:
                            case R.id.night:
                                isOperator(mTextView.getText().toString());
                                if(getCurrentInput(mTextView.getText().toString()).equals("0")) {
                                    mTextView.setText(mTextView.getText().toString().substring(0, mTextView.getText().length() - 1) + btnText);
                                }else {
                                    mTextView.setText(mTextView.getText() + btnText);
                                }
                                break;
                            case R.id.point:
                                int tmp = mTextView.getText().toString().indexOf(".");
                                if(isNumber(getCurrentInput(mTextView.getText().toString())) && getCurrentInput(mTextView.getText().toString()).indexOf(".") == -1) {
                                    mTextView.setText(mTextView.getText() + btnText);
                                }
                                break;
                            case R.id.positive:
                            case R.id.negative:
                            case R.id.times:
                            case R.id.divide:
                                if(isOperator(mTextView.getText().toString()) || isPointer(mTextView.getText().toString())) {
                                    mTextView.setText(mTextView.getText().toString().substring(0, mTextView.getText().length() - 3) + " " + btnText + " ");
                                }else {
                                    mTextView.setText(mTextView.getText() + " " + btnText + " ");
                                }
                                break;
                            case R.id.AC:
                                mTextView.setText("");
                                break;
                            case R.id.equal:
                                if (isOperator(mTextView.getText().toString())) {
                                    mTextView.setText(mTextView.getText().toString().substring(0, mTextView.length() - 3) + btnText);
                                }
//                                ArrayList<String> array=new ArrayList<String>(mTextView.getText().toString().split(" "));
//                                for(int indexOfarray=0;indexOfarray<=array.length;indexOfarray++) {
                                while(tmpArray.size()!=1){
                                    if(tmpArray.indexOf("×") > -1 || tmpArray.indexOf("÷") > -1) {
                                        int indexOftimes = tmpArray.indexOf("×");
                                        int indexOfdivide = tmpArray.indexOf("÷");
                                        if(indexOftimes > indexOfdivide) {
                                            double anser = Double.valueOf(tmpArray.get(indexOftimes - 1)) * Double.valueOf(tmpArray.get(indexOftimes + 1));
                                            tmpArray.set(indexOftimes, String.valueOf(anser));
                                            tmpArray.remove(indexOftimes + 1);
                                            tmpArray.remove(indexOftimes - 1);
                                        }else {
                                            double anser = Double.valueOf(tmpArray.get(indexOfdivide - 1)) / Double.valueOf(tmpArray.get(indexOfdivide + 1));
                                            tmpArray.set(indexOfdivide, String.valueOf(anser));
                                            tmpArray.remove(indexOfdivide + 1);
                                            tmpArray.remove(indexOfdivide - 1);
                                        }
                                    }else {
                                        int indexOfpositive = tmpArray.indexOf("+");
                                        int indexOfnegative = tmpArray.indexOf("-");
                                        if (indexOfpositive > indexOfnegative) {
                                            double anser = Double.valueOf(tmpArray.get(indexOfpositive - 1)) + Double.valueOf(tmpArray.get(indexOfpositive + 1));
                                            tmpArray.set(indexOfpositive, String.valueOf(anser));
                                            tmpArray.remove(indexOfpositive + 1);
                                            tmpArray.remove(indexOfpositive - 1);
                                        } else {
                                            double anser = Double.valueOf(tmpArray.get(indexOfnegative - 1)) / Double.valueOf(tmpArray.get(indexOfnegative + 1));
                                            tmpArray.set(indexOfnegative, String.valueOf(anser));
                                            tmpArray.remove(indexOfnegative + 1);
                                            tmpArray.remove(indexOfnegative - 1);
                                        }
                                    }
                                }
                                mAnswer.setText(tmpArray.get(0).toString());
                                break;
                            case R.id.DEL:
                                tmpArray.remove(tmpArray.size()-1);
                                mTextView.setText("");
                                for(int index=0;index<tmpArray.size();index++) {
                                    if(isNumber(tmpArray.get(index))) {
                                        mTextView.setText(mTextView.getText()+tmpArray.get(index));
                                    }else {
                                        mTextView.setText(mTextView.getText()+" "+tmpArray.get(index)+" ");
                                    }
                                }
                                break;
                            case R.id.PAN:
                               if(isNumber(tmpArray.get(tmpArray.size()-1))) {
                                   if(tmpArray.get(tmpArray.size()-1).toString(). contains("-")){
                                       tmpArray.set(tmpArray.size()-1,tmpArray.get(tmpArray.size()-1).toString().substring(1));
                                   }else {
                                       tmpArray.set(tmpArray.size()-1,"-"+tmpArray.get(tmpArray.size()-1).toString());
                                   }
                                   mTextView.setText("");
                                   for(int index=0;index<tmpArray.size();index++) {
                                       if(isNumber(tmpArray.get(index))) {
                                           mTextView.setText(mTextView.getText()+tmpArray.get(index));
                                       }else {
                                           mTextView.setText(mTextView.getText()+" "+tmpArray.get(index)+" ");
                                       }
                                   }
                               }
                                break;
                        }
                    }
                }
            }


            public String getCurrentInput(String wholeText) {
                if(TextUtils.isEmpty(wholeText)) {
                    return "";
                }
                String lastChar = String.valueOf(wholeText.charAt(wholeText.length() - 1));
                if(isOperator(lastChar)) {
                    return "";
                }
                String[] inputs = mTextView.getText().toString().split("\\+|\\-|÷|×");
                return inputs[inputs.length - 1];
            }

            public boolean isOperator(String text) {
                if(TextUtils.isEmpty(text)) {
                    return false;
                }
                char lastChar = text.charAt(text.length()-1);
                if(lastChar==' '){
                    return true ;
                }else {
                    return false;
                }


            }
            public boolean isPointer(String text) {
                if(TextUtils.isEmpty(text)) {
                    return false;
                }
                char lastChar = text.charAt(text.length()-1);
                if(lastChar=='.') {
                    return true ;
                }else {
                    return false;
                }

            }
            public boolean isNumber(String text){
                if(text.isEmpty()) {
                    return false;
                }else {
                    char numberOrNot = text.charAt(text.length() - 1);
                    if(Character.isDigit(numberOrNot)) {
                        return true;
                        }else {
                            return false;
                        }
                }
            }

        };

        mBtnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText("0");
            }
        });

        mBtnZero.setOnClickListener(clickListener);
        mBtnOne.setOnClickListener(clickListener);
        mBtnTwo.setOnClickListener(clickListener);
        mBtnThree.setOnClickListener(clickListener);
        mBtnFour.setOnClickListener(clickListener);
        mBtnFive.setOnClickListener(clickListener);
        mBtnSix.setOnClickListener(clickListener);
        mBtnSeven.setOnClickListener(clickListener);
        mBtnEight.setOnClickListener(clickListener);
        mBtnNight.setOnClickListener(clickListener);
        mBtnNegative.setOnClickListener(clickListener);
        mBtnPositive.setOnClickListener(clickListener);
        mBtnTimes.setOnClickListener(clickListener);
        mBtnDivide.setOnClickListener(clickListener);
        mBtnPoint.setOnClickListener(clickListener);
        mBtnAC.setOnClickListener(clickListener);
        mBtnEqual.setOnClickListener(clickListener);
        mBtnDEL.setOnClickListener(clickListener);
        mBtnPAN.setOnClickListener(clickListener);

    }
}
