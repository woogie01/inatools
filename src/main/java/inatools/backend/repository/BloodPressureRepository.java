package inatools.backend.repository;

import inatools.backend.domain.BloodPressure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {

}
