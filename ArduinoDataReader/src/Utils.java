import java.util.List;

import javax.swing.JComboBox;

public class Utils
{
	public static <T> void CargarCombo(JComboBox<T> combo, List<T> items)
	{
		for(T item : items)
		{
			combo.addItem(item);
		}
		
		combo.setSelectedIndex(-1);
	}

}
