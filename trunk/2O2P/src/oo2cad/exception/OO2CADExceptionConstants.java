package oo2cad.exception;

/**
 * Konstantenklasse für die Fehlerbehandung
 * 
 * @author Nabu
 * 
 */
public class OO2CADExceptionConstants
{

	private String message;

	private OO2CADExceptionConstants(String message)
	{
		this.message = message;
	}

	/**
	 * Konfigurations-Fehler
	 */
	public static final OO2CADExceptionConstants CONFIG_ERROR = new OO2CADExceptionConstants("Fehler bei der Konfiguration!");

	/**
	 * Eingabefehler
	 */
	public static final OO2CADExceptionConstants INPUT_ERROR = new OO2CADExceptionConstants("Eingabefehler festgestellt!");

	/**
	 * Entpackungs-Fehler
	 */
	public static final OO2CADExceptionConstants UNZIP_ERROR = new OO2CADExceptionConstants("Fehler beim Entpacken!");
	
	/**
	 * Parser-Fehler
	 */
	public static final OO2CADExceptionConstants PARSER_ERROR = new OO2CADExceptionConstants("Fehler beim Parsen!");
	
	/**
	 * CAD-Datei Fehler
	 */
	public static final OO2CADExceptionConstants CAD_CREATION_ERROR = new OO2CADExceptionConstants("Fehler beim Schreiben der CAD-Datei!");
	
	/**
	 * Gibt den Fehler als Text zurück
	 * 
	 * @return message den Fehlertext
	 */
	public String getMessage()
	{
		return this.message;
	}
}
