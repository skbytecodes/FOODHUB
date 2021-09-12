<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Cabin+Condensed&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Dancing+Script&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="/FOODHUB/URLToReachResourcesFolder/css/addProduct.css">
    <script src="/FOODHUB/URLToReachResourcesFolder/js/addProduct.js" async></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>addProduct</title>
</head>
<body>
    <header>
        <div class="logo">
            <span>Foodhub</span>
        </div>
        <nav>
            <p>Add Product</p>
        </nav>
        <div class="log-out">
            <a href="logut.html">logout</a>
        </div>
   </header>

   <section class="presentation">
       <div class="formDiv">
           <div class="heading"> 
               <h4>Add product to the database</h4>
           </div>
           <form id="myform">
               Product name <input type="text" name="pname" id="pname" required><br>
               Product cost <input type="text" name="pcost" id="pcost"  required><br>
               Product description <input type="text" name="pdesc" id="pdesc" required><br>
               Product image <input type="file" name="file" id="file" required><br>
               Product category <select name="pcategory" id="pcategory" required>
                    <option value="Breakfast">Breakfast</option>
                    <option value="Lunch">Lunch</option>
                    <option value="Dinner">Dinner</option>
               	</select><br>
                <input type="submit" value="submit" id="submit"> 
           </form> 
       </div>
   </section>
</body>
</html>