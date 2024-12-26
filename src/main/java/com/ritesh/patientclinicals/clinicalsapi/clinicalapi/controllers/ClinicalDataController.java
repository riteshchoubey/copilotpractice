package com.ritesh.patientclinicals.clinicalsapi.clinicalapi.controllers;

import com.ritesh.patientclinicals.clinicalsapi.clinicalapi.dto.ClinicalDataRequest;
import com.ritesh.patientclinicals.clinicalsapi.clinicalapi.models.ClinicalData;
import com.ritesh.patientclinicals.clinicalsapi.clinicalapi.models.Patient;
import com.ritesh.patientclinicals.clinicalsapi.clinicalapi.repos.ClinicalDataRepository;
import com.ritesh.patientclinicals.clinicalsapi.clinicalapi.repos.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clinicaldata")
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalData> getClinicalDataById(@PathVariable Long id) {
        Optional<ClinicalData> clinicalData = clinicalDataRepository.findById(id);
        if (clinicalData.isPresent()) {
            return ResponseEntity.ok(clinicalData.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ClinicalData createClinicalData(@RequestBody ClinicalData clinicalData) {
        return clinicalDataRepository.save(clinicalData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalData> updateClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalDataDetails) {
        Optional<ClinicalData> clinicalData = clinicalDataRepository.findById(id);
        if (clinicalData.isPresent()) {
            ClinicalData existingClinicalData = clinicalData.get();
            existingClinicalData.setComponentName(clinicalDataDetails.getComponentName());
            existingClinicalData.setComponentValue(clinicalDataDetails.getComponentValue());
            existingClinicalData.setMeasuredDateTime(clinicalDataDetails.getMeasuredDateTime());
            ClinicalData updatedClinicalData = clinicalDataRepository.save(existingClinicalData);
            return ResponseEntity.ok(updatedClinicalData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalData(@PathVariable Long id) {
        Optional<ClinicalData> clinicalData = clinicalDataRepository.findById(id);
        if (clinicalData.isPresent()) {
            clinicalDataRepository.delete(clinicalData.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //method that recieves the patient id, clinical data and saves it to database
    @PostMapping("/clinicals")
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(request.getComponentName());
        clinicalData.setComponentValue(request.getComponentValue());
        Patient patient = patientRepository.findById(request.getPatientId()).get();
        clinicalData.setPatient(patient);
        return clinicalDataRepository.save(clinicalData);
    }
    
}