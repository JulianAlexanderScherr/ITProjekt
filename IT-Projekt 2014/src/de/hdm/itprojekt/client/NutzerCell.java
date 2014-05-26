package de.hdm.itprojekt.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.itprojekt.shared.bo.Nutzer;

public class NutzerCell extends AbstractCell<Nutzer> {

	@Override
	public void render(Context context, Nutzer value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
	      if (value == null) {
	        return;
	      }

	      sb.appendHtmlConstant("<div>");
	      sb.appendEscaped(value.getVorname());
	      sb.appendHtmlConstant(" ");
	      sb.appendEscaped(value.getNachname());
	      sb.appendHtmlConstant("</div>");
		
	}

}
