<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <title>Payment View</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/transparent_logo.ico" type="image/x-icon">
        <style>
            * {
                font-family: "Calibri", sans-serif;
            }

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

            #upi {
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
                font-family: "Codec cold", sans-serif;
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
            <div id="tagline">
                <div lang="bn">
                    পেমেন্ট করার সময় চেক করে নেবেন রিসিভার এর নামে <font color="red">আকাশ মজুমদার</font> কি না, যদি আকাশ না হয় ইমিডিয়েট KPL কমিটি কে ইস্যু টা জানান              
                </div>
                <div>
                    <p lang="bn">
                        টেকনিক্যাল ইস্যুর জন্য UPI অ্যাপ লিংক কাজ না করলে ডাইরেক্টলি <font color="red"> 8116199489 </font> এই নম্বর বা দেওয়া upi
                        আইডি অথবা QR কোড স্ক্যান করে<font color="red"> 70 টাকা</font>   পেমেন্ট করুন

                    </p>
                </div>
            </div>

            <div id="upi">
                <div> <img src="${pageContext.request.contextPath}/images/akashup.jpeg" alt="QR CODE"
                        style="width: 80%;"></div>
                <!-- <div><img src="akashup.jpeg" alt="QR CODE" style="width: 80%;"></div>  -->
                <div>
                    <p>UPI Link : <a href="upi://pay?pa=8116199489fdl@ybl&pn=%20&tr=%20&am=70&cu=INR">
                            Click here to Pay</a>
                    <p>
                    <p>UPI ID : 8116199489fdl@ybl </p>
                    <p>Phone Number : 8116199489(Phone Pe,G-Pay,Paytm)</p>
                </div>
            </div>
            <div id="tagline" lang="bn">
                পেমেন্ট কমপ্লিট হবার পর অবশ্যই রেজিস্ট্রেশন আইডি  <a href="https://wa.me/8116199489" target="_blank"><font color="red">8116199489</font>
                </a> হোয়াটসঅ্যাপ নম্বর এ  পাঠাবেন। আপনি আপনার রেজিস্ট্রেশন আইডি রেজিষ্টার করা মেইল এ গিয়ে চেক করতে পারেন অথবা SIGN IN পেজ এ গিয়ে আপনার আইডি পাসওয়ার্ড দিয়ে লগ ইন করে দেখতে পারেন |
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
