package com.app.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JWTServiceImple implements JWTService{

	
	public String generateToken(UserDetails userdetails)
	{
		return Jwts.builder().setSubject(userdetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
				.signWith(getSignatureKey(),SignatureAlgorithm.HS256).compact();
	}
	
	private <T> T extractClaim(String token,Function<Claims, T> claimsResolvers)
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}
	
	public String extractUserName(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}
	
	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignatureKey()
	{
		byte[] key = Decoders.BASE64.decode("jwtsecret");
		return Keys.hmacShaKeyFor(key);
		
	}

	@Override
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username =extractUserName(token);
		return (username.equals(userDetails.getUsername()) && isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
}
