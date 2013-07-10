/**
 * 
 */
package org.pt.sports.SportsWebApp.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.pt.sports.SportsWebApp.model.Sport;

/**
 * @author NB19276
 *
 */
@Stateful
@Model
public class SportRegistration {

	@Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Sport> sportEventSrc;

	   private Sport newSport;

	   @Produces
	   @Named
	   public Sport getNewSport() {
	      return newSport;
	   }

	   public void register() throws Exception {
	      log.info("Registering " + newSport.getName());
	      em.persist(newSport);
	      sportEventSrc.fire(newSport);
	      initNewSport();
	   }

	   @PostConstruct
	   public void initNewSport() {
	      newSport = new Sport();
	   }
	}
