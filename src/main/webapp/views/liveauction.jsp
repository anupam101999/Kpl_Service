<!DOCTYPE html>
<html lang="en">
<link rel="icon" href="${pageContext.request.contextPath}/images/transparent_logo.ico" type="image/x-icon">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="stylelive.css" />
    <title>Live Auction</title>
</head>

<!-- <script src="livedata.js"></script> -->

<style>
    * {
        font-family: "Calibri", sans-serif;
      
    }

    header {
        padding: 20px;
        font-size: 22px;
        background-color: teal;
        color: #fff;
        text-align: center;
    }


    /* Footer styles */
    footer {
        background-color: teal;
        padding: 20px;
        text-align: center;
        color: #fff;
        font-size: 24px;
    }

    .footer-content {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-bottom: 10px;
    }

    .developer-name {
        margin-right: 10px;
    }

    .developer-name,
    a img {
        height: 25px;
    }

    /* Media queries for mobile responsiveness */
    @media (max-width: 768px) {
        .footer-content {
            flex-direction: column;
        }

        .developer-name {
            margin-bottom: 15%;
        }

        .developer-name,
        a img {
            height: 20px;
        }
    }


    .container {
        height: 100vh;
        display: flex;
        overflow: hidden;
    }

    .right-section {
        flex: 1;
        overflow-y: scroll;
    }

    .flex-container {
        display: flex;
        justify-content: space-around;
    }

    .flex-container>div {
        /* background-color: #a8e024;
    margin: 10px;
    padding: 20px; */
      background-color: #afcf20;
        font-size: 25px;
        cursor: pointer;
        font-weight: bold;
        /* border-radius: 30px; */
    }

    @media (max-width: 800px) {
        .flex-container {
            flex-direction: column;
        }
    }

    /* CSS styles for the table */
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th,
    td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
    }


    .footer a {
        color: #333333;
        text-decoration: none;
        font-weight: bold;
    }

    .footer a:hover {
        color: #007bff;
    }

    .content {
        flex: 1;
    }
</style>





















<body>
    <header>
        <h1>Kashipur Premier League - 5 organized by Jonaki Sangha</h1>
    </header>


    <div class="flex-container">
        <div class="right-section">
            <p style="font-size: 200%;padding-left: 5%;"><u>Sold Player</u></p>
            <ul id="api-data"></ul>
        </div>
        <div>
            <p style="font-size: 200%;padding-left: 5%;"><u>Team Related Information</u></p>








            <div id="table-container"></div>

            <script>
                function fetchSoldPlayerData() {
                    fetch('https://kpl2023.online/registration/kpl/registration/api/soldPlayerList')
                        .then(response => response.json())
                        .then(data => {
                            // Process the received data and update the UI
                            const apiDataElement = document.getElementById("api-data");
                            apiDataElement.innerHTML = ""; // Clear previous data

                            // Append each item as a list item
                            data.forEach(item => {
                                const listItem = document.createElement("li");
                                listItem.textContent = "" + JSON.stringify(item).replace(/"/g, "") + ""; // Replace double quotes 
                                apiDataElement.appendChild(listItem);
                            });
                        })
                        .catch(error => {
                            // Handle any errors
                            console.error('Error:', error);
                        });
                }



                // Fetch data initially
                fetchSoldPlayerData();

                // Refresh and fetch data every 1 minute=60000
                setInterval(fetchSoldPlayerData, 10000);












                // 

                function fetchData() {
                    // Retrieve JSON data from the API
                    fetch('https://kpl2023.online/registration/kpl/registration/api/liveTeamData')
                        .then(response => response.json())
                        .then(data => {
                            // Create the table dynamically
                            const table = document.createElement('table');
                            const thead = document.createElement('thead');
                            const tbody = document.createElement('tbody');

                            // Create table header row
                            const headerRow = document.createElement('tr');
                            for (let key in data[0]) {
                                const th = document.createElement('th');

                                th.textContent = key;
                                headerRow.appendChild(th);
                            }
                            thead.appendChild(headerRow);
                            table.appendChild(thead);

                            // Populate the table with data
                            data.forEach(item => {
                                const row = document.createElement('tr');
                                for (let key in item) {
                                    const cell = document.createElement('td');
                                    cell.textContent = item[key];
                                    if (key === 'Money Rem') {
                                        cell.style.color = 'red';
                                    }
                                    if (key === 'Max Bet') {
                                        cell.style.color = 'blue';
                                    }
                                    row.appendChild(cell);
                                }
                                tbody.appendChild(row);
                            });

                            table.appendChild(tbody);

                            // Append the table to the container
                            const container = document.getElementById('table-container');
                            container.innerHTML = ''; // Clear previous table
                            container.appendChild(table);

                            // Set the timer to fetch data again after 1 minute
                            // setTimeout(fetchData, 10000); // 60000 milliseconds = 1 minute
                        })
                        .catch(error => console.log(error));
                }

                // Fetch data initially
                fetchData();

                // Refresh and fetch data every 1 minute=60000
                // setInterval(fetchData, 10000);

            </script>











        </div>
    </div>
</body>

<footer>
    <div class="footer-content">
        <span class="developer-name">Devloped By : Kpl Dev Team | Connect With US on
            <a href="https://www.facebook.com/kplofficial11" target="_blank">Facebook</a> |
            <a href="https://wa.me/8116199489" target="_blank" style="color: #ebe7d9;">Whatsapp</a>
        </span>

    </div>
    <div class="copyright">
        &copy; Kashipur Premier League 2023. All rights reserved.
    </div>
</footer>

</html>