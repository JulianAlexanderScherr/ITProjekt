package de.hdm.itprojekt.shared;



import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.report.MehrereBeitraegeReport;
import de.hdm.itprojekt.shared.report.MehrnutzerReport;

/**
 * Das paralell zum Interface {@link ReportGenerator} erstellte Interface. 
 * Es wird automatisch vom Google Plugin erstellt und gepflegt.
 * @author Stefan
 *
 */
public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void createMehrereBeitraegeReport(Date az, Date ez, Nutzer n,
			String auswahl, AsyncCallback<MehrereBeitraegeReport> callback);

	

	void createMehrnutzerReport(Date az, Date ez, String auswahl,
			AsyncCallback<MehrnutzerReport> callback);

}
