package de.hdm.itprojekt.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.itprojekt.shared.bo.Nutzer;

/**
 * Zur Realisierung der Cell-Lists in GWT wird eine Cell-Klasse benötigt,
 * welche die Aufgabe hat, den Inhalt der Cell-List darzustellen. 
 * @author Martin
 *
 */
public class NutzerCell extends AbstractCell<Nutzer> {

	@Override
	public void render(Context context, Nutzer value, SafeHtmlBuilder sb) {
		// der Nutzer könnte null sein, deshalb ein Null-Check
	      if (value == null) {
	        return;
	      }

	      sb.appendHtmlConstant("<div><strong>");
	      sb.appendEscaped(value.getVorname());
	      sb.appendHtmlConstant(" ");
	      sb.appendEscaped(value.getNachname());
	      sb.appendHtmlConstant("</strong> (");
	      sb.appendEscaped(value.getNickname());
	      sb.appendHtmlConstant(")");
	      sb.appendHtmlConstant("</div>");
		
	}

}
