package com.softvan.repository;

import com.softvan.dto.PatientDto;
import com.softvan.dto.request.PatientPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomeRepoImp implements CustomRepoIntreface{


    @Autowired
    private EntityManager entityManager;



    @Override
    @Transactional
    public void serachApi(PatientPageRequest patientPageRequest) {

        Pageable pageable = PageRequest.of(patientPageRequest.getPageNumber()-1, patientPageRequest.getPageSize());
        String  projectionBased  = "select new com.softvan.dto.PatientDto(p.id,p.firstName,p.lastName,p.email,p.dateOfBirth,p.createdDateTime,p.updatedDateTime,p.status,p.patientInfoEntity.hasAllergy,p.patientInfoEntity.hasBloodPressure)  from PatientEntity as p join p.patientInfoEntity where 1=1";
        String queryMainPart  = "select p  from PatientEntity as p join p.patientInfoEntity where 1=1";

        // for add another quwry part
        List<String> jpqlParts = new ArrayList<>();

        //
        //jpqlParts.add(queryMainPart);
        jpqlParts.add(projectionBased);

        HashMap<String, Object> params = new HashMap<>();

        if(patientPageRequest.getFirstName()!=null){
            jpqlParts.add("AND (p.firstName) LIKE ('%' || :firstname || '%')");
            params.put("firstname",patientPageRequest.getFirstName());
        }








        String join = String.join("\n", jpqlParts);
        System.out.println(join);
//

        TypedQuery<PatientDto> query = entityManager.createQuery(join,PatientDto.class);
          for(String param : params.keySet()){
              System.out.println(param);
              query.setParameter(param,params.get(param));
          }
        int pageNo = patientPageRequest.getPageNumber() - 1;
        query.setMaxResults(patientPageRequest.getPageSize());
         query.setFirstResult(pageNo*patientPageRequest.getPageSize());

        List<PatientDto> patients = query.getResultList();
        int size = patients.size();
        for(PatientDto patient : patients ){
              System.out.println(patient.getFirstName());
              System.out.println(patient.getHasAllergy());
        }
        System.out.println(size);
//        Page page = new PageImpl(patients, pageable, patients.size());
//           return page;

//        List<PatientEntity> resultList = query.getResultList();
//        resultList.forEach(patient-> System.out.println(resultList1));

    }
}
