package model;

import java.time.LocalTime;

public class BusTrip {
    private final Company company;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;

    public BusTrip(Company company, LocalTime departureTime, LocalTime arrivalTime) {
        this.company = company;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public BusTrip(Company company, String simpleDepartureTime, String simpleArrivalTime) {
        this.company = company;
        this.departureTime = LocalTime.parse(simpleDepartureTime);
        this.arrivalTime = LocalTime.parse(simpleArrivalTime);
    }

    public Company getCompany() {
        return company;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public boolean isLongerThanHour() {
        LocalTime oneHourAfterDepartureTime = departureTime.plusHours(1);
        return arrivalTime.isAfter(oneHourAfterDepartureTime);
    }

    public boolean effectiveThan(BusTrip anotherBusTrip) {
        if (anotherBusTrip.isLongerThanHour() && !this.isLongerThanHour()) return true;
        int departureComparingValue = this.departureTime.compareTo(anotherBusTrip.departureTime);
        int arrivalComparingValue = this.arrivalTime.compareTo(anotherBusTrip.arrivalTime);
        if (onlyOneArrivesTomorrow(this, anotherBusTrip)) {
            departureComparingValue = this.plusHour().departureTime.compareTo(anotherBusTrip.plusHour().departureTime);
            arrivalComparingValue = this.plusHour().arrivalTime.compareTo(anotherBusTrip.plusHour().arrivalTime);
        }
        if (departureComparingValue == 0 && arrivalComparingValue == 0)
            return this.company.getComfortRate() > anotherBusTrip.company.getComfortRate();
        else return departureComparingValue >= 0 && arrivalComparingValue <= 0;
    }

    private static boolean onlyOneArrivesTomorrow(BusTrip trip1, BusTrip trip2) {
        return (trip1.isArrivesTomorrow() && !trip2.isArrivesTomorrow()) || (!trip1.isArrivesTomorrow() && trip2.isArrivesTomorrow());
    }

    private BusTrip plusHour() {
        return new BusTrip(company, departureTime.plusHours(1), arrivalTime.plusHours(1));
    }

    private boolean isArrivesTomorrow() {
        return departureTime.isAfter(arrivalTime);
    }

    public boolean matches(BusTrip anotherBusTrip) {
        return !this.equals(anotherBusTrip)
                && this.company.equals(anotherBusTrip.company)
                && this.departureTime.equals(anotherBusTrip.departureTime)
                && this.arrivalTime.equals(anotherBusTrip.arrivalTime);
    }

    @Override
    public String toString() {
        return getCompany().getName() + " " + departureTime + " " + arrivalTime;
    }
}