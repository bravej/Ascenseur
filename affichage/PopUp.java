package affichage;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUp extends JFrame{
	
	private String message;
	
	public PopUp(String message){
		
		this.message = message;
		this.setTitle("Information");
		this.setSize(500, 70);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel label = new JLabel(message);
	    
	    Box box = Box.createVerticalBox();
	    box.add(label);
		
	    this.getContentPane().add(box);
		this.setVisible(true);
	}
}
