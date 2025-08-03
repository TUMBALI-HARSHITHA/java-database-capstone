package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.models.Login;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.path}doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private Service sharedService;

    // 3. Check Doctor Availability
    @GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(
            @PathVariable String user,
            @PathVariable String doctorId,
            @PathVariable String date,
            @PathVariable String token) {

        Map<String, Object> response = new HashMap<>();
        if (!sharedService.tokenValidate(token, user)) {
            response.put("message", "Invalid token");
            return ResponseEntity.status(401).body(response);
        }

        List<String> availableTimes = doctorService.getDoctorAvailability(doctorId, date);
        response.put("availableTimes", availableTimes);
        return ResponseEntity.ok(response);
    }

    // 4. Get all doctors
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getDoctor() {
        Map<String, Object> response = new HashMap<>();
        response.put("doctors", doctorService.getAllDoctors());
        return ResponseEntity.ok(response);
    }

    // 5. Register new doctor
    @PostMapping("/register/{token}")
    public ResponseEntity<Map<String, String>> saveDoctor(
            @Valid @RequestBody Doctor doctor,
            @PathVariable String token) {

        Map<String, String> response = new HashMap<>();
        if (!sharedService.tokenValidate(token, "admin")) {
            response.put("message", "Unauthorized access");
            return ResponseEntity.status(401).body(response);
        }

        if (doctorService.doctorExists(doctor.getEmail())) {
            response.put("message", "Doctor already exists");
            return ResponseEntity.status(409).body(response);
        }

        doctorService.saveDoctor(doctor);
        response.put("message", "Doctor registered successfully");
        return ResponseEntity.ok(response);
    }

    // 6. Doctor Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> doctorLogin(
            @Valid @RequestBody Login login) {

        return ResponseEntity.ok(doctorService.doctorLogin(login));
    }

    // 7. Update doctor details
    @PutMapping("/update/{token}")
    public ResponseEntity<Map<String, String>> updateDoctor(
            @Valid @RequestBody Doctor doctor,
            @PathVariable String token) {

        Map<String, String> response = new HashMap<>();
        if (!sharedService.tokenValidate(token, "admin")) {
            response.put("message", "Unauthorized access");
            return ResponseEntity.status(401).body(response);
        }

        if (!doctorService.doctorExistsById(doctor.getId())) {
            response.put("message", "Doctor not found");
            return ResponseEntity.status(404).body(response);
        }

        doctorService.updateDoctor(doctor);
        response.put("message", "Doctor updated successfully");
        return ResponseEntity.ok(response);
    }

    // 8. Delete doctor
    @DeleteMapping("/delete/{doctorId}/{token}")
    public ResponseEntity<Map<String, String>> deleteDoctor(
            @PathVariable String doctorId,
            @PathVariable String token) {

        Map<String, String> response = new HashMap<>();
        if (!sharedService.tokenValidate(token, "admin")) {
            response.put("message", "Unauthorized access");
            return ResponseEntity.status(401).body(response);
        }

        if (!doctorService.doctorExistsById(doctorId)) {
            response.put("message", "Doctor not found");
            return ResponseEntity.status(404).body(response);
        }

        doctorService.deleteDoctor(doctorId);
        response.put("message", "Doctor deleted successfully");
        return ResponseEntity.ok(response);
    }

    // 9. Filter doctors
    @GetMapping("/filter/{name}/{time}/{speciality}")
    public ResponseEntity<Map<String, Object>> filter(
            @PathVariable String name,
            @PathVariable String time,
            @PathVariable String speciality) {

        Map<String, Object> response = new HashMap<>();
        response.put("doctors", sharedService.filterDoctors(name, time, speciality));
        return ResponseEntity.ok(response);
    }
}
