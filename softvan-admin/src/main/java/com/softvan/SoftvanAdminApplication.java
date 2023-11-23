package com.softvan;

import com.softvan.dto.PatientDto;
import com.softvan.dto.request.PatientPageRequest;
import com.softvan.dto.response.PatientPagedResponse;
import com.softvan.jwt.JwtProperties;
import com.softvan.repository.PatientRepository;
import com.softvan.repository.PatientsCustomRepository;
import com.softvan.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

@SpringBootApplication//@EnableJpaRepositories(basePackageClasses = {com.softvan.Repository.RoleRepository.class} )
@ComponentScan({"com.softvan.*"})
//@EntityScan("com.softvan.*")
@Slf4j
@RequiredArgsConstructor
public class SoftvanAdminApplication  implements CommandLineRunner {


	private final PatientService patientService;


	private final JwtProperties jwtProperties;

	public static void main(String[] args) {

		SpringApplication.run(SoftvanAdminApplication.class, args);

		}

	@Override
	public void run(String... args) throws Exception {
		PatientPageRequest pageRequest = new PatientPageRequest(null,2,"mal",null,null);
		PatientPagedResponse patientPagedResponse = patientService.searchPatient(pageRequest);
		System.out.println("Firstname\t Lastname\t HasAllergy\n");
		for (PatientDto patientDto : patientPagedResponse.getPatientlist()) {


			System.out.printf("%s\t %s\t %s\n",
					patientDto.getFirstName() ,
					patientDto.getLastName(),
					patientDto.getHasAllergy());
		}
		System.out.printf("Total page : %s\n", patientPagedResponse.getTotalPage());
		System.out.printf("Total Records : %s\n", patientPagedResponse.getTotalRecords());
		System.out.printf("Has next : %s\n", patientPagedResponse.isHasNext());
		System.out.printf("Has Previous : %s\n", patientPagedResponse.isHasNext());
		System.out.printf("Is First : %s\n", patientPagedResponse.isFirstPage());
		System.out.printf("Is Last : %s\n", patientPagedResponse.isLastPage());

	}
//	@Override
//	public void run(String... args) throws Exception {
//      log.info(jwtProperties.getJwtSecretKey());
//	  log.info("jwt {}",jwtProperties.getJwtExpireTimeInMinute());
//	  log.info("public path {}",jwtProperties.getPermitAllPaths());
//	}
}
