package com.maverik.realestate.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextServletListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	// Method should be implemented just if an action should happens once
	// the context is destroyed
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
	List<String> states = new ArrayList<String>();
	states.add("AL");
	states.add("AK");
	states.add("AZ");
	states.add("AR");
	states.add("CA");
	states.add("CO");
	states.add("CT");
	states.add("DE");
	states.add("FL");
	states.add("GA");
	states.add("HI");
	states.add("ID");
	states.add("IL");
	states.add("IN");
	states.add("IA");
	states.add("KS");
	states.add("KY");
	states.add("LA");
	states.add("ME");
	states.add("MD");
	states.add("MA");
	states.add("MI");
	states.add("MN");
	states.add("MS");
	states.add("MO");
	states.add("MT");
	states.add("NE");
	states.add("NV");
	states.add("NH");
	states.add("NJ");
	states.add("NM");
	states.add("NY");
	states.add("NC");
	states.add("ND");
	states.add("OH");
	states.add("OK");
	states.add("OR");
	states.add("PA");
	states.add("RI");
	states.add("SC");
	states.add("SD");
	states.add("TN");
	states.add("TX");
	states.add("UT");
	states.add("VT");
	states.add("VA");
	states.add("WA");
	states.add("WV");
	states.add("WI");
	states.add("WY");

	// Move this logic to retrieve this info from db later
	List<String> projectTypes = new ArrayList<String>();
	projectTypes.add("New Store");
	projectTypes.add("Rebuild");
	projectTypes.add("Powermove");
	projectTypes.add("Flash Remodel");
	projectTypes.add("Cinnabon");
	projectTypes.add("BTO");
	projectTypes.add("Other");

	List<String> projectPhases = new ArrayList<String>();
	projectPhases.add("Land Use Permitting");
	projectPhases.add("Pre-Construction Permitting");
	projectPhases.add("Project Management");
	projectPhases.add("Close-Out");

	sce.getServletContext().setAttribute("usStatesLst", states);
	sce.getServletContext().setAttribute("projectTypes", projectTypes);
	sce.getServletContext().setAttribute("projectPhases", projectPhases);
    }

}
