package co.edu.uniandes.dse.parcialejemplo.services;


import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(HotelService.class)
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HotelEntity> hotelList = new ArrayList<>();

    private List<HabitacionEntity> habitacionList = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        clearData();
        insertData();

    }

    private void clearData()
    {
        entityManager.getEntityManager().createQuery("delete from HabitacionEntity");
        entityManager.getEntityManager().createQuery("delete from HotelEntity");
    }

    private void insertData()
    {
        for(int i = 0; i < 3; i++)
        {
            HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
            entityManager.persist(habitacionEntity);
            habitacionList.add(habitacionEntity);
        }
        HotelEntity hotel = factory.manufacturePojo(HotelEntity.class);
        hotel.setHabitaciones(habitacionList);
        entityManager.persist(hotel);
        hotelList.add(hotel);



    }

    @Test
    void testCreateHotel() throws IllegalOperationException {

        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);

        newEntity.setNombre("Hotel California");
        newEntity.setHabitaciones(habitacionList);
        newEntity.setNumEstrellas(3);
        newEntity.setDireccion("California Blvd");

        HotelEntity result = hotelService.createHotel(newEntity);
        assertNotNull(result);
        HotelEntity entity = entityManager.find(HotelEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getNumEstrellas(), entity.getNumEstrellas());
        assertEquals(newEntity.getDireccion(), entity.getDireccion());
        assertEquals(newEntity.getHabitaciones(), entity.getHabitaciones());



    }

    @Test
    void testCreateHotelWithStoredName(){

        assertThrows(IllegalOperationException.class, () -> {
            HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
            newEntity.setNombre(hotelList.get(0).getNombre());
            hotelService.createHotel(newEntity);
        });

    }

    @Test
    void testCreateHotelWithInvalidEstrellas(){

        assertThrows(IllegalOperationException.class, () -> {
            HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
            newEntity.setNumEstrellas(0);
            hotelService.createHotel(newEntity);
        });

    }





}
