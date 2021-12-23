package DTO;

import DTO.ServicioXplan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-23T00:41:53")
@StaticMetamodel(PlanMovil.class)
public class PlanMovil_ { 

    public static volatile SingularAttribute<PlanMovil, Integer> precio;
    public static volatile CollectionAttribute<PlanMovil, ServicioXplan> servicioXplanCollection;
    public static volatile SingularAttribute<PlanMovil, String> nombrePlan;
    public static volatile SingularAttribute<PlanMovil, Integer> codigoPlan;

}