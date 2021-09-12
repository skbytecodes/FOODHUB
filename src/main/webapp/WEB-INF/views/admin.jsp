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
    <link rel="stylesheet" href="/FOODHUB/URLToReachResourcesFolder/css/admin.css">
    <script src="/FOODHUB/URLToReachResourcesFolder/js/admin.js"></script>
    <title>admin</title>
</head>
<body>
   <header>
        <div class="logo">
            <span>Foodhub</span>
        </div>
        <nav>
            <p>Products</p>
        </nav>
        <div class="log-out">
            <a href="logut">logout</a>
        </div>
   </header>

   <section class="presentation"> 
    <div class="addProduct">
        <a class="add" href="addProduct">Add Product</a>
    </div>
    <div class="items">
        <table>
            <tr class="toprow">
                <th>Product ID</th>
                <th>Product name</th>
                <th>Product cost</th>
                <th>Product description</th>
                <th>Product image</th>
                <th>UPDATE</th>
                <th>DELETE</th>
            </tr>
        </table>
    </div>
   </section>
</body>
</html>
























