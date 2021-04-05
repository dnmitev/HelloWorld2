import javax.swing.*;

import java.awt.event.*;
import java.util.*;

//// Sortirane na studenti -->
public class Apl3 extends Apl2 {
	private static final long serialVersionUID = 1L;
	protected JButton sortByNote, sortByName;

	Apl3() {
		// sort by notes
		sortByNote = new JButton("Sort By Note");
		sortByNote.addActionListener(new NoteSorter());
		othContr.add(sortByNote);

		// sort by name
		sortByName = new JButton("Sort By Name");
		sortByName.addActionListener(new NameSorter());
		othContr.add(sortByName);

		setTitle("Student's directory - version 4");
		revalidate();
	}

	class NoteSorter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			prs.sort(new StudentNoteComparator());

			// clear the list
			slist.setText("");
			for (Student s : prs) {
				slist.append(s + "\n");
				slist.setCaretPosition(slist.getDocument().getLength());
			}

			revalidate();
		}
	}

	class StudentNoteComparator implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			// za da stane vuv vuzhodqst red, razkomentirai tova:
			return s1.note - s2.note;
			// za da stane v nizhodqsht red, razkomentirai tova:
			// return s2.note - s1.note;
		}
	}

	class NameSorter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			prs.sort(new StudentNameComparator());

			// clear the list
			slist.setText("");
			for (Student s : prs) {
				slist.append(s + "\n");
				slist.setCaretPosition(slist.getDocument().getLength());
			}

			revalidate();
		}
	}

	class StudentNameComparator implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			//// za da stane vuv vuzhodqst red, razkomentirai tova:
			return s1.name.compareTo(s2.name);
			//// za da stane v nizhodqsht red, razkomentirai tova:
			// return s2.name.compareTo(s1.name);
		}
	}
}
