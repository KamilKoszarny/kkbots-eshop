<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="News" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
  		<section class="sectionArticle">
			<a onclick="activeMenuItem(0, 1, 'New robots');" class="sectionArticleCategory" href="#">New robots</a>
			<div class="sectionArticleDate">28.12.2017 09:03</div>
			<br/>
			<h3 class="sectionArticleTitle"><a href="article4.html">RGS01 construction ready</a></h3>
			<img class="sectionArticleImg" src="/resources/img/RGS01.jpg">
			<div class="sectionArticleContent">
				Lorem ipsum dolor sit amet, consecteturnisi ut blanipit. Suspendisse tempor imperdiet ex non suscipit. Donec eget gravida neque. Sed placerat semper mi non blandit. Nullam quis diam nec est pulvinar cursus. 
			</div>
		</section>
		<section class="sectionArticle">
			<a onclick="activeMenuItem(0, 4, 'Upgrades');" class="sectionArticleCategory"  href="#">Upgrades</a>
			<div class="sectionArticleDate">08.06.2017 21:35</div>
			<br/>
			<h3 class="sectionArticleTitle"><a href="article3.html">New soft for RKZ02</a></h3>
			<img class="sectionArticleImg" src="/resources/img/RKZ02.jpg">
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
    </jsp:attribute>
</t:genericpage>