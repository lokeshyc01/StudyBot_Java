package com.app.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.service.JWTService;
import com.app.service.UserDetailService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	private final JWTService jwtService;
	
	private final UserDetailService userService;
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		System.out.println(authHeader);
		
		final String jwt;
		final String username;
		
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer "))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		username = jwtService.extractUserName(jwt);
		
		if(StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
			
			if(jwtService.isTokenValid(jwt, userDetails))
			{
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				securityContext.setAuthentication(token);
				SecurityContextHolder.setContext(securityContext);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}
