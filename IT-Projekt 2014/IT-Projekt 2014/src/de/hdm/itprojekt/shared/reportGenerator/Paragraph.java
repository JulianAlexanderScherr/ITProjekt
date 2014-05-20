package de.hdm.itprojekt.shared.reportGenerator;

import java.io.Serializable;



/**
 * Reports ben�tigen die M�glichkeit, Text strukturiert abspeichern zu k�nnen.
 * Die Verwendung der Klasse <code>String</code>
 * reicht hier nicht aus, da allein das Hinzuf�gen eines Zeilenumbruchs zur
 * Markierung eines Absatzendes Kenntnisse �ber das Zielformat voraussetzt. Im
 * Falle einer rein textuellen Darstellung w�rde hierzu ein doppeltes "
 * <code>\n</code>" gen�gen. Bei dem Zielformat HTML m�sste jedoch der gesamte
 * Absatz in entsprechendes Markup eingef�gt werden.
 * <p>
 * 
 * <code>Paragraph</code> ist <code>Serializable</code>, so das Objekte dieser
 * Klasse durch das Netzwerk �bertragbar sind.
 * 
 * 
 * @author vgl.Peter Thies
 */
public class Paragraph implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
