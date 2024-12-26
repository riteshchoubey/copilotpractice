package com.ritesh.patientclinicals.clinicalsapi.clinicalapi.repos;

import com.ritesh.patientclinicals.clinicalsapi.clinicalapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}