package de.hdm.itprojekt.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.VerwaltungsklasseImpl;
import de.hdm.itprojekt.shared.ReportGenerator;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.reportGenerator.EinzelbeitragsReport;
import de.hdm.itprojekt.shared.reportGenerator.EinzelnutzerReport;
import de.hdm.itprojekt.shared.reportGenerator.MehrereBeitraegeReport;

/**
 * Implementierung des <code>ReportGenerator</code>-Interface.
 * @author Stefan
 *
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ein ReportGenerator benoetigt Zugriff auf die Verwaltungsklasse.
	 */
	private Verwaltungsklasse administration = null;
	/**
	 * No-Argument-Konstruktor zur erstellung eines RemoteServiceServlets.
	 * @throws IllegalArgumentException
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
		
	}
	public void init1() throws IllegalArgumentException {
		VerwaltungsklasseImpl a = new VerwaltungsklasseImpl();
		a.init();
		this.administration = a;
	}
	
	protected Verwaltungsklasse getVerwaltung(){
	return this.administration;
	}
	
	@Override
	public void init() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNutzer(Nutzer n) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MehrereBeitraegeReport createMehrereBeitraegeReport()
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EinzelnutzerReport createEinzelnutzerReport()
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EinzelbeitragsReport createEinzelbeitragsReport()
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
