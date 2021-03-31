package file;

import model.BusTrip;
import model.Company;
import model.Timetable;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TimetableFileReader {
    private final BufferedReader reader;

    public TimetableFileReader(File file) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(file));
    }

    public Timetable read() throws IOException {
        return new Timetable(getBusTrips());
    }

    private List<BusTrip> getBusTrips() throws IOException {
        List<BusTrip> trips = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null)
            trips.add(stringToBusTrip(line));
        reader.close();
        return trips;
    }

    private BusTrip stringToBusTrip(String string) throws IOException {
        String[] parameters = string.split(" ");
        try {
            return new BusTrip(Company.valueOf(parameters[0].toUpperCase()), parameters[1], parameters[2]);
        } catch (ParseException e) {
            throw new IOException("The file contains records in the wrong format.", e);
        }
    }
}
