package com.virtusa.employeeapi.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.virtusa.employeeapi.exceptions.JwtTokenMalformedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.tokenValidity}")
	private long tokenValidity;
	public String getUserName(final String token) {
		try {
			Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			return claims.getSubject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public String generateToken(Authentication authentication)
	{
		User user=(User)authentication.getPrincipal();
		Claims claims=Jwts.claims().setSubject(user.getUsername());
		final long currTimeInMillis=System.currentTimeMillis();
		final long expMillis=currTimeInMillis+tokenValidity;
		Date expiryDate=new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuer("GreatLearning Pvt Ltd").setExpiration(expiryDate)
				.setAudience("Java Developers").signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	public void validateToken(final String token) throws JwtTokenMalformedException {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (SignatureException e) {
			throw new JwtTokenMalformedException("Invalid Jwt Signature");
		}
		catch (ExpiredJwtException e) {
			throw new JwtTokenMalformedException("Expired Jwt Token");
		}
		catch (UnsupportedJwtException e) {
			throw new JwtTokenMalformedException("Unsupported Jwt Token");
		}
	}
}
