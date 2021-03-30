import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Aircraft> aircraftList = Airport.getInstance().getAllAircrafts();

        System.out.println(aircraftList.size());
        
    }

}
