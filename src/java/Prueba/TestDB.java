/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import DAO.Conexion;
import DAO.PlanMovilJpaController;
import DTO.PlanMovil;
import java.util.List;

/**
 *
 * @author jeiso
 */
public class TestDB {
 
    public static void main(String[] args) {
        //1. Establecer la conexion a la DB
        Conexion c = new Conexion();
        PlanMovilJpaController plan = new PlanMovilJpaController(c.getBd());
        List<PlanMovil> planes = plan.findPlanMovilEntities();
        
        
        for (PlanMovil p : planes) {
            System.out.println("Codigo plan: +"+ p.getNombrePlan()+ " "+p.getPrecio());
        }
        
         
        
    }
}
