package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class HabitacionService {

    @Autowired
    HabitacionRepository habitacionRepository;


    @Transactional
    public HabitacionEntity createHabitacion(HabitacionEntity habitacionEntity) throws IllegalOperationException {
        log.info("Inicia proceso de creación de la habitación");

        if(!(habitacionEntity.getNumIndentificacion() <= habitacionEntity.getNumBanios()))
        {
            throw new IllegalOperationException("El número de identificación debe ser menor o igual al número de baños");
        }

        habitacionEntity.setNumIndentificacion(habitacionEntity.getNumIndentificacion());


        return habitacionRepository.save(habitacionEntity);

    }

}
