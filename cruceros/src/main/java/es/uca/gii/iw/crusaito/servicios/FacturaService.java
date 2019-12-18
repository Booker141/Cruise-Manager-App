package es.uca.gii.iw.crusaito.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.gii.iw.crusaito.clases.Factura;
import es.uca.gii.iw.crusaito.clases.Pago;
import es.uca.gii.iw.crusaito.repositorios.FacturaRepository;

@Service
public class FacturaService {

	private FacturaRepository repo;
	
	@Autowired
	public FacturaService(FacturaRepository repo){
		this.repo=repo;
	}
	
	public Factura findById(int id) {
		return repo.findById(id);
	}
	
	public Factura findByfFechaFactura(LocalDate fFechaFactura) {
		return repo.findByfFechaFactura(fFechaFactura);
	}
	
	public List<Factura> findByPago(Pago pago){
		return repo.findByPago(pago);
	}
	
	public Factura save(Factura factura) {
		return repo.save(factura);
	}
	
	public List<Factura> load(){
		return repo.findAll();
	}

	public void delete (Factura factura) {
        repo.delete(factura);
    }
}
