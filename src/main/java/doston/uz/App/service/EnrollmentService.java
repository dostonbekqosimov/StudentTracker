package doston.uz.App.service;

import doston.uz.App.model.Enrollment;
import doston.uz.App.repository.EnrollmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public void saveEnrollment(Enrollment enrollment) {
        enrollment.setActive(true);
        enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {

        return enrollmentRepository.findAll();
    }

    public Enrollment findEnrollmentById(Integer id) {

        return enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEnrollmentById(Integer id) {

        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);

    }

    @Transactional
    public void markAsAddedToGroup(Integer enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with id: " + enrollmentId));

        enrollment.setActive(false);
        enrollmentRepository.save(enrollment);
    }
}
