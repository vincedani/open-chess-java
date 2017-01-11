package main.java.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NewGame extends JPanel {
	private JTextField player2;
	private JTextField player1;

	/**
	 * Create the panel.
	 */
	public NewGame() {
		
		JLabel lblPlayer = new JLabel("Player 1: ");
		add(lblPlayer);
		
		player1 = new JTextField();
		add(player1);
		player1.setColumns(10);
		
		player2 = new JTextField();
		add(player2);
		player2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Player 2:");
		add(lblNewLabel);

	}

}
