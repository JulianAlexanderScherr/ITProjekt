package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.reportGenerator.EinzelbeitragsReport;
import de.hdm.itprojekt.shared.reportGenerator.EinzelnutzerReport;
import de.hdm.itprojekt.shared.reportGenerator.MehrereBeitraegeReport;

/**
 * Das paralell zum Interface {@link ReportGenerator} erstellte Interface. 
 * Es wird automatisch vom Google Plugin erstellt und gepflegt.
 * @author Stefan
 *
 */
public interface ReportGeneratorAsync {

	void createMehrereBeitraegeReport(
			AsyncCallback<MehrereBeitraegeReport> callback);

	void init(AsyncCallback<Void> callback);

	void setNutzer(Nutzer n, AsyncCallback<Void> callback);

	void createEinzelnutzerReport(AsyncCallback<EinzelnutzerReport> callback);

	void createEinzelbeitragsReport(AsyncCallback<EinzelbeitragsReport> callback);

}
