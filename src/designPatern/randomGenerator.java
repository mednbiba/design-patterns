package designPatern;

import java.util.Random;


import java.text.DecimalFormat;

public class randomGenerator {
private static final DecimalFormat df = new DecimalFormat("0.00");
    public static String randomGB(int min,int max) {
    	Random ran = new Random();
    	int x = ran.nextInt(max) + min;
    	 String s = Integer.toString(x) + " GB";
    	 return s;
    }
    public static String randomHZ(float min,float max) {
    	Random ran = new Random();
    	float random = (min + ran.nextFloat() * (max - min));
    	String rf = df.format(random);
    	
    	String s = rf + " GHz";
    	 return s;
    }
    public static int randomchoice(int min,int max) {
    	Random ran = new Random();
    	int x = ran.nextInt(max) + min;

    	 return x;
    }

}
