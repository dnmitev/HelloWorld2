import javax.swing.*;

import org.w3c.dom.ranges.RangeException;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;;

public class Apl extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private String NOTE_TEXT = "note?";
	private String NAME_TEXT = "name?";
	
	protected JButton ad;
	protected JTextField tf[] = new JTextField[2];
	protected JTextArea slist;

	protected JPanel contr, othContr, plst;

	protected AdSt adSt;
	protected ArrayList<Student> prs = new ArrayList<Student>(10);

	Apl(int x, int y, int ln, int ht) {
		this.setLayout(new BorderLayout());
		this.setBounds(x, y, ln, ht);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Student's directory - version 1");
		
		slist = new JTextArea(25, 50);
		slist.setEditable(false);

		contr = new JPanel(new FlowLayout());
		othContr = new JPanel(new FlowLayout());
		plst = new JPanel(new FlowLayout());

		ad = new JButton("add");
		contr.add(ad);
		
		tf[0] = new JTextField(NAME_TEXT, 25);
		tf[1] = new JTextField(NOTE_TEXT, 3);
		
		contr.add(tf[0]);
		contr.add(tf[1]);
		
		ad.addActionListener(adSt = new AdSt());
		
		tf[1].addActionListener(adSt);
		plst.add(new JScrollPane(slist));
		
		add("North", contr);
		add("South", othContr);
		add("Center", plst);
		
		revalidate();
	}

	class AdSt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Student s;
			int note;
			String name = tf[0].getText();
			
			// Proverka dali sa vuvedeni 3 imena --->
			// https://regex101.com/r/mGyGCg/1 
			String pattern = "^\\s*(?:(?:\\b[a-zA-Z]+\\b)[ ]*){3}$";
			Matcher m = Pattern.compile(pattern).matcher(name);
			if	(!m.matches()) {
				JOptionPane.showMessageDialog(contr, "Student name should have 3 names, i.e. Ivan Petrov Ivanov", "Invalid name", 2);
				
				tf[0].setText(NAME_TEXT);
				return;
			}
			// Proverka dali sa vuvedeni 3 imena <---
			
			try {
				note = Integer.parseInt(tf[1].getText());
				
				// Proverka na ocenkta mejdu 2 i 6 --->
				if (note < 2 || note > 6) {
					throw new RangeException((short) 0, "Note should be in range 2 to 6");
				}
				// proverka na ocenka mejdu 2 i 6 <----
			} catch (NumberFormatException ex) {
				// Proverka na tipa na ocenka, izkarva greshka, ako ne e cqlo chislo ->>>
				JOptionPane.showMessageDialog(contr, "Note should be an integer", "Invalid note", 2);
				// proverka na tipa na ocenka <---
				
				tf[1].setText(NOTE_TEXT);
				return;
			}
			catch (RangeException ex) {
				// Proverka na ocenkta mejdu 2 i 6 --->
				JOptionPane.showMessageDialog(contr, ex.getMessage(), "Invalid note", 2);
				// proverka na ocenka mejdu 2 i 6 <----
				
				tf[1].setText(NOTE_TEXT);
				return;
			}
			
			prs.add(s = new Student(name, note));
			
			tf[0].setText(NAME_TEXT);
			tf[1].setText(NOTE_TEXT);
			
			slist.append(s + "\n");
			
			slist.setCaretPosition(slist.getDocument().getLength());

			revalidate();
		}
	}
}