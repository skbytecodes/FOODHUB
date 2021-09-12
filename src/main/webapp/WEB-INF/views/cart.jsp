<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Cabin+Condensed&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script&display=swap" rel="stylesheet">
    <script src="/FOODHUB/URLToReachResourcesFolder/js/cart.js"></script>
    <link rel="stylesheet" href="/FOODHUB/URLToReachResourcesFolder/css/cart.css">
    <title>cart</title>
    
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
            <a href="logout" class="login">logout</a>
            
            <span class="menu-container"
				onclick="openNav()">
				<img class="menu" style="width: 17px;" src="/FOODHUB/URLToReachResourcesFolder/images/icons8-menu-24.png"
				alt="my-menu">
			</span>
        </div>
    </nav>
	<section class="presentation">
		   <div class="whole-container" >
		      <div class="items" id="items">
		       
		      </div>
		      <div class="outer-proceed-total">
		        <div class="proceed-total-div">
		            <div class="total-div">
		                <p>Total</p><span>&#8377;</span><span class="total">0</span>
		                <p>Subtotal</p><span>&#8377;</span><span class="total subtotal">0</span>
		            </div>
		            <div class="proceed-div">
		                <a href="shipping" class="proceed-btn">proceed to buy </a>
		            </div>
		        </div>
		    </div>
		   </div>
   </section>
   
   <div id="myNav" class="overlay">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<div class="overlay-content">
			<a href="breakfast">breakfast</a> <a href="lunch">lunch</a> <a
				href="dinner">dinner</a> <a href="logout">logout</a>
		</div>
	</div>
</body>
</html>
