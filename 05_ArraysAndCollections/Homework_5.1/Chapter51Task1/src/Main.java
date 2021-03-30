public class Main {

    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] words = text.split(",?\\s+");
        for (int wordIndex = 0; wordIndex < words.length / 2; wordIndex++) {
            String x = words[wordIndex];
            words[wordIndex] = words[words.length - wordIndex - 1];
            words[words.length - wordIndex - 1] = x;
        }
        for (String word : words) {
            System.out.println(word);
        }
    }

}
