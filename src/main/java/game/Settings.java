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
package main.java.game;

import java.io.Serializable;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import main.java.LogToFile;

/**
 * Class representing game settings available for the current player
 */
public class Settings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ResourceBundle loc = null;
	public LogToFile logger = new LogToFile();

	public enum GameMode {

		newGame, loadedGame
	}

	public GameMode gameMode;

	public enum BoardType {

		squareBoard, circleBoard
	}
	
	public BoardType boardType;

	public Player[] players ;

	
	public Settings(Player[] players, BoardType boardType){
		this.players= players;
		this.boardType = boardType;
	}


	public static String lang(String key) {
		if (Settings.loc == null) {
			Settings.loc = PropertyResourceBundle.getBundle("main.java.resources.i18n.main_en");
			Locale.setDefault(Locale.ENGLISH);
		}
		String result = "";
		try {
			result = Settings.loc.getString(key);
		} catch (java.util.MissingResourceException exc) {
			result = key;
		}
		return result;
	}

	public Player nextPlayer(Player actualPlayer){
		int actualIndex = java.util.Arrays.asList(players).indexOf(actualPlayer);
		if(actualIndex== (players.length - 1))
			return players[0];
		return players[actualIndex+1];
	}
}
