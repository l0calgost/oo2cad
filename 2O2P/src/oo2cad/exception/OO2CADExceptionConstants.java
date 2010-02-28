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
	 * Gibt den Fehler als Text zurück
	 * 
	 * @return message den Fehlertext
	 */
	public String getMessage()
	{
		return this.message;
	}
}
