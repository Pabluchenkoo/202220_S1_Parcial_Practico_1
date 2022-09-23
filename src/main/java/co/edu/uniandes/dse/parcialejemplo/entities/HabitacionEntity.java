package co.edu.uniandes.dse.parcialejemplo.entities;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class HabitacionEntity extends BaseEntity{

    /**
     * numero de identificacion de la habitacion
     */
    private Integer numIndentificacion;

    /**
     * Numero de camas de la habitacion
     */
    private Integer capacidad;

    /**
     * numero de camas de la habitacion
     */
    private Integer numCamas;

    /**
     * numero de banios de la habitacion
     */

    private Integer numBanios;

    /**
     * asociacion a hotel de la habitacion
     */
    @PodamExclude
    @ManyToOne
    private HotelEntity hotel;



}
