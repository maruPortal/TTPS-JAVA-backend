package ttps.spring.serviciosImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.EventoDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.Organizador;
import ttps.spring.model.DTO.EventoDTO;
import ttps.spring.model.DTO.FoodTruckDTO;
import ttps.spring.servicios.EventoService;
import ttps.spring.servicios.OrganizadorService;

@Service
@Transactional
public class EventoServiceImp implements EventoService {
	
	@Autowired
	private EventoDAO eventoImp;
	
	@Autowired
	OrganizadorService organizadorImp;
	

	@Override
	public List<EventoDTO> recuperarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento recuperarPorId(Long id) {
		Evento e= eventoImp.recuperarPorId(id);
		
		return e;
	}

	@Override
	public void persistir(EventoDTO e) {
		Organizador org = organizadorImp.recuperarPorId(e.getOrganizador().getId());
		this.eventoImp.persistir(new Evento(e,org));		
	}

	@Override
	public void actualizar(Evento f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventoDTO recuperarPorIdDTO(Long id) {
		Evento e= eventoImp.recuperarPorId(id);
		return new EventoDTO(e);
	}

}
