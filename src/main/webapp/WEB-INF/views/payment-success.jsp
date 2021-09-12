<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet" href = "/FOODHUB/URLToReachResourcesFolder/css/payment-success.css">
<link href="https://fonts.googleapis.com/css2?family=Cabin+Condensed&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Dancing+Script&display=swap" rel="stylesheet"> 
<title>payment success</title>
</head>
<body>
		<p><span class = "conratsSpan">Congratulations !</span> For Successful Order</p>
		<section class="section">
			<h3>Thank you for ordering food from our website mr. sushil. Hope you will like it!</h3>
			<div class="outerdiv">
				
				<div class="div1">
					<span class="oid">Order Id </span>
					<span>${paymentSuccessDetails.orderid}</span>
				</div>
				
				<div class="div2">
					<span>Payment Id</span>
					<span>${paymentSuccessDetails.paymentid}</span>
				</div>
				
				<div class="div3">
					<span class="uid">User Id  </span>
					<span>${paymentSuccessDetails.userid}</span>
				</div>
				
				<div class="div4">
					<span class="amount">Total Amount</span>
					<span>&#8377; ${amount}</span>
				</div>
				
				<div class="div5">
					<span class="odate">Order Date </span>
					<span>${paymentSuccessDetails.date}</span>
				</div>
				
			</div>
		</section>
		<h5>Remainder: You can use these credentials for further queries regarding to the products you have ordered. Thank you!</h5>
		<a class="homebtn" href="home">Home</a>
		
</body>
</html>