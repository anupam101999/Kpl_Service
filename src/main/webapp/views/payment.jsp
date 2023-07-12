<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Google tag (gtag.js) -->
   <script async src="https://www.googletagmanager.com/gtag/js?id=G-2DKXMGP7B2"></script>
   <script>
     window.dataLayer = window.dataLayer || [];
     function gtag() { dataLayer.push(arguments); }
     gtag('js', new Date());

     gtag('config', 'G-2DKXMGP7B2');
   </script>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Payment Validation</title>
  <link rel="icon" href="${pageContext.request.contextPath}/images/transparent_logo.ico" type="image/x-icon">
  <style>
    header {
      padding: 0.005%;
      text-align: center;
      background: #1abc9c;
      color: white;
      font-size: 20px;
      text-align: center;

    }

    #tagline {
      font-size: 172%;
      padding-top: 2%;
      padding-left: 2%;
      padding-bottom: 2%;
    }
    #upi{
      display: flex;
      font-size: 172%;
      justify-content: space-evenly;
      padding-top: 2%;
      padding-left: 2%;
      padding-bottom: 2%;
    }
    
    #button {
      font-size: 172%;
      padding-top: 2%;
      padding-left: 40%;
      padding-bottom: 2%;
    }
    /* CSS */
.button-75 {
  align-items: center;
  background-image: linear-gradient(135deg, #f34079 40%, #fc894d);
  border: 0;
  border-radius: 10px;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  font-family: "Codec cold",sans-serif;
  font-size: 16px;
  font-weight: 700;
  height: 54px;
  justify-content: center;
  letter-spacing: .4px;
  line-height: 1;
  max-width: 100%;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 3px;
  text-decoration: none;
  text-transform: uppercase;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-75:active {
  outline: 0;
}

.button-75:hover {
  outline: 0;
}

.button-75 span {
  transition: all 200ms;
}

.button-75:hover span {
  transform: scale(.9);
  opacity: .75;
}

@media screen and (max-width: 991px) {
  .button-75 {
    font-size: 15px;
    height: 50px;
  }

  .button-75 span {
    line-height: 50px;
  }
}

@media (max-width: 800px) {
  #upi {
    flex-direction: column;
  }
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
    <div id="tagline">Please Check whether the receiver name is <font color="red">Akash Majumder</font> or not; if not, please contact the KPL
      team immediately.</div>
      <div id="upi">
    <div> <img src="${pageContext.request.contextPath}/images/qrcodeakash.jpeg" alt="QR CODE"></div>
    <div><p>UPI ID : <a href="upi://pay?pa=8116199489@ybl">8116199489@ybl</a></p>
    <p>Phone Number : 8116199489(Phone Pe,G-Pay,Paytm)</p>
    </div>
  </div>
  <div id="tagline">
   <font color="red">After payment completion</font> , Please Log In With Your ID,Password
    or check your registered mail box and WhatsApp your <font color="red">registration ID</font> to <a href="https://wa.me/7074401961" target="_blank"> <font color="red">7074401961</font> </a>
  </div>

  <div id="button">
    <a href='https://kpl2023.online/registration/loginHomePage'>
<button class="button-75" role="button"><span class="text">
  Paid successfully</span></button>
</a>
</div>

  </main>

</body>

</html>