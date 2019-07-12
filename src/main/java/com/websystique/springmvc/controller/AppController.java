package com.websystique.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.websystique.springmvc.model.Solicitudes_reset_pass;
import com.websystique.springmvc.model.User;
//import com.websystique.springmvc.model.Menus;
import com.websystique.springmvc.model.UserProfile;
import com.websystique.springmvc.service.MenusService;
import com.websystique.springmvc.service.Solicitudes_reset_passService;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_vendedores_sap_vwService;
import com.websystique.springmvc.utilities.JsonFunctions;
import com.websystique.springmvc.utilities.SendMailGmail;

@Controller
@RequestMapping("/")
//@SessionAttributes({"roles"})
@SessionAttributes({"roles","menus"})
public class AppController {
	
	private Logger logger = Logger.getLogger(AppController.class);
	//private List<Menus> ListaMenu = new ArrayList<Menus>();
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	Solicitudes_reset_passService srps;
	
	@Autowired
	Catalogo_vendedores_sap_vwService cvss;
	
	@Autowired
	MenusService mss;
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/principal" }, method = RequestMethod.GET)
	public String main(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "/appconfig/principal";
	}
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		try {
		List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        logger.info(getPrincipal() + " - list.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - list. - " + e.getMessage());
		}
        return "/appconfig/userslist";
	}
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		try {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("vendedores",cvss.ListaVendedores());
		model.addAttribute("loggedinuser", getPrincipal());
		logger.info(getPrincipal() + " - newuser GET.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - newuser GET. - " + e.getMessage());
		}
		return "/appconfig/registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {
		try {
		if (result.hasErrors()) {
			return "/appconfig/registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "/appconfig/registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		logger.info(getPrincipal() + " - newuser POST.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - newuser POST. - " + e.getMessage());
		}
		return "/appconfig/registrationsuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		try {
		model.addAttribute("vendedores",cvss.ListaVendedores());
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		logger.info(getPrincipal() + " - edit-user GET.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - edit-user GET. - " + e.getMessage());
		}
		return "/appconfig/registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {
		try {
		if (result.hasErrors()) {
			return "/appconfig/registration";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		logger.info(getPrincipal() + " - edit-user POST.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - edit-user POST. - " + e.getMessage());
		}
		
		return "/appconfig/registrationsuccess";
	}
	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		try {
		userService.deleteUserBySSO(ssoId);
		logger.info(getPrincipal() + " - delete-user.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - delete-user. - " + e.getMessage());
		}
		return "redirect:/list";
	}
	
	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	/*@ModelAttribute("userData")
	public User userData() {
		User u = new User();
		u = userService.findBySSO(getPrincipal());
		u.setPassword("");
		return u;
	}*/
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "/appconfig/accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			//logger.info(AppController.getPrincipal() + " - /appconfig/login.");
			return "/appconfig/login";
	    } else {
	    	//logger.info(AppController.getPrincipal() + " - redirect : /principal");
	    	return "redirect:/principal";  
	    }
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		try {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - logout. - " + e.getMessage());
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	public static String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}

	@RequestMapping(value = { "/cambio_p__" }, method = RequestMethod.GET)
	public String cambioPassword(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", getPrincipal());
		logger.info(getPrincipal() + " - cambio_p__.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - cambio_p__. - " + e.getMessage());
		}
		return "/appconfig/changepassword";
	}
	
	@RequestMapping(value = {"/act_pass"} , method = RequestMethod.POST)
	public @ResponseBody String act_pass(@RequestBody String note, HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		 JsonFunctions jf = new JsonFunctions();
		 JSONObject jsonObject = jf.jsonObject(note);
		 String result = "";
		 try
		 {
			 userService.updatePass(getPrincipal(), (String)jsonObject.get("p"));
			 result = "Contraseña actualizada correctamente.";
			 logger.info(getPrincipal() + " - act_pass.");
		 }
		 catch(Exception e)
		 {
			 logger.error(getPrincipal() + " - act_pass. - " + e.getMessage());
			 result = e.getMessage();
		 }
	     return result;
	}
	
	@RequestMapping(value = { "/edit_data_user" }, method = RequestMethod.GET)
	public String edit_user(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("user",userService.findBySSO(getPrincipal()));
		logger.info(getPrincipal() + " - edit_data_use GETr.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - edit_data_user GET. - " + e.getMessage());
		}
		return "/appconfig/edit_data_usr";
	}
	
	@RequestMapping(value = { "/edit_data_user" }, method = RequestMethod.POST)
	public String edit_data_User(@Valid User user, BindingResult result,
			ModelMap model) {
		try {
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("user",user);
		
		if (result.hasErrors()) {
			model.addAttribute("error", result.getFieldErrors());
			return "/appconfig/edit_data_usr";
		}

		userService.updateUserByUser(user);

		model.addAttribute("success", "Usuario " + user.getFirstName() + " "+ user.getLastName() + " actualizado correctamente.");
		logger.info(getPrincipal() + " - edit_data_user POST.");
		}
		catch(Exception e) {
			logger.error(getPrincipal() + " - edit_data_user POST. - " + e.getMessage());
		}
		return "/appconfig/edit_data_usr";
	}
	
	@RequestMapping(value = { "/sol_cam_passs_" }, method = RequestMethod.GET)
	public String sol_cam_passs_(ModelMap model) {
		try {
		Solicitudes_reset_pass sol = new Solicitudes_reset_pass();
		model.addAttribute("solicitud", sol);
		logger.info(" - sol_cam_passs_ GET.");
		}
		catch(Exception e) {
			logger.error(" - sol_cam_passs_ GET. - " + e.getMessage());
		}
		return "/appconfig/solicitud_reset_pass";
	}
	
	@RequestMapping(value = { "/sol_cam_passs_" }, method = RequestMethod.POST)
	public String sol_cam_passs_(@Valid Solicitudes_reset_pass Solicitud, BindingResult result,
			ModelMap model,HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		try {
			logger.info(Solicitud.getSso_id() + " - sol_cam_passs_ POST. - ");
			Solicitudes_reset_pass sol = new Solicitudes_reset_pass();
			
			model.addAttribute("solicitud", sol);
			
			if (result.hasErrors()) {
				model.addAttribute("result", result.getFieldErrors());
				return "/appconfig/solicitud_reset_pass";
			}
			
			User user = userService.findBySSOnEmail(Solicitud.getSso_id(), Solicitud.getEmail());
			Integer iduser = 0;
			Integer iduserS = 0;
			Solicitudes_reset_pass solVal = null;
			try {
				iduser = user.getId();
				Date date = new Date();
				
				try {
					solVal = srps.findByFechaCadSoo(date, user.getSsoId());
					iduserS = solVal.getId();
				}
				catch(Exception e) {
					iduserS = 0;
				}
				
			}
			catch(Exception e) {
				iduser = 0;
			}
	
			if(iduser > 0)
			{
				if(iduserS <= 0)
				{
					Calendar calendar = Calendar.getInstance();
					Date date = new Date();
					calendar.setTime(date);
					Solicitud.setFecha_sol(calendar.getTime());
					
					calendar.add(Calendar.DAY_OF_YEAR, 1);
					Solicitud.setFecha_caducidad(calendar.getTime());
					Solicitud.setLink("Unlink");
					
					srps.save(Solicitud);
					SendMailGmail email = new SendMailGmail();
					
					StringBuffer url = req.getRequestURL();
					String uri = req.getRequestURI();
					String ctx = req.getContextPath();
					String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
					//System.out.println(base);
					
					email.sendMail(user.getEmail(),email.generaMensage(base,Solicitud.getSso_id(), Solicitud.getEmail(), Solicitud.getFecha_sol(), Solicitud.getFecha_caducidad()),"Recupareación de contraseña SIIBM");
					model.addAttribute("result", "Se ha enviado un correo a la dirección indicada.\n "
							+ "Favor de revisarlo y seguir las indicaciones para el reseteo de contraseña. \n"
							+ "El correo tiene vigencia de un día a partir de la fecha de envío.");
					return "/appconfig/solicitud_reset_pass";
				}
				else
				{
					model.addAttribute("result", "Existe una solicitud activa. Favor de revisar su correo institucional.");
					return "/appconfig/solicitud_reset_pass";
				}
			}
			else
			{
				model.addAttribute("result", "No existe usuario registrado con la información proporcionada. Favor de verificar.");
				return "/appconfig/solicitud_reset_pass";
			}
		}
		catch(Exception e)
		{
			logger.error(Solicitud.getSso_id() + " - sol_cam_passs_ POST. - " + e.getMessage());
			model.addAttribute("result", "Error:"+ e.getMessage());
			return "/appconfig/solicitud_reset_pass";
		}
	} 
	
	@RequestMapping(value = { "/resetp" }, method = RequestMethod.GET)
	public String reset_passs_(ModelMap model,HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException, ParseException {
		String param =  req.getParameter("xyqedt");
		try {
			//System.out.println("paraController: "+param);
			SendMailGmail email = new SendMailGmail();
			Map<String,String> mapP = email.getParams(param);
			User user = new User();
			Solicitudes_reset_pass Sol_reset_pass = null;
			Date fs = null,fc = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				 
				fs = formatter.parse(mapP.get("fecha_sol"));
				fc = formatter.parse(mapP.get("fecha_caducidad"));//new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(mapP.get("fecha_caducidad"));
				//System.out.println("FEcha Sol: "+fs + "- FEcha cad: "+fc);
			} catch (ParseException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
			
			Integer idsol = 0;
			Sol_reset_pass = srps.findByAll(mapP.get("sso"), mapP.get("email"), fs, fc);
			logger.info(mapP.get("sso") + " - resetp GET. - ");
			try {
			idsol = Sol_reset_pass.getId();
			}
			catch(Exception e) {
				idsol = 0;
			}
			
			if(idsol <= 0)
			{
				//model.addAttribute("user", user);
				//model.addAttribute("error", "No se quiera pasar de listo .l.");
				return "/appconfig/accessDenied";
			}
			else
			{
				Date hoy=new Date();
				
				if(hoy.after(fc))
				{
					model.addAttribute("error", "La solicitud ha caducado.");
					return "/appconfig/sol_reset_pass_caducada";
				}
			}
			
			StringBuffer url = req.getRequestURL();
			String uri = req.getRequestURI();
			String ctx = req.getContextPath();
			String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
			model.addAttribute("linkBarcasii", base);
			user = userService.findBySSO(mapP.get("sso"));
			user.setPassword("");
			model.addAttribute("user", user);
			return "/appconfig/paginadereset";
		}
		catch(Exception e)
		{
			logger.error(param + " - resetp GET. - " + e.getMessage());
			return "";
		}
	}

   @RequestMapping(value = { "/resetp" }, method = RequestMethod.POST)
	public String resetp(@Valid User user, BindingResult result,
			ModelMap model){
	   	try {
		 String msg = "algo";
		 if (result.hasErrors()) {
				msg = "Error, valide sus datos. Si no consulte con el administrador del sistema";
			}
		 try
		 {
			 Encoder decoder = Base64.getEncoder();
			 byte[] encodedBytes = decoder.encode(user.getPassword().getBytes());
			 userService.updatePass(user.getSsoId(), new String(encodedBytes));
			 msg = "Contraseña actualizada correctamente.";
		 }
		 catch(Exception e)
		 {
			 msg = e.getMessage();
		 } 
		 logger.info( user.getSsoId() + " - resetp POST. - ");
		 model.addAttribute("success", msg);
	   	}
	   	catch(Exception e)
	   	{
	   		logger.error( user.getSsoId() + " - resetp POST. - " + e.getMessage());
	   	}
	     return "/appconfig/paginadereset";
	}
   
	/*@RequestMapping(value = { "/menus" }, method = RequestMethod.GET)
	public String menus(ModelMap model) {
		 Menus(0);
		System.out.println(cadena);
		model.addAttribute("menus", mss.ListaMenusxpadre(0));
		logger.info(" - menus.");
		
		
		return "/appconfig/menus";
	} */
	
/*    @ModelAttribute("menus")
    public List<Menus> atrMenus()
    {
    	return Menus(0);
    }
    String cadena = "";
    int b = 0;
	public List<Menus> Menus(Integer Padre)
	{
		List<Menus> Lista = mss.ListaMenusxpadre(Padre);
		Menus menu = new Menus();
		if(Lista.size() > 0)
		{
			for(int i = 0; i < Lista.size(); i++)
			{
				
				menu = Lista.get(i); 
				ListaMenu.add(menu);				
				Menus(Lista.get(i).getId());
			}
			
		}
		
		return ListaMenu;
	}*/

}