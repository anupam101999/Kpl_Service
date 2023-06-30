<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!doctype html>
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
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Fonts -->
  <link rel="dns-prefetch" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

  <style>
    @import url(https://fonts.googleapis.com/css?family=Raleway:300,400,600);

    header {
      background-color:
        #d3df5b;
      box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1),
        0 4px 6px -4px rgba(0, 0, 0, 0.1);
      padding: 10px 20px;
    }

    header .wrapper {
      max-width: 1000px;
      margin: 0 auto;
      display: flex;
      flex-wrap: wrap;
      align-items: center;
      justify-content: space-between;
    }

    header .logo img {
                    height: 50px;
                    display: block;
                }
                .navbar {
                    position: fixed;
                    top: 0;
                    /* left: 100%; */
                    margin: 0;
                    width: 100%;
                    height: 100%;
                    background-color:
                    #d3df5b;
                    padding: 20px;
                    transition: left 0.3s;
                }

                .hide-scroll {
                    overflow: hidden;
                }

                .navbar ul {
                    all: unset;
                    list-style-type: none;
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    gap: 25px;
                }

                .navbar ul a {
                    all: unset;
                    color:
                        #444444;
                    text-transform: uppercase;
                    cursor: pointer;
                    font-weight: bold;
                    font-size: 28px;
                }

                .navbar ul a:hover {
                    color:
                        #111111;
                    text-decoration: underline overline;
                    text-decoration-thickness: 3px;
                }

                .close-nav {
                    text-align: right;
                    margin-bottom: 20px;
                }

                .close-nav button {
                    all: unset;
                    background:
                    #23b5a9;
                    font-size: 42px;
                    cursor: pointer;
                    border: 1px solid rgba(0, 0, 0, 0.2);
                    padding: 15px;
                    border-radius: 3px;
                    color:
                        #444444;
                }

                .close-nav button:hover {
                    color:
                        #222222;
                    background:
                    #23b5a9;
                }

                .menu-bar button {
                    border: 1px solid rgba(0, 0, 0, 0.1);
                    background:
                        #f7f7f7;
                    height: 50px;
                    width: 50px;
                    padding: 5px 10px;
                    cursor: pointer;
                    border-radius: 3px;
                }

                .menu-bar i {
                    display: block;
                    border-top: 3px solid #444444;
                    border-bottom: 3px solid #444444;
                }

                .menu-bar i::after {
                    display: block;
                    content: "";
                    border-top: 3px solid #444444;
                    margin: 6px 0;
                }

                .menu-bar button:hover {
                    background:
                        white;
                }

                .menu-bar button:hover i {
                    border-color:
                        #222222;
                }
                @media (min-width: 550px) {
                    .navbar {
                        all: unset;
                        display: block;
                    }

                    .navbar ul {
                        flex-direction: row;
                        gap: 20px;
                    }

                    .navbar ul a {
                        font-size: inherit;
                    }

                    .close-nav,
                    .menu-bar {
                        display: none;
                    }
                }
    

    .menu-bar button {
      border: 1px solid rgba(0, 0, 0, 0.1);
      background:
        #f7f7f7;
      height: 50px;
      width: 50px;
      padding: 5px 10px;
      cursor: pointer;
      border-radius: 3px;
    }

    .menu-bar i {
      display: block;
      border-top: 3px solid #444444;
      border-bottom: 3px solid #444444;
    }

    .menu-bar i::after {
      display: block;
      content: "";
      border-top: 3px solid #444444;
      margin: 6px 0;
    }

    .menu-bar button:hover {
      background:
        white;
    }

    .menu-bar button:hover i {
      border-color:
        #222222;
    }


    @media (min-width: 550px) {
      .navbar {
        all: unset;
        display: block;
      }

      .navbar ul {
        flex-direction: row;
        gap: 20px;
      }

      .navbar ul a {
        font-size: inherit;
      }

      .close-nav,
      .menu-bar {
        display: none;
      }
    }

    body {
      font-family: Arial, sans-serif;
    }

    .container {
                    max-width: 900px;
                    margin: 0 auto;
                    padding: 1%;
                }

    .user-details {
      margin-bottom: 10px;
    }

    .user-details label {
      font-weight: bold;
    }


    .my-form,
    .login-form {
      font-family: Raleway, sans-serif;
    }

    .my-form {
      padding-top: 1.5rem;
      padding-bottom: 1.5rem;
    }

    .my-form .row {
      margin-left: 0;
      margin-right: 0;
    }

    .login-form {
      padding-top: 1.5rem;
      padding-bottom: 1.5rem;
    }

    .login-form .row {
      margin-left: 0;
      margin-right: 0;
    }

    .error-popup {
      position: fixed;
      top: 90%;
      left: 50%;
      transform: translate(-50%, -50%);
      padding: 20px;
      color: rgb(240, 8, 8);
      border-radius: 5px;
    }
  </style>

  <link rel="icon" href="Favicon.png">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

  <title>Password Reset</title>
</head>

<body>
  <header>
    <div class="wrapper">
      <div class="logo">
        <a href="https://www.google.com"><img src="${pageContext.request.contextPath}/images/transparent_logo.png"
            alt="KPL logo"></a>
      </div>
      <div>
        <h2>Kashipur Premier League (season 5)</h2>
      </div>
      <div class="navbar">
        <!-- <div class="close-nav"><button></button></div> -->
        <nav>
          <ul>
            <li><a href="https://www.google.com">Home</a></li>
            <!-- <li><a href="#">Blog</a></li> -->
          </ul>
        </nav>
      </div>
      <!-- <div class="menu-bar">
        <button><i></i></button>
      </div> -->
    </div>
  </header>
  <div class="container">
    <!-- Content -->
  </div>
  <script>
    const theBody = document.querySelector('body');
    const openNav = document.querySelector('.menu-bar button');
    const closeNav = document.querySelector('.close-nav button');
    const Navbar = document.querySelector('.navbar');
    function showHide() {
      Navbar.classList.toggle('show');
    }

    openNav.onclick = showHide;
    closeNav.onclick = showHide;
  </script>













  <main class="login-form">
    <div class="cotainer">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card">
            <div class="card-header">Reset Password</div>
            <div class="card-body">
              <form action="https://kpl-test-v01-production.up.railway.app/resetPassword" method="post">
                <div class="form-group row">
                  <label for="phNumber" class="col-md-4 col-form-label text-md-right">Phone Number</label>
                  <div class="col-md-6">
                    <input type="number" id="phNumber" class="form-control" name="phNumber" required autofocus
                      placeholder="Enter your Registered Phone number">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="pinCode" class="col-md-4 col-form-label text-md-right">Pin Code</label>
                  <div class="col-md-6">
                    <input type="number" id="pinCode" class="form-control" name="pinCode" required autofocus
                      placeholder="Enter your Address Pin Code">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="aadhaarNo" class="col-md-4 col-form-label text-md-right">Aadhaar ID</label>
                  <div class="col-md-6">
                    <input type="number" id="aadhaarNo" class="form-control" name="aadhaarNo" required autofocus
                      placeholder="Enter your Registered document id">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="password" class="col-md-4 col-form-label text-md-right">New Password</label>
                  <div class="col-md-6">
                    <input type="password" id="password" class="form-control" name="password" required
                      placeholder="Enter your new Password">
                  </div>
                </div>

                <div class="col-md-6 offset-md-4">
                  <button type="submit" class="btn btn-primary">
                    Reset Password
                  </button>
                </div>
              </form>
            </div>

          </div>
        </div>
      </div>
    </div>
    </div>

  </main>
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