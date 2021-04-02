package file;

import model.BusTrip;
import model.Company;
import model.Timetable;

import java.io.*;

public class TimetableFileWriter implements AutoClosable {
    private final File file;

    public TimetableFileWriter(File file) {
        this.file = file;
    }

    public void write(Timetable timetable) throws IOException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            writeAllTimetable(bufferedWriter, timetable);
        } finally {
            autoClose(bufferedWriter);
            autoClose(fileWriter);
        }
    }

    private void writeAllTimetable(BufferedWriter bufferedWriter, Timetable timetable) throws IOException {
        writeCompanyTrips(bufferedWriter, timetable, Company.POSH);
        bufferedWriter.newLine();
        writeCompanyTrips(bufferedWriter, timetable, Company.GROTTY);
    }

    private void writeCompanyTrips(BufferedWriter writer, Timetable timetable, Company company) throws IOException {
        for (BusTrip trip : timetable.getSortedEffectiveTrips()) {
            if (trip.getCompany().equals(company)) writer.write(trip.toString() + "\n");
        }
    }
}
