<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="About" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<h2>About project</h2>
    		<span id="aboutproject" class="relativeAnchor"></span>
    		<span class="abouttext">Welcome on KKBots page. This is a project made for educational and presentation purposes, so unfortunately those amazing robots you have probably seen in a shop are not available for sale. Please feel free to check all functionality of this sample page. You can click everywhere, try to register, order a robot, check for status, confirm receive and more with no worries. If you found something unusual I will appreciate contacting me. <br/> Project on GitHub: </span>
    		<a></a>
    		<h4>Tools used in project:</h4>
    		<ul>
    			<li>Back-end logic
    				<ul>
    					<li>Java</li>
    					<li>Spring Boot/MVC</li>
    				</ul>
    			</li>
    			<li>Front-end
    			    <ul>
    					<li>HTML/CSS</li>
    					<li>JavaScript</li>
    					<li>JSP</li>
    				</ul>
    			</li>
    			<li>Database
    				<ul>
    					<li>SQL</li>
    					<li>Hibernate/JPA</li>
    				</ul>
    			</li>
    			<li>Tools
    				<ul>
    					<li>Eclipse (STS)</li>
    					<li>Maven</li>
    					<li>Git</li>
    					<li>Firefox Developer Tools</li>
    				</ul>
    			</li>
    		</ul>
    	<h2>About author</h2>
    	<span id="aboutauthor" class="relativeAnchor"></span>
    	I am an robotics engineer developing my programming skills both in low level (Fanuc, Karel) and high level (Java, JavaScript, web) languages. You can find more about me on: 
    		<ul>
    			<li>Github: <a target="_blank" href="https://github.com/KamilKoszarny">github.com/KamilKoszarny</a></li>
    			<li>LinkedIn: <a target="_blank" href="https://www.linkedin.com/in/kamil-koszarny">linkedin.com/in/kamil-koszarny</a></li>
    		</ul>
    		
		<script>
		    window.onload = activeMenuItem(3, 99, '');
		</script>	    	
    </jsp:attribute>
</t:genericpage>