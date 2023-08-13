<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mail Template Payment Validation</title>
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
      <div style="padding: 3%;">
        <span>
          <h2>Hi ${firstname},</h2>
        </span>
      </div>
      <div class="message" style=" padding-left: 3%;padding-right: 10%;">
        <h4>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;On the big auction day of Kashipur Premier League Season 5, you have been sold to franchise ${soldteam} for Rs.${soldamount}.
        </h4>    

        <h4>
          Please contact your team's owner ${ownername}, at ${phnum}.
        </h4> 
  <div  style=" padding-left: 3%;padding-right: 3%";>
    <h4>
      Thank you for taking part in KPL 2023. We'll see you on the pitch.
      <br><br>
    </h4>
  </div>
  </div>


  <footer style="padding-left: 5%;padding-bottom: 5%;">
    <h4><div>Regards,</div>
      <div>
        Team KPL Core</div></h4>
  </footer>

</body>

</html>