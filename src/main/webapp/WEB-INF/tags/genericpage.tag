<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="main" fragment="true" %>
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
				<c:if test="${user == null}">
					<div id="loginTextFields">
						<form method="post" action="uservalidation">
							<div class="loginText">Login:<input class="loginTextField" type="text" name="login" title="Login"/></div>
							<div style="clear:both;"> </div>
							<div class="loginText">Password:<input class="loginTextField" type="password" name="password" title="Password"/></div>
						
					</div>
					<div id="logButtons">
						<input class="logButton" id="loginButton" type="submit" value="Log in"/>
						<div style="clear:both;"> </div>
						</form>
						<form method="get" action="register">
							<input class="logButton" id="registerButton" type="submit" value="Register"/>
						</form>
						<div style="clear:both;"> </div>	
					</div>
					<div style="clear:both;"> </div>
				</c:if>
				<c:if test="${user != null}">
					<div id="loginTextFields">
						<form method="get" action="logout">
							<div class="loginText">Logged as: <br/>${user.name} ${user.surname}</div>
						</form>
					</div>
					<div id="logButtons">
						<form method="post" action="logout">
							<input class="logButton" id="logoutButton" type="submit" value="Log out"/>
						</form>
					</div>
				</c:if>
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
					<li><a href="shop">Shop</a>
						<ul class="subMenu">
							<li><a href="shop">All</a></li>
							<li><a href = "shop">Robots</a></li>
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
					 <li><a href="shop">Shop</a>
						<ul class="sideNavSubList">
							<li><a href="shop">All</a></li>
							<li><a href = "shop">Robots</a></li>
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
			
			<div id="main">
				<jsp:invoke fragment="main"/>
			</div>
			
			<aside id="rightSide">
				<div id="basket">
					<div id="basketTitle">Your basket</div>
					<div id="basketContent">
						<c:forEach items="${basketbymodels}" var="robotmodel">
							<div class="basketItem">
								<div class="basketItemName"><b>${robotmodel.model}</b></div>
								<div class="basketItemPrice">$${robotmodel.price} each</div>
								<div style="clear:both;"> </div>
								<div class="basketItemCount">Qty:${robotmodel.robotCount}</div>
								<div class="basketItemPriceTotal">Total: $${robotmodel.price * robotmodel.robotCount}</div>
							</div>
						</c:forEach>
					</div>	
					<div class="basketItem">
						<div class="basketItemName"><b>TOTAL</b></div>
						<div class="basketItemPriceTotal"><b>$${sumPrice}</b></div>
					</div>
					<form method="get" action="order">
						<input class="basketButton" type="submit" value="Go to order"/>
					</form>
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
