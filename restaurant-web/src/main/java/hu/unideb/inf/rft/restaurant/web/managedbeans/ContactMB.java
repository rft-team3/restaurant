package hu.unideb.inf.rft.restaurant.web.managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name="contactBean")
@RequestScoped
public class ContactMB {
    @EJB
    private UserService userService;

    private UserVo user;

    private MapModel geoModel;
    private String centerGeoMap;

    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        user = userService.getUserByName("admin");
        double locLat = 47.541637;
        double locLng = 21.638548;
        centerGeoMap = locLat + "," + locLng;
        LatLng loc = new LatLng(locLat, locLng);
        geoModel.addOverlay(new Marker(loc, user.getAddress()));
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public UserVo getUser() {
        return user;
    }
}
