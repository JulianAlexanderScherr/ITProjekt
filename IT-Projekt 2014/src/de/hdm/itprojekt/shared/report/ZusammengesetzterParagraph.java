package de.hdm.itprojekt.shared.report;


import java.io.Serializable;

import java.util.Vector;


/**
 * In dieser Klasse werden einzelne Abschnitte in einer Vectorliste abgelegt und Bilden
 * so gemeinsam einen größeren Abschnitt. In diesem sind die Abschnitte dann als <code>unterparagraphen</code> 
 * hinterlegt.
 * @author Stefan
 *
 */
public class ZusammengesetzterParagraph extends Paragraph implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private Vector<EinfacherParagraph> unterparagraphen = new Vector<EinfacherParagraph>();

/**
 * Einen Unterabschnitt hinzufügen
 * @param p
 */
public void hinzufuegenUnterParagraph( EinfacherParagraph p){
	this.unterparagraphen.addElement(p);
}

/**
 * Einen Unterabschnitt entfernen.
 * @param p
 */
public void loeschenUnterParagraph( EinfacherParagraph p){
	this.unterparagraphen.remove(p);
}

/**
 * Auslesen sämtlicher Unterabschnitte
 * @return
 */
public Vector<EinfacherParagraph> getParagraphen(){
	return unterparagraphen;
	
}
/**
 * Auslesen der Anzahl der Unterabschnitte
 * @return
 */
public int anazahlParagraphen(){
	return unterparagraphen.size();
}

/**
 * Auslesen eines Einzelnen Unterabschnitts.
 * @param i
 * @return
 */
public EinfacherParagraph getParagraphAt(int i){
	return this.unterparagraphen.elementAt(i);
}

/**
 * Umwandeln eines <code>ZusamengesetzterParagraph</code> in einen String
 * Hierfür wird eine Schleife genutzt die jeden einzelnen <code>EinfacherParagraph</code> aus einem 
 * <code>unterparagraphen</code>-Vektor ausließt und diesen an eine Zeichenkette anhängt.
 */
public String toString(){
	StringBuffer result = new StringBuffer();
	  for (int i = 0; i < this.unterparagraphen.size(); i++) {
	      EinfacherParagraph p = this.unterparagraphen.elementAt(i);
	      result.append(p.toString() + "\n");
	    }
	  return result.toString();
	
}

}
