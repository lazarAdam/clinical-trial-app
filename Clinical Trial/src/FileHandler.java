import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import com.google.gson.*;

public class FileHandler {
	// TODO add file selector to upload a json file.
	private final static String TEST = ".\\assignment_1_example.json";

	private class PatientReadingsJson {
		private ArrayList<ReadingJson> patient_readings;
	}

	private class ReadingJson {
		private String patient_id;
		private String reading_type;
		private String reading_id;
		private String reading_value;
		private String reading_date;
	}

	public static void main(String[] args){
		readJsonFile(TEST);
	}

	//TODO remove static after testing!
	protected static void readJsonFile (String fileLocation) {
		Gson gson = new GsonBuilder().serializeNulls().create();

		try (Reader fileReader = new FileReader(fileLocation)) {
			PatientReadingsJson readingList = gson.fromJson(fileReader, PatientReadingsJson.class);
			addPatientsToTrial(readingList.patient_readings);
			AddReadingToPatient(readingList.patient_readings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO DELETE THIS AFTER TESTING!!! This will create a new patient for
	// every reading in the file
	private static void addPatientsToTrial(ArrayList<ReadingJson> readings) {
		for (ReadingJson readingJson : readings) {
			Patient patient = new Patient(readingJson.patient_id);
			ClinicalTrial.getAllPatients().add(patient);
		}
	}

	private static void AddReadingToPatient(ArrayList<ReadingJson> readings) {
		for (ReadingJson reading : readings) {
			Patient patient = ClinicalTrial.findPatient(reading.patient_id);
			if (patient == null) {
				continue;
			}
			String readingId = reading.reading_id;
			String type = reading.reading_type;
			long date = Long.parseLong(reading.reading_date);
			try {
				double value = Integer.parseInt(reading.reading_value);
				patient.addReading(readingId, type, value, date);
			} catch (java.lang.NumberFormatException e) {
				String value = reading.reading_value;
				patient.addReading(readingId, type, value, date);
			}
		}

		//TODO REMOVE AFTER TESTING!!!
		for (Patient patient : ClinicalTrial.getAllPatients()) {
			for (Reading reading : patient.getReadings()) {
				System.out.println("Patient: " + patient.getPatientId());
				System.out.println(reading.toString());
				System.out.println("**************************************");
			}
		}
	}

}
