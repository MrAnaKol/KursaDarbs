package lv.venta.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.ServletException;
import lv.venta.service.IDalibnieksCRUDService;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IDalibnieksCRUDService dalibnieksService;

	@Override
	public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("test");
		try {
            // Custom logic after successful login
            // For example, retrieving user-related data and processing it
            String username = authentication.getName();
            int userId = dalibnieksService.izveletiesDalibniekuPecLietotajvarda(username).getIdD();

            // Redirect to a specific URL after successful login
            String redirectUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("profils/" + userId)
                                    .toUriString();
            response.sendRedirect(redirectUrl);
        } catch (Exception e) {
            // Handle exceptions appropriately, for example, log the error and redirect to an error page
            response.sendRedirect("/error-page");
        }
	}
}
