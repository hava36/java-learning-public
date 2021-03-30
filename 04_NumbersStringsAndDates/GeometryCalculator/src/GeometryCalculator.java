public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        double result = 0;
        if (radius > 0) {
            result = Math.PI * Math.pow(radius, 2);
        }
        return result;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        double result = 0;
        if (radius > 0) {
            result = 4d/3d * Math.PI * Math.pow(radius, 3);
        }
        return result;
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        return ((a + b > c) && (a + c) > b && (b + c > a));
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        double result = -1;
        if (isTriangleRightAngled(a, b, c)) {
            double p = (a + b + c) / 2;
            result = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        return result;
    }
}
