package kkbots.jpa.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kkbots.jpa.order.Order;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	private String role;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String address;
	
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Order> orders;
	
	

	public User() {
		
	}
	
	public User(Long id) {
		this.id = id;
	}
	
	
	public User(Long id, String role, String login, String password, String name, String surname, String email, String phone, String address) {
		super();
		this.id = id;
		this.role = role;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	public User(Long id, String role, String login, String password, String name, String surname, String email) {
		super();
		this.id = id;
		this.role = role;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}


	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	
}
