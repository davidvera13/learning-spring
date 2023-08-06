<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reading html form data</title>
    <link rel="stylesheet" type="text/css"
          href="./resources/assets/css/style.css">
</head>
<body>
<img src="./resources/assets/img/img.png"  alt="logo" />
<h3>Show form...</h3>
    <form action="handle-form" method="GET">
        <label for="studentName">Name: </label>
        <input type="text"
               name="studentName"
               id="studentName"
               placeholder="What is your name" />
        <input type="submit" />
    </form>
</body>
</html>