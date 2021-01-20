package com.graba.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.graba.entity.Role;
import com.graba.entity.User;
import com.graba.repository.RoleRepository;
import com.graba.service.GrabaUserDetailsService;
import com.graba.service.UserService;

@Controller
@RequestMapping("hazelcast")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping(value="/user")
	public Optional<User> getGreeting(){
		return userService.getUserById(1l);
	}
	
	@GetMapping("/users")
	public String listAll(Model model) {
		return listByPage(model, 1,  "id", "asc", null);
	}
	
	@GetMapping("/products/page/{pageNum}")
	public String listByPage(Model model, @PathVariable(name = "pageNum") Integer pageNum, 
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		
		Page<User> page = userService.listAll(pageNum, sortField, sortDir, keyword);
		List<User> userList = page.getContent();
		
			
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("userList", userList);
		
		long startCount = (pageNum - 1) * UserService.USERS_PER_PAGE + 1;
		model.addAttribute("startCount", startCount);
		
		long endCount = startCount = UserService.USERS_PER_PAGE - 1;
		if(endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		model.addAttribute("endCount", endCount);
		
		if(page.getTotalPages() > 1) {
			model.addAttribute("pageTitle", "Users (page " + pageNum + ")");
		} else {
			model.addAttribute("pageTitle", "Users");
		}
		
		return "users";
	}

	@GetMapping(value="/users/new")
	public ModelAndView mewUser(){
		User user = new User();
		ModelAndView mav = new ModelAndView("userForm");
		mav.addObject("user",user);
		
		List<Role> roles = (List<Role>)roleRepository.findAll();
		
		mav.addObject("allRoles", roles);
		mav.addObject("pageTitle", "Create New User");
		
		return mav;
	}
	
	@GetMapping("/userHome")
	public String viewUserHome(Model model, @AuthenticationPrincipal GrabaUserDetailsService user) {
		model.addAttribute("user", user);
		return "userHome";
	}
	
	
	@PostMapping("/users/save")
	public RedirectView saveUser(User user, RedirectAttributes redirectAttributes, 
									@RequestParam("image") MultipartFile multipartFile) throws IOException{
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		}
		return null;
	}
	
	@GetMapping(value="/user/{userId}")
	public Optional<User> getUserById(@PathVariable("userId") Long userId){
		return userService.getUserById(userId);
	}
	
	@DeleteMapping(value="/user/{userId}")
	public void deleteUser(@PathVariable("userId")Long UserId){
		userService.deleteUser(UserId);
	}
	
	@PutMapping(value="/user")
	public User updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
}