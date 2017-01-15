package main.java.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import main.java.JChessApp;
import main.java.game.Game;
import main.java.game.Player;
import main.java.game.Settings;
import java.awt.event.ActionListener;

public class NewGameWindowV2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField namePlayer1;
	private JTextField namePlayer2;
	private JTextField namePlayer3;
	private JCheckBox threePlayerGame;
	private JComboBox<String> player1Color;
	private JComboBox<String> player2Color;
	private JComboBox<String> player3Color;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameWindowV2 frame = new NewGameWindowV2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewGameWindowV2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		namePlayer1 = new JTextField();
		namePlayer1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Player 1:");
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(158px;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),},
			new RowSpec[] {
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		contentPane.add(namePlayer1, "2, 4, left, top");
		contentPane.add(lblNewLabel, "2, 2, left, top");
		
		player1Color = new JComboBox<String>();
		player1Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "White", "Blue"}));
		player1Color.setSelectedIndex(0);
		contentPane.add(player1Color, "4, 4, left, default");
		
		JLabel lblNewLabel_1 = new JLabel("Player 2:");
		contentPane.add(lblNewLabel_1, "2, 6");
		
		namePlayer2 = new JTextField();
		contentPane.add(namePlayer2, "2, 8, left, default");
		namePlayer2.setColumns(10);
		
		player2Color = new JComboBox<String>();
		player2Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "White", "Blue"}));
		player2Color.setSelectedIndex(1);
		contentPane.add(player2Color, "4, 8, left, default");
		
		JLabel lblThreePersonChess = new JLabel("Three person chess?");
		contentPane.add(lblThreePersonChess, "2, 10");
		
		threePlayerGame = new JCheckBox("");
		threePlayerGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namePlayer3.setEnabled(threePlayerGame.isSelected());
				player3Color.setEnabled(threePlayerGame.isSelected());
			}
		});
		threePlayerGame.setSelected(true);
		contentPane.add(threePlayerGame, "4, 10");
		
		JLabel lblNewLabel_2 = new JLabel("Player 3:");
		contentPane.add(lblNewLabel_2, "2, 12");
		
		namePlayer3 = new JTextField();
		contentPane.add(namePlayer3, "2, 14, left, default");
		namePlayer3.setColumns(10);
		
		player3Color = new JComboBox<String>();
		player3Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "White", "Blue"}));
		player3Color.setSelectedIndex(2);
		contentPane.add(player3Color, "4, 14, left, default");
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(threePlayerGame.isSelected() && player1Color.getSelectedIndex() != player2Color.getSelectedIndex() && player1Color.getSelectedIndex() != player3Color.getSelectedIndex() && player2Color.getSelectedIndex() != player3Color.getSelectedIndex())
				{
					//Three player chess in a circle board
					String name1 = namePlayer1.getText();
					Player player1 = new Player(name1, (String) player1Color.getSelectedItem());
					
					String name2 = namePlayer2.getText();
					Player player2 = new Player(name2, (String) player2Color.getSelectedItem());
					
					String name3 = namePlayer3.getText();
					Player player3 = new Player(name3, (String) player3Color.getSelectedItem());
					
					Player[] players = {player1, player2, player3};
					
					Game newGUI = JChessApp.getJcv().addNewTab(name1 + " vs. " + name2 + " vs. " + name3);
					Settings gameSettings= new Settings(players, Settings.boardTypes.circleBoard, Settings.gameTypes.local);
					newGUI.newGameV2(gameSettings);
				}
				else if(!threePlayerGame.isSelected() && player1Color.getSelectedIndex() != player2Color.getSelectedIndex()){
					//Two player chess in a square board
					String name1 = namePlayer1.getText();
					Player player1 = new Player(name1, (String) player1Color.getSelectedItem());
					
					String name2 = namePlayer2.getText();
					Player player2 = new Player(name2, (String) player2Color.getSelectedItem());
					
					Player[] players = {player1, player2};
					
					Game newGUI = JChessApp.getJcv().addNewTab(name1 + " vs. " + name2 );
					Settings gameSettings= new Settings(players, Settings.boardTypes.squareBoard, Settings.gameTypes.local);
					newGUI.newGameV2(gameSettings);
					newGUI.getChessboard().getDisplay().repaint();
					
				}else{
					JOptionPane.showMessageDialog(null, "Select different colors for each player");
				}
			}
		});
		contentPane.add(btnNewButton, "4, 18, right, default");
	}
	
}
