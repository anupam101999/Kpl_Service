<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Google tag (gtag.js) -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-2DKXMGP7B2"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-2DKXMGP7B2');
</script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Personal Details</title>
  <style>
    @import url("https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&display=swap");

    *,
    *::before,
    *::after {
      box-sizing: border-box;
      line-height: 1.5em;
    }

    html {
      font-size: 16px;
      scroll-behavior: smooth;
      -webkit-text-size-adjust: 100%;
    }

    body {
      margin: 0;
      font-family: "Open Sans", sans-serif;
      /* background-color:
        #27c5ad; */
    }

    header {
      background-color:
        #d3df5b;
      /* box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1),
        0 4px 6px -4px rgba(0, 0, 0, 0.1); */
      padding: 1.5% 0%;
    }

    header .wrapper {
      max-width: 1000px;
      margin: 0 auto;
      display: flex;
      flex-wrap: wrap;
      align-items: center;
      justify-content: space-between;
    }

    header .logo img {
      height: 60px;
      display: block;
    }

    .navbar {
      position: fixed;
      top: 0;
      left: 100%;
      margin: 0;
      width: 100%;
      height: 100%;
      background-color:
        rgb(185, 231, 20);
      padding: 20px;
      transition: left 0.3s;
    }

    .navbar.show {
      left: 0 !important;
    }

    .hide-scroll {
      overflow: hidden;
    }

    .navbar ul {
      all: unset;
      list-style-type: none;
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 25px;
    }

    .navbar ul a {
      all: unset;
      color:
        #444444;
      text-transform: uppercase;
      cursor: pointer;
      font-weight: bold;
      font-size: 28px;
    }

    .navbar ul a:hover {
      color:
        #111111;
      text-decoration: underline overline;
      text-decoration-thickness: 3px;
    }

    .close-nav {
      text-align: right;
      margin-bottom: 20px;
    }

    .close-nav button {
      all: unset;
      background:
        #f7f7f7;
      font-size: 42px;
      cursor: pointer;
      border: 1px solid rgba(0, 0, 0, 0.2);
      padding: 15px;
      border-radius: 3px;
      color:
        #444444;
    }

    .close-nav button:hover {
      color:
        #222222;
      background:
        white;
    }

    .menu-bar button {
      border: 1px solid rgba(0, 0, 0, 0.1);
      background:
        #f7f7f7;
      height: 50px;
      width: 50px;
      padding: 5px 10px;
      cursor: pointer;
      border-radius: 3px;
    }

    .menu-bar i {
      display: block;
      border-top: 3px solid #444444;
      border-bottom: 3px solid #444444;
    }

    .menu-bar i::after {
      display: block;
      content: "";
      border-top: 3px solid #444444;
      margin: 6px 0;
    }

    .menu-bar button:hover {
      background:
        white;
    }

    .menu-bar button:hover i {
      border-color:
        #222222;
    }

    /* .container {
    max-width: 900px;
    margin: 0 auto;
    padding: 1%;
} */

    @media (min-width: 550px) {
      .navbar {
        all: unset;
        display: block;
      }

      .navbar ul {
        flex-direction: row;
        gap: 20px;
      }

      .navbar ul a {
        font-size: inherit;
      }

      .close-nav,
      .menu-bar {
        display: none;
      }
    }

    body {
      font-family: Arial, sans-serif;
    }

    .container_body {
      max-width: 600px;
      margin: 0 auto;
      padding: 5%;
      /* background-color: #27c5ad; */
    }

    .user-details {
      margin-bottom: 10px;
    }

    .user-details label {
      font-weight: bold;
    }













    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap');

    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Poppins', sans-serif;
    }

    .login-box {
      display: flex;
      justify-content: center;
      flex-direction: column;
      width: 97%;
      height: 100%;
      padding-left: 25%;
      padding-right: 25%;
      padding-top: 5%;
    }

    .login-header {
      text-align: center;
      margin: 20px 0 40px 0;
    }

    .login-header header {
      color: #333;
      font-size: 30px;
      font-weight: 600;
    }

    .input-box .input-field {
      width: 100%;
      height: 41px;
      font-size: 17px;
      padding: 0 25px;
      margin-bottom: 15px;
      border-radius: 30px;
      border: none;
      box-shadow: 0px 5px 10px 1px rgba(0, 0, 0, 0.05);
      outline: none;
      transition: .3s;
    }

    ::placeholder {
      font-weight: 500;
      color: #222;
    }

    .input-field:focus {
      width: 105%;
    }

    .forgot {
      display: flex;
      justify-content: flex-end;
      margin-bottom: 8px;
    }

    section {
      display: flex;
      align-items: center;
      font-size: 14px;
      color: #555;
    }

    #check {
      margin-right: 10px;
    }

    a {
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }

    section a {
      color: #555;
    }

    .input-submit {
      position: relative;
    }

    .submit-btn {
      width: 100%;
      height: 41px;
      background: #d1cd18;
      border: none;
      border-radius: 30px;
      cursor: pointer;
      transition: .3s;
    }

    .input-submit label {
      position: absolute;
      top: 45%;
      left: 50%;
      color: #fff;
      -webkit-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
      cursor: pointer;
    }

    .submit-btn:hover {
      background: #000;
      transform: scale(1.05, 1);
    }

    .sign-up-link {
      text-align: center;
      font-size: 17px;
      margin-top: 20px;
    }

    .sign-up-link a {
      color: #000;
      font-weight: 600;
    }

    .error-popup {
      position: fixed;
      top: 90%;
      left: 48%;
      transform: translate(-50%, -50%);
      padding: 20px;
      color: rgb(240, 8, 8);
      border-radius: 5px;
    }
    span{
      color: black;
      font-size: 90%;
     padding-left: 500%;
    }
  </style>




  <title>Login</title>
</head>

<header>
  <div class="wrapper">
    <div class="logo">
      <a href="https://kpl-test-v01-production.up.railway.app/home"><img src="${pageContext.request.contextPath}/images/transparent_logo.png"
          alt="KPL logo"></a>
    </div>
    <div>
      <h2>Kashipur Premier League (season 5)</h2>
    </div>
    <div class="navbar">
      <div class="close-nav"><button></button></div>
      <nav>
        <ul>
          <li><a href="https://kpl-test-v01-production.up.railway.app/home">Home</a></li>
        </ul>
      </nav>
    </div>
    <div class="menu-bar">
      <a href="https://kpl-test-v01-production.up.railway.app/home"><h2> <span>Home</span></h2></a>   
    </div>
  </div>
</header>
<script>
  const theBody = document.querySelector('body');
  const openNav = document.querySelector('.menu-bar button');
  const closeNav = document.querySelector('.close-nav button');
  const Navbar = document.querySelector('.navbar');
  function showHide() {
    Navbar.classList.toggle('show');
  }

  openNav.onclick = showHide;
  closeNav.onclick = showHide;
</script>







<div class="login-box">
  <div class="login-header">
    <h2>Check Your Registration Status</h2>
  </div>
  <form action="https://kpl-test-v01-production.up.railway.app/login" method="post">
    <div class="input-box">
      <input type="text" class="input-field" id="username" name="username"
        placeholder="Enter your Phone Number or email ID" autocomplete="off" required>
    </div>
    <div class="input-box">
      <input type="password" class="input-field" id="password" name="password" placeholder="Enter your Password"
        autocomplete="off" required>
    </div>
    <div class="forgot">

      <section>
        <a href="https://kpl-test-v01-production.up.railway.app/forgetPassword" target="_blank">Forgot password</a>
      </section>
    </div>
    <div class="input-submit">
      <button class="submit-btn" id="submit" value="login"></button>
      <label for="submit">Sign In</label>
    </div>
  </form>
  <div class="sign-up-link">
    <p>Don't have account? <a href="https://kpl-test-v01-production.up.railway.app/signUpHomePage" target="_blank">Sign Up</a></p>
  </div>
</div>
<% if (request.getAttribute("errorShown")==null) { %>
  <c:if test="${not empty errorMessage && 'POST' eq pageContext.request.method}">
    <div class="error-popup">
      <p>
      <h2>${errorMessage}</h2>
      </p>
    </div>
    <script>
      // JavaScript code to hide the error message after 5 seconds
      setTimeout(function () {
        var errorPopup = document.querySelector('.error-popup');
        if (errorPopup) {
          errorPopup.style.display = 'none';
        }
      }, 5000);
    </script>
    <% request.setAttribute("errorShown", true); %>
  </c:if>
  <% } %>




    <div><br></div>
    <div><br></div>
    <div><br></div>
    <div><br></div>
    <div><br></div>
    </body>

</html>