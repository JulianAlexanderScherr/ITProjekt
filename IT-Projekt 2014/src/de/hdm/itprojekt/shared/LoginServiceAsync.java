package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.LoginInfo;

/**
 * Zur LoginService Klasse dazugehörige Async-Klasse
 * @author Schwab
 *
 */
public interface LoginServiceAsync {
	
  public void login(String requestUri, AsyncCallback<LoginInfo> async);
  
}