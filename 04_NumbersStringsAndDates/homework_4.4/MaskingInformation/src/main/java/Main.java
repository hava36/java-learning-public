public class Main {

    public static void main(String[] args) {

        String text = "Номер кредитной карты <4008> 1234 <5678> 8912";
        text = searchAndReplaceDiamonds(text, "***");
        System.out.println(text);
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        int start = text.indexOf('<');
        int end = text.indexOf('>');
        while (start < end) {
            text = text.replace(text.substring(start, end + 1), placeholder);
            start = text.indexOf('<');
            end = text.indexOf('>');
        }
        return text;
    }

}