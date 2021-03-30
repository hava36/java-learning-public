public class Main {

    public static void main(String[] args) {

        String text = "Номер кредитной карты *** 1234 *** 8912";
        System.out.println(searchAndReplaceDiamonds(text, "****"));

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        return text.replaceAll("[\\<][\\s\\d.]+[\\>]", placeholder);
    }

}