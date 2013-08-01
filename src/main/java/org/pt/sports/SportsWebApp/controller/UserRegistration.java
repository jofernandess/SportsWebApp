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

import org.pt.sports.SportsWebApp.model.User;

// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class UserRegistration {

   @Inject
   private Logger log;

   @Inject
   private EntityManager em;

   @Inject
   private Event<User> userEventSrc;

   private User newUser;

   @Produces
   @Named
   public User getNewUser() {
      return newUser;
   }

   public void register() throws Exception {
      log.info("Registering " + newUser.getName());
      em.persist(newUser);
      userEventSrc.fire(newUser);
      initNewUser();
      emptyFields();
   }
   /**
    * Empty fields from bean
    */
   private void emptyFields() {
	
	   newUser.setEmail(null);
	   newUser.setName(null);
	   newUser.setPhoneNumber(null);
}

@PostConstruct
   public void initNewUser() {
	newUser = new User();
   }
}
