package cn.edu.bupt.sdmda.main;

import java.util.HashMap;
import java.util.Map;

import cn.edu.bupt.sdmda.ds.linearlist.SeqStack;

public class NumBaseConvertor {
	public enum Base {
		BIN, OCT, DEC, HEX
	}
	
	private final static Map<Base, Integer> map;
	private final static char[] alpha 
		= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	static{
		map = new HashMap<Base, Integer>();
		map.put(Base.BIN, 2);
		map.put(Base.OCT, 8);
		map.put(Base.DEC, 10);
		map.put(Base.HEX, 16);
	}
	
	public static String Convert(String src, Base srcBase, Base desBase) {
        // convert src from srcBase to DEC 

        // construct a empty stack
        
        // divide and push reminder to stack
        // Note the last reminder
        
        // convert and concatenate the reminder in stack
		SeqStack<Integer> s = new SeqStack<Integer>();
		int dec = toDec(src,srcBase);
		int d = map.get(desBase);
		while(dec>0) {
			s.push(dec%d);
			dec/=d;
		}
		String ret="";
		while(!s.isEmpty()) {
			ret += decToAny(s.pop());
		}
		return ret;
	}
	
	// convert any src to dec
	private static int toDec(String src, Base srcBase) {
		int len = src.length();
		int b = 1;
		int d = map.get(srcBase);
		int ret = 0;
		for(int i=len-1;i>=0;i--) {
			ret += toDec(src.charAt(i),srcBase) * b;
			b *= d;
		}
		return ret;
	}
	
    // convert a char to int from srcBase
	private static int toDec(char src, Base srcBase) {
		if(src<='9' && src>='0') {
			return (int)(src-'0');
		}
		if(src>='a' && src<='f') {
			return (int)(src-'a')+10;
		}
		if(src>='A' && src<='F') {
			return (int)(src-'A')+10;
		}
		return 0;
	}
	
    // convert a small int to char
	private static char decToAny(int src){
        return alpha[src];
	}
}
