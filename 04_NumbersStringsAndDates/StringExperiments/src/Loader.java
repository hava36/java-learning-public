
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String[] earnings = text.replaceAll("[a-яА-я\\-\\,]", "").trim().split("\\s+");
        int total = 0;
        for (String value: earnings
             ) {
            total += Integer.parseInt(value);
        }
        System.out.println(total);
    }
}