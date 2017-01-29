package main.java.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import main.java.game.Settings;

public class ChooseThemeWindow extends JFrame {

	private JPanel contentPane;
	private JList<String> themeList;
	JLabel previewImage;
	private JButton btnSelect;

	public ChooseThemeWindow(Frame parent) {
		setResizable(false);
		setTitle("Select Theme");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("450px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("130px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));	
		
		JPanel imageContainer = new JPanel();
		contentPane.add(imageContainer, "4, 2, fill, fill");
		
		previewImage = new JLabel("");
		imageContainer.add(previewImage);
		
		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Properties prp = GUI.getConfigFile();
				String selectedTheme = themeList.getSelectedValue();
				if (selectedTheme !=null) {
					prp.setProperty("THEME", selectedTheme);
					try {
						FileOutputStream fOutStr = new FileOutputStream("config.txt");
						prp.store(fOutStr, null);
						fOutStr.flush();
						fOutStr.close();
					} catch (java.io.IOException exc) {
						exc.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, Settings.lang("changes_visible_after_restart"));
					ChooseThemeWindow.this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Select a valid theme");
				
			}}
		});
		contentPane.add(btnSelect, "4, 4, right, default");
		
		File dir = new File(GUI.getJarPath() + File.separator + "main" + File.separator + "java" + File.separator
				+ "theme" + File.separator);

		System.out.println("Theme path: " + dir.getPath());

		File[] files = dir.listFiles();
		if (files != null && dir.exists()) {
			
			String[] dirNames = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				dirNames[i] = files[i].getName();
			}
			themeList = new JList<>(dirNames);
			contentPane.add(themeList, "2, 2, fill, fill");
			themeList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					String selectedTheme = themeList.getSelectedValue();
					ImageIcon image = new ImageIcon(GUI.loadImage(selectedTheme, "Preview.png"));
					previewImage = new JLabel(image);
					imageContainer.removeAll();
					imageContainer.add(previewImage);
					imageContainer.revalidate();
				}
			});
		
			
		} 

	}}
