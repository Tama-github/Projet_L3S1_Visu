import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class RenduCell extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        ArrayList<Integer> lignes = new ArrayList<>();
        lignes.add(1);
        lignes.add(3);
        final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (lignes.contains(row))
        {
            cellComponent.setBackground(Color.red);
        }
        else
        {
            cellComponent.setBackground(Color.white);
        }
        return cellComponent;
    }


}