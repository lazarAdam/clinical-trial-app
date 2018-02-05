public class Reading {
	private String readingId;
	private String type;
	private double value;
	private String bpValue;
	private long date;

	protected Reading(String readingId, String type, double value, long date) {
		this.readingId = readingId;
		this.type = type;
		this.value = value;
		this.date = date;
	}

	protected Reading(String readingId, String type, String bpValue, long date) {
		this.readingId = readingId;
		this.type = type;
		this.bpValue = bpValue;
		this.date = date;
	}

	public String getReadingId() {
		return readingId;
	}

	public String getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

	public String getBpValue() {
		return bpValue;
	}

	public Long getDate() {
		return date;
	}
	
	/**
	 * Set new type and reading value for patients in trial
	 * @param type
	 * @param ReadingValue
	 * @param patient
	 */
	public void setTypeandReading(String type, String ReadingValue, Patient patient) {
		if(patient.isActive()) { //Reading can only be added if patient is active i.e in patient trial
			this.type = type; //Set the Reading type
			try {
				double newValue = Integer.parseInt(ReadingValue);
				this.value = newValue; //Set value that is not of blood_pressure
			} catch (java.lang.NumberFormatException e) {
				String newValue = ReadingValue;
				this.bpValue = newValue; //Set the bpValue
			}
			
			
		}
	}
	
	//Added comma by replacing /n for printing in the GUI
	public String toString() {
		String string = "Reading ID: " + readingId + ", " + "Type: " + type + ", ";
		if (bpValue == null) {
			string = string + "Value: " + value + ", ";
		}else{
			string = string + "Value: " + bpValue + ", ";
		}

		string = string + "Date: " + date;
		return string;
	}
}