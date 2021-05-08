package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;
//  private ArrayList<View> viewList = new ArrayList<View>();
//  private Serializable state /*= new Serializable()*/;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    TextView calculatorOutput = findViewById(R.id.textViewCalculatorOutput);
    calculatorOutput.setText(calculator.output());
    Resources res = getResources();
    for(int i=0; i<10; i++)
    {
      int id = res.getIdentifier("button" + i, "id", getPackageName());
//      viewList.add(findViewById(id));
      int curr = i;
      findViewById(id).setOnClickListener(v -> {
        calculator.insertDigit(curr);
        calculatorOutput.setText(calculator.output());
      });
    }
    TextView clearButton = findViewById(R.id.buttonClear);
    clearButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.clear();
        calculatorOutput.setText(calculator.output());
      }
    });
//    viewList.add(clearButton);
    View backSpaceButton = findViewById(R.id.buttonBackSpace);
    backSpaceButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.deleteLast();
        calculatorOutput.setText(calculator.output());
      }
    });
//    viewList.add(backSpaceButton);
    TextView equalsButton = findViewById(R.id.buttonEquals);
    equalsButton.setOnClickListener(v -> {
      calculator.insertEquals();
      calculatorOutput.setText(calculator.output());
    });
//    viewList.add(equalsButton);
    TextView plusButton = findViewById(R.id.buttonPlus);
    plusButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertPlus();
        calculatorOutput.setText(calculator.output());
      }
    });
//    viewList.add(plusButton);
    TextView minusButton = findViewById(R.id.buttonMinus);
    minusButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertMinus();
        calculatorOutput.setText(calculator.output());
      }
    });
//    viewList.add(minusButton);
//    TextView calculatorOutput = findViewById(R.id.textViewCalculatorOutput);
//    calculatorOutput.setText(calculator.output());
//    viewList.add(calculatorOutput);

//    for(int i=0; i<16; i++)
//    {
//      if(i<10)
//      {
//        int curr = i;
//        viewList.get(curr).setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            calculator.insertDigit(curr);
//            calculatorOutput.setText(calculator.output());
//          }
//        });
//      }
//      else
//      {
//        int curr = i;
//        viewList.get(curr).setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            calculator.insertDigit(curr);
//            calculatorOutput.setText(calculator.output());
//          }
//        });
//      }

  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    // todo: save calculator state into the bundle
    outState.putSerializable("cal_state", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
    calculator.loadState(savedInstanceState.getSerializable("cal_state"));
  }
}