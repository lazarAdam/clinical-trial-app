package trial;
/**
 * Patient class contains patient Id, a boolean active for being in the trial, and an arraylist of corresponding readings.
 */

import java.util.ArrayList;
import java.util.Date;

/**
 * The Class Patient.
 */
public class Patient {
	
	/** The patient id. */
	// Initialization
	private String patientId;
	
	/** The active. */
	private boolean active;
	
	/** The readings. */
	private ArrayList<Reading> readings;
	
	/** The reading count. */
	private int readingCount = 1;

	/**
	 * Class Constructor specifying the patient Id.
	 *
	 * @param patientId            the id of the patient
	 */
	public Patient(String patientId) {
		this.patientId = patientId;
		this.setActive(true);
		this.readings = new ArrayList<Reading>(); // Creates an arrayList of reading
	}

	/**
	 * Getter for patient Id.
	 *
	 * @return patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * Getter for patient's readings.
	 *
	 * @return an arrayList of readings
	 */
	public ArrayList<Reading> getReadings() {
		return readings;
	}
	
	/**
	 * Gets the reading uid.
	 *
	 * @return the reading uid
	 */
	private String getReadingUid() {
		String Uid = patientId + readingCount++;
		for (Reading reading : readings) {
			if (Uid.equals(reading.getReadingId())) {
				return getReadingUid();
			}
		}

		return Uid;
	}

	/**
	 * Adds the new readings to the Patient object readings ArrayList if the patient
	 * is active i.e on trial
	 *
	 * @param readingId            the reading Id number
	 * @param type            the reading type
	 * @param value            the reading value
	 * @param date            the date of reading
	 * @param clinic the clinic
	 * @return the boolean
	 */
	public Boolean addReading(String readingId, String type, String value, Date date, Clinic clinic) {
		if (active) { // Only add if the patient is active i.e in clinical trial
			try {
				if (readingId == null || readingId.equals("")) {
					readingId = getReadingUid();
				} else {
					// check for duplicate readings
					for (Reading reading : readings) {
						if (readingId.equals(reading.getReadingId())) {
							return false;
						}
					}
				}
				Reading reading = new Reading(readingId, type, value, date, clinic); // create reading object
				readings.add(reading); // add the new reading to the reading ArrayList
				return true;
			} catch (IllegalArgumentException e) {
			}
		}
		return false;

	}

	/**
	 * Returns patient's active boolean value.
	 *
	 * @return active the boolean to show if patient is in trial
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets true if patient is in trial.
	 *
	 * @param active            the boolean to show if patient is in trial
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * toString method to print the parameters of the Patient class in string form.
	 *
	 * @param dateFormat the date format
	 * @return the string
	 */
	public String toString(String dateFormat) {
		String string = new String();
		for (Reading reading : readings) {
			string += reading.toString(dateFormat);
		}
		return string;
	}

}
