package de.hdm.itprojekt.shared.reportGenerator;

import java.util.List;
import java.util.Vector;

public class ZusammengesetzterParagraph extends Paragraph {

private List<EinfacherParagraph> paragraphen = new Vector<EinfacherParagraph>();

public void hinzufuegenParagraph( EinfacherParagraph p){
	
}
public void loeschenParagraph( EinfacherParagraph p){
	
}
public Vector<EinfacherParagraph> getParagraphen(){
	
}
public int anazahlParagraphen(){
	return paragraphen.legth;
}
public EinfacherParagraph getParagraphAt (int i){
	//return???
}
public String toString(){
	//return???
}

}
