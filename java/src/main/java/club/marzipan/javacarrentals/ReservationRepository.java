package club.marzipan.javacarrentals;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

}
