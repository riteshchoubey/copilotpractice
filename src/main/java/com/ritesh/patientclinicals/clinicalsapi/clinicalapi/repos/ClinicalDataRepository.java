package com.ritesh.patientclinicals.clinicalsapi.clinicalapi.repos;

import com.ritesh.patientclinicals.clinicalsapi.clinicalapi.models.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
}