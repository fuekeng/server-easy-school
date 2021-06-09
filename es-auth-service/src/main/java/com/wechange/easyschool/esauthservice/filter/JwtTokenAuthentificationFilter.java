package com.wechange.easyschool.esauthservice.filter;

import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.escommon.exception.CommonException;
import com.wechange.easyschool.escommon.security.CustomUserDetails;
import com.wechange.easyschool.escommon.security.JwtConfig;
import com.wechange.easyschool.escommon.security.JwtTokenProvider;
import com.wechange.easyschool.escommon.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rkouete
 */
public class JwtTokenAuthentificationFilter extends OncePerRequestFilter {


    private UserDetailsServiceImpl userDetailsService;
    private JwtConfig config;
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenAuthentificationFilter(UserDetailsServiceImpl userDetailsService, JwtConfig config, JwtTokenProvider jwtTokenProvider) {
        this.config = config;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService=userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtTokenProvider.getJwtFromRequest(request);

        if (token != null) {

            try {
                if (jwtTokenProvider.validateToken(token)) {
                    String userId = jwtTokenProvider.getUserIdFromToken(token);

                    CustomUserDetails userDetails = userDetailsService.loadUserById(userId);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(userDetails);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (CommonException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getErrorCode().getCode());
                return;
            }
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, EnumErrorCode.ERROR_JWT_TOKEN_NOTFOUND.getCode());
            return;
        }

        filterChain.doFilter(request, response);
    }


}
