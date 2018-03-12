<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Welcome" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<script>
	    	window.onload = activeMenuItem(0, 5, "Free topics");
		</script>
		<section class="sectionArticle">
			<a href="/news/5" class="sectionArticleCategory">Free topics</a>
			<div class="sectionArticleDate">08.06.2017 21:35</div>
			<br/>
			<h3 class="sectionArticleTitle">Welcome</h3>
			<div class="sectionArticleContent">
				Lorem ipsum dolor sit amet, consecteturnisi ut blanipit. Suspendisse tempor imperdiet ex non suscipit. Donec eget gravida neque. Sed placerat semper mi non blandit. Nullam quis diam nec est pulvinar cursus. 
				Nullam tristique pretium sapien et ultrices. Aliquam luctus ultrices ipsum, ac vestibulum nisi feugiat sed. Maecenas in euismod elit, vitae finibus leo. Aenean maximus tortor et posuere rhoncus. Quisque ultricies, risus non suscipit molestie, velit ex tincidunt ante, id euismod dui ante a purus. Suspendisse bibendum enim sed blandit aliquam. Sed ac varius purus. In ultrices nisl at lorem malesuada pellentesque tempor in est. Etiam mi turpis, lacinia non enim consectetur, commodo condimentum tortor. Duis tempus luctus viverra. Nulla nec lobortis enim. Nunc pharetra, lorem quis euismod porttitor, tortor metus tincidunt tellus, et cursus felis leo suscipit quam. Ut auctor tincidunt congue. Donec elementum enim vel convallis tempor. Nam sit amet urna imperdiet, lobortis diam id, dignissim eros.
				<br/><br/>
				Nam ac eros est. Vestibulum nec lacus tempus, dignissim elit sit amet, feugiat ipsum. Mauris suscipit ligula tortor, in commodo ligula porta sed. Pellentesque eu dignissim nisi. Fusce eu dolor convallis erat scelerisque posuere eget vitae nunc. Suspendisse aliquam bibendum nibh, eget ultrices enim tempor vel. Nam finibus elementum fermentum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sollicitudin tellus non condimentum efficitur. Phasellus a magna et eros venenatis efficitur eget vitae felis. Pellentesque condimentum metus in enim accumsan, non posuere nisl feugiat.
				<br/><br/>
				Suspendisse nisi velit, condimentum vel dictum a, posuere commodo massa. Vivamus volutpat nunc ut purus mattis, et vulputate nulla consectetur. Vivamus est tellus, dictum eu sem id, cursus varius tortor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque erat eros, ultricies et nisi at, cursus pharetra tellus. Duis facilisis imperdiet diam a semper. Sed at leo interdum, consectetur dolor ac, sollicitudin dolor. Proin cursus quam ac ligula sodales cursus. Nam bibendum metus tellus, vel porttitor nibh tempor tincidunt. Mauris rutrum erat et nisi ornare, et hendrerit dui facilisis. Quisque justo tellus, iaculis sit amet erat vitae, volutpat hendrerit quam. Donec aliquet tellus risus, non consequat ipsum tempor eu. Suspendisse vitae mattis nisi. Vivamus vel lacinia quam. Etiam urna arcu, sodales id pulvinar ut, congue finibus augue.
				<br/><br/>
			</div>
		</section>
    </jsp:attribute>
</t:genericpage>