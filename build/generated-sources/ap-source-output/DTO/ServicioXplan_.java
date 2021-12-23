package DTO;

import DTO.PlanMovil;
import DTO.ServicioXplanPK;
import DTO.ServiciosAdicionales;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-22T20:59:59")
@StaticMetamodel(ServicioXplan.class)
public class ServicioXplan_ { 

    public static volatile SingularAttribute<ServicioXplan, ServicioXplanPK> servicioXplanPK;
    public static volatile SingularAttribute<ServicioXplan, Integer> precio;
    public static volatile SingularAttribute<ServicioXplan, ServiciosAdicionales> serviciosAdicionales;
    public static volatile SingularAttribute<ServicioXplan, PlanMovil> planMovil;

}