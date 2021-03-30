import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.time.*;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


public class Solution {

    public static void main(String[] args) {

        List<Terminal> terminals = Airport.getInstance().getTerminals();

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusHours(2);

        terminals.stream().forEach(t -> t.getFlights().stream()
                .filter(flight -> {
                    LocalDateTime flyDate = flight.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    return flyDate.isBefore(endDate) && flyDate.isAfter(startDate);
                })
                .forEach(flight -> System.out.println(String.format("%s - %s", flight.getDate().toString(), flight.getAircraft()))));

    }


}




