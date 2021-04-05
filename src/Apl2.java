import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Apl2 extends Apl1 {
	private static final long serialVersionUID = 1L;

	Apl2(){
        tf[0].addFocusListener(new InName());
        tf[1].addFocusListener(new InNote());
        this.setTitle("Student's directory - version 3");
    }

	class InName implements FocusListener {
		public void focusGained(FocusEvent e) {
			if (tf[0].getText().equals("name?"))
				tf[0].setText("");
		}

		public void focusLost(FocusEvent e) {
			if (tf[0].getText().equals(""))
				tf[0].setText("name?");
		}
	}

	class InNote implements FocusListener {
		public void focusGained(FocusEvent e) {
			if (tf[1].getText().equals("note?"))
				tf[1].setText("");
		}

		public void focusLost(FocusEvent e) {
			if (tf[1].getText().equals(""))
				tf[1].setText("note?");
		}
	}
}