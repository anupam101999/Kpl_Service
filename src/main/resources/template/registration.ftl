<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mail Template Registration</title>
  <style>
    header {
      padding: 0.005%;
      text-align: center;
      background: #1abc9c;
      color: white;
      font-size: 20px;
      text-align: center;

    }

    body {
      font-family: Arial;
      margin: 0;
      background-color: #afcf20;
    }

    body {
      font-family: Arial, Helvetica, sans-serif;
    }

  </style>
</head>

<body>
  <header>
    <div>
      <h1>Kashipur Premier Leauge - 5</h1>
    </div>
  </header>
  <main>
      <div style="padding: 3% 0% 0% 3%;">
        <span>
          <h2>Hi ${firstname},</h2>
        </span>
      </div>
      <div class="message" style=" padding-left: 3%;padding-right: 3%">
        <h4>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your initial registration is completed. Please ensure that you have paid the registration fees to <font color="red"> 8116199489 (Phone Pe,G-Pay,Paytm) or UPI ID : 8116199489@ybl </font> within the time frame and whatsapp your <font color="red">registration ID : ${regid} </font>  to ###ph number for further proceedings.
        </h4>  
       
        <h4>
            Please verify the data you have provided during Registration  : 
        </h4>
        </div>
        <div style="padding-left: 10%;padding-right: 5%;">
          <strong>Name :  ${name}</strong><br>
          <strong>Address :  ${address}</strong><br>
           <strong>Playing Category :  ${category}</strong><br>
          <strong>Phone Number :  ${phNo}</strong><br>
          <strong>Email ID :  ${mail}</strong><br>
          <strong>Location Category :  ${location}</strong><br>
          <strong>Password : ${password}</strong>
        </div>
       
  <div  style=" padding-left: 3%;padding-right: 3%";>
    <h4>
      Thank you for registering with us. See you on the Grand auction day of KPL season 5.
      <br>
    </h4>
  </div>

  <footer style="padding-left: 5%;padding-bottom: 5%;">
    <h4><div>Regards,</div>
      <div>
        Team KPL Core</div></h4>
  </footer>

</body>

</html>