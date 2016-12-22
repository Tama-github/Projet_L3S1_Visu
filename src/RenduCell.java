import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenduCell extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Object val = table.getValueAt(row, 3);

        System.out.println("Si " + val + " est egal à " + value);
        if(val.equals(value)) {
            System.out.println("ok pour " + val);
            cellComponent.setForeground(Color.black);
            cellComponent.setBackground(Color.red);
        }
        else
        {
            cellComponent.setForeground(Color.black);
            cellComponent.setBackground(Color.white);
        }
        return cellComponent;
    }


}