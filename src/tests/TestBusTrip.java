package tests;

import model.BusTrip;
import model.Company;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class TestBusTrip {
    @Test
    public void testEffectiveThanPositive() throws ParseException {
        BusTrip trip1 = new BusTrip(Company.GROTTY, "12:30", "13:25");
        BusTrip trip2 = new BusTrip(Company.GROTTY, "12:45", "13:25");
        Assert.assertTrue(trip2.effectiveThan(trip1));
    }

    @Test
    public void testEffectiveThanNegative() throws ParseException {
        BusTrip trip1 = new BusTrip(Company.POSH, "17:25", "18:01");
        BusTrip trip2 = new BusTrip(Company.GROTTY, "12:45", "13:25");
        Assert.assertFalse(trip1.effectiveThan(trip2));
    }

    @Test
    public void testIsLongerThanHourPositive() throws ParseException {
        BusTrip trip = new BusTrip(Company.POSH, "17:25", "18:26");
        Assert.assertTrue(trip.isLongerThanHour());
    }

    @Test
    public void testIsLongerThanHourNegative() throws ParseException {
        BusTrip trip1 = new BusTrip(Company.POSH, "23:03", "00:01");
        BusTrip trip2 = new BusTrip(Company.GROTTY, "23:03", "00:06");
        Assert.assertFalse(trip1.isLongerThanHour());
        Assert.assertTrue(trip2.isLongerThanHour());
    }
}