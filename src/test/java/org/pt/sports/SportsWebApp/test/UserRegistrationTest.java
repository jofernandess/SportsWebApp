package org.pt.sports.SportsWebApp.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.pt.sports.SportsWebApp.controller.UserRegistration;
import org.pt.sports.SportsWebApp.model.User;
import org.pt.sports.SportsWebApp.util.Resources;

@RunWith(Arquillian.class)
public class UserRegistrationTest {
   @Deployment
   public static Archive<?> createTestArchive() {
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(User.class, UserRegistration.class, Resources.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Inject
   UserRegistration userRegistration;

   @Inject
   Logger log;

   @Test
   public void testRegister() throws Exception {
      User newUser = userRegistration.getNewUser();
      newUser.setName("Jane Doe");
      newUser.setEmail("jane@mailinator.com");
      newUser.setPhoneNumber("2125551234");
      newUser.setPassword("123da123A13f");
      userRegistration.register();
      assertNotNull(newUser.getId());
      log.info(newUser.getName() + " was persisted with id " + newUser.getId());
   }
   
}
