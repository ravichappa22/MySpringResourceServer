package com.my.app;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyResourceController {
	 private String message = "Hello world!";
	
	@PreAuthorize("hasRole('ROLE_RS_READ')")
	   @RequestMapping(value = "/", method = RequestMethod.GET)
	   public Map<String, String> home() {
	       return Collections.singletonMap("message", message);
	   }

	   @PreAuthorize("hasRole('ROLE_WRITE')")
	   @RequestMapping(value = "/", method = RequestMethod.POST)
	   public void updateMessage(@RequestBody String message) {
	       this.message = message;
	   }

	  // @PreAuthorize("#oauth2.hasScope('resource-server-read')")
	 //  @PreAuthorize("hasRole('ROLE_READ')") 
	   @PreAuthorize("hasRole('ROLE_reader')") 
	   @RequestMapping(value = "/user", method = RequestMethod.GET, produces="application/json")
	   public Principal user(Principal user) {
	       //return Collections.singletonMap("message", "user is: " + user.toString());
		   return user;
	   }

}
