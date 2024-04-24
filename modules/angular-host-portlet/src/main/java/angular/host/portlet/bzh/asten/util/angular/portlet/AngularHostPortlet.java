package angular.host.portlet.bzh.asten.util.angular.portlet;

import angular.host.portlet.bzh.asten.util.angular.constants.AngularHostPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * Permet d'inclure une application Angular dans un portlet Liferay en mode
 * développement.
 * 
 * @author Julien
 */
@Component(property = {
		"com.liferay.portlet.display-category=Angular",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AngularHost",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + AngularHostPortletKeys.ANGULARHOST,
		"javax.portlet.security-role-ref=power-user,user"
}, service = Portlet.class)
public class AngularHostPortlet extends GenericPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		serveNgServeProxy(renderRequest, renderResponse);
	}

	/**
	 * Génère le markup nécessaire pour inclure dans la page le contenu servi par le
	 * serveur de développement Angular. (ng serve).
	 * Par défaut la commande ng serve est lancée sur le port 4200.
	 * en utilisant la balise <app-root></app-root> qui est le point d'entrée de
	 * l'application Angular.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void serveNgServeProxy(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		PrintWriter out = renderResponse.getWriter();
		out.println("<script type='module' src='http://localhost:4200/@vite/client'></script>");
		out.println("<script type='module' src='http://localhost:4200/polyfills.js'></script>");
		out.println("<script type='module' src='http://localhost:4200/main.js'></script>");
		out.println("<app-root></app-root>");
		out.println(
				"<div><span> ⚠️ Development mode : Angular content from <a href='http://localhost:4200'>http://localhost:4200</a></span></div>");
		out.flush();
	}

}