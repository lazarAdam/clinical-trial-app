package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.GuiController;
import trial.ClinicalTrial;

public class ButtonEndTrialListener implements ActionListener {
	GuiController guiController; //Initialize
	
	/**
	 * Constructor 
	 * @param guiController the controller of the GUI components
	 */
	public ButtonEndTrialListener(GuiController guiController){
		this.guiController = guiController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		try { // Handle the exception if no patient is selected to
			// resume the trial.
		ClinicalTrial.findPatient(
				ClinicalTrial.getAllPatients().get(guiController.getDisplayPatientListView().getComboBoxPatientsIds().getSelectedIndex()).getPatientId())
				.setActive(false);
		JOptionPane.showMessageDialog(null, "Patient ID: "
				+ ClinicalTrial.getAllPatients().get(guiController.getDisplayPatientListView().getComboBoxPatientsIds().getSelectedIndex()).getPatientId()
				+ "\nTrial has ended");
	} catch (ArrayIndexOutOfBoundsException ex) {
		JOptionPane.showMessageDialog(null,
				"Please select a patient from the list. Add a patient if list is empty.");
		//displayPatientList(); // Go back to the display frame again
	}
	}
	
}