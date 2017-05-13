<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des plats</title>
<%@ include file="liens.jsp"%>
</head>
<body class="home1">
	<%@ include file="header.jsp"%>
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">${sessionUtilisateur.surname}
						${sessionUtilisateur.name}</h2>
					<h3 class="secondery-title">Fa&Icirc;tes votre recherche</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<hr />
					<div class="search-area">
						<form action="ServletAcheter" method="post">
							<input type="text" name="search" id="search"
								placeholder="Exp: lasagne.." /> <input type="hidden"
								name="action" value="chercherProduit" />
							<button type="submit">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
							<br />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<section id="page-title-area">
	<h3 align="left" class="primery-title">Tous les produits</h3>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-lg padding-bottom-lg">
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<div class="pagination">
									<table class="table shop-cart">
										<thead>
											<tr>
												<th>Nom du produit</th>
												<th>Description</th>
												<th>Prix</th>
												<th>Ajouter au panier</th>
											</tr>
										</thead>

										<tbody>
											<c:set var="total" value="0" />
											<c:set var="i" value="0" />
											<c:forEach var="article"
												items="${requestScope['searchResults']}">
												<tr>
													<td>${article.name }</td>
													<td>${article.description }</td>
													<td>${article.price}</td>
													<td align="center"><button
															class="btn btn-primary btn-	s">
															<a style="color: white;"
																href="acheter?action=chargerPanier&idarticle=${i}">+1</a>
														</button></td>
												</tr>
												<c:set var="total" value="${total+1}" />
												<c:set var="i" value="${i+1}" />
											</c:forEach>


										</tbody>

										<tfoot>
											<td><b>TOTAL</b></td>
											<td></td>
											<td></td>
											<td><b>${requestScope['total']}</b></td>
										</tfoot>
									</table>
									${requestScope['pagination']}
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>