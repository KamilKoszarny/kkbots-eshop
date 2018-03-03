<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body onload="activeMenuItem(0, 0, '');">
		<header id="header">
	     	<h1 id="logo">
				<img id="logoImg" src="/resources/img/logo.jpg"/>
				KK<span style="color: hsl(360, 100%, 30%);">Bots</span>
			</h1>
      		<div style="clear:both;"> </div>
			<div id="login">
				<div id="loginTextFields">
					<c:if test="${user == null}">
						<form method="post" action="uservalidation">
						<div class="loginText">Login:<input class="loginTextField" type="text" name="login" title="Login"/></div>
						<div style="clear:both;"> </div>
						<div class="loginText">Password:<input class="loginTextField" type="password" name="password" title="Password"/></div>
					</c:if>
					<c:if test="${user != null}">
						<form method="get" action="logout">
						<div class="loginText">Logged as: <br/>${user.name} ${user.surname}</div>
					</c:if>
				</div>
				<div id="logButtons">
					<c:if test="${user == null}">
						<input class="logButton" id="loginButton" type="submit" value="Log in"/>
						<div style="clear:both;"> </div>
						<input class="logButton" id="registerButton" type="submit" value="Register"/>
						<div style="clear:both;"> </div>
					</c:if>
					<c:if test="${user != null}">
						<input class="logButton" id="logoutButton" type="submit" value="Log out"/>
					</c:if>
					</form>
				</div>
				<div style="clear:both;"> </div>
			</div>
      
      
	      	<nav id="mainNav">
				<ol id="mainMenu">
					<li><a href="index.html">News</a>
						<ul class="subMenu">
							<li onclick="activeMenuItem(0, 0, 'all');">All</li>
							<li onclick="activeMenuItem(0, 1, 'New robots');">New robots</li>
							<li onclick="activeMenuItem(0, 2, 'New buildings');">New buildings</li>
							<li onclick="activeMenuItem(0, 3, 'New items');">New items</li>
							<li onclick="activeMenuItem(0, 4, 'Upgrades');">Upgrades</li>
							<li onclick="activeMenuItem(0, 5, 'Free topics');">Free topics</li>
						</ul>
					</li>
					<li><a href="sklep.html">Shop</a>
						<ul class="subMenu">
							<li><a href="sklep.html">All</a></li>
							<li><a href = "#">Robots</a></li>
						</ul>				
					</li>
					<li>My panel
						<ul class="subMenu">
							<li><a href = "#">Main</a></li>
							<li><a href = "#">Robots</a></li>
							<li><a href = "#">Orders</a></li>
						</ul>
					</li>
					<li>About
						<ul class="subMenu">
							<li><a href = "#">About project</a></li>
							<li><a href = "#">About author</a></li>
						</ul>
					</li>
					<li><a href = "#footer">Contact</a></li>
				</ol>
			</nav>
		</header>
		<div id="container">
			<div id="leftSide">
				<ol id="sideNavList">
					<li><a href = "index.html">News</a>
						<ul class="sideNavSubList">
							<li onclick="activeMenuItem(0, 0, 'all');"><span>All</span>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li onclick="activeMenuItem(0, 1, 'New robots');"><span>New robots</span>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li onclick="activeMenuItem(0, 2, 'New buildings');"><span>New buildings</span>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li onclick="activeMenuItem(0, 3, 'New items');"><span>New items</span>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li onclick="activeMenuItem(0, 4, 'Upgrades');"><span>Upgrades</span>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li onclick="activeMenuItem(0, 5, 'Free topics');"><span>Free topics</span>
								<ul class="sideNavSubSubList"></ul>
							</li>
						</ul>
					 </li>
					 <li><a href="sklep.html">Shop</a>
						<ul class="sideNavSubList">
							<li><a href="sklep.html">All</a></li>
							<li><a href = "#">Robots</a></li>
						</ul>
					 </li>
					 <li>My panel
						<ul class="sideNavSubList">
							<li><a href = "#">Main</a></li>
							<li><a href = "#">Robots</a></li>
							<li><a href = "#">Orders</a></li>
						</ul>
					 </li>
					 <li>About
						<ul class="sideNavSubList">
							<li><a href = "#">About project</a></li>
							<li><a href = "#">About author</a></li>
						</ul>
					 </li>
					 <li>Contact</li>
				</ol>
				<div id="ad1">
					ad1
				</div>
			</div>
			
			<main id="main">
				<section class="sectionArticle">
					<a onclick="activeMenuItem(0, 1, 'New robots');" class="sectionArticleCategory" href="#">New robots</a>
					<div class="sectionArticleDate">28.12.2017 09:03</div>
					<br/>
					<h3 class="sectionArticleTitle"><a href="article4.html">HS14 construction ready</a></h3>
					<img class="sectionArticleImg" src="/resources/img/article1img.jpg">
					<div class="sectionArticleContent">
						Lorem ipsum dolor sit amet, consecteturnisi ut blanipit. Suspendisse tempor imperdiet ex non suscipit. Donec eget gravida neque. Sed placerat semper mi non blandit. Nullam quis diam nec est pulvinar cursus. 
					</div>
				</section>
				<section class="sectionArticle">
					<a onclick="activeMenuItem(0, 4, 'Upgrades');" class="sectionArticleCategory"  href="#">Upgrades</a>
					<div class="sectionArticleDate">08.06.2017 21:35</div>
					<br/>
					<h3 class="sectionArticleTitle"><a href="article3.html">New soft for RK08</a></h3>
					<img class="sectionArticleImg" src="/resources/img/article2img.jpg">
					<div class="sectionArticleContent">
						Lorem ipsum dolor sit amet, consecteturnisi ut blanipit. Suspendisse tempor imperdiet ex non suscipit. Donec eget gravida neque. Sed placerat semper mi non blandit. Nullam quis diam nec est pulvinar cursus. 
					</div>
				</section>
				<section class="sectionArticle">
					<a onclick="activeMenuItem(0, 2, 'New buildings');" class="sectionArticleCategory"  href="#">New buildings</a>
					<div class="sectionArticleDate">08.06.2017 21:35</div>
					<br/>
					<h3 class="sectionArticleTitle"><a href="article2.html">Building BS22 concept</a></h3>
					<div class="sectionArticleContent">
						Lorem ipsum dolor sit amet, consecteturnisi ut blanipit. Suspendisse tempor imperdiet ex non suscipit. Donec eget gravida neque. Sed placerat semper mi non blandit. Nullam quis diam nec est pulvinar cursus. 
					</div>
				</section>
				<section class="sectionArticle">
					<a onclick="activeMenuItem(0, 5, 'Free topics');" class="sectionArticleCategory"  href="#">Free topics</a>
					<div class="sectionArticleDate">08.06.2017 21:35</div>
					<br/>
					<h3 class="sectionArticleTitle"><a href="article1.html">Welcome</a></h3>
					<div class="sectionArticleContent">
						Lorem ipsum dolor sit amet, consecteturnisi ut blanipit. Suspendisse tempor imperdiet ex non suscipit. Donec eget gravida neque. Sed placerat semper mi non blandit. Nullam quis diam nec est pulvinar cursus. 
					</div>
				</section>		
			</main>
			
			<aside id="rightSide">
				<div id="basket">
					<div id="basketTitle">Your basket</div>
					<div id="basketContent">
						<c:forEach items="${basketbymodels}" var="robotmodel">
							<div class="basketItem">
								<div class="basketItemName">${robotmodel.model}</div>
								<div class="basketItemPrice">$${robotmodel.price} each</div>
								<div style="clear:both;"> </div>
								<div class="basketItemCount">Qty:${robotmodel.robotCount}</div>
								<div class="basketItemPriceTotal">Total: $${robotmodel.price * robotmodel.robotCount}</div>
							</div>
						</c:forEach>
					</div>	
					<div class="basketItem">
						<div class="basketItemName"><b>TOTAL</b></div>
						<div class="basketItemPrice"><b>$${sumprice}</b></div>
					</div>
					<input id="basketButton" type="submit" value="Order"/>
				</div>
				<div id="ad2">
					ad2
				</div>
			</aside>
			<div style="clear:both;"> </div>
			
		</div>
		<footer id="footer">
			<div style="float: right;">KKBots - Kamil Koszarny<br/>k.koszarny@wp.pl</div>
			<div id="footerMiddle" style="text-align: center;">KKBots thanks for your visit. See you soon :)</div>
		</footer>
    
    
 <%--   <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div> --%>
    
    <script src="/resources/js/jquery-1.11.3.min.js"></script>
	<script src="/resources/js/stickyNav.js"></script>
	<script type="text/javascript">
	    var msg = '${message}';
	    if(msg != '')
	    	alert(msg);
	</script>
  </body>
</html>
