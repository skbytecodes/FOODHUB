<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Cabin+Condensed&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/FOODHUB/URLToReachResourcesFolder/css/login.css">
    <title>login</title>
</head>
<body>  
    <div id="log-container" class="login-container">
        <div class="login-div">
            <h1>login</h1>
           <form action="login" class="form" method="POST">
            <input type="email" name="email" placeholder="Email" id="email" required/><br>
            <input type="password" name = "password" placeholder="password" id="pass" required/><br>
            <button class="login-btn">login</button>
           </form>
        </div>
       <p>by signing-in you agree to the term and conditions of foodhub  use and sale.please see our
       privacy notice,our cookies notice and our interest based notice.</p>
       <button id ="register" class="register-btn">register</button>
    </div>


    <div id="sign-container" class="signup-container">
        <div class="signup-div">
            <h1>sign-up</h1>
           <form action="register" class="register-form" method="POST">
            <input type="text" placeholder="username" name="username" class="username"/><br>
            <input type="email" name="email" placeholder="Email" id="signemail" required/><br>
            <input type="password" name = "password" placeholder="password" id="signpass" required/><br>
            <button class="signup-btn">register</button>
           </form>
        </div>
       <p>by signing-in you agree to the term and conditions of foodhub  use and sale.please see our
       privacy notice,our cookies notice and our interest based notice.</p>
       <button id="login">login</button>
    </div>


    <script>
        const register = document.querySelector('#register')
        const container = document.querySelector('.signup-container');
        const logincontainer = document.querySelector('.login-container') 
        register.addEventListener('click',()=>{
            logincontainer.style.display = "none";
            container.style.display = "block";
        })
        let loginbtn = document.getElementById('login');
        loginbtn.addEventListener('click',()=>{
            container.style.display = "none";
            logincontainer.style.display = "block";
        })
    </script>
</body>
</html>