package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HabitacionRepository habitacionRepository;

    /**
     * Crea un hotel
     * Valida que el nombre del hotel no esté repetido y que el número de estrellas este entre 1 y 5.
     */
    @Transactional
    public HotelEntity createHotel(HotelEntity hotelEntity) throws IllegalOperationException {
        log.info("Inicia proceso de creación del hotel");

        if(!hotelRepository.findByNombre(hotelEntity.getNombre()).isEmpty())
        {
            throw new IllegalOperationException("El nombre del hotel ya existe");
        }

        if(hotelEntity.getNumEstrellas() < 1 || hotelEntity.getNumEstrellas() > 5)
        {
            throw new IllegalOperationException("El número de estrellas debe estar entre 1 y 5");
        }

        hotelEntity.setNombre(hotelEntity.getNombre());
        hotelEntity.setNumEstrellas(hotelEntity.getNumEstrellas());

        hotelRepository.save(hotelEntity);

        return hotelRepository.save(hotelEntity);

    }



}
