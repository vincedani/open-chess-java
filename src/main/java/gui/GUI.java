/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package main.java.gui;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

import main.java.JChessApp;
import main.java.LogToFile;
import main.java.game.Game;

/**
 * Class representing the game interface which is seen by a player and where are
 * lockated available for player opptions, current games and where can he start
 * a new game (load it or save it)
 */
public class GUI {

	public Game game;
	protected static final  Properties configFile = GUI.getConfigFile();

	public GUI() {
		this.game = new Game();
	}/*--endOf-GUI--*/

	/*
	 * Method load image by a given name with extension
	 * 
	 * @name : string of image to load for ex. "chessboard.jpg"
	 * 
	 * @returns : image or null if cannot load
	 */

	public static Image loadImage(String name) {
		if (configFile == null) {
			return null;
		}
		Image img = null;
		URL url = null;
		try {
			String imageLink = "theme/" + configFile.getProperty("THEME", "default") + "/images/" + name;
			url = JChessApp.class.getResource(imageLink);
			img = ImageIO.read(url);
		} catch (Exception e) {
			LogToFile.log(e, "ERROR", "some error loading image!");
			e.printStackTrace();
		}
		return img;
	}/*--endOf-loadImage--*/

	
	public static Image loadImage(String theme, String name) {
		
		Image img = null;
		URL url = null;
		try {
			String imageLink = "theme/" + theme + "/images/" + name;
			url = JChessApp.class.getResource(imageLink);
			img = ImageIO.read(url);

		} catch (Exception e) {
			LogToFile.log(e, "ERROR", "some error loading image!");
			e.printStackTrace();
		}
		return img;
	}/*--endOf-loadImage--*/


	static String getJarPath() {
		String path = GUI.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		path = path.replaceAll("[a-zA-Z0-9%!@#$%^&*\\(\\)\\[\\]\\{\\}\\.\\,\\s]+\\.jar", "");
		int lastSlash = path.lastIndexOf(File.separator);
		if (path.length() - 1 == lastSlash) {
			path = path.substring(0, lastSlash);
		}
		path = path.replace("%20", " ");
		return path;
	}

	static Properties setDefaultConfigFile() {
		Properties confFile = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.txt");

			// set the properties value
			confFile.setProperty("THEME", "default");

			// save properties to project root folder
			confFile.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return confFile;
	}

	static Properties getConfigFile() {

		Properties confFile = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.txt");
			confFile.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
			confFile = setDefaultConfigFile();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return confFile;
	}
}
