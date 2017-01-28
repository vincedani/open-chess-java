package main.java;

public class Constants {
	
	public enum progressBarStatus {
		started("started"),
		done("done"),
		message("message"),
		progress("progress");
		
		 private final String name;       

	    private progressBarStatus(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        // (otherName == null) check is not needed because name.equals(null) returns false 
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
	}
	
	
	public enum LogLevel {
		Error("Error"),
		Info("INFO"),
		Debug("Debug");
		
	    private final String name;       

	    private LogLevel(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        // (otherName == null) check is not needed because name.equals(null) returns false 
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
	}
	
	public enum Symbols
	{
		Pawn("P"),
		Queen("Q"),
		Rook("R"),
		Knight("N"),
		Bishop("B"),
		King("K"),
		Dragon("D");
		
		  private final String name;       

		    private Symbols(String s) {
		        name = s;
		    }

		    public boolean equalsName(String otherName) {
		        // (otherName == null) check is not needed because name.equals(null) returns false 
		        return name.equals(otherName);
		    }

		    public String toString() {
		       return this.name;
		    }
				
	}
	
	public enum Pieces
	{
		Pawn("Pawn"),
		Queen("Queen"),
		Rook("Rook"),
		Knight("Knight"),
		Bishop("Bishop"),
		King("King"),
		Dragon("D");
		
		  private final String name;       

		    private Pieces(String s) {
		        name = s;
		    }

		    public boolean equalsName(String otherName) {
		        // (otherName == null) check is not needed because name.equals(null) returns false 
		        return name.equals(otherName);
		    }

		    public String toString() {
		       return this.name;
		    }
				
	}
	
	public static final String  NoSupported = "Not supported yet.";

}
