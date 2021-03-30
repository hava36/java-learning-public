import java.util.ArrayList;
import java.util.List;

public class Loader
{
    public static void main(String[] args)
    {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        System.out.println(String.format("Cat's count: %s", Cat.getCount()));

        System.out.println(cat1.getWeight());
        System.out.println(cat2.getWeight());
        System.out.println(cat3.getWeight());
        System.out.println(cat4.getWeight());
        System.out.println(cat5.getWeight());


        cat1.feed(10.0);
        cat2.feed(15.0);

        System.out.println(cat1.getWeight());
        System.out.println(cat2.getWeight());

        while (!cat3.getStatus().equals("Exploded")) {
            cat3.feed(100.0);
        }

        System.out.println(cat3.getStatus());
        while (!cat4.getStatus().equals("Dead")) {
            cat4.meow();
        }
        System.out.println(cat4.getStatus());

        cat5.feed(150.00);
        cat5.pee();


        System.out.println(cat5.getFeedAmount());

        System.out.println(String.format("Cat's count: %s", Cat.getCount()));

        Cat cat6 = Loader.getKitten();
        Cat cat7 = Loader.getKitten();
        Cat cat8 = Loader.getKitten();

        System.out.println(cat6.getWeight());
        System.out.println(cat7.getWeight());
        System.out.println(cat8.getWeight());


        Cat cat10 = new Cat(cat8);

    }

    private static Cat getKitten() {
        return new Cat(1100.00);
    }

}