package listeners;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import gui.GuiController;
import trial.SystemSettings;

public class CheckBoxXmlAddPatients implements ItemListener{
	GuiController guiController;
	
	public CheckBoxXmlAddPatients(GuiController guiController){
		this.guiController = guiController;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		SystemSettings settings = guiController.getClinicalTrial().getSettings();
		if (e.getStateChange() == 1) {
			settings.setXmlAddUnknownPatients(true);
			guiController.getSystemSettingView().checkBoxXmlReadings.setEnabled(true);
		}else {
			settings.setXmlAddUnknownPatients(false);
			settings.setXmlAddUnknownReadings(false);
			guiController.getSystemSettingView().checkBoxXmlReadings.setEnabled(false);
			guiController.getSystemSettingView().checkBoxXmlReadings.setSelected(false);
		}
	}		

}