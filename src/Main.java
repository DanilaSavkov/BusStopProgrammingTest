import file.TimetableFileReader;
import file.TimetableFileWriter;
import model.BusTrip;
import model.Timetable;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("/home/savok/dualLab/tests/test3.txt");
        TimetableFileReader reader = new TimetableFileReader(input);
        Timetable timetable = reader.read();
        for (BusTrip trip : timetable.getDailyTrips()) System.out.println(trip);
        TimetableFileWriter writer = new TimetableFileWriter(new File(input.getParent(), "output.txt"));
        writer.write(timetable);
    }
}