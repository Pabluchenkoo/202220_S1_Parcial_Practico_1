package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class HotelHabitacionService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    public HabitacionEntity addHabitacion(Long hotelId, Long habitacionId) throws EntityNotFoundException {
        log.info("Inicia proceso de agregarle una habitación al hotel con id = {0}", hotelId);

        Optional<HotelEntity> hotel = hotelRepository.findById(hotelId);

        if(hotel.isEmpty()){
            throw new EntityNotFoundException("El hotel no existe");
        }

        Optional<HabitacionEntity> habitacion = habitacionRepository.findById(habitacionId);

        if(habitacion.isEmpty()){
            throw new EntityNotFoundException("La habitación no existe");
        }

        habitacion.get().setHotel(hotel.get());
        return habitacion.get();
    }
}
