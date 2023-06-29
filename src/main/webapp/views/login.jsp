<!DOCTYPE html>
<html>

<head>
  <title>Login Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #27c5ad;
    }

    .logo-container {
      margin-bottom: 1%;
      padding-left: 45%;
      padding-top: 2%;
    }

    .logo-container img {
      width: 15%;
    }

    .form-container {
      width: 60%;
      padding-left: 18%;
    }

    h2 {
      margin-top: 0;
      margin-bottom: 20px;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      margin-bottom: 5px;
      color: #555555;
    }

    .form-group input {
      width: 100%;
      padding: 2%;
      border: 1px solid #dddddd;
      border-radius: 5px;
    }

    .form-group input[type="submit"] {
      background-color: #007bff;
      color: #ffffff;
      cursor: pointer;
    }

    .button-container {
      display: flex;
      justify-content: center;
      padding-left: 24%;
      padding-right: 18%;
      padding-top: 3%;
      flex-direction: row;
      align-content: flex-start;
      align-items: baseline;
    }


    .button-container a {
      text-decoration: none;
      color: #88ff00;
    }

    #heading {
      padding-left: 25%;
      padding-top: 4%;
    }

    .error-popup {
      position: fixed;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      padding: 20px;
      color: rgb(240, 8, 8);
      border-radius: 5px;
    }

    .links>a {
      padding-left: 18%;
      margin: 0;
      color: #0e3dd8;
    }
  </style>
</head>

<body>
  <div id="heading">
    <h1>Kashipur Premier League (season 5)</h1>
  </div>
  <div class="container">
    <div class="logo-container">
      <img src="${pageContext.request.contextPath}/images/transparent_logo.png" alt="KPL Logo">
    </div>
    <div class="form-container">
      <h2>Login</h2>
      <form action="/login" method="post">
        <div class="form-group">
          <label for="username"><strong>Phone No/Email:</strong></label>
          <input type="text" id="username" name="username" placeholder="Enter Your Phone Number or Mail ID" required>
        </div>
        <div class="form-group">
          <label for="password"><strong>Password:</strong></label>
          <input type="password" id="password" name="password" placeholder="Enter your Password" required>
        </div>
        <div class="form-group button-container">
          <input type="submit" value="Login">
        </div>
      </form>
    </div>
  </div>
  <div>
    <!-- <form action="/createNewAccount" method="post"> -->
    <div class="links">
      <a href="https://www.google.com" target="_blank">Create New Regitration</a><a href="#"></a> <a
        href="http://192.168.0.201:1999/forgetPassword" target="_blank">Forget Password ?</a>
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
</body>

</html>