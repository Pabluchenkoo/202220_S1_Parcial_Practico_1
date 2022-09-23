package co.edu.uniandes.dse.parcialejemplo.entities;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class HotelEntity extends BaseEntity{

    /**
     * Asociacion a habitaciones del hotel
     */
    @PodamExclude
    @OneToMany( mappedBy = "hotel", cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private List<HabitacionEntity> habitaciones = new ArrayList<>();

    /**
     * Nombre del hotel
     */
    private String nombre;

    /**
     * Dirección del hotel
     */
    private String direccion;

    /**
     * Ciudad del hotel
     */
    private Integer numEstrellas;


}
