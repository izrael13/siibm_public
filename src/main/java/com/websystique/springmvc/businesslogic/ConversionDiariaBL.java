package com.websystique.springmvc.businesslogic;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.websystique.springmvc.model.reportes.ConversionDiaria;
import com.websystique.springmvc.model.reportes.EntradaAlmacen;
import com.websystique.springmvc.utilities.DateUtils;

public class ConversionDiariaBL {
    
    private List<ConversionDiaria> conversionDiariaList = new ArrayList<ConversionDiaria>();
    private List<EntradaAlmacen> listEntradaAlmacen = new ArrayList<EntradaAlmacen>();
    private Date fechaIni;
    private Date fechaFin;



    public List<ConversionDiaria> addDataToReport(List<ConversionDiaria> conversionDiariaList, List<EntradaAlmacen> listEntradaAlmacen, String fechaIni, String fechaFin){
        this.conversionDiariaList = conversionDiariaList;
        this.listEntradaAlmacen = listEntradaAlmacen;
        String format = "yyyy/MM/dd HH:mm:ss";
        this.fechaIni = DateUtils.convertStringToDate(fechaIni,format);
        this.fechaFin = DateUtils.convertStringToDate(fechaFin, format);
        processConversionDiaria();
        return conversionDiariaList;
    }
    
    private void processConversionDiaria(){
        int first = 0;
        int laminasPorProcesar = 0;
        for (int i=0; i < conversionDiariaList.size(); i++) {
            if (i < (conversionDiariaList.size() -1)) {
                if (first == 0) {
                    conversionDiariaList.get(i).setPiezasConversion(conversionDiariaList.get(i).getCorrugadas());
                    laminasPorProcesar = conversionDiariaList.get(i).getCorrugadas();
                    first = 1;
                }
               if (conversionDiariaList.get(i).getPedido().equalsIgnoreCase(conversionDiariaList.get(i + 1).getPedido())) {
                   conversionDiariaList.get(i).setPiezasConversion(laminasPorProcesar);
                   laminasPorProcesar = laminasPorProcesar - (((conversionDiariaList.get(i).getPiezasContadas() == null)? 0:conversionDiariaList.get(i).getPiezasContadas()) +
                                                               ((conversionDiariaList.get(i).getLaminasMalas() == null)? 0:conversionDiariaList.get(i).getLaminasMalas())); 
                   conversionDiariaList.get(i).setLaminasPorProcesar(laminasPorProcesar);
                   
               } else {
                   first = 0;
                   conversionDiariaList.get(i).setPiezasConversion(laminasPorProcesar);
                   laminasPorProcesar = laminasPorProcesar - (((conversionDiariaList.get(i).getPiezasContadas() == null)? 0:conversionDiariaList.get(i).getPiezasContadas()) +
                           ((conversionDiariaList.get(i).getLaminasMalas() == null)? 0:conversionDiariaList.get(i).getLaminasMalas()));
                   conversionDiariaList.get(i).setLaminasPorProcesar(laminasPorProcesar);
                   conversionDiariaList.get(i).setPiezasEntregadas(getEntradaAlmacen(conversionDiariaList.get(i).getPedido()));
               }
            } else {
                laminasPorProcesar = conversionDiariaList.get(i).getCorrugadas();
                laminasPorProcesar = laminasPorProcesar - (((conversionDiariaList.get(i).getPiezasContadas() == null)? 0:conversionDiariaList.get(i).getPiezasContadas()) +
                        ((conversionDiariaList.get(i).getLaminasMalas() == null)? 0:conversionDiariaList.get(i).getLaminasMalas()));
                conversionDiariaList.get(i).setLaminasPorProcesar(laminasPorProcesar);
                conversionDiariaList.get(i).setPiezasEntregadas(getEntradaAlmacen(conversionDiariaList.get(i).getPedido()));
                conversionDiariaList.get(i).setPiezasConversion(conversionDiariaList.get(i).getCorrugadas());
            }
        }  
    }
    
    private int getEntradaAlmacen(String pedido) {
        int piezasEntregadas = 0;
        for (EntradaAlmacen entradaAlmacen : listEntradaAlmacen) {
          
           if (pedido.equalsIgnoreCase(entradaAlmacen.getNumeroPedido())){
               Date dateAlmacen = getDateAlmacen(entradaAlmacen.getFechaEntAlm(), entradaAlmacen.getHoraEntrega());
               if ((dateAlmacen.after(fechaIni) || dateAlmacen.equals(fechaIni)) && (dateAlmacen.before(fechaFin) || dateAlmacen.equals(fechaFin))) {
               //if ((DateUtils.isSameDay(fechaIni,dateAlmacen) && fechaIni.before(dateAlmacen)) || fechaIni.equals(dateAlmacen)) {
                piezasEntregadas = piezasEntregadas + entradaAlmacen.getPiezasEntregadas();
               }
           }
           if (entradaAlmacen.getNumeroPedido().compareTo(pedido) > 0) {
               break;
           } 
        }
        return piezasEntregadas;
    }
    
    private Date getDateAlmacen(String fecha, String hora) {
        return DateUtils.getDateCalendar(fecha, hora);   
    }
    
    public String getListaDePedidos(List<ConversionDiaria> conversionDiaria) {
        String pedidos = "";
        for (int i=0; i < conversionDiaria.size(); i++) {
            if (!pedidos.contains(conversionDiaria.get(i).getPedido())){
                pedidos = pedidos + conversionDiaria.get(i).getPedido();
                if (i< (conversionDiaria.size() - 1)) {
                    pedidos = pedidos + ",";
                }
            }
            
        }
        return pedidos;
    }
    

}
