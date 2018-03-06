package kkbots.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MainService {

	@SuppressWarnings("serial")
	private List<String> categories = new ArrayList<String>() {{
		add("All");
		add("New robots");
		add("New buildings");
		add("New items");
		add("Upgrades");
		add("Free topics");
	}};

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
