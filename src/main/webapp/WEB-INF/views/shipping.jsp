<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Cabin+Condensed&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Dancing+Script&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="/FOODHUB/URLToReachResourcesFolder/css/shipping.css">
<title>shipping</title>
</head>
<body>
	<header>
		<span>Foodhub</span>
	</header>
	<section class="container">
		<div class="shipping">

			<h3>shipping address</h3>
			<form:form action="shipping" method="POST" modelAttribute="shipping"
				cssClass="myform">

				<label for="houseNo" class=label>HouseNo</label>
				<form:input path="houseNo" id="houseNo" cssClass="house" />
				<form:errors path="houseNo" cssClass="error" />


				<label for="block" class="label">Block</label>
				<form:input path="block" id="block" cssClass="block" />
				<form:errors path="block" cssClass="error" />


				<label for="zipcode" class="label">Zipcode</label>
				<form:input path="zipcode" id="zipcode" cssClass="zipcode" />
				<form:errors path="zipcode" cssClass="error" />


				<label for="city" class="label">City</label>
				<form:input path="city" id="city" cssClass="city" />
				<form:errors path="city" cssClass="error" />


				<label for="state" class="label">State</label>
				<form:input path="state" id="state" cssClass="state" />
				<form:errors path="state" cssClass="error" />

				<label for="country" class="label">Country</label>
				<form:input path="country" id="country" cssClass="country" />
				<form:errors path="country" cssClass="error" />


				<input type="submit" value="checkout" id="checkout" />
			</form:form>
		</div>
	</section>
</body>
</html>