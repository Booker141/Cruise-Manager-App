package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;


import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.AxisTitle;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataLabels;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.PlotOptionsBar;
import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.charts.model.VerticalAlign;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.common.Funciones;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.ServicioUsuarioService;

@Secured("Gerente")
@SuppressWarnings("serial")
@Route(value = "Estadisticas",layout = MainView.class)
public class EstadisticasView extends PrincipalView{
	
	    private ServicioService servicioService;
	    private ServicioUsuarioService susuarioService;

	    @Autowired
	    public EstadisticasView(ServicioService servicioService, ServicioUsuarioService susuarioService) {
	        this.servicioService = servicioService;
	        this.susuarioService = susuarioService;

	        /**
	         * Primera gráfica
	         */
	            Chart chartVolumen = new Chart(ChartType.BAR);

	            Configuration configuracion1 = chartVolumen.getConfiguration();
	            configuracion1.setTitle("Volumen total de facturación");


	            for (ServicioUsuario servicioUsuario: susuarioService.findAll()) {
	                double dTotal = 0;
	                for (Servicio servicio : servicioService.findAll()) {
	                    if (servicio.getsNombre().equals(servicioUsuario.getServicio().getsNombre())) {
	                        dTotal = dTotal + servicio.getsPrecio();
	                    }
	                }
	                configuracion1.addSeries(new ListSeries(servicioUsuario.getServicio().getsNombre(), dTotal));
	            }


	            XAxis xVolumen = new XAxis();
	            xVolumen.setCategories("Servicios");
	            configuracion1.addxAxis(xVolumen);

	            YAxis yVolumen = new YAxis();
	            yVolumen.setMin(0);
	            AxisTitle yTitle = new AxisTitle();
	            yTitle.setText("Volumen total por servicio");
	            yTitle.setAlign(VerticalAlign.HIGH);
	            yVolumen.setTitle(yTitle);
	            configuracion1.addyAxis(yVolumen);

	            Tooltip tooltip = new Tooltip();
	            tooltip.setValueSuffix(" Volumen total por servicio");
	            configuracion1.setTooltip(tooltip);

	            PlotOptionsBar plotOptions = new PlotOptionsBar();
	            DataLabels dataLabels = new DataLabels();
	            dataLabels.setEnabled(true);
	            plotOptions.setDataLabels(dataLabels);
	            configuracion1.setPlotOptions(plotOptions);

	            chartVolumen.addPointLegendItemClickListener(event -> {
		            Funciones.notificacionAcierto("Objeto" + " : " + event.getItemIndex()
		                    + " : " + event.getItem().getName());
		        });
	            
	            /**
	             * Segunda gráfica
	             */
	            
	            Chart chartMas = new Chart(ChartType.PIE);

	            Configuration configuracion2 = chartMas.getConfiguration();
	            configuracion2.setTitle("Servicios más solicitados");


	            for (ServicioUsuario servicioUsuario: susuarioService.findAll()) {
	                int iSolicitado = 0;
	                for (Servicio servicio : servicioService.findAll()) {
	                    if (servicio.getsNombre().equals(servicioUsuario.getServicio().getsNombre())) {
	                        iSolicitado++;
	                    }
	                }
	                configuracion2.addSeries(new ListSeries(servicioUsuario.getServicio().getsNombre(), iSolicitado));
	            }


	            XAxis xMas = new XAxis();
	            xMas.setCategories("Servicios");
	            configuracion2.addxAxis(xMas);

	            YAxis yMas = new YAxis();
	            yMas.setMin(0);
	            AxisTitle yTitleMas = new AxisTitle();
	            yTitleMas.setText("Servicios mas solicitados");
	            yTitleMas.setAlign(VerticalAlign.HIGH);
	            yMas.setTitle(yTitleMas);
	            configuracion2.addyAxis(yMas);

	            Tooltip tooltipMas = new Tooltip();
	            tooltipMas.setValueSuffix(" Servicios mas solicitados");
	            configuracion2.setTooltip(tooltipMas);

	            PlotOptionsBar plotOptionsMas = new PlotOptionsBar();
	            DataLabels dataLabelsMas = new DataLabels();
	            dataLabelsMas.setEnabled(true);
	            plotOptionsMas.setDataLabels(dataLabelsMas);
	            configuracion2.setPlotOptions(plotOptionsMas);
	            
	            chartMas.addPointLegendItemClickListener(event -> {
		            Funciones.notificacionAcierto("Objeto" + " : " + event.getItemIndex()
		                    + " : " + event.getItem().getName());
		        });
	            
	            /**
	             * Tercera gráfica
	             */
	            
	            Chart chartMenos = new Chart(ChartType.PIE);

	            Configuration configuracion3 = chartMenos.getConfiguration();
	            configuracion3.setTitle("Excursiones mas demandadas");


	            for (ServicioUsuario servicioUsuario: susuarioService.findAll()) {
	                int iDemandada = 0;
	                for (Servicio servicio : servicioService.findBysTipo(ServicioTipo.Excursion)) {
	                    if (servicio.getsNombre().equals(servicioUsuario.getServicio().getsNombre())) {
	                        iDemandada++;
	                    }
	                }
	                configuracion3.addSeries(new ListSeries(servicioUsuario.getServicio().getsNombre(), iDemandada));
	            }


	            XAxis xExcursion = new XAxis();
	            xExcursion.setCategories("Excursiones");
	            configuracion3.addxAxis(xExcursion);

	            YAxis yExcursion = new YAxis();
	            yExcursion.setMin(0);
	            AxisTitle yTitleExcursion = new AxisTitle();
	            yTitleExcursion.setText("Excursiones mas demandadas");
	            yTitleExcursion.setAlign(VerticalAlign.HIGH);
	            yExcursion.setTitle(yTitleExcursion);
	            configuracion3.addyAxis(yExcursion);

	            Tooltip tooltipExcursion = new Tooltip();
	            tooltipExcursion.setValueSuffix(" Excursiones mas demandadas");
	            configuracion3.setTooltip(tooltipExcursion);

	            PlotOptionsBar plotOptionsExcursion = new PlotOptionsBar();
	            DataLabels dataLabelsExcursion = new DataLabels();
	            dataLabelsExcursion.setEnabled(true);
	            plotOptionsExcursion.setDataLabels(dataLabelsExcursion);
	            configuracion3.setPlotOptions(plotOptionsExcursion);
	            
	            chartMenos.addPointLegendItemClickListener(event -> {
		            Funciones.notificacionAcierto("Objeto" + " : " + event.getItemIndex()
		                    + " : " + event.getItem().getName());
		        });

	        add(chartVolumen, chartMas, chartMenos);
	      
	        Notification.show("No se ha realizado ninguna reserva de los servicios en este crucero", 3000, Notification.Position.MIDDLE);
	        Label mensaje = new Label("Vuelva mas tarde");
	        mensaje.setVisible(true);
	}
}
