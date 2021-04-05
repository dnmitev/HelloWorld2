import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Apl1 extends Apl {
	private static final long serialVersionUID = 1L;
	protected JButton load, save;

	Apl1() {
		super(40, 80, 800, 650);
		load = new JButton("load");
		load.addActionListener(new Load());
		contr.add(load);
		save = new JButton("save");
		save.addActionListener(new Save());
		contr.add(save);
		setTitle("Student's directory - version 2");
		revalidate();
	}

	class Save implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream("save.ser"));
				oos.writeObject(prs);
				tf[0].setText("saved");
			} catch (IOException ex) {
				System.out.println(ex);
			}
			try {
				if (oos != null)
					oos.close();
			} catch (IOException ex) {
			}
		}
	}

	class Load implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			ObjectInputStream ios = null;
			try {
				ios = new ObjectInputStream(new FileInputStream("save.ser"));
				prs = (ArrayList<Student>) ios.readObject();
			} catch (Exception ex) {
				tf[0].setText("Error in save.ser");
			}
			try {
				if (ios != null)
					ios.close();
			} catch (IOException ex) {
			}
			slist.setText("");
			for (Student s : prs) {
				slist.append(s + "\n");
			}
			slist.setCaretPosition(slist.getDocument().getLength());
		}
	}
}