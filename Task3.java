import com.sun.deploy.util.StringUtils;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        System.out.println("№1"+" "+solutions(1,0,-1));
        System.out.println("№2"+" "+findZip("all zip files are zipped"));
        System.out.println("№3"+" "+checkPerfect(6));
        System.out.println("№4"+" "+flipEndChars("z"));
        System.out.println("№5"+" "+isValidHexCode(("#eaeee")));
        System.out.println("№6"+" "+same(new Integer[]{4,4,4,1,2},new Integer[]{2,4,5,4}));
        System.out.println("№7"+" "+isKaprekar(0));
        System.out.println("№8"+" "+longestZero("01100001011000"));
        System.out.println("№9"+" "+nextPrime(18));
        System.out.println("№10"+" "+rightTriangle(145,105,100));
    }
    public static int solutions(int a,int b,int c){
        double D=b*b-4*a*c;
        if(D>0){
            return 2;
        }else if(D==0){
            return 1;
        }
        return 0;
    }
    public static int findZip(String s){
        int count=0;
        for( int i=0;i<s.length();i++){
            if(s.charAt(i)=='z' && s.charAt(i+1)=='i' && s.charAt(i+2)=='p'){
                count+=1;
            }
            if (count==2){
                return i;
            }
        }
        return -1;
    }
    public static boolean checkPerfect(int x)
    {
        int sum = 0;
        for (int i=1;i<x;i++)
        {
            if (x%i==0) sum+=i;
        }
        return sum == x;
    }
    public static String flipEndChars(String s){
        char[] str=s.toCharArray();
        if (s.length()>=2){
            if(str[0]!=str[s.length()-1]){
                char b=str[0];
                str[0]=str[s.length()-1];
                str[s.length()-1]=b;
                s=new String(str);
                return s;
            }else{
                return "Two's a pair.";
            }
        }
        return "Incompatible.";
    }
    public static boolean isValidHexCode (String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') str += s.charAt(i);
        }
        if(str.length()!=6) return false;
        try {
            Integer.parseInt(str, 16);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static boolean same(Integer[] arr1,Integer[] arr2){
        ArrayList<Integer> res1 = new ArrayList<Integer>();
        ArrayList<Integer> res2 = new ArrayList<Integer>();
        for(int i=0;i<arr1.length;i++) {
            if (res1.indexOf(arr1[i])==-1) {
                res1.add(arr1[i]);
            }
        }
        for(int i=0;i<arr2.length;i++) {
            if (res2.indexOf(arr2[i])==-1) {
                res2.add(arr2[i]);
            }
        }
        return (res1.size()==res2.size());
    }
    public static boolean isKaprekar(int n){
        int Res=n*n;
        String Value2=Integer.toString(Res);
        if(Value2.length()==1){
            char[] right=new char[1];
            right[0] = Value2.charAt(0);
            return (Integer.valueOf(String.valueOf(right[0])) == n);
        }else if(Value2.length()%2==0){
            char[] right=new char[Value2.length()/2];
            char[] left=new char[Value2.length()/2];
            int k=0;
            for(int i=0;i<=(Value2.length()/2)-1;i++){
                    left[i]=Value2.charAt(i);
            }
           for(int i=Value2.length()/2;i<Value2.length();i++){
              right[k]=Value2.charAt(i);
              k++;
            }
           return (Integer.valueOf(String.valueOf(left))+Integer.valueOf(String.valueOf(right))==n);
        }else {
            int k=0;
            char[] left=new char[Value2.length()/2];
            char[] right=new char[(Value2.length()/2)+1];
            for(int i=0;i<=(Value2.length()/2)-1;i++){
                left[i]=Value2.charAt(i);
        }
            for(int i=Value2.length()/2;i<Value2.length();i++){
                right[k]=Value2.charAt(i);
                k++;
            }
            return (Integer.valueOf(String.valueOf(left))+Integer.valueOf(String.valueOf(right))==n);
    }
    }
        public static String longestZero(String str){
        int Kol=0;
        int MaxKol=0;
        for(int i=0;i<str.length();i++){
     if(str.charAt(i)=='0'){
        Kol++;
        if(Kol>MaxKol){
            MaxKol=Kol;
        }
    }else if(str.charAt(i)=='1'){
        Kol=0;
    }
}
return Arrays.toString(new int[MaxKol]);
}
public static int nextPrime(int n){
        int k=0;
        for(int i=2;i<n;i++){
            if(n%i==0){
                k++;
            }
        }
        if(k==0){
            return n;
        }else if(k>0){
            do {
                n++;
                k=0;
                for(int i=2;i<n;i++){
                    if(n%i==0){
                        k++;
                    }
                }
            }while (k!=0);
        }
        return n;
}
public static boolean rightTriangle(int x1,int x2,int x3){
    ArrayList<Integer> Tri = new ArrayList<Integer>();
    Tri.add(x1);
    Tri.add(x2);
    Tri.add(x3);
    Collections.sort(Tri);
    return (Tri.get(0)*Tri.get(0)+Tri.get(1)*Tri.get(1)==Tri.get(2)*Tri.get(2));
}
}


