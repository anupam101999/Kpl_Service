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

<script type="text/javascript">
  // Function to disable forward navigation
  function disableForwardButton() {
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
      history.pushState(null, null, document.URL);
    });
  }
</script>

  <title>Admin Dashboard</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="${pageContext.request.contextPath}/images/transparent_logo.ico" type="image/x-icon">
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

    input[type=text] {
      width: 242px;
      height: 36px;
      border-radius: 25px;
      font-size: 16px;
      border: 1px solid #6eac1e;
      text-align: center;
    }  

    select {
      width: 242px;
      height: 36px;
      border-radius: 25px;
      font-size: 16px;
      border: 1px solid #6eac1e;
      text-align: center;
    }  
  .flex-container1 {
  display: flex;
  justify-content: space-evenly;
  
}
.flex-container1 > div {
 font-size: 20px;
}
.flex-container2 {
  display: flex;
  justify-content: space-evenly;
  
}
.flex-container2 > div {
 font-size: 20px;
}
.flex-container3 {
  display: flex;
  justify-content: space-evenly;
  
}
.flex-container3 > div {
 font-size: 20px;
}
.flex-container4 {
  display: flex;
  justify-content: space-evenly;
  
}
.flex-container4 > div {
 font-size: 20px;
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

<body onload="disableForwardButton()">
  <% if (request.getAttribute("errorShown") == null) { %>
    <c:if test="${not empty errorMessage && 'POST' eq pageContext.request.method}">
   
  <div class="header">
    <h1>Admin Dashboard view</h1>
  </div>
  <div class="flex-container1">
    <div>
      <p>Players who made the payments</p>
      <form action="http://13.126.58.1:5000/paymentUpdate" method="post">
        <input type="text" name="regid" placeholder="Enter Reg ID like 1,2,3" required>
        <button type="submit">Submit</button>
      </form>
    </div>

    <div>
      <p>Players to be updated in Category A</p>
      <form action="http://13.126.58.1:5000/updateCategory" method="post">
        <input type="text" name="regid" placeholder="Enter Reg ID like 1,2,3" required>
        <button type="submit" >Submit</button>
      </form>
    </div>
  




  </div>
  

  <div class="error-message">
    <p style="color: #ff0000; padding-left: 35%; font-size:300%">${errorMessage}</p>
</div>
  <br><br>





  <div class="flex-container3" style="padding-left: 25px;">
   
    <div class="select-box"">
      <p>Player category specific photo download</p>
        <form action="http://13.126.58.1:5000/kpl/registration/api/downloadGenerueSpImage"
          method="get">
          <select name="generue">
            <option hidden>Category</option>
            <option value="Batsman">Batsman</option>
            <option value="Bowler">Bowler</option>
            <option value="Wicket Keeper">Wicket Keeper</option>
            <option value="All Rounder">All Rounder</option>
            <option value="List A">List A</option>
          </select>
          <button type="submit">Download</button>
        </form>
    </div>

    <div class="select-box">
      <p>Player category specific pdf for committee</p>
     <form action="http://13.126.58.1:5000/kpl/registration/api/generate/finalPlayerListPdf"
       method="get" target="_blank">
       <select name="generue">
         <option hidden>Category</option>
         <option value="Batsman">Batsman</option>
         <option value="Bowler">Bowler</option>
         <option value="Wicket Keeper">Wicket Keeper</option>
         <option value="All Rounder">All Rounder</option>
         <option value="List A">List A</option>
       </select>
       <button type="submit">Download</button>
     </form>

  </div>

</div>










<div class="flex-container2">
  <div>
    <p>Documents Front</p>
      <form action="http://13.126.58.1:5000/kpl/registration/api/downloadAllDocFrontImage"
        method="get">
        <button type="submit">Download</button>
      </form>
  </div>
  <div>
    <p>Document Back</p>
      <form action="http://13.126.58.1:5000/kpl/registration/api/downloadAllDocBackImage"
        method="get">
        <button type="submit">Download</button>
      </form>
  </div>

</div>



<div class="flex-container4"  >
  <!-- <div class="container3"> -->
    <div style="padding-left: 260px;">
      <p >All Player PDF</p>
        <form action="http://13.126.58.1:5000/kpl/registration/api/generate/AllplayerPdf"
          method="get" target="_blank">
          <button type="submit">Download</button>
        </form>
    </div>
    <div class="select-box" style="padding-left: 240px;">
         <p>Player category specific pdf for owners</p>
        <form action="http://13.126.58.1:5000/kpl/registration/api/generate/playerPdf"
          method="get" target="_blank">
          <select name="generue">
            <option hidden>Category</option>
            <option value="Batsman">Batsman</option>
            <option value="Bowler">Bowler</option>
            <option value="Wicket Keeper">Wicket Keeper</option>
            <option value="All Rounder">All Rounder</option>
            <option value="List A">List A</option>
          </select>
          <button type="submit">Download</button>
        </form>
    </div>
  <!-- </div> -->


  <br><br>
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