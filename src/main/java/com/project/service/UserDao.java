package com.project.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.domain.Roles;
import com.project.domain.UserRoles;
import com.project.domain.Users;

public interface UserDao extends UserDetailsService {

	UserDetails loadUserByUsername(String UserName);

	Users saveUserRegistration(Users userVO);

	Set<UserRoles> saveUserRegistrationRoles(Set<UserRoles> userRoleVOs,String userOid);
	
	Set<Roles> getAllRoles();
	
	Set<Users> getAllUsers();

	
}
