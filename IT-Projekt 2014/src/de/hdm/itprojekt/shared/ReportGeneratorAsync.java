package de.hdm.itprojekt.shared;



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

	void createMehrereBeitraegeReport(Nutzer n,
			AsyncCallback<MehrereBeitraegeReport> callback);

	

	void createMehrnutzerReport(AsyncCallback<MehrnutzerReport> callback);

}
