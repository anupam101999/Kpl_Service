<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Google tag (gtag.js) -->
  <script async src="https://www.googletagmanager.com/gtag/js?id=G-2DKXMGP7B2"></script>
  <script>
    window.dataLayer = window.dataLayer || [];
    function gtag() { dataLayer.push(arguments); }
    gtag('js', new Date());

    gtag('config', 'G-2DKXMGP7B2');
  </script>
  <script type="text/javascript">
    // Function to disable forward navigation
    function disableForwardButton() {
      history.pushState(null, null, document.URL);
      window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
      });
    }


  </script>
  <link rel="icon" href="${pageContext.request.contextPath}/images/transparent_logo.ico" type="image/x-icon">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Live Data Feeding</title>


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
    .flex-container {
  display: flex;
  
}
.container {
      max-width: 100%;
      margin: 0 auto;
    }

    label {
      display: block;
      margin-bottom: 10px;
    }

    .result {
      margin-top: 20px;
      font-size: 18px;
    }

    .image {
      max-width: 200px;
      margin-bottom: 10px;
    }
.flex-container > div {
 
  /* margin: 10px; */
  padding: 20px;
  font-size: 20px;
}
button[type=submit]{
      border-style: solid;
  border-color: black black black black;
  width: 84px;
        height: 38px;
        border-radius: 25px;
  }
  input[type=number] {
      width: 242px;
      height: 36px;
      border-radius: 25px;
      font-size: 16px;
      border: 1px solid #6eac1e;
      text-align: center;
    }  
    .error-popup {
      position: fixed;
      top: 90%;
      left: 28%;
      transform: translate(-50%, -50%);
      padding: 20px;
      color: rgb(240, 8, 8);
      border-radius: 5px;
    }
    
  </style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function() {
    // Listen for input change event
    $("#searchInput").on("input", function() {
      var searchInput = $(this).val();
      var resultContainer = $("#resultContainer");

      // Make an AJAX request to search by ID
      $.get("https://kpl2023.online/registration/kpl/registration/api/search", {id: searchInput}, function(response) {
        // Display the search result
        if (response) {
          var resultHtml = "<img src='data:image/png;base64," + response.image + "' class='image' />";
          resultHtml += "<p>Reg ID: " + response.registrationId + "</p>";          
          resultHtml += "<p>Name: " + response.playerFirstName +" "+response.playerLastName  + "</p>";
          resultHtml += "<p>DOB: " + response.dob + "</p>";
          resultHtml += "<p>Location Category: " + response.location + "</p>";
          resultHtml += "<p>Player Category: " + response.generue + "</p>";
          resultHtml += "<p>Address: " + response.playerAddress + "</p>";
          resultContainer.html(resultHtml);
        } else {
          resultContainer.html("No data found.");
        }
      });
    });
  });
</script>

</head>

<body onload="disableForwardButton()">
  <% if (request.getAttribute("errorShown") == null) { %>
    <c:if test="${not empty errorMessage && 'POST' eq pageContext.request.method}">
  <div class="header">
    <h1>Live Data feeding</h1>
  </div>
  <div class="flex-container">
    <div style="padding: 0% 5% 0% 8%; "><form action="https://kpl2023.online/registration/soldAmountandTeam" method="post">
      <h3>
        <U>Update Player Information</U>
      </h3>
        <label for="regid">Reg ID:</label>
        <input type="number" id="id" name="id" placeholder="Enter Registration ID" required><br><br>
        <label for="sold amount">Sold Amount:</label>
        <input type="number" id="soldAmount" name="soldAmount" placeholder="Enter sold amount" required><br><br>
        <label for="team">Sold to team:
        <select name="team" style=" width: 242px;
        height: 38px;
        border-radius: 25px;text-align: center;" required>
          <option hidden>Team List</option>
          <option value="Team 1">1</option>
          <option value="Team 2">2</option>
          <option value="Team 3">3</option>
          <option value="Team 4">4</option>
          <option value="Team 5">5</option>
          <option value="Team 6">6</option>
          <option value="Team 7">7</option>
          <option value="Team 8">8</option>
          <option value="Team 9">9</option>
          <option value="Team 10">10</option>
        </select>
      </label>
      
      <br>
     <div style="padding-left: 40%;"> <button type="submit" class="btn btn-primary" value="dataupdate">
      Submit
    </button></div>
    <div class="error-message">
      <h4 style="color: #ff0000; font-size:100%">${errorMessage}</h4>
  </div>
    </form></div>
    
    <div class="container" style="padding: 0% 5% 0% 8%;">
      <label for="searchInput"><h3>
        <U>Search Player By Registration ID      </U>   
        <span style="padding-left: 15px;"> <input type="number" id="searchInput" placeholder="Enter Registration ID"></span> 
    
     </label>
      <div class="result" id="resultContainer"></div>
    </div>
    
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
  </div>
</body>

</html>