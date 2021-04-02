package file;

import model.BusTrip;
import model.Company;
import model.Timetable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimetableFileReader implements AutoClosable {
    private final File file;

    public TimetableFileReader(File file) {
        this.file = file;
    }

    public Timetable read() throws IOException {
        return new Timetable(getBusTrips());
    }

    private List<BusTrip> getBusTrips() throws IOException {
        List<BusTrip> trips = new ArrayList<>();
        fillTripsList(trips);
        return trips;
    }

    private void fillTripsList(List<BusTrip> tripsToFill) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            linesToTripsList(tripsToFill, bufferedReader);
        } finally {
            autoClose(bufferedReader);
            autoClose(fileReader);
        }
    }

    private void linesToTripsList(List<BusTrip> tripsToFill, BufferedReader bufferedReader) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null){
            tripsToFill.add(stringToBusTrip(line));
        }
    }

    private BusTrip stringToBusTrip(String string) {
        String[] parameters = string.split(" ");
        return new BusTrip(Company.valueOf(parameters[0].toUpperCase()), parameters[1], parameters[2]);
    }
}
