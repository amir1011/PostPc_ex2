package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class SimpleCalculatorImpl implements SimpleCalculator {

  private ArrayList <Integer> cur_canculat_list = new ArrayList<Integer>();
  private String output = "0";
  private String curr_number = "";
  private int ADD = -1;
  private int MINUS = -2;
//  SimpleCalculatorImpl(){
//    cur_canculat_list = new ArrayList<String>();
//  }
  // todo: add fields as needed


  @Override
  public String output() {
    // todo: return output based on the current state
//    StringBuilder output = new StringBuilder();
//    for(String s: cur_canculat_list)
//    {
//      output.append(s);
//    }
    return output;
  }

  @Override
  public void insertDigit(int digit) {
    // todo: insert a digit
    if(digit <= 9 && digit >= 0)
    {
      String curDigit = Integer.toString(digit);
      curr_number += curDigit;
      output = output.equals("0") ? curDigit : output + curDigit;
    }
    else
    {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
    if(isDigit())
    {
      if(!curr_number.equals(""))
      {
        cur_canculat_list.add(Integer.parseInt(curr_number));
        curr_number = "";
      }
      if(output.equals("0"))
      {
        cur_canculat_list.add(0);
      }
      cur_canculat_list.add(ADD);
      output += "+";
    }
  }

  private boolean isDigit()
  {
    return Character.isDigit(output.charAt(output.length()-1));
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
    if(isDigit())
    {
      if(!curr_number.equals(""))
      {
        cur_canculat_list.add(Integer.parseInt(curr_number));
        curr_number = "";
      }
      if(output.equals("0"))
      {
        cur_canculat_list.add(0);
      }
      cur_canculat_list.add(MINUS);
      output += "-";
    }
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    if(!curr_number.equals(""))
    {
      cur_canculat_list.add(Integer.parseInt(curr_number));
      curr_number = "";
    }
    if(cur_canculat_list.size() == 1 || cur_canculat_list.size() == 0)
    {
      return;
    }
    if(cur_canculat_list.get(cur_canculat_list.size() - 1) < 0)
    {
      cur_canculat_list.add(0);
    }
    int total = cur_canculat_list.get(0);
    for(int i=1; i<cur_canculat_list.size(); i++)
    {
      if(cur_canculat_list.get(i) == ADD)
      {
        total += cur_canculat_list.get(i+1);
      }
      if(cur_canculat_list.get(i) == MINUS)
      {
        total -= cur_canculat_list.get(i+1);
      }
    }
    output = Integer.toString(total);
    cur_canculat_list.clear();
//    cur_canculat_list.add(total);
    curr_number = Integer.toString(total);
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if(!output.equals("0"))
    {
      if(isDigit())
      {
        curr_number = curr_number.substring(0, curr_number.length() - 1);
      }
      else
      {
        curr_number = Integer.toString(cur_canculat_list.get(cur_canculat_list.size() - 2));
      }
      output = output.substring(0, output.length() - 1);
      output = output.equals("") ? "0" : output;
    }
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    output = "0";
    curr_number = "";
    cur_canculat_list.clear();
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    state.cur_canculat_list = cur_canculat_list;
    state.output = output;
    state.curr_number = curr_number;
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    cur_canculat_list = casted.cur_canculat_list;
    output = casted.output;
    curr_number = casted.curr_number;

  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    private String output;
    private ArrayList<Integer> cur_canculat_list;
    private String curr_number;
  }
}
