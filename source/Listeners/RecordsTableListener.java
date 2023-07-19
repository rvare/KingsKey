import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RecordsTableListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        UI.getContentDisplayPanel().removeAll();
        createDefaultUI();
        UI.getContentDisplayPanel().revalidate();
        UI.getContentDisplayPanel().repaint();
    }

    public void createDefaultUI() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable dataTable = new JTable(Backend.getDataObjects(), Backend.getColumnNames());
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane);
        UI.getContentDisplayPanel().add(panel);
    }
}