package cn.edu.bupt.sdmda.main;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.bupt.sdmda.ds.linearlist.SeqStack;

public class Calculator {
	// some test case 
    // (2-3*4)-(4*(3*3-1)+3)
    // (2-3*4)-(4*(3*3+(-1))+3)
    // -123/(4-1)+3*(4-86)+98*2
	String exp;
	static ArrayList<Character> ops = new ArrayList<>();

	static HashMap<Character, Integer> prior = new HashMap<>();
	static{
		ops.add('+');
		ops.add('-');
		ops.add('*');
		ops.add('/');
		ops.add('(');
		ops.add(')');

		prior.put('+', 1);
		prior.put('-', 1);
		prior.put('*', 2);
		prior.put('/', 2);
		prior.put('(', 0);
	}

	SeqStack<Character> opsStack = new SeqStack<Character>();
	SeqStack<Integer> numsStack = new SeqStack<Integer>();

	StringBuilder numBuilder;

	public Calculator(String str) {
		exp = str.replace(" ","");
		numBuilder = new StringBuilder();
	}


	public int calc(){
		int flag = -1;
		for(int i=0,len=exp.length();i<len;i++) {
			flag = getFlag(i);
//			System.out.printf("exp[%d]:%c\n",i,exp.charAt(i));
			switch(flag) {
				case 0:
					numBuilder.append(exp.charAt(i));
					break;
				case 1:
					if(numBuilder.length()>0) {
						numsStack.push(Integer.parseInt(numBuilder.toString()));
						numBuilder = new StringBuilder();
					}
					compareAndCalc(i);
					break;
			}
		}
		if(numBuilder.length()>0) {
			numsStack.push(Integer.parseInt(numBuilder.toString()));
			numBuilder = new StringBuilder();
		}
		while(!opsStack.isEmpty()) {
			popAndCalcAndPush();
		}
		return numsStack.pop();
	}


	// return 0 for number
	// return 1 for operator
	// note the '-' which can be both number or operator
	private int getFlag(int i){
		if(i<0) return -1;
		char c = exp.charAt(i);
		if(c>='0' && c<='9' || i==0 && c=='-' || getFlag(i-1)==1 && c=='-') {
			return 0;
		}
		if(ops.contains(c)) {
			return 1;
		}
		return -1;
	}


	// pop two numbers from stack, one operator from stack
	// calculate result and push in stack
	private void popAndCalcAndPush(){
		int b = numsStack.pop();
		int a = numsStack.pop();
		char c = opsStack.pop();
//		System.out.printf("%d %c %d\n",a,c,b);
		switch(c) {
			case '+':
				numsStack.push(a+b);
				break;
			case '-':
				numsStack.push(a-b);
				break;
			case '*':
				numsStack.push(a*b);
				break;
			case '/':
				numsStack.push(a/b);
				break;
		}
//		System.out.printf("%d\n",numsStack.getTop());
//		System.out.printf("%c\n",opsStack.getTop());
	}


	// compare current operator and top operator in stack
	// if prior[cur]>prior[stack[top]], just push cur to stack
	// else popAndCalculate, then push cur to stack
	// note '(' and ')'
	// '(' always be pushed into stack
	// ')' always popAndCalculate until ')'
	private void compareAndCalc(int i){
		char c = exp.charAt(i);
		if(c==')') {
			while(opsStack.getTop()!='(') {
				popAndCalcAndPush();
			}
			opsStack.pop();
			return;
		}
		if(c=='(' || opsStack.isEmpty() || prior.get(c)>prior.get(opsStack.getTop())) {
			opsStack.push(c);
			return;
		}
		while(!opsStack.isEmpty() && prior.get(c)<=prior.get(opsStack.getTop())){
			popAndCalcAndPush();
		}
		opsStack.push(c);
	}
}
