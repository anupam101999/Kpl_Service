<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.kpl.registration.dto.RegistrationResponse" %>
        <!-- Assuming you have a User class with properties: id, name, etc. -->

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Personal Details</title>
            <style>
                @import url("https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&display=swap");

                *,
                *::before,
                *::after {
                    box-sizing: border-box;
                    line-height: 1.5em;
                }

                html {
                    font-size: 16px;
                    scroll-behavior: smooth;
                    -webkit-text-size-adjust: 100%;
                }

                body {
                    margin: 0;
                    font-family: "Open Sans", sans-serif;
                    /* background-color:
                        #27c5ad; */
                }

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
                    left: 100%;
                    margin: 0;
                    width: 100%;
                    height: 100%;
                    background-color:
                        rgb(185, 231, 20);
                    padding: 20px;
                    transition: left 0.3s;
                }

                .navbar.show {
                    left: 0 !important;
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
                        #f7f7f7;
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
                        white;
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

                .container {
                    max-width: 900px;
                    margin: 0 auto;
                    padding: 1%;
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

                .container_body {
                    max-width: 600px;
                    margin: 0 auto;
                    padding: 5%;
                    /* background-color: #27c5ad; */
                }

                .user-details {
                    margin-bottom: 10px;
                }

                .user-details label {
                    font-weight: bold;
                }
            </style>
        </head>

        <body>
            <header>
                <div class="wrapper">
                    <div class="logo">
                        <a href="https://www.google.com"><img
                                src="${pageContext.request.contextPath}/images/transparent_logo.png" alt="KPL logo"></a>
                    </div>
                    <div>
                        <h2>Kashipur Premier League (season 5)</h2>
                    </div>
                    <div class="navbar">
                        <div class="close-nav"><button></button></div>
                        <nav>
                            <ul>
                                <li><a href="https://www.google.com">Home</a></li>
                                <!-- <li><a href="#">Blog</a></li> -->
                            </ul>
                        </nav>
                    </div>
                    <div class="menu-bar">
                        <button><i></i></button>
                    </div>
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



            <div class="container_body">
                <h2>${firstname}'s Registration Details</h2>
                <div class="user-details">
                    <label>Registration ID:</label>
                    <span>${id}</span>
                </div>
                <div class="user-details">
                    <label>Player Name:</label>
                    <span>${name}</span>
                </div>
                <div class="user-details">
                    <label>Registration Time:</label>
                    <span>${registerDate}</span>
                </div>
                <div class="user-details">
                    <label>Payment Status:</label>
                    <span>${status}</span>
                </div>
            </div>
            <div style="padding: 3%;color: #0011f3;">
               <h3> *If your payment is in the pending stage, don't worry; wait for 24 hours after successful compilation of
                the payment. For further queries, please contact development or the KPL core team for further
                clarification.</h3>
            </div>
        </body>

        </html>