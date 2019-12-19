package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Factura {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Pago pago;

	    private double fPrecioTotal;
	    private LocalDate fFechaFactura;
	    
		public Factura(Long id, double fPrecioTotal, LocalDate fFechaFactura) {
			this.id = id;
			this.fPrecioTotal = fPrecioTotal;
			this.fFechaFactura = fFechaFactura;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Pago getPago() {
			return pago;
		}

		public void setPago(Pago pago) {
			this.pago = pago;
		}

		public double getfPrecioTotal() {
			return fPrecioTotal;
		}

		public void setfPrecioTotal(double fPrecioTotal) {
			this.fPrecioTotal = fPrecioTotal;
		}

		public LocalDate getfFechaFactura() {
			return fFechaFactura;
		}

		public void setfFechaFactura(LocalDate fFechaFactura) {
			this.fFechaFactura = fFechaFactura;
		}
		

}
