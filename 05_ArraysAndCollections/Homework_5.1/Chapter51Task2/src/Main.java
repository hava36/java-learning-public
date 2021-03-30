import java.util.Locale;

public class Main {

    private static final int COUNT_OF_PATIENTS = 30;
    private static final float MIN_TEMPERATURE = 36.2f;
    private static final float MAX_TEMPERATURE = 36.9f;

    public static void main(String[] args) {
        float[] array = new float[COUNT_OF_PATIENTS];
        float totalTemperature = 0f;
        int countOfHealthyPatients = 0;
        System.out.print("Температуры пациентов:");
        for (int index = 0; index < array.length; index++) {
            array[index] = 32 + (float) Math.round(80 * Math.random()) / 10;
            totalTemperature += array[index];
            if (array[index] >= MIN_TEMPERATURE && array[index] <= MAX_TEMPERATURE) countOfHealthyPatients++;
            System.out.printf(Locale.US, " %.01f", array[index]);
        }
        System.out.printf(Locale.US, "\nСредняя температура: %.01f", totalTemperature/COUNT_OF_PATIENTS);
        System.out.printf(Locale.US, "\nКоличество здоровых: %d", countOfHealthyPatients);
    }

}
