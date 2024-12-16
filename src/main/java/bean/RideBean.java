package bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import businessLogic.BLFacade;
import domain.Driver;
import domain.User;


public class RideBean implements Serializable {
	private String departCity;
	private String arrivalCity;
	private Date rideDate;
	private int numSeats;
	private float price;
	private Driver driver;


	private BLFacade facadeBL;
	private static final long serialVersionUID = 1L;

	public RideBean() {
		
		this.facadeBL = FacadeBean.getBusinessLogic();
		this.numSeats = 1;
	}

	public String getDepartCity() {
		return departCity;
	}

	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Date getRideDate() {
		return rideDate;
	}

	public void setRideDate(Date rideDate) {
		this.rideDate = rideDate;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public BLFacade getFacadeBL() {
		return facadeBL;
	}

	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}

	public boolean createRide() {
	    try {
	    	FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
	    	User currentUser = (User) session.getAttribute("currentUser");
	    	String driverEmail = currentUser.getEmail();
	        facadeBL.createRide(departCity, arrivalCity, rideDate, numSeats, price, driverEmail);
	        System.out.println("Ride created successfully");
	        return true; // Indica éxito
	    } catch (Exception e) {
	        System.err.println("Error creating ride: " + e.getMessage());
	        return false; // Indica error
	    }
	}


	public String close() {
		return "Main";
	}

}
