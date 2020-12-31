package ttps.spring.serviciosImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.*;
import ttps.spring.clasesDAOImpJPA.FoodTruckDAOImpJPA;
import ttps.spring.model.*;
import ttps.spring.model.DTO.*;
import ttps.spring.servicios.FoodTruckService;
import ttps.spring.servicios.FoodTruckerService;
import ttps.spring.servicios.FoodTruckService;

@Service
@Transactional
public class FoodTruckServiceImp implements FoodTruckService {

	@Autowired
	private FoodTruckDAO FoodTruckImp;

	@Autowired
	FoodTruckerService foodtruckerImp;
	
	public List<FoodTruckDTO> recuperarTodos(){
        List<FoodTruck> fts = FoodTruckImp.recuperarTodos();
        List<FoodTruckDTO> FoodTrucksFinal = new ArrayList<FoodTruckDTO>();
        for (FoodTruck f:fts) {
        	FoodTrucksFinal.add(new FoodTruckDTO(f));
        }
        return FoodTrucksFinal;
    }
	
	public List<FoodTruckDTO> foodTrucksDeFtrucker(Long id){
		return this.FoodTruckImp.foodTrucksDeFtrucker(id);
	}
	
	
	public FoodTruckDTO recuperarPorId(Long id) {
		FoodTruck ft= FoodTruckImp.recuperarPorId(id);
		
		return new FoodTruckDTO(ft);
	}
	
	public FoodTruck recuperarFoodTruckPorId(Long id) {
		FoodTruck ft= FoodTruckImp.recuperarPorId(id);
		return ft;
	}
	
	public void persistir(FoodTruckDTO ft) {
		FoodTrucker owner = foodtruckerImp.recuperarPorId(ft.getDue�o().getId());
		this.FoodTruckImp.persistir(new FoodTruck(ft,owner));
	}

	
	public Boolean actualizar(Long id,FoodTruckDTO ftruck) {
		FoodTruck foodtruck = this.recuperarFoodTruckPorId(id);
		if (foodtruck == null) {
			return false;
		}
		foodtruck.setDescripcion(ftruck.getDescripcion());
        foodtruck.setFacebook(ftruck.getFacebook());
        foodtruck.setInstagram(ftruck.getInstagram());
        foodtruck.setNombre(ftruck.getNombre());
        foodtruck.setURL(ftruck.getURL());
        foodtruck.setWhatsapp(ftruck.getWhatsapp());
        foodtruck.setTipo_servicio(ftruck.getTipo_servicio());	
        FoodTrucker owner = foodtruckerImp.recuperarPorId(ftruck.getDue�o().getId());
        foodtruck.setDue�o(owner);
		return this.FoodTruckImp.actualizar(foodtruck);
	}

	public void borrar(FoodTruck FoodTruck) {
		this.FoodTruckImp.borrar(FoodTruck);
	}
}
