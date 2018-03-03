function activeMenuItem(mIN, sMIN, cat){

//get html name
	var path = window.location.pathname;
	var page = path.split("/").pop();
	console.log( page );

//get html specific vars
	
	var menuItemNo = mIN;
	var subMenuItemNo = sMIN;
	var category = cat;


	
//filter main		
	var sections = document.getElementsByClassName("sectionArticle");				
	for (var i = 0; i < sections.length; i++){
		var sectionCategory = sections[i].getElementsByClassName("sectionArticleCategory");		

		if(sectionCategory[0].innerHTML != category && subMenuItemNo != 0){
			sections[i].style.display = "none";
		} else {
			sections[i].style.display = "block";
		}
		
	}
	
//filter sideNav				
	var sideNavSubLists = document.getElementsByClassName("sideNavSubList");
	for (var i = 0; i < sideNavSubLists.length; i++){
		if (i != menuItemNo) {
			sideNavSubLists[i--].remove();
		} else {
			sideNavSubLists[i].parentNode.style.fontWeight = "900";	
			sideNavSubLists[i].style.fontWeight = "400";
			for (var j = 0; j < 6; j++)
				if (j == subMenuItemNo)			
					sideNavSubLists[i].children[j].style.fontWeight = "900";
				else
					sideNavSubLists[i].children[j].style.fontWeight = "400";
	//create articlesSubSubList
			for (var j = 0; j < 6; j++){
				var subSubList = sideNavSubLists[i].children[j].children[1];
		//clear
				while (subSubList.firstChild)
					subSubList.removeChild(subSubList.firstChild);
		//fill		
				if (j == subMenuItemNo){
					for (var s = 0; s < sections.length; s++){
						var li = document.createElement("li");
						if (sections[s].style.display == "block"){
							var title = sections[s].getElementsByClassName("sectionArticleTitle")[0];
							var anchor = title.children[0];
							var text = anchor.innerHTML;
							var href = anchor.getAttribute("href");
							var a = document.createElement('a');
							a.appendChild(document.createTextNode(text));
							a.title = text;
							a.href = href;
							li.appendChild(a);
							subSubList.appendChild(li);
						}
					}
				}
			}
		}
	}		
	
//color						
	var item = document.getElementById("mainMenu").children[menuItemNo];
	var subMenus = document.getElementsByClassName("subMenu");
	if (menuItemNo%2 == 0) {
		item.style.backgroundColor = "hsl(250, 100%, 30%)";
		for (var j = 0; j < 6; j++)
			if (j == subMenuItemNo) {			
				item = subMenus[menuItemNo].children[j];
				item.style.backgroundColor = "hsl(250, 100%, 30%)";
			} else {
				item = subMenus[menuItemNo].children[j];
				item.style.backgroundColor = "hsl(0, 0%, 15%)";
			}
	} else {
		item.style.backgroundColor = "hsl(360, 100%, 30%)";
		for (var j = 0; j < 6; j++)
			if (j == subMenuItemNo) {	
				item = subMenus[menuItemNo].children[subMenuItemNo];
				item.style.backgroundColor = "hsl(360, 100%, 30%)";
			} else {
				item = subMenus[menuItemNo].children[j];
				item.style.backgroundColor = "hsl(0, 0%, 15%)";
			}			
	}
}