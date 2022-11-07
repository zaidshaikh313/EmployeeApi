package com.virtusa.employeeapi.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.virtusa.employeeapi.exceptions.JwtTokenNotFoundException;
import com.virtusa.employeeapi.services.UserServiceImpl;
import com.virtusa.employeeapi.util.JwtUtil;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header=request.getHeader("Authorization");
		System.out.println(header); //Bearer dsfsdjfhsdhfdsi.dssufudsufhsd.dssgfgdsuifdshfhd
		if(header==null || !header.startsWith("Bearer"))
		{
			throw new JwtTokenNotFoundException("No Jwt Token Found in request header");
		}
		String token=header.substring("Bearer".length()+1);
		String userName=jwtUtil.getUserName(token);
		UserDetails userDetails=userServiceImpl.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		if(SecurityContextHolder.getContext().getAuthentication()==null)
		{
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		filterChain.doFilter(request, response);
		
	}

}
