<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="en">
  <link rel="icon" href="${pageContext.request.contextPath}/images/transparent_logo.ico" type="image/x-icon">

  <head>
    <meta charset="UTF-8">
    <!-- Google tag (gtag.js) -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-2DKXMGP7B2"></script>
    <script>
      window.dataLayer = window.dataLayer || [];
      function gtag() { dataLayer.push(arguments); }
      gtag('js', new Date());

      gtag('config', 'G-2DKXMGP7B2');
    </script>








    <script>
      let isSubmitting = false;
      document.addEventListener("DOMContentLoaded", function () {
        const form = document.querySelector(".form");
        const docImageFrontInput = form.querySelector('[name="docImageFront"]');
        const docImageBackInput = form.querySelector('[name="docImageBack"]');
        const ownImageInput = form.querySelector('[name="playerPhoto"]');


        form.addEventListener("submit", async function (event) {
          event.preventDefault();

          const maxSize = 300 * 1024; // Maximum size in bytes (300 KB)

          const playerFirstName = form.querySelector('[name="playerFirstName"]').value;
          const playerLastName = form.querySelector('[name="playerLastName"]').value;
          const playerPhNo = form.querySelector('[name="phNo"]').value;
          const aadharNo = form.querySelector('[name="aadharNo"]').value;
          const phNo = form.querySelector('[name="phNo"]').value;
          const mail = form.querySelector('[name="mail"]').value;
          const pinCode = form.querySelector('[name="pinCode"]').value;
          const password = form.querySelector('[name="password"]').value;


          if (isSubmitting) {
            alert("ব্যাকগ্রাউন্ড এ প্রসেস চলছে দয়া করে একাধিক বার সাবমিট বাটন এ ক্লিক করবেন না।");
            return;
          }

          // Set the flag to indicate that the form submission is in progress
          isSubmitting = true;

          setTimeout(() => {
            isSubmitting = false;
          }, 2000)




          // Check the image size before submission
          const fileSizeFront = docImageFrontInput.files[0].size;
          if (fileSizeFront > maxSize) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he has selected Aadhar Front image more than 300 KB, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              console.log(encodedText);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("আঁধার কার্ড এর সামনের ছবির সাইজ ৩০০ কেবি এর নিচে করুন.");
            return;
          }

          const fileSizeBack = docImageBackInput.files[0].size;
          if (fileSizeBack > maxSize) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he has selected Aadhar Back image more than 300 KB, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              console.log(encodedText);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("আঁধার কার্ড এর পিছনের ছবির সাইজ ৩০০ কেবি এর নিচে করুন.");
            return;
          }


          const ownImage = ownImageInput.files[0].size;
          if (ownImage > maxSize) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he has selected his own image more than 300 KB, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("আপনার নিজের ছবির সাইজ ৩০০ কেবি এর নিচে করুন.");
            return;
          }

          if (playerFirstName.toString().length > 20) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he is using First name more than 20 words, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("নামের প্রথম অংশ ২০ টা ওয়ার্ড এর কম করুন.");
            return;
          }

          if (pinCode.toString().length > 6) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he is using Postal code more than 6 digits, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("পিন কোড এর সংখ্যা ৬ থেকে বেশি হওয়া সম্ভব না পুনরায় চেক করুন.");
            return;
          }

          if (playerLastName.toString().length > 20) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he is using Last name more than 20 words, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("নামের পদবী  ২০ টা ওয়ার্ড এর কম করুন.");
            return;
          }
          if (aadharNo.toString().length > 12) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he is not using 12 digits Aadhar no, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("আঁধার এর সংখ্যা ১২ থেকে বেশি হওয়া সম্ভব না পুনরায় চেক করুন.");
            return;
          }

          const url = "https://kpl2023.online/registration/kpl/registration/api/phNoCheck?phNo=" + playerPhNo;
          const response = await fetch(url);
          const data = await response.text();
          console.log(data)

          if (data == "ok") {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register using already registered Phone Number, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("এক বার ব্যবহৃত ফোন নম্বর পুনরায় ব্যবহার যোগ্য নয়.");
            return;
          }


          const urlAadhar = "https://kpl2023.online/registration/kpl/registration/api/aadharCheck?aadharNo=" + aadharNo;
          const responseAadhar = await fetch(urlAadhar);
          const dataAadhar = await responseAadhar.text();
          console.log(dataAadhar)

          if (dataAadhar == "ok") {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he is using already registered Aadhar no, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("এক বার ব্যবহৃত আঁধার নম্বর পুনরায় ব্যবহার যোগ্য নয়.");
            return;
          }


          const urlmail = "https://kpl2023.online/registration/kpl/registration/api/emailCheck?mail=" + mail;
          const responseurlmail = await fetch(urlmail);
          const datamail = await responseurlmail.text();
          console.log(datamail)

          if (datamail == "ok") {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register using already registered Email ID, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("এক বার ব্যবহৃত ইমেইল নম্বর পুনরায় ব্যবহার যোগ্য নয়.");
            return;
          }

          if (!(password.length > 3 && password.length < 9)) {
            const text = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown " + playerFirstName + " " + playerLastName + " is trying to Register but he has not selected password between 4 to 8 character, please help him and his Phone number is : " + playerPhNo;

            try {
              const encodedText = encodeURIComponent(text);
              const url = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=" + encodedText;
              const response = await fetch(url);
              const data = await response.json();
              console.log(data); // Log the response for debugging
            } catch (error) {
              console.error('Error calling API:', error);
            }
            alert("পাসওয়ার্ড ৪ থেকে ৮ টি ওয়ার্ড এর মধ্যে রাখুন.");
            return;
          }



         
          // If all checks pass, submit the form
          form.submit();
        });
      });

    </script>




    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Information Feed</title>
    <!---Custom CSS File--->
    <style>
      /* Import Google font - Poppins */
      @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");

      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Calibri", sans-serif;
      }

      body {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 20px;
        background: #d3df5b;
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
    <% if (request.getAttribute("errorShown")==null) { %>
      <c:if test="${not empty errorMessage && 'POST' eq pageContext.request.method}">

        <section class="container">
          <header>Registration Form</header>
          <form action="https://kpl2023.online/registration/signUp" class="form" method="post"
            enctype="multipart/form-data">
          <!-- <form action="http://localhost:1999/signUp" class="form" method="post" enctype="multipart/form-data"> -->
            <div style="color: #FFA500">
              <strong lang="bn">
                আঁধার এর সামনের পিছনের এবং নিজের ছবি ৩০০ কেবি এর মধ্যে রাখবেন
              </strong>
            </div>
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
                <input type="number" name="aadharNo" maxlength="12" placeholder="Enter Your Aadhaar card No" required />
              </div>
              <div class="input-box">
                <label>Phone Number</label>
                <input type="number" name="phNo" maxlength="10" placeholder="Enter phone number" required />
              </div>
            </div>

            <div class="column">
              <div class="input-box">
                <label>Aadhaar Card Image Front</label>
                <input type="file" name="docImageFront" accept="image/**" required />
              </div>
              <div class="input-box">
                <label>Aadhaar Card Image Back</label>
                <input type="file" name="docImageBack" accept="image/**" required />
              </div>
            </div>
            <div class="column">
              <div class="input-box">
                <label>Birth Date</label>
                <input type="date" name="dob" placeholder="Enter birth date" required />
              </div>
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
                  <select name="location" required>
                    <option option value="" disabled selected>Your Home location</option>
                    <option value="Local">Local(Kumra Panchayet)</option>
                    <option value="Overseas">Overseas</option>
                  </select>
                </div>

                <input type="email" name="mail" placeholder="Enter email ID" required />
              </div>
              <div class="column">
                <div class="select-box">
                  <select name="playerCategory" required>
                    <option option value="" disabled selected>Player Category</option>
                    <option value="Batsman">Batsman</option>
                    <option value="Bowler">Bowler</option>
                    <option value="Wicket Keeper">Wicket Keeper</option>
                    <option value="All Rounder">All Rounder</option>
                  </select>
                </div>
                <input type="Number" name="pinCode" maxlength="6" placeholder="Enter postal code" required />
              </div>
              <div class="column">

                <div class="input-box">
                  <label>Your Photo</label>
                  <input type="file" name="playerPhoto" accept="image/**" required />
                </div>
                <div class="input-box">
                  <label>password</label>
                  <input type="password" name="password" maxlength="8" placeholder="more than 3 and less than 9 digit"
                    required />
                </div>
              </div>
              <br>
              <div style="color: red;">
                <strong lang="bn">
                  মালিক পক্ষ থেকে খেলায় অংশগ্রহন করার জন্য কোন প্রকার এর অর্থ কিংবা উপঢৌকন এর দাবি করা শাস্তিযোগ্য
                  অপরাধ
                  হিসাবে বিবেচিত হবে
                </strong>
              </div>
            </div>
            <label style="font-size: 120%;">
              <br>
              <input type="checkbox" required> I have read and agree to the terms and conditions of KPL2023
            </label>
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
          }, 10000);
        </script>

        <% request.setAttribute("errorShown", true); %>
      </c:if>
      <% } %>
  </body>

  </html>