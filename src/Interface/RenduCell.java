package Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenduCell extends DefaultTableCellRenderer
{
    private Alerte alerte;

    public RenduCell(Alerte alerte)
    {
        this.alerte = alerte;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (alerte.isOkay()) {
            //verif type et valeur
            try {
                if (table.getValueAt(row, 1).toString().equals(alerte.getTypeDonnees())) {
                    if (Double.parseDouble(table.getValueAt(row, 3).toString()) < alerte.getMin() || Double.parseDouble(table.getValueAt(row, 3).toString()) > alerte.getMax()) {
                        cellComponent.setBackground(Color.red);
                    } else {
                        cellComponent.setBackground(Color.white);
                    }
                } else {
                    cellComponent.setBackground(Color.white);
                }
            }catch (NumberFormatException nfe)
            {
                cellComponent.setBackground(Color.white);
            }
            }
            else
            {
                cellComponent.setBackground(Color.white);
            }
        return cellComponent;
    }


}