/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DAO.Conexion;
import DAO.PlanMovilJpaController;
import DAO.ServicioXplanJpaController;
import DAO.ServiciosAdicionalesJpaController;
import DAO.SmartphoneJpaController;
import DTO.PlanMovil;
import DTO.ServicioXplan;
import DTO.ServicioXplanPK;
import DTO.ServiciosAdicionales;
import DTO.Smartphone;
import java.util.LinkedList;
import java.util.List;

/**
 * Responde a una PACADE
 *
 * @author jeiso
 */
public class Telefonia {

    private Conexion c = new Conexion();

    public Telefonia() {

    }

    public String getListadoPlanesDropDown() {

        String dropdown = "";
        PlanMovilJpaController plan = new PlanMovilJpaController(c.getBd());

        List<PlanMovil> planes = plan.findPlanMovilEntities();
        for (PlanMovil p : planes) {
            dropdown += "<option value=" + p.getCodigoPlan() + ">" + p.getNombrePlan() + "</option>\n";
        }
        return dropdown;
    }

    public List<ServicioXplan> serviciosDelPlan(int idPlan) {

        ServicioXplanJpaController ser = new ServicioXplanJpaController(c.getBd());
        List<ServicioXplan> servicios = ser.findServicioXplanEntities();
        LinkedList<ServicioXplan> res = new LinkedList<>();
        for (ServicioXplan servicio : servicios) {
            if (servicio.getPlanMovil().getCodigoPlan() == idPlan) {
                res.add(servicio);
            }
        }
        return res;

    }

    public List<Smartphone> getSmarphones() {

        SmartphoneJpaController s = new SmartphoneJpaController(c.getBd());

        return s.findSmartphoneEntities();
    }

    public String[] getPlanInfo(int idPlan) {
        PlanMovilJpaController p = new PlanMovilJpaController(c.getBd());
        PlanMovil plan = p.findPlanMovil(idPlan);
        String ifo[] = {String.valueOf(plan.getCodigoPlan()), plan.getNombrePlan(), String.valueOf(plan.getPrecio())};
        return ifo;
    }

    public String checkBoxSmarphones() {

        List<Smartphone> lista = getSmarphones();
        String checkBox = "";
        for (Smartphone smartphone : lista) {
            checkBox += "<label><input type='checkbox' name='" + "selectEquipos" + "' value='" + smartphone.getId()
                    + "-" + smartphone.getNombre() + "-" + smartphone.getPrecio()
                    + "'>" + smartphone.getNombre() + "</label>\n";
        }
        return checkBox;
    }

    public int getPrecioServicioXPlan(int idPlan, int idServ) {
        ServicioXplanJpaController s = new ServicioXplanJpaController(c.getBd());
        ServicioXplanPK id = new ServicioXplanPK(idPlan, idServ);
        return s.findServicioXplan(id).getPrecio();

    }

    public int calcularCotizacion(List<Integer> precios) {
        int p = 0;
        for (int precio : precios) {
            p += precio;
        }
        return p;
    }

    public String getNombreServicio(int idServ) {
        ServiciosAdicionalesJpaController s = new ServiciosAdicionalesJpaController(c.getBd());
        return s.findServiciosAdicionales(idServ).getNombreServicio();
    }
    public Smartphone getEquipoPorId(int idEquipo){
        SmartphoneJpaController s = new SmartphoneJpaController(c.getBd());
        return s.findSmartphone(idEquipo);
    }

    public String crearTablaCotizacion(String plan, String servicios, String equipos[]) {

        String table = "<table style=\"text-aling:center;\">\n"
                + "<tr>\n"
                + " <th colspan=3> Cotizacion</th>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "  <th class='item'> item </th>\n"
                + "  <th class='descripcion'> descripcion </th>\n"
                + "  <th class='valor'> valor </th>\n"
                + "</tr>";

        LinkedList<Integer> precios = new LinkedList<>();
        String planinfo[] = plan.split("-");
        int planId = Integer.parseInt(planinfo[0]);
        String planNombre = planinfo[1];
        int planPrecio = Integer.parseInt(planinfo[2]);
        precios.add(planPrecio);
        String urls[]=new String[equipos.length];
        table += "<tr>\n"
                + "<td>\n" + "1"
                + "</td>\n"
                + "<td>\n" + planNombre +"</td>\n"
                + "<td>\n" + planPrecio +"</td>\n"
                + "</tr>";

        
        int i=1;
        if (!servicios.isEmpty()) {
            String serv[] = servicios.split("-");
            for (int k = 0; k < serv.length; k++) {
                int idServ = Integer.parseInt(serv[k]);
                int precioS = getPrecioServicioXPlan(planId, idServ);
                i++;
                table += "<tr>\n"
                + "<td>\n" + i
                + "</td>\n"
                + "<td>\n" + getNombreServicio(idServ) +"</td>\n"
                + "<td>\n" + precioS +"</td>\n"
                + "</tr>";
                
                precios.add(precioS);
            }

        }
        i++;
        if(equipos.length!=0){
            for (int j = 0; j < equipos.length; j++) {
                String eq = equipos[j];
                String eqInfo[]=eq.split("-");
                int precioEq = Integer.parseInt(eqInfo[2]);
                table += "<tr>\n"
                + "<td>\n" + (i++)
                + "</td>\n"
                + "<td>\n" + eqInfo[1] +"</td>\n"
                + "<td>\n" + precioEq +"</td>\n"
                + "</tr>";
                
                urls[j]=getEquipoPorId(Integer.parseInt(eqInfo[0])).getUrlImagen();
                precios.add(precioEq);
            }
        }
        table += "<tr>\n"
                + "<th colspan=2>\n" + "Total:"
                + "</th>\n"
                + "<td>\n" + calcularCotizacion(precios) +"</td>\n"
                + "</tr>\n" 
                +"</table>";
        table+="<div class='imagenes'>\n";
        for (String url : urls) {
            table+="<img src='"+url+"' alt='Imagen de un equipo celular'>\n";
        }
        table+="</div>";
        
        return table;
    }

    public List<PlanMovil> getPlanes(){
        PlanMovilJpaController p = new PlanMovilJpaController(c.getBd());
        
        return p.findPlanMovilEntities();
    } 
    public ServiciosAdicionales getServicio(int idServ){
        ServiciosAdicionalesJpaController p = new ServiciosAdicionalesJpaController(c.getBd());
        return p.findServiciosAdicionales(idServ);
    }
    public String getTablaInfoPlanes(){
        
        String tabla = " <table class='table-bordered'>\n";
        String servicioT = "<tr class='trPrincipal'>\n"
                +"<th>Id del Plan</th>"
                +"<th>Nombre</th>"
                + "<th>Precio</th>"
                +"</tr>";
        
        for (PlanMovil plan : getPlanes()) {
            int idPlan = plan.getCodigoPlan();
            String nombrePlan = plan.getNombrePlan();
            int precioPlan = plan.getPrecio();
            
            
            List<ServicioXplan> servicios = serviciosDelPlan(idPlan);
            String rowSp= String.valueOf(servicios.size()+2);
            servicioT+= "<tr>"
            +"<td rowspan='"+ rowSp +"'>"+String.valueOf(idPlan)+"</td>"
            +"<td>"+nombrePlan+"</td>"
            +"<td>"+String.valueOf(precioPlan)+"</td>"
            + "</tr>";
            
            servicioT+= "<tr>\n" +
"            <td colspan=\"2\" class=\"tdadicionales\">Adicionales</td>\n" +
"        </tr>";
        
            
            for (ServicioXplan servicio : servicios) {
                int servId = servicio.getServicioXplanPK().getCodigoServicio();
                ServiciosAdicionales servA = getServicio(servId);
                String nombA = servA.getNombreServicio();
                servicioT+= "<tr>\n" +
"                   <td>"+nombA+"</td>\n" +
"                   <td>"+String.valueOf(servicio.getPrecio())+"</td>\n" +
"                   </tr>";             
                
            }
        }
        tabla+=servicioT;
        return tabla +"</table>";
    }
    
    public String getListadoPlanes() {
        String tabla = "\n<table border='1'>";
        tabla += "\n<tr>"
                + "\n<td> Id Plan</td>"
                + "\n<td> Nombre Plan</td>"
                + "\n<td> Precio Plan</td>"
                + "\n</tr>";

        PlanMovilJpaController plan = new PlanMovilJpaController(c.getBd());
        List<PlanMovil> planes = plan.findPlanMovilEntities();
        for (PlanMovil p : planes) {
            tabla += "\n<tr>"
                    + "\n<td>" + p.getCodigoPlan() + "</td>"
                    + "\n<td>" + p.getNombrePlan() + "</td>"
                    + "\n<td>" + p.getPrecio() + "</td>"
                    + "\n</tr>";
        }

        return tabla + "</table>";

    }
}
