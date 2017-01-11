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

import main.java.game.Settings;

public class NewGameWindowV2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField player1;
	private JTextField player2;
	private JTextField player3;
	private JCheckBox threePlayerGame;
	private JComboBox<String> player1Color;
	private JComboBox<String> player2Color;
	private JComboBox<String> player3Color;
	private final Action threePersonGame = new SwingAction();
	private final Action newGame = new SwingAction_1();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		player1 = new JTextField();
		player1.setColumns(10);
		
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
		contentPane.add(player1, "2, 4, left, top");
		contentPane.add(lblNewLabel, "2, 2, left, top");
		
		player1Color = new JComboBox<String>();
		player1Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "White", "Blue"}));
		player1Color.setSelectedIndex(0);
		contentPane.add(player1Color, "4, 4, left, default");
		
		JLabel lblNewLabel_1 = new JLabel("Player 2:");
		contentPane.add(lblNewLabel_1, "2, 6");
		
		player2 = new JTextField();
		contentPane.add(player2, "2, 8, left, default");
		player2.setColumns(10);
		
		player2Color = new JComboBox<String>();
		player2Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "White", "Blue"}));
		player2Color.setSelectedIndex(1);
		contentPane.add(player2Color, "4, 8, left, default");
		
		JLabel lblThreePersonChess = new JLabel("Three person chess?");
		contentPane.add(lblThreePersonChess, "2, 10");
		
		threePlayerGame = new JCheckBox("");
		threePlayerGame.setAction(threePersonGame);
		threePlayerGame.setSelected(true);
		contentPane.add(threePlayerGame, "4, 10");
		
		JLabel lblNewLabel_2 = new JLabel("Player 3:");
		contentPane.add(lblNewLabel_2, "2, 12");
		
		player3 = new JTextField();
		contentPane.add(player3, "2, 14, left, default");
		player3.setColumns(10);
		
		player3Color = new JComboBox<String>();
		player3Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "White", "Blue"}));
		player3Color.setSelectedIndex(2);
		contentPane.add(player3Color, "4, 14, left, default");
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setAction(newGame);
		contentPane.add(btnNewButton, "4, 18, right, default");
	}
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			player3.setEnabled(threePlayerGame.isSelected());
			player3Color.setEnabled(threePlayerGame.isSelected());
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			if(threePlayerGame.isSelected() && player1Color.getSelectedIndex() != player2Color.getSelectedIndex() && player1Color.getSelectedIndex() != player3Color.getSelectedIndex() && player2Color.getSelectedIndex() != player3Color.getSelectedIndex())
			{
				
			}
			else if(!threePlayerGame.isSelected() && player1Color.getSelectedIndex() != player2Color.getSelectedIndex()){
				
				
			}else{
				JOptionPane.showMessageDialog(null, "Select different colors for each player");
			}
		}
	}
}
