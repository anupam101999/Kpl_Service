<!DOCTYPE html>
<!---Coding By CodingLab | www.codinglabweb.com--->
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
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <!--<title>Registration Form in HTML CSS</title>-->
  <!---Custom CSS File--->
  <style>
    /* Import Google font - Poppins */
    @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");

    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: "Poppins", sans-serif;
    }

    body {
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 20px;
      background:  #d3df5b;
    }

    .container {
      position: relative;
      max-width: 700px;
      width: 100%;
      background: #fff;
      padding: 25px;
      border-radius: 8px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
    }

    .container header {
      font-size: 1.5rem;
      color: #333;
      font-weight: 500;
      text-align: center;
    }

    .container .form {
      margin-top: 30px;
    }

    .form .input-box {
      width: 100%;
      margin-top: 20px;
    }

    .input-box label {
      color: #333;
    }

    .form :where(.input-box input, .select-box) {
      position: relative;
      height: 50px;
      width: 100%;
      outline: none;
      font-size: 1rem;
      color: #707070;
      margin-top: 8px;
      border: 1px solid #ddd;
      border-radius: 6px;
      padding: 0 15px;
    }

    .input-box input:focus {
      box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1);
    }

    .form .column {
      display: flex;
      column-gap: 15px;
    }

    .form .location-box {
      margin-top: 20px;
    }

    .location-box h3 {
      color: #333;
      font-size: 1rem;
      font-weight: 400;
      margin-bottom: 8px;
    }

    .form :where(.player-location, .location) {
      display: flex;
      align-items: center;
      column-gap: 50px;
      flex-wrap: wrap;
    }

    .form .location {
      column-gap: 5px;
    }

    .location input {
      accent-color: rgb(130, 106, 251);
    }

    .form :where(.location input, .location label) {
      cursor: pointer;
    }

    .location label {
      color: #707070;
    }

    .address :where(input, .select-box) {
      margin-top: 15px;
    }

    .select-box select {
      height: 100%;
      width: 100%;
      outline: none;
      border: none;
      color: #707070;
      font-size: 1rem;
    }

    .form button {
      height: 55px;
      width: 100%;
      color: #fff;
      font-size: 1rem;
      font-weight: 400;
      margin-top: 30px;
      border: none;
      cursor: pointer;
      transition: all 0.2s ease;
      background: rgb(130, 106, 251);
    }

    .form button:hover {
      background: rgb(88, 56, 250);
    }

    /*Responsive*/
    @media screen and (max-width: 500px) {
      .form .column {
        flex-wrap: wrap;
      }

      .form :where(.player-location, .location) {
        row-gap: 15px;
      }

      .error-message {
    color: #ff0000;
    font-weight: bold;
    margin-top: 5px;
}
    }
  </style>
</head>

<body>
  <% if (request.getAttribute("errorShown") == null) { %>
    <c:if test="${not empty errorMessage && 'POST' eq pageContext.request.method}">
   
  <section class="container">
    <header>Registration Form</header>
    <form action="https://kpl-test-v01-production.up.railway.app/signUp" class="form" method="post" enctype="multipart/form-data">
      <div class="column">
        <div class="input-box">
          <label>First Name</label>
          <input type="text" name="playerFirstName" placeholder="Enter Your FirstName" required />
        </div>
        <div class="input-box">
          <label>Last Name</label>
          <input type="text" name="playerLastName" placeholder="Enter Your LastName" required />
        </div>
      </div>

      <div class="column">
        <div class="input-box">
          <label>Aadhaar No</label>
          <input type="number" name="aadharNo" placeholder="Enter Your Aadhaar card No" required />
        </div>
        <div class="input-box">
          <label>Aadhaar Card Image</label>
          <input type="file" name="docImage" accept="image/**" required />
        </div>
      </div>

      <div class="column">
        <div class="input-box">
          <label>Phone Number</label>
          <input type="number" name="phNo" placeholder="Enter phone number" required />
        </div>
        <div class="input-box">
          <label>Birth Date</label>
          <input type="date" name="dob" placeholder="Enter birth date" required />
        </div>
      </div>
    </div>
    <div class="error-message">
      <h2 style="color: #ff0000; padding-left: 10%; font-size:300%">${errorMessage}</h2>
  </div>
      <div class="input-box address">
        <div class="input-box">
          <label>Address</label>
          <input type="text" name="address" placeholder="Enter Your Full address" required />
        </div>
        <div class="column">
          <div class="select-box">
            <select name="location">
              <option hidden>Your Home location</option>
              <option value="Local">Local(Kumra Panchayet)</option>
              <option value="Overseas">Overseas</option>
            </select>
          </div>

          <input type="email" name="mail" placeholder="Enter email ID" />
        </div>
        <div class="column">
          <div class="select-box">
            <select name="playerCategory">
              <option hidden>Player Category</option>
              <option value="Batsman">Batsman</option>
              <option value="Bowler">Bowler</option>
              <option value="Wicket Keeper">Wicket Keeper</option>
              <option value="All Rounder">All Rounder</option>
            </select>
          </div>
          <input type="Number" name="pinCode" placeholder="Enter postal code" required />
        </div>
        <div class="column">

          <div class="input-box">
            <label>Your Photo</label>
            <input type="file" name="playerPhoto" accept="image/**" required />
          </div>
          <div class="input-box">
            <label>password</label>
            <input type="password" name="password" placeholder="more than 3 and less than 9 digit" required />
          </div>
        </div>
      
      <div class="col-md-6 offset-md-4">
        <button type="submit" class="btn btn-primary" value="signUp">
          Submit
        </button>
      </div>
    </form>
  </section>

  <script>
    // JavaScript code to hide the error message after 5 seconds
    setTimeout(function () {
        var errorContainer = document.querySelector('.error-message');
        if (errorContainer) {
            errorContainer.style.display = 'none';
        }
    }, 5000);
</script>

<% request.setAttribute("errorShown", true); %>
</c:if>
<% } %>
</body>

</html>