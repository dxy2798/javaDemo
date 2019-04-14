package CalendarTest;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComboBox4 {
	String[] s = { "����", "ƻ��", "��ݮ", "�㽶", "����" };

	public JComboBox4() {
		JFrame f = new JFrame("JComboBox");
		Container contentPane = f.getContentPane();

		JComboBox combo = new JComboBox(s);
		combo.setBorder(BorderFactory.createTitledBorder("����ϲ������Щˮ��?"));
		combo.setRenderer(new ACellRenderer());
		combo.setMaximumRowCount(5);

		contentPane.add(combo);
		f.pack();
		f.show();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new JComboBox4();
	}
}

class ACellRenderer extends JLabel implements ListCellRenderer {
	ACellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value != null) {
			setText(value.toString());
			setIcon(new ImageIcon(".\\icons\\fruit" + (index + 1) + ".jpg"));
		}
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}
}
