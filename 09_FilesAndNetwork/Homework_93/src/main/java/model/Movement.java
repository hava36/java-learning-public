package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
public class Movement {

    @Getter
    private Company company;
    @Getter
    private double income;
    @Getter
    private double expense;
    private String currency;



}
