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
    .container {
    display: flex;
    justify-content: space-around;
  }
  .container2 {
    display: flex;
    justify-content: space-around;
  }
  .container3{
    display: flex;
    justify-content: space-around;
  }

  
  </style>
</head>

<body>

  <div class="header">
    <h1>Admin Dashboard view</h1>
  </div >
  <br>
  <div class="container">
    <div>
      <h2>Players who made the payments</h2> 
      <form action="https://kpl-test-v01-production.up.railway.app/paymentUpdate" method="post">
        <input type="text" name="regid" placeholder="Enter Reg ID like 1,2,3" required>
        <button type="submit">Submit</button>
      </form>
    </div>
  
    <div>
      <h2>Players to be updated in Category A</h2>
      <form action="https://kpl-test-v01-production.up.railway.app/updateCategory" method="post">
        <input type="text" name="regid" placeholder="Enter Reg ID like 1,2,3" required>
        <button type="submit">Submit</button>
      </form>
    </div>
  </div>


<br><br>




  
  <div class="container2">


    <div >
      <h2>All document download based <br>on registration ID</p>
    <form action="https://kpl-test-v01-production.up.railway.app/downloadDocImg" method="GET">
        <button type="submit">Download all document</button>
    </form>
    </div>
    <div class="select-box">
      <h2>Player category specific photo download</p>
      <form action="https://kpl-test-v01-production.up.railway.app/downloadCategorySpecificImage" method="post">
      <select name="playerCategory">
        <option hidden>Player Category</option>
        <option value="Batsman">Batsman</option>
        <option value="Bowler">Bowler</option>
        <option value="Wicket Keeper">Wicket Keeper</option>
        <option value="All Rounder">All Rounder</option>
        <option value="List A">List A</option>
      </select>
      <button type="submit">Download category specific image</button>
    </form>
    </div>
  </div>



<br><br>






<div class="container3">
    <div>
      <h2>All Player PDF</p>
    <form action="https://kpl-test-v01-production.up.railway.app/kpl/registration/api/generate/AllplayerPdf" method="get" target="_blank">
        <button type="submit">Download All Player Pdf</button>
    </form>
    </div>
    <div class="select-box">
      <h2>Player category specific pdf for owners</p>
      <form action="https://kpl-test-v01-production.up.railway.app/kpl/registration/api/generate/finalPlayerListPdf" method="get" target="_blank">
      <select name="generue">
        <option hidden>Player Category</option>
        <option value="Batsman">Batsman</option>
        <option value="Bowler">Bowler</option>
        <option value="Wicket Keeper">Wicket Keeper</option>
        <option value="All Rounder">All Rounder</option>
        <option value="List A">List A</option>
      </select>
      <button type="submit">Download category specific pdf</button>
    </form>
    </div>
  </div>






<br><br>



<div style="display: flex;
justify-content:space-around">
    <div class="select-box" >
      <h2>Player category specific pdf for committee</p>
      <form action="https://kpl-test-v01-production.up.railway.app/kpl/registration/api/generate/playerPdf" method="get" target="_blank">
      <select name="generue">
        <option hidden>Player Category</option>
        <option value="Batsman">Batsman</option>
        <option value="Bowler">Bowler</option>
        <option value="Wicket Keeper">Wicket Keeper</option>
        <option value="All Rounder">All Rounder</option>
        <option value="List A">List A</option>
      </select>
      <button type="submit">Download category specific pdf</button>
    </form>
    </div>
  </div>
</body>

</html>