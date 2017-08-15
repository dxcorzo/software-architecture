package listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import model.Ciudad;
import model.Departamento;

public class ItemChangeListener implements ItemListener
{
	JComboBox<Ciudad> comboBoxCiudad;
	
	public ItemChangeListener(JComboBox<Ciudad> comboBoxCiudad)
	{
		this.comboBoxCiudad = comboBoxCiudad;
	}

	@Override
	public void itemStateChanged(ItemEvent event)
	{
		if (event.getStateChange() == ItemEvent.SELECTED)
		{
			model.Departamento item = (Departamento) event.getItem();
			
			comboBoxCiudad.removeAllItems();
			
			for(model.Ciudad ciudad : item.getCiudades())
			{
				comboBoxCiudad.addItem(ciudad);
			}
		}
	}
}