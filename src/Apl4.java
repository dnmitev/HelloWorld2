import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

// Iztrivane na student po ime
public class Apl4 extends Apl3 {
	private static final long serialVersionUID = 1L;
	protected JButton deleteStudent;
	
	Apl4() {
		deleteStudent = new JButton("Delete");
		deleteStudent.addActionListener(new DeleteStudent());
		othContr.add(deleteStudent);

		setTitle("Student's directory - version 5");
		revalidate();
	}
	
	class DeleteStudent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String nameToDelete = JOptionPane.showInputDialog("Enter student name that should be deleted: ");
			
			ArrayList<Student> students = new ArrayList<Student>();
			prs.forEach(s ->  {
				if (s.name.compareToIgnoreCase(nameToDelete) != 0) {
					students.add(s);
				}
			});
			
			prs = students;
			
			slist.setText("");
			for (Student s : prs) {
				slist.append(s + "\n");
				slist.setCaretPosition(slist.getDocument().getLength());
			}

			revalidate();
		}
	}
}
