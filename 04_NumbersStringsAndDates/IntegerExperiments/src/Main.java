public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 22221;

        Main main = new Main();

        System.out.println(main.sumDigits(12345));
        System.out.println(main.sumDigits(10));
        System.out.println(main.sumDigits(5059191));

        System.out.println(main.sumDigitsFromCharacter(12345));
        System.out.println(main.sumDigitsFromCharacter(10));
        System.out.println(main.sumDigitsFromCharacter(5059191));

    }

    public Integer sumDigits(Integer number)
    {

        String numberByString = Integer.toString(number);

        int sum = 0;
        for (int i = 0; i < numberByString.length(); i++) {
            sum += Integer.parseInt(numberByString.substring(i,i+1));
        }

        return sum;

    }

    public Integer sumDigitsFromCharacter(Integer number)
    {

        String numberByString = Integer.toString(number);

        int sum = 0;
        for (int i = 0; i < numberByString.length(); i++) {
            sum += Integer.parseInt(String.valueOf(numberByString.charAt(i)));
        }

        return sum;

    }

}
