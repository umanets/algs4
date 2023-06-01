/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        String helloMsg = String.format("Hello %s and %s.", args[0], args[1]);
        String goodbyeMsg = String.format("Goodbye %s and %s.", args[1], args[0]);
        System.out.println(helloMsg);
        System.out.println(goodbyeMsg);
    }
}
