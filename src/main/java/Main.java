import file.TimetableFileReader;
import file.TimetableFileWriter;
import model.BusTrip;
import model.Timetable;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File(args[0]);
        File output = new File(input.getParent(), "output.txt");
        TimetableFileReader reader = new TimetableFileReader(input);
        Timetable timetable = reader.read();
        TimetableFileWriter writer = new TimetableFileWriter(output);
        writer.write(timetable);
        System.out.println("New timetable was generated in " + output);
    }
}