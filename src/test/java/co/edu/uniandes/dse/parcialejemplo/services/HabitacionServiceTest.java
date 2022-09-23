package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
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
@Import(HabitacionService.class)
public class HabitacionServiceTest {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HabitacionEntity> habitacionList = new ArrayList<>();


    @BeforeEach
    void setUp()
    {
        clearData();
        insertData();

    }

    public void clearData()
    {
        entityManager.getEntityManager().createQuery("delete from HabitacionEntity");

    }
    public void insertData()
    {
        for(int i = 0; i < 3; i++)
        {
            HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
            entityManager.persist(habitacionEntity);
            habitacionList.add(habitacionEntity);
        }
    }

    @Test
    public void testCreateHabitacion() throws IllegalOperationException {
        HabitacionEntity habitacion = factory.manufacturePojo(HabitacionEntity.class);
        HabitacionEntity result = habitacionService.createHabitacion(habitacion);

        assertNotNull(result);
        habitacion.setNumIndentificacion(1);
        habitacion.setNumBanios(6);
        HabitacionEntity entity = entityManager.find(HabitacionEntity.class, result.getId());
        assertEquals(habitacion.getCapacidad(), entity.getCapacidad());
        assertEquals(habitacion.getId(), entity.getId());
        assertEquals(habitacion.getNumIndentificacion(), entity.getNumIndentificacion());
        assertEquals(habitacion.getNumBanios(), entity.getNumBanios());
        assertEquals(habitacion.getNumCamas(), entity.getNumCamas());


    }

    @Test
    public void testCreateHabitacionInvalidNumIdentificacion()
    {
        assertThrows(IllegalOperationException.class, () -> {
            HabitacionEntity habitacion = factory.manufacturePojo(HabitacionEntity.class);
            habitacion.setNumIndentificacion(6);
            habitacion.setNumBanios(1);
            habitacionService.createHabitacion(habitacion);
        });
    }


}
