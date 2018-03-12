<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="main" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
		<header id="header">
	     	<h1 id="logo">
				<img id="logoImg" src="/resources/img/logo.jpg"/>
				KK<span style="color: hsl(360, 100%, 30%);">Bots</span>
			</h1>
      		<div style="clear:both;"> </div>
			<div id="login">
				<c:if test="${user == null}">
					<form method="post" action="uservalidation">
						<div id="loginTextFields">
							<div class="loginText">Login:<input class="loginTextField" type="text" name="login" title="Login"/></div>
							<div style="clear:both;"> </div>
							<div class="loginText">Password:<input class="loginTextField" type="password" name="password" title="Password"/></div>
						</div>
						<div id="logButtons">
							<input class="logButton" id="loginButton" type="submit" value="Log in"/>
							<div style="clear:both;"> </div>
						</div>
					</form>
					<div id="logButtons">
						<form method="get" action="register">
							<input class="logButton" id="registerButton" type="submit" value="Register"/>
						</form>
						<div style="clear:both;"> </div>	
					</div>
					<div style="clear:both;"> </div>
				</c:if>
				<c:if test="${user != null}">
					<div id="loginTextFields">
						<div class="loginText">Logged as: <br/>${user.name} ${user.surname}</div>
					</div>
					<div id="logButtons">
						<a href="/logoutothername">
							<input class="logButton" id="logoutButton" type="button" value="Log out"/>
						</a>
					</div>
				</c:if>
			</div>
      
      
	      	<nav id="mainNav">
				<ol id="mainMenu">
					<li><a href="/news">News</a>
						<ul class="subMenu">
							<li><a href="/news/0">All</a></li>
							<li><a href="/news/1">New robots</a></li>
							<li><a href="/news/2">New buildings</a></li>
							<li><a href="/news/3">New items</a></li>
							<li><a href="/news/4">Upgrades</a></li>
							<li><a href="/news/5">Free topics</a></li>
						</ul>
					</li>
					<li><a href="/shop">Shop</a>
						<ul class="subMenu">
							<li><a href="/shop">Products</a></li>
							<li><a href="/order">Basket</a></li>
						</ul>				
					</li>
					<li><a href = "/panel">My panel</a>
						<ul class="subMenu">
							<li><a href = "/panel">Main</a></li>
							<li><a href = "/robots">Robots</a></li>
							<li><a href = "/orders">Orders</a></li>
						</ul>
					</li>
					<li><a href="about">About</a>
						<ul class="subMenu">
							<li><a class="relative" href = "about#aboutproject">About project</a></li>
							<li><a class="relative" href = "about#aboutauthor">About author</a></li>
						</ul>
					</li>
					<li><a href = "#footer">Contact</a></li>
				</ol>
			</nav>
		</header>
		<div id="container">
			<div id="leftSide">
				<ol id="sideNavList">
					<li><a href = "/news">News</a>
						<ul class="sideNavSubList">
							<li><a href="/news/0">All</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href="/news/1">New robots</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href="/news/2">New buildings</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href="/news/3">New items</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href="/news/4">Upgrades</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href="/news/5">Free topics</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
						</ul>
					 </li>
					 <li><a href="/shop">Shop</a>
						<ul class="sideNavSubList">
							<li><a href = "/shop">Products</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href = "/order">Basket</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
						</ul>
					 </li>
					 <li><a href = "/panel">My panel</a>
						<ul class="sideNavSubList">
							<li><a href = "/panel">Main</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href = "/robots">Robots</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li><a href = "/orders">Orders</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
						</ul>
					 </li>
					 <li><a href="/about">About</a>
						<ul class="sideNavSubList">
							<li>
								<a href = "/about#aboutproject">About project</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
							<li>
								<a href = "/about#aboutauthor" >About author</a>
								<ul class="sideNavSubSubList"></ul>
							</li>
						</ul>
					 </li>
					 <li>Contact</li>
				</ol>
				<div id="ad1">
					<a target="_blank" href="https://www.emag.ro/prosoape/brand/saheser/c">
						<img src="/resources/img/ad1.jpg" height="800" width="147"/>
					</a>
				</div>
			</div>
			
			<div id="main">
				<jsp:invoke fragment="main"/>
			</div>
			
			<aside id="rightSide">
				<div id="basket">
					<div id="basketTitle"><a href="/order">Your basket</a></div>
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
					<form method="get" action="/order">
						<input class="basketButton" type="submit" value="Go to basket"/>
					</form>
				</div>
				<div id="ad2">
					<a target="_blank" href="https://businessinsider.com.pl/lifestyle/podroze/japonia-co-warto-zwiedzic-w-kraju-kwitnacej-wisni/qjr0dpf">
						<img src="/resources/img/ad2.jpg" height="800" width="197"/>
					</a>
				</div>
			</aside>
			<div style="clear:both;"> </div>
			
		</div>
		<footer id="footer">
			<div id="footerRight">KKBots - Kamil Koszarny<br/>k.koszarny@wp.pl</div>
			<div id="footerMiddle">KKBots thank for your visit. See you soon :)</div>
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
