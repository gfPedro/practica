package com.practica.oee;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCaching
@EnableJpaAuditing
@EnableTransactionManagement
public class PracticaBackendConfiguration {

	@Bean
	public ModelMapper modelMapper(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		final PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
		modelMapper.getConfiguration().setPropertyCondition(context -> unitUtil.isLoaded(context.getSource()));
		return modelMapper;
	}

}
