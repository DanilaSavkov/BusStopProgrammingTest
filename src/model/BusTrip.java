package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BusTrip {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    private final Company company;
    private final Calendar departureTime;
    private final Calendar arrivalTime;

    public BusTrip(Company company, Calendar departureTime, Calendar arrivalTime) {
        this.company = company;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        if (arrivalTime.before(departureTime)) arrivalTime.add(Calendar.DAY_OF_YEAR, 1);
    }

    public BusTrip(Company company, String simpleDepartureTime, String simpleArrivalTime) throws ParseException {
        this.company = company;
        this.departureTime = Calendar.getInstance();
        this.departureTime.setTime(SIMPLE_DATE_FORMAT.parse(simpleDepartureTime));
        this.arrivalTime = Calendar.getInstance();
        this.arrivalTime.setTime(SIMPLE_DATE_FORMAT.parse(simpleArrivalTime));
        if (arrivalTime.before(departureTime)) arrivalTime.add(Calendar.DAY_OF_YEAR, 1);
    }

    public Company getCompany() {
        return company;
    }

    public Calendar getDepartureTime() {
        return departureTime;
    }

    public boolean isLongerThanHour() {
        Calendar hourAfterDepartureTime = (Calendar) departureTime.clone();
        hourAfterDepartureTime.add(Calendar.HOUR, 1);
        return arrivalTime.after(hourAfterDepartureTime);
    }

    public boolean effectiveThan(BusTrip anotherBusTrip) {
        int departureComparingValue = this.departureTime.compareTo(anotherBusTrip.departureTime);
        int arrivalComparingValue = this.arrivalTime.compareTo(anotherBusTrip.arrivalTime);
        if (departureComparingValue == 0 && arrivalComparingValue == 0)
            return this.company.getComfortRate() > anotherBusTrip.company.getComfortRate();
        else return departureComparingValue >= 0 && arrivalComparingValue <= 0;
    }

    private static String getSimpleDateFormat(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    @Override
    public String toString() {
        return getCompany().getName() + " " + getSimpleDateFormat(departureTime.getTime()) + " " + getSimpleDateFormat(arrivalTime.getTime());
    }
}