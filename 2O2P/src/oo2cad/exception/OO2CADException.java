package oo2cad.exception;

/**
 * Exception-Klasse fuer die Exceptions die beim OO2CAD auftreten koennen
 * 
 * @author Nabu
 *
 */
public class OO2CADException extends Exception
{
	private OO2CADExceptionConstants constant;
	
	/**
	 * verstecke leeren Konstruktor
	 */
	private OO2CADException()
	{
		super();
	}
	
	/**
     * Konstruktor, der eine OO2CADExceptionConstants nimmt um den Fehler
     * zu beschreiben,
     * 
     * @param constant
     */
    public OO2CADException(OO2CADExceptionConstants constant) {
        super(constant.getMessage());
        this.constant=constant;
        
    }
    
    /**
     * Konstruktor, der zusätzlich zur OO2CADExceptionConstants noch
     * eine Exception aufnimmt, die als Cause gesetzt wird
     * @param constant
     * @param ex
     */
    public OO2CADException(OO2CADExceptionConstants constant,Exception ex) {
    	
        this(constant);        
        initCause(ex);
        
    }
}
