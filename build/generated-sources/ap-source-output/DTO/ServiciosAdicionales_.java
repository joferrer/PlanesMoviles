package DTO;

import DTO.ServicioXplan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-23T00:41:53")
@StaticMetamodel(ServiciosAdicionales.class)
public class ServiciosAdicionales_ { 

    public static volatile SingularAttribute<ServiciosAdicionales, String> nombreServicio;
    public static volatile SingularAttribute<ServiciosAdicionales, Integer> codigoServicio;
    public static volatile CollectionAttribute<ServiciosAdicionales, ServicioXplan> servicioXplanCollection;

}