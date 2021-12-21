import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    public static void main(String[] args) {
        System.out.println("Task1: " + bell(2));
        System.out.println("Task2: "+ translateWord("ashimp"));
        System.out.println("Task2: " + translateSentence("Somewhere it's raining, and somewhere I'm walking"));
        System.out.println("Task3: "+ validColor("rgba(0,0,0,0)"));
        System.out.println("Task4: "+ stripUrlParams("https://edabit.com?a=1&b=2&a=2","b"));
        System.out.println("Task5: "+ Arrays.toString(getHashTags("Whya Jimmi You, So, Boorrow")));
        System.out.println("Task6: "+ ulam(206));
        System.out.println("Task7: "+ longest("abscaaaaa"));
        System.out.println("Task8: "+ convertToRoman(300));
        System.out.println("Task9: "+ formula("10 * 5 = 50 = 35 + 15  + 13"));
        System.out.println("Task10: "+ polindrome(112111230L));
    }
/*№1.Число Белла - это количество способов, которыми массив из n элементов может быть разбит на непустые подмножества.
Создайте функцию, которая принимает число n и возвращает соответствующее число Белла.*/
    public static int bell(int n) {
        int[][] bellTriangle = new int[n + 1][n + 1];
        bellTriangle[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            bellTriangle[i][0] = bellTriangle[i - 1][i - 1];
            for (int j = 1; j <= i; j++) {
                bellTriangle[i][j] = bellTriangle[i - 1][j - 1] + bellTriangle[i][j - 1];
            }
        }
        return bellTriangle[n][0];
    }
/*№2.В «поросячей латыни» (свинский латинский) есть два очень простых правила:
– Если слово начинается с согласного, переместите первую букву (буквы) слова до гласного до конца слова и добавьте «ay» в конец.
have ➞ avehay
cram ➞ amcray
take ➞ aketay
cat ➞ atcay
shrimp ➞ impshray
trebuchet ➞ ebuchettray
–	Если слово начинается с гласной, добавьте "yay" в конце слова.
ate ➞ ateyay
apple ➞ appleyay
oaken ➞ oakenyay
eagle ➞ eagleyay
Напишите две функции, чтобы сделать переводчик с английского на свинский латинский.
Первая функция translateWord (word) получает слово на английском и возвращает это слово, переведенное на латинский язык.
Вторая функция translateSentence (предложение) берет английское предложение и возвращает это предложение, переведенное на латинский язык.
*/
    public static String translateWord(String word) {
        if (word.equals("")) {
            return "";
        }
        String vowels = "aeiouAEIOU";
        if (vowels.contains(word.charAt(0) + "")) {
            word += "yay";
        } else {
            for (int i = 0; i < word.length(); i++) {
                if (vowels.contains(word.charAt(i) + "")) {
                    word = word.substring(i) + word.substring(0, i) + "ay";
                    break;
                }
            }
        }

        return word;
    }

    public static String translateSentence(String s) {
        if (s.equals("")) {
            return "";
        }

        s = s.toLowerCase();

        Pattern patt = Pattern.compile("[^ ,.!?]*");
        Matcher matcher = patt.matcher(s);
        while (matcher.find()) {
            s = s.replace(matcher.group(), translateWord(matcher.group()));
        }

        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
/* №3.Учитывая параметры RGB (A) CSS, определите, является ли формат принимаемых значений допустимым или нет.
Создайте функцию, которая принимает строку (например, " rgb(0, 0, 0)") и возвращает true, если ее формат правильный,
в противном случае возвращает false.*/
    public static boolean validColor(String string){
        int i = string.indexOf("(");
        String[] split = string.substring(i+1,string.length()-1).split(",");
        if (string.substring(0,i).equals("rgb") && split.length==3){
            for (int j = 0; j < split.length; j++) {
                if (Integer.parseInt(split[j]) < 0 && Integer.parseInt(split[j]) > 255){
                    return false;
                }
            }
            return true;
        }
        if(string.substring(0,i).equals("rgba")  && split.length==4){
            if (Integer.parseInt(split[split.length-1]) < 0 && Integer.parseInt(split[split.length-1]) > 1){
                return false;
            }
            for (int j = 0; j < split.length-1; j++) {
                if (Integer.parseInt(split[j]) < 0 && Integer.parseInt(split[j]) > 255){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
/*№4.Создайте функцию, которая принимает URL (строку), удаляет дублирующиеся параметры запроса и параметры,
указанные во втором аргументе (который будет необязательным массивом).*/
    public static String stripUrlParams(String url, String ...paramsToStrip) {
        String str = "";
        if (!url.contains("?"))
            return url;
        else {
            str = url.substring(url.indexOf("?") + 1);
            url = url.substring(0, url.indexOf("?") + 1);
        }
        char[] params = str.toCharArray();
        StringBuilder print = new StringBuilder();
        for (char param : params) {
            if (Character.isLetter(param))
                if (!(print.toString().contains(String.valueOf(param)))) {
                    if (paramsToStrip.length > 0) {
                        for (String arg : paramsToStrip) {
                            if (!(arg.contains(String.valueOf(param))))
                                print.append(str, str.lastIndexOf(param), str.lastIndexOf(param) + 3).append("&");
                        }
                    }
                    else print.append(str, str.lastIndexOf(param), str.lastIndexOf(param) + 3).append("&");
                }
        }
        return url + print.substring(0, print.length()-1);
    }
/*№5.Напишите функцию, которая извлекает три самых длинных слова из заголовка газеты и преобразует их в хэштеги.
Если несколько слов одинаковой длины, найдите слово, которое встречается первым.*/
    public static String[] getHashTags(String string){
        String[] split = string.split("[\\s,]+");
        for (int i = 0; i < split.length; i++) {
            if (split[0].length() < split[i].length()){
                String tmp = split[i];
                for (int j = i; j > 0; j--) {
                    split[j]= split[j-1];
                }
                split[0]=tmp;
            }
        }
        String[] result = new String[3];
        return result = Arrays.copyOfRange(split,0,3);
    }
/*№6.Последовательность Улама начинается с:
ulam = [1, 2]
Следующее число в последовательности - это наименьшее положительное число,
равное сумме двух разных чисел (которые уже есть в последовательности) ровно одним способом.
Тривиально, это 3, так как в стартовой последовательности есть только 2 числа.
ulam = [1, 2, 3]
Следующее число 4, которое является суммой 3 + 1. 4 также равно 2 + 2, но это уравнение не учитывается, так как 2 добавления должны быть различны.
ulam = [1, 2, 3, 4]
Следующее число не может быть 5, так как 5 = 1 + 4, но также и 5 = 2 + 3.
Должен быть только один способ сделать число Улама из 2 различных добавлений, найденных в последовательности.
Следующее число 6 (2 + 4). Есть 2 способа сделать 7 (1 + 6 или 3 + 4), поэтому следующий - 8 (2 + 6). И так далее.
ulam = [1, 2, 3, 4, 6, 8, 11, 13, 16, 18, 26, 28, 36, 38, 47, 48, 53, …]
Создайте функцию, которая принимает число n и возвращает n-е число в последовательности Улама.
*/
    public static int ulam(int n){
        int[] arr = new int[n];
        arr[0]=1;
        arr[1]=2;
        int len=2, next=3;
        while (next < Integer.MAX_VALUE && len < n){
            int count = 0;
            for (int i = 0; i < len; i++){
                for (int j = len-1; j > i; j--){
                    if (arr[i] + arr[j] == next && arr[i] != arr[j])
                        count++;
                    else if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }
            if (count == 1) {
                arr[len] = next;
                len++;
            }
            next++;
        }
        return arr[n-1];
    }
/*№7.Напишите функцию, которая возвращает самую длинную неповторяющуюся подстроку для строкового ввода.*/
    public static String longest(String string){
        Map visited = new HashMap<>();
        String subString = "";
        int strX = 0;
        for (int endX = 0; endX < string.length(); endX++) {
            char currChar = string.charAt(endX);
            if (visited.containsKey(currChar)) {
                strX = Math.max((int)visited.get(currChar)+1, strX);
            }
            if (subString.length() < endX - strX + 1) {
                subString = string.substring(strX, endX + 1);
            }
            visited.put(currChar, endX);
        }
        return subString;
    }
/*№8.Создайте функцию, которая принимает арабское число и преобразует его в римское число*/
    public static String convertToRoman(int n){
        if (n<1 || n>3999) return "incorrect";
        StringBuilder str = new StringBuilder();
        Map<Integer, String> roman = new LinkedHashMap<Integer,String>()
        {
            {
                put(1000,"M");put(900,"CM");put(500,"D");put(400,"CD");
                put(100,"C"); put(90,"XC"); put(50,"L"); put(40,"XL");
                put(10,"X");  put(9,"IX");  put(5,"V");  put(4,"IV");
                put(1,"I");
            }
        };

        for (Map.Entry<Integer, String> entry : roman.entrySet()) {
            int key = entry.getKey();
            if(n/key!=0){
                for (int i = 0; i < (n/key); i++) {
                    str.append(roman.get(key));
                }
                n%=key;
            }
        }
        return str.toString();
    }
/*№9.Создайте функцию, которая принимает строку и возвращает true или false в зависимости от того,
является ли формула правильной или нет.*/
    public static boolean formula(String formula){
        String[] tokens = formula.split(" ");
        ArrayList<Integer> ans = new ArrayList<>();
        int m = 0;
        if (!Character.isDigit(tokens[0].charAt(0))) return false;
        else ans.add(m, Integer.parseInt(tokens[0]));
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]){
                case "+":
                    ans.add(m, ans.get(m)+Integer.parseInt(tokens[i+1]));
                    break;
                case "-":
                    ans.add(m, ans.get(m)-Integer.parseInt(tokens[i+1]));
                    break;
                case "*":
                    ans.add(m, ans.get(m)*Integer.parseInt(tokens[i+1]));
                    break;
                case "/":
                    ans.add(m, ans.get(m)/Integer.parseInt(tokens[i+1]));
                    break;
                case "=":
                    m++;
                    ans.add(m, Integer.parseInt(tokens[i+1]));
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (ans.get(i) != ans.get(i+1)){
                return false;
            }
        }
        return true;
    }
/*10.	Число может не быть палиндромом, но его потомком может быть.
Прямой потомок числа создается путем суммирования каждой пары соседних цифр, чтобы создать цифры следующего числа.
Например, 123312 – это не палиндром, а его следующий потомок 363, где: 3 = 1 + 2; 6 = 3 + 3; 3 = 1 + 2.
Создайте функцию, которая возвращает значение true, если само число является палиндромом или любой из его потомков
вплоть до 2 цифр (однозначное число - тривиально палиндром).
*/
    public static boolean polindrome(long init){
        StringBuilder string = new StringBuilder(Long.toString(init));
        while(string.length() >0){
            if(string.toString().equals(string.reverse().toString())){
                return true;
            }
            StringBuilder tmpStr=new StringBuilder();
            for (int i = 0; i < string.length()-1; i+=2) {
                tmpStr.append((string.charAt(i)+string.charAt(i+1)-2*'0'));
            }
            string=tmpStr;
        }
        return false;
    }
}
