package com.example.vestiback.security.jwt;

import com.example.vestiback.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Cette classe est utilisée pour filtrer les requêtes HTTP dans une application web basée sur Spring Security
 * et effectuer des opérations liées à l'authentification basée sur les jetons JWT (JSON Web Tokens).
 * */
public class AuthTokenFilter extends OncePerRequestFilter {

    private  JwtUtils jwtUtils;

    private final UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * AuthTokenFilter est utilisée pour filtrer les requêtes HTTP, extraire et valider les jetons JWT,
     * charger les détails de l'utilisateur associé au jeton et
     * configurer l'authentification de l'utilisateur dans le contexte de sécurité.
     * La méthode doFilterInternal() est une méthode importante de la classe
     * OncePerRequestFilter qui est appelée pour chaque
     * requête HTTP. Cette méthode effectue les actions suivantes
     * */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * La méthode parseJwt() est utilisée pour extraire le jeton JWT de l'en-tête d'autorisation de la requête.
     * Elle vérifie si l'en-tête d'autorisation est présent et commence par le préfixe "Bearer ".
     * Si c'est le cas, elle renvoie le jeton JWT sans le préfixe.
     * Sinon, elle renvoie null.
     * */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7,headerAuth.length());
        }
        return null;
    }
}
