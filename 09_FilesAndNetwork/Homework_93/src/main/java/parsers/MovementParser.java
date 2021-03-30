package parsers;

import exceptions.ParseMovementException;
import model.Company;
import model.Movement;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovementParser {

    private static ResourceBundle bundle = ResourceBundle.getBundle("errors");
    private static HashMap<String, Company> companies = new HashMap<>();

    private MovementParser() {

    }

    public static Movement parseCSV(String sourceData) throws ParseMovementException,
            NumberFormatException, ArrayIndexOutOfBoundsException {

        String regex = "(?<type>[\\S\\s]+)[,]" +
                "(?<accountnumber>[\\S\\s]+)[,]" +
                "(?<currency>[\\S\\s]+)[,]" +
                "(?<date>[\\S\\s^,]+)[,]" +
                "(?<refferance>[\\S\\s]+)[,]" +
                "(?<details>[\\S\\s^,]+)[,]" +
                "(?<income>([\"]{1}[\\d]+[\\,]{1}[\\d]+[\"]{1})|([\\d]+))[,]" +
                "(?<expense>([\"]{1}[\\d]+[\\,]{1}[\\d]+[\"]{1})|([\\d]+))";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceData);
        if (!matcher.matches()) {
            throw new ParseMovementException(String.format(bundle.getString("movement_parse_error"), sourceData));
        }

        return new Movement(parseCompany(matcher.group("details")),
                Double.parseDouble(matcher.group("income")
                        .replaceAll("\"", "")
                        .replace(',', '.')),
                Double.parseDouble(matcher.group("expense")
                        .replaceAll("\"", "")
                        .replace(',', '.')),
                matcher.group("currency"));
    }

    private static Company parseCompany(String details) {
        String[] array = details.split("[\\s]+");
        String code = details.split("[\\s]+")[array.length-1];
        if (companies.containsKey(code)) {
            return companies.get(code);
        }
        Company company = new Company(details, code);
        companies.put(code, company);
        return company;
    }

}
