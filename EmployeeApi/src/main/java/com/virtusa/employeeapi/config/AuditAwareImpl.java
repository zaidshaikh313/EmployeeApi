package com.virtusa.employeeapi.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import net.bytebuddy.asm.Advice.Return;

@Component
public class AuditAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("ksrao");
	}
	
}
