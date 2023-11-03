package com.softvan;

import com.softvan.Repository.UserRepsitory;
import com.softvan.dto.request.PatientPageRequest;
import com.softvan.jwt.JwtProperties;
import com.softvan.repository.CustomRepoIntreface;
import com.softvan.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

@SpringBootApplication//@EnableJpaRepositories(basePackageClasses = {com.softvan.Repository.RoleRepository.class} )
@ComponentScan({"com.softvan.*"})
//@EntityScan("com.softvan.*")
@Slf4j
@RequiredArgsConstructor
public class SoftvanAdminApplication  implements CommandLineRunner {


	@Autowired
	private CustomRepoIntreface customRepoIntreface;

	private final JwtProperties jwtProperties;

	public static void main(String[] args) {

		SpringApplication.run(SoftvanAdminApplication.class, args);
		//System.out.println(LocalDate.now());
	}

	@Override
	public void run(String... args) throws Exception {
		 customRepoIntreface.serachApi(new PatientPageRequest(1, 5, "rushi"));

	}
//	@Override
//	public void run(String... args) throws Exception {
//      log.info(jwtProperties.getJwtSecretKey());
//	  log.info("jwt {}",jwtProperties.getJwtExpireTimeInMinute());
//	  log.info("public path {}",jwtProperties.getPermitAllPaths());
//	}
}
