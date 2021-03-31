package file;

import model.BusTrip;
import model.Company;
import model.Timetable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TimetableFileWriter {
    private final BufferedWriter writer;

    public TimetableFileWriter(File file) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(file));
    }

    public void write(Timetable timetable) throws IOException {
        writeCompanyTrips(timetable, Company.POSH);
        writer.newLine();
        writeCompanyTrips(timetable, Company.GROTTY);
        writer.close();
    }

    private void writeCompanyTrips(Timetable timetable, Company company) throws IOException {
        for (BusTrip trip : timetable.getSortedEffectiveTrips()) {
            if (trip.getCompany().equals(company)) writer.write(trip.toString() + "\n");
        }
    }
}
