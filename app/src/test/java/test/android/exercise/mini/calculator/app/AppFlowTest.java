package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class AppFlowTest {

  private ActivityController<MainActivity> activityController;
  private MainActivity activityUnderTest;
  private View button0;
  private View button1;
  private View button2;
  private View button3;
  private View button4;
  private View button5;
  private View button6;
  private View button7;
  private View button8;
  private View button9;
  private View buttonBackspace;
  private View buttonClear;
  private View buttonPlus;
  private View buttonMinus;
  private View buttonEquals;
  private TextView textViewOutput;

  /** initialize main activity with a real calculator */
  @Before
  public void setup(){
    activityController = Robolectric.buildActivity(MainActivity.class);
    activityUnderTest = activityController.get();
    activityController.create().start().resume();
    button0 = activityUnderTest.findViewById(R.id.button0);
    button1 = activityUnderTest.findViewById(R.id.button1);
    button2 = activityUnderTest.findViewById(R.id.button2);
    button3 = activityUnderTest.findViewById(R.id.button3);
    button4 = activityUnderTest.findViewById(R.id.button4);
    button5 = activityUnderTest.findViewById(R.id.button5);
    button6 = activityUnderTest.findViewById(R.id.button6);
    button7 = activityUnderTest.findViewById(R.id.button7);
    button8 = activityUnderTest.findViewById(R.id.button8);
    button9 = activityUnderTest.findViewById(R.id.button9);
    buttonBackspace = activityUnderTest.findViewById(R.id.buttonBackSpace);
    buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
    buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);
    buttonMinus = activityUnderTest.findViewById(R.id.buttonMinus);
    buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);
    textViewOutput = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
  }

  @Test
  public void flowTest1(){
    // run clicks on "13+5"
    for (View button: Arrays.asList(
      button1, button3, buttonPlus, button5
    )) {
      button.performClick();
    }

    assertEquals("13+5", textViewOutput.getText().toString());
  }


  @Test
  public void flowTest2(){
    // run clicks on "7+5<backspace>4="
    for (View button: Arrays.asList(
      button7, buttonPlus, button5, buttonBackspace, button4, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("11", textViewOutput.getText().toString());
  }

  // TODO: add at last 10 more flow tests

  @Test
  public void flowTest3(){
    // run clicks on "13+5-48="
    for (View button: Arrays.asList(
            button1, button3, buttonPlus, button5, buttonMinus, button4, button8, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("-30", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest4(){
    // run clicks on "13+6<clear>"
    for (View button: Arrays.asList(
            button1, button3, buttonPlus, button6, buttonClear
    )) {
      button.performClick();
    }

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest5(){
    // run clicks on "-" only
    for (View button: Arrays.asList(
            buttonMinus
    )) {
      button.performClick();
    }

    assertEquals("0-", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest6(){
    // run clicks on "1+5=<backspace>"
    for (View button: Arrays.asList(
            button1, buttonPlus, button5, buttonEquals, buttonBackspace
    )) {
      button.performClick();
    }

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest7(){
    // run clicks on "13+5-9-7-2"
    for (View button: Arrays.asList(
            button1, button3, buttonPlus, button5,
            buttonMinus, button9, buttonMinus, button7, buttonMinus, button2, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest8(){
    // run clicks on "1+3+5-9-7-2+0"
    for (View button: Arrays.asList(
            button1, buttonPlus, button3, buttonPlus, button5,
            buttonMinus, button9, buttonMinus, button7, buttonMinus, button2, buttonPlus, button0
    )) {
      button.performClick();
    }

    assertEquals("1+3+5-9-7-2+0", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest9(){
    // run clicks on "1+3+5-9-7-2+0" and then 13_X_times backspace
    for (View button: Arrays.asList(
            button1, buttonPlus, button3, buttonPlus, button5,
            buttonMinus, button9, buttonMinus, button7, buttonMinus, button2, buttonPlus, button0,
            buttonBackspace, buttonBackspace, buttonBackspace, buttonBackspace, buttonBackspace,
            buttonBackspace, buttonBackspace, buttonBackspace, buttonBackspace, buttonBackspace,
            buttonBackspace, buttonBackspace, buttonBackspace
    )) {
      button.performClick();
    }
    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest10(){
    // run clicks on "9999+9999+9999="
    for (View button: Arrays.asList(
            button9, button9, button9, button9, buttonPlus, button9, button9, button9, button9,
            buttonPlus, button9, button9, button9, button9,
            buttonPlus, button9, button9, button9, button9, buttonEquals, buttonPlus, button4
    )) {
      button.performClick();
    }

    assertEquals("39996+4", textViewOutput.getText().toString());
  }

  @Test
  public void flowTes11(){
    // run clicks on "1+3+5-9-7-2+0<clear>13+5="
    for (View button: Arrays.asList(
            button1, buttonPlus, button3, buttonPlus, button5,
            buttonMinus, button9, buttonMinus, button7, buttonMinus, button2, buttonPlus, button0,
            buttonClear ,button1, button3, buttonPlus, button5
    )) {
      button.performClick();
    }

    assertEquals("13+5", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest12(){
    // run clicks on "1+3+5-9-7-2+0<clear><backspace><backspace>"
    for (View button: Arrays.asList(
            button1, buttonPlus, button3, buttonPlus, button5,
            buttonMinus, button9, buttonMinus, button7, buttonMinus, button2, buttonPlus, button0,
            buttonClear, buttonBackspace,buttonBackspace
    )) {
      button.performClick();
    }

    assertEquals("0", textViewOutput.getText().toString());
  }
}
