<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reading html form data</title>
</head>
<body>
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