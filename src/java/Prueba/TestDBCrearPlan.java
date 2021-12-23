/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import DAO.Conexion;
import DAO.PlanMovilJpaController;
import DTO.PlanMovil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeiso
 */
public class TestDBCrearPlan {
    public static void main(String[] args) {
        Conexion c = new Conexion();
        PlanMovilJpaController plan = new PlanMovilJpaController(c.getBd());
        
        PlanMovil nuevo = new PlanMovil(4, "NuevoPlan", 150000);
        
        try {
            plan.create(nuevo);
            System.out.println("Nuevo plan insertado");
        } catch (Exception ex) {
            System.err.println("No se pudo insertar el nuevo plan en DB");
        }
    }
}
