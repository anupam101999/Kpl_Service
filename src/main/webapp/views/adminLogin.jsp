<!DOCTYPE html>
<html lang="en">
<head>
<title>Page Title</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial;
  margin: 0;
  background-color: #afcf20;
}

.header {
  padding: 0.005%;
  text-align: center;
  background: #1abc9c;
  color: white;
  font-size: 20px;
  
}

body {font-family: Arial, Helvetica, sans-serif;}

.rainbow-hover {
  font-size: 16px;
  font-weight: 700;
  color: #ff7576;
  background-color: #2B3044;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 4px 24px;
  position: relative;
  line-height: 24px;
  border-radius: 9px;
  box-shadow: 0px 1px 2px #2B3044,
    0px 4px 16px #2B3044;
  transform-style: preserve-3d;
  transform: scale(var(--s, 1)) perspective(600px)
    rotateX(var(--rx, 0deg)) rotateY(var(--ry, 0deg));
  perspective: 600px;
  transition: transform 0.1s;
}

.sp {
  background: linear-gradient(
      90deg,
      #866ee7,
      #ea60da,
      #ed8f57,
      #fbd41d,
      #2cca91
    );
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  display: block;
}

.rainbow-hover:active {
  transition: 0.3s;
  transform: scale(0.93);
}
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  border-radius: 15%;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
button:hover {
  opacity: 0.8;
}
}
.container {
  padding-top: 8%;
  padding-left: 18%;
  padding-right: 18%; 
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
</style>
</head>
<body>

<div class="header">
  <h1>Admin Dashboard Log in</h1>
</div>
<form action="https://kpl-test-v01-production.up.railway.app/adminDashboardView" method="post">
<div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" name="id" placeholder="Enter Your Username" name="uname" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" name="password" placeholder="Enter Password" name="psw" required>
    <div><br></div>
    <button type="submit"data-label="Sign In" class="rainbow-hover" value="login">
        <span class="sp">Sign In</span>
      </button>     
</div>
</form>
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