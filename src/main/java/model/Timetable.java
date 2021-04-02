package model;

import java.util.*;

public class Timetable {
    private final List<BusTrip> dailyTrips;

    public Timetable(List<BusTrip> dailyTrips) {
        this.dailyTrips = dailyTrips;
    }

    public List<BusTrip> getDailyTrips() {
        return dailyTrips;
    }

    public List<BusTrip> getEffectiveTrips() {
        List<BusTrip> result = new ArrayList<>(dailyTrips);
        result.removeAll(getIneffectiveTrips());
        return result;
    }

    public List<BusTrip> getSortedEffectiveTrips() {
        return sort(getEffectiveTrips());
    }

    public List<BusTrip> getIneffectiveTrips() {
        List<BusTrip> result = new ArrayList<>();
        for (BusTrip trip1 : dailyTrips) {
//            if (trip1.isLongerThanHour() && !result.contains(trip1)) result.add(trip1);
            for (BusTrip trip2 : dailyTrips) {
                if (trip1.effectiveThan(trip2) && !result.contains(trip2)) result.add(trip2);
                if (trip1.matches(trip2) && !result.contains(trip2) && !result.contains(trip1)) result.add(trip2);
            }
        }
        return result;
    }

    private static List<BusTrip> sort(List<BusTrip> trips) {
        List<BusTrip> result = new ArrayList<>(trips);
        result.sort(new Comparator<BusTrip>() {
            @Override
            public int compare(BusTrip trip1, BusTrip trip2) {
                return trip1.getDepartureTime().compareTo(trip2.getDepartureTime());
            }
        });
        return result;
    }
}