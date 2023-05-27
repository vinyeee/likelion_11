package com.example.helloSpring.service;

import com.example.helloSpring.domain.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public void calculate(Calculator calculator){
        float result = 0.0f;
        switch (calculator.getOp()){
            case '+':
                result=calculator.getA()+calculator.getB();
                break;
            case '-':
                result=calculator.getA()-calculator.getB();
                break;
            case '*':
                result=calculator.getA()*calculator.getB();
                break;
            case '/':
                result=calculator.getA()*calculator.getB();
                break;
        }

    }
}
