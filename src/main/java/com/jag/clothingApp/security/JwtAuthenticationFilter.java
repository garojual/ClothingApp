package com.jag.clothingApp.security;

import com.jag.clothingApp.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService){
        this.tokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    //This method executes automatically in every request
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                    throws ServletException, IOException {

        //Extracts the token from Authorization header
        String token = getToken(request);

        //Verifies if the token is valid
        if (token != null && tokenProvider.validateToken(token)) {

            //Gets the email from the token
            String email = tokenProvider.getTokenEmail(token);

            //Loads user details (roles, password, etc.)
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            //Creates the authentication object
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


            //Establish the authentication in Spring context, allowing de restControllers access the authenticated
            //user with @AuthenticationPrincipal or SecurityContextHolder.getContext().getAuthentication()
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /*
    * Verifies if the header starts with Bearer and extracts the token from it.
    * */
    private String getToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");

        if (bearer != null && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }
}
