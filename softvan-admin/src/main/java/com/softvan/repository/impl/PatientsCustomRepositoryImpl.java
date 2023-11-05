package com.softvan.repository.impl;

import com.softvan.dto.PatientDto;
import com.softvan.dto.request.PatientPageRequest;
import com.softvan.repository.PatientsCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class PatientsCustomRepositoryImpl implements PatientsCustomRepository {

    private final EntityManager em;

    @Override
    public Page<PatientDto> search(PatientPageRequest patientPageRequest) {

        Map<String, Object> whereClause = new HashMap<>();

        // JDK 14 Text Block Feature
        StringBuilder commonJpqlBuider = new StringBuilder("""
                SELECT
                @projection
                FROM PatientEntity p
                JOIN p.patientInfoEntity pi
                WHERE 1=1
                """);

        if (nonNull(patientPageRequest.searchText())){
                commonJpqlBuider.append(" AND CONCAT(p.firstName,' ', p.lastName) LIKE :searchText");
                whereClause.put("searchText", "%" + patientPageRequest.searchText() + "%");
        }

        String projection = """
                new com.softvan.dto.PatientDto
                (
                    p.id,
                    p.firstName,
                    p.lastName,
                    p.email,
                    p.dateOfBirth,
                    p.createdDateTime,
                    p.updatedDateTime,
                    p.status,
                    pi.hasAllergy,
                    pi.hasBloodPressure            
                )
                """;
        String listJpql = commonJpqlBuider.toString().replace("@projection", projection);

        TypedQuery<PatientDto> listQuery = this.em.createQuery(listJpql, PatientDto.class);

        for (String key : whereClause.keySet()) {
            listQuery.setParameter(key,whereClause.get(key));
        }

        // Calculate and set LIMIT AND OFFSET
        // Pagination logic for limit and offset calculation
        /*  Page size = 5
            Page 1 -> 0 -> (1-1) * 5 = 0
            Page 2 -> 5 -> (2-1) * 5 = 5
            Page 3 -> 10 -> (3-1) * 5 = 10
            Page 4 -> 15 -> (4-1) * 5 = 15
            offset = (pageNo - 1) * pageSize
         */
        int offset = (patientPageRequest.pageNumber() - 1) * patientPageRequest.pageSize();
        int limit = patientPageRequest.pageSize();
        listQuery.setFirstResult(offset);
        listQuery.setMaxResults(limit);

        List<PatientDto> patients = listQuery.getResultList();

        // COUNT QUERY
        String countJpql = commonJpqlBuider.toString().replace("@projection", "COUNT(*)");
        TypedQuery<Long> countQuery = this.em.createQuery(countJpql, Long.class);

        for (String key : whereClause.keySet()) {
            countQuery.setParameter(key,whereClause.get(key));
        }

        Long total = countQuery.getSingleResult();

        // Create Page object
        Pageable pageable = PageRequest.of(patientPageRequest.pageNumber() - 1, patientPageRequest.pageSize());
        Page<PatientDto> pagedPatientDto = new PageImpl<>(patients, pageable, total);
        return pagedPatientDto;


//        String  projectionBased  = "select new com.softvan.dto.PatientDto(p.id,p.firstName,p.lastName,p.email,p.dateOfBirth,p.createdDateTime,p.updatedDateTime,p.status,p.patientInfoEntity.hasAllergy,p.patientInfoEntity.hasBloodPressure)  from PatientEntity as p join p.patientInfoEntity where 1=1";
//        String queryMainPart  = "select p  from PatientEntity as p join p.patientInfoEntity where 1=1";
//
//        // for add another quwry part
//        List<String> jpqlParts = new ArrayList<>();
//
//        //
//        //jpqlParts.add(queryMainPart);
//        jpqlParts.add(projectionBased);
//
//        HashMap<String, Object> params = new HashMap<>();
//
//        if(patientPageRequest.getSearchText()!=null){
//            jpqlParts.add("AND (p.firstName) LIKE ('%' || :firstname || '%')");
//            params.put("firstname",patientPageRequest.getSearchText());
//        }
//        String join = String.join("\n", jpqlParts);
//        System.out.println(join);
//        TypedQuery<PatientDto> query = em.createQuery(join,PatientDto.class);
//          for(String param : params.keySet()){
//              System.out.println(param);
//              query.setParameter(param,params.get(param));
//          }
//        int pageNo = patientPageRequest.getPageNumber() - 1;
//        query.setMaxResults(patientPageRequest.getPageSize());
//         query.setFirstResult(pageNo*patientPageRequest.getPageSize());
//
//        Pageable pageable = PageRequest.of(patientPageRequest.getPageNumber()-1, patientPageRequest.getPageSize());
//        List<PatientDto> patients = query.getResultList();
//        int size = patients.size();
//        for(PatientDto patient : patients ){
//              System.out.println(patient.getFirstName());
//              System.out.println(patient.getHasAllergy());
//        }
//        System.out.println(size);
//        Page page = new PageImpl(patients, pageable,c);
//           return page;

//        List<PatientEntity> resultList = query.getResultList();
//        resultList.forEach(patient-> System.out.println(resultList1));

    }
}
