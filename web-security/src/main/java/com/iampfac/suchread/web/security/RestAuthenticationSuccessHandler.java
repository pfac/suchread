package com.iampfac.suchread.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		final SavedRequest savedRequest = requestCache.getRequest(request, response);

		final String targetUrlParameter = getTargetUrlParameter();
		if (savedRequest != null
				&& (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.isNotBlank(request.getParameter(targetUrlParameter))))) {
			requestCache.removeRequest(request, response);
		}

		clearAuthenticationAttributes(request);
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

}
