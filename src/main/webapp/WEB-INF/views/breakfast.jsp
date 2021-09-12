<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Cabin+Condensed&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Dancing+Script&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="/FOODHUB/URLToReachResourcesFolder/css/breakfast.css">
<script src="/FOODHUB/URLToReachResourcesFolder/js/breakfast.js"></script>
<title>breakfast</title>
</head>
<body>
	<nav class="navbar">
		<div class="logo">
			<span class="logoname">Foodhub</span>
		</div>
		<ul class="list">
			<li><a href="breakfast">breakfast</a></li>
			<li><a href="lunch">lunch</a></li>
			<li><a href="dinner">dinner</a></li>
		</ul>
		<div class="login-cart">
			<a href="cart" class="cart-container"> <img class="cart"
				src="/FOODHUB/URLToReachResourcesFolder/images/icons8-shopping-cart-96.png"
				alt="my-cart">
			</a>
			<span class="menu-container"
				onclick="openNav()">
				<img class="menu" style="width: 17px;" src="/FOODHUB/URLToReachResourcesFolder/images/icons8-menu-24.png"
				alt="my-menu">
			</span>
		</div>
	</nav>
	<div class="top-header">
		<img class="top-image"
			src="/FOODHUB/URLToReachResourcesFolder/images/service2.jpg"
			alt="img">
	</div>

	<div id="myNav" class="overlay">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<div class="overlay-content">
			<a href="breakfast">breakfast</a> <a href="lunch">lunch</a> <a
				href="dinner">dinner</a> <a href="cart">cart</a>
		</div>
	</div>

	<div class="all-products-container">
		<div id="products-container" class="products-container"></div>
	</div>
</body>
</html>