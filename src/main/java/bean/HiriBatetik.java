package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import businessLogic.BLFacade;
import domain.Driver;
import domain.Ride;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean(name = "HiriBatetik")
@SessionScoped
public class HiriBatetik {

	private String from;
	private String to;
	private Date date = new Date();
	private List<String> originCities;
	private List<String> destinationCities = new ArrayList<>();
	private List<Date> datesRides = new ArrayList<>();
	private List<Ride> foundRides = new ArrayList<>();
	

	public HiriBatetik() {
		this.originCities = new ArrayList<>();
		BLFacade facade = FacadeBean.getBusinessLogic();
		originCities = facade.getDepartCities();
	}

	public void updateDestinationCities(AjaxBehaviorEvent event) {
		if (from == null || from.isEmpty()) {
			destinationCities.clear();
		} else {
			BLFacade facade = FacadeBean.getBusinessLogic();
			destinationCities = facade.getDestinationCities(from);
		}
	}



	public String findRides() {
	    BLFacade facade = FacadeBean.getBusinessLogic();
	    List<Ride> rides = facade.getRides2(from);
	    foundRides = new ArrayList<>(rides);

	    // Verificar si hay datos en foundRides
	    System.out.println("Found rides: " + foundRides.size());

	    return "ridesResults?faces-redirect=true";
	}

	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getOriginCities() {
		return originCities;
	}

	public List<String> getDestinationCities() {
		return destinationCities;
	}

	public List<Date> getDatesRides() {
		return datesRides;
	}

	public List<Ride> getFoundRides() {
		return foundRides;
	}

	public String close() {
		return "Main";
	}
}

