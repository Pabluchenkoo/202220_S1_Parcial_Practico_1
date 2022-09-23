package co.edu.uniandes.dse.parcialejemplo.repositories;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Long> {



}


