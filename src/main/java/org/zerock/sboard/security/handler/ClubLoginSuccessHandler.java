package org.zerock.sboard.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.sboard.security.dto.ClubAuthMember;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ClubLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("--------------------------------------");
        log.info("onAuthenticationSuccess");

        ClubAuthMember authMember = (ClubAuthMember)authentication.getPrincipal();

        log.info("Need Modify Member?" + authMember.isFromSocial());


        redirectStratgy.sendRedirect(request, response, "/member/modify?from=social");

    }

}
