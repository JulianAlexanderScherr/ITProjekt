package de.hdm.itprojekt.shared.report;

import java.io.Serializable;

/**
 * Die Klasse stellt einzelne Abs�tze dar die als String gespeichert werden.
 * @author Stefan
 *
 */
public class EinfacherParagraph extends Paragraph implements Serializable {



/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * Text des Absatzes.
 */
private String text = "";

/**
 * <p>
 * Serialisierbare Klassen, die mittels GWT-RPC transportiert werden sollen,
 * m�ssen einen No-Argument-Konstruktor besitzen. Ist kein Konstruktor
 * explizit angegeben, so existiert in Java-Klassen implizit der
 * Default-Konstruktor, der dem No-Argument-Konstruktor entspricht.
 * </p>
 * <p>
 * Besitzt eine Klasse mind. einen explizit implementierten Konstruktor, so
 * gelten nur diese explizit implementierten Konstruktoren. Der
 * Default-Konstruktor gilt dann nicht. Wenn wir in einer solchen Situation
 * aber dennoch einen No-Argument-Konstruktor ben�tigen, m�ssen wir diesen wie
 * in diesem Beispiel explizit implementieren.
 * </p>
 * 
 * @see #EinfacherParagraph(String)
 * @author Peter Thies
 */

public EinfacherParagraph(){
	
}
/**
 * Dieser Konstruktor erm�glicht es, bereits bei Instantiierung von
 * <code>EinfacherParagraph</code>-Objekten deren Inhalt angeben zu k�nnen.
 * 
 * @param value der Inhalt des Absatzes
 * @see #EinfacherParagraph()
 * @author Peter Thies
 */
public EinfacherParagraph(String wert){
	this.text = wert;
}

/**
 * Inhalt Auslesen
 * @return Inhalt als String
 */
public String getText(){
	return this.text;
}
/**
 * �berschreiben des Inhalts
 * @param text der neue Inhalt
 */
public void setText( String text){
	this.text = text;
}

/**
 * Umwandeln des <code>EinfacherParagraph</code>-Objekts in einen String.
 */

public String toString(){
	return this.text;
}

}

