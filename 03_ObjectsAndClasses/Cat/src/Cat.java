import model.enums.Color;

public class Cat
{

    private static final int EYE_COUNT = 2;
    private static final double MIN_WEIGHT = 1000.0;
    private static final double MAX_WEIGHT = 9000.0;

    private static int count = 0;

    private double originWeight;
    private double weight;
    private double feedAmount;
    private boolean isAlive;
    private Color color;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        feedAmount = 0;
        isAlive = true;
        color = Color.BLACK;
        count++;

    }

    public Cat(double weight) {
        this();
        weight = weight;
        originWeight = weight;
    }

    public Cat(Cat originCat)
    {
        setWeight(originCat.getWeight());
        setOriginWeight(originCat.getOriginWeight());
        setFeedAmount(originCat.getFeedAmount());
        setAlive(originCat.isAlive());
        setColor(originCat.getColor());

        count++;

    }

    public static int getCount() {
        return count;
    }

    public void meow() {

        if (isAlive) {
            weight = weight - 1;
            System.out.println("Meow");
            checkCatAlive();
        }

    }

    public void feed(Double amount)
    {

       if (isAlive) {
            weight = weight + amount;
            feedAmount += amount;
           checkCatAlive();
        }

    }

    public void drink(Double amount)
    {

        if (isAlive) {
            weight = weight + amount;
            checkCatAlive();
        }

    }

    public void pee() {

        if (isAlive) {
            weight = weight - 500 * Math.random();
            System.out.println("Cat peed!");
            checkCatAlive();
        }

    }



    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setFeedAmount(double feedAmount) {
        this.feedAmount = feedAmount;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public double getFeedAmount() {
        return feedAmount;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void checkCatAlive() {

        if ((weight < MIN_WEIGHT || weight > MAX_WEIGHT)  && isAlive) {
            count--;
            isAlive = false;
            System.out.println("The Cat died. It doesn't do it.");
        }

    }

}