import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenduCell extends DefaultTableCellRenderer
{
    private ModeleTab modele;

    public RenduCell(ModeleTab modele)
    {
        this.modele = modele;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellComponent.setBackground(modele.getRowColor(row));

        return cellComponent;
    }


}