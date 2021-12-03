import java.util.*;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("№1"+" "+Bessy(10,7,"hello my name is Bessie and this is my essay"));
        System.out.println("№2"+" "+Split("((()))(())()()(()())"));
        System.out.println("№3"+" "+ToCamelCase("hello_edabit"));
        System.out.println("№3"+" "+toSnakeCase("helloEdabitBooba"));
        System.out.println("№4"+" "+overTime(new double[]{16.0D, 18.0D, 30.0D, 1.8D}));
        System.out.println("№5"+" "+BMI(new String[]{"55 kilos", "1.65 meters"}));
        System.out.println("№6"+" "+bugger(39));
        System.out.println("№7"+" "+toStarShorthand("abbccc"));
        System.out.println("№8"+" "+doesRhyme("Sam I am!","Green eggs and ham."));
        System.out.println("№9"+" "+trouble(451999277L,41177722899L));
        System.out.println("№10"+" "+countUniqueBooks("AZYWABBCATTTA", 'A'));
    }
    public static String Bessy(int n,int k,String str){
        String[] text = str.split(" ");
        str="";
        String FinalText="";
        for(int i=0;i<n;i++){
            if(str.length()+text[i].length()>k){
                FinalText=FinalText.trim()+"\r\n"+text[i]+" ";
                str=text[i];
            }else{
                FinalText+=text[i]+" ";
                str+=text[i];
            }
        }
        return FinalText.trim();
    }
    public static String Split(String str){
        List<String> list = new ArrayList();
        int f = 0;
        int i = 0;
        while(str.length() > 0) {
            if (str.charAt(i) == '(') {
                ++f;
            } else {
                --f;
            } if (f == 0) {
                list.add(str.substring(0, i + 1)); //добавление элемента в начало списка
                str = str.substring(i + 1);
                i = 0;
            } else {
                ++i;
            }
        }
        return list.toString();
    }
    public static String ToCamelCase(String str){
        String[] word = str.split("_");
        String FinalWord="";
        for(int i=1;i< word.length;i++){
            word[i]=word[i].substring(0,1).toUpperCase()+word[i].substring(1);
            FinalWord+=word[i];
        }
        return word[0].toString()+FinalWord;
    }
    public static String toSnakeCase(String str){
        ArrayList<Character> str1=new ArrayList<>();
        for(int i=0;i<str.length();i++){
            str1.add(str.charAt(i));
        }
        String result="";
        for(int i=0;i< str1.size();i++){
            if(Character.isUpperCase(str1.get(i))){
                str1.add(i,'_');
                str1.set(i+1,str1.get(i+1).toLowerCase(str1.get(i+1)));
            }
            result+=str1.get(i);
        }
        return result;
    }
    public static String overTime(double[] var){
        double sum = 0;
        if (17 - var[0] >= 0)
            sum += (17 - var[0]) * var[2];
        if (var[1] - 17 >= 0)
            sum += (var[1] - 17) * var[2] * var[3];
        return ('$' + String.valueOf(sum));
    }
    public static String BMI(String[] fat) {
        double ves = Double.parseDouble(fat[0].split(" ")[0]);
        double rost = Double.parseDouble(fat[1].split(" ")[0]);
        String out = " ";
        if (fat[0].contains("pounds"))
            ves = ves * 0.45;
        if (fat[1].contains("inches"))
            rost *= 0.0254;
        double BMI = Math.round((ves / (rost * rost)) * 10.0) / 10.0;
        if (BMI < 18.5) out = BMI + " Underweight";
        if (BMI >= 18.5 && BMI <= 24.9) out = BMI + " Normal weight";
        if (BMI > 25) out = BMI + " Overweight";
        return out;
    }
    public static Integer bugger(int num){
        int count = 0;
        while (num > 9) {
            int chnum = 1;
            while (num > 0) {
                chnum *= num % 10;
                num /= 10;
            }
            num = chnum;
            count++;
        }
        return count;
    }
    public static String toStarShorthand(String str) {
        int count = 0;
        char let = str.charAt(0);
        String newStr = "";
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != let) {
                if (count > 1) {
                    newStr = newStr + let + "*" + count;
                } else {
                    newStr = newStr + let;
                }
                let = str.charAt(i);
                count = 1;
            } else {
                ++count;
            }
        }
        if (count != 1) {
            newStr = newStr + let + "*" + count;
        } else {
            newStr = newStr + let;
        }
        return newStr;
    }
    public static boolean doesRhyme(String str1,String str2){
        str1 = str1.substring(str1.lastIndexOf(" ") + 1);
        str2 = str2.substring(str2.lastIndexOf(" ") + 1);
        String let = "aeiouyAEIOUY";
        String res1 = "";
        String res2 = "";
        int i;
        for(i = 0; i < str1.length(); ++i) {
            if (let.indexOf(str1.charAt(i)) != -1) {
                res1 = res1 + str1.charAt(i);
            }
        }
        for(i = 0; i < str2.length(); ++i) {
            if (let.indexOf(str2.charAt(i)) != -1) {
                res2 = res2 + str2.charAt(i);
            }
        }
        if (res1.toLowerCase().equals(res2.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean trouble(long x,long y){
        String aa = Long.toString(x);
        String bb = Long.toString(y);
        int num = 0;
        int i;
        for(i = 2; i < aa.length(); ++i) {
            if (aa.charAt(i) == aa.charAt(i - 1) && aa.charAt(i) == aa.charAt(i - 2)) {
                num = aa.charAt(i);
            }
        }
        for(i = 0; i < bb.length(); ++i) {
            if (bb.charAt(i) == num && bb.charAt(i + 1) == num) {
                return true;
            }
        }
        return false;
    }
    public static Integer countUniqueBooks(String str,char end) {
        Map<Character, Integer> values = new HashMap();
        boolean start = true;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == end && start) {
                ++i;
                for (; str.charAt(i) != end; ++i) {
                    Integer n = (Integer) values.get(str.charAt(i));
                    if (n == null) {
                        values.put(str.charAt(i), 1);
                    } else {
                        values.put(str.charAt(i), n + 1);
                    }
                }
                start = false;
            } if (str.charAt(i) == end) {
                start = true; }
        } return values.size();
    }
}
