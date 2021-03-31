import file.TimetableFileReader;
import file.TimetableFileWriter;
import model.Timetable;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File(args[0]);
        TimetableFileReader reader = new TimetableFileReader(input);
        Timetable timetable = reader.read();
        TimetableFileWriter writer = new TimetableFileWriter(new File(input.getParent(), "output.txt"));
        writer.write(timetable);
    }
}