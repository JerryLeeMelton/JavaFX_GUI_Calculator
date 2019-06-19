//Jerry Melton
//CSIS139 JAVA (Sat 12:50pm)
//13 - GUI Calculator App

package com.jerryleemelton.javafxcalculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

class Model {

    static String calculate(String num1, String num2, String operator){
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);

        switch(operator) {
            case "+":
                return bd1.add(bd2).toString();
            case "-":
                return bd1.subtract(bd2).toString();
            case "×":
                return bd1.multiply(bd2, new MathContext(10, RoundingMode.HALF_UP)).toString();
            case "÷":
                if(bd2.doubleValue() == 0) return "0";
                return bd1.divide(bd2, new MathContext(10, RoundingMode.HALF_UP)).toString();
        }

        System.out.println("ERROR: Unknown operator");
        return "0";

    }

    static String percentConversion(String input) {

        boolean isNegative = input.contains("-");

        if(isNegative) {
            input = input.substring(1);
        }

        // Check to see if the result will be over the character limit
        // if so, just return the original input
        if((isNegative && input.length() == 9) || !isNegative && input.length() == 10) {
            for(int i = 0; i < 4; i ++) {
                if(input.charAt(i) == '.') return (isNegative) ? ("-" + input) : input;
            }
        }

        String result = "";
        int decimalIndex = ((input.contains(".")) ? input.indexOf(".") : input.length());
        input = input.replace(".", "");

        input = "00" + input;

        for(int i = 0; i < input.length(); i++) {
            if(i == decimalIndex) {
                result = result + "." + input.charAt(i);
            }
            else {
                result = result + input.charAt(i);
            }
        }

        int resultTrimIndex = 0;
        for(int i = 0; i < result.length(); i++) {

            if(result.charAt(i) == '.') break;

            if(Character.getNumericValue(result.charAt(i)) > 0) {
                break;
            }
            else {
                if(Character.isDigit(result.charAt(i + 1))) {
                    resultTrimIndex++;
                }
            }
        }

        result = ((isNegative) ? "-" + result.substring(resultTrimIndex) : result.substring(resultTrimIndex));

        return result;
    }
}
