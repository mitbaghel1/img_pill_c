<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Image Upload App</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

header {
	background-color: #333;
	color: #fff;
	padding: 10px;
	text-align: center;
}

footer {
	background-color: #333;
	color: #fff;
	padding: 10px;
	text-align: center;
	position: fixed;
	bottom: 0;
	width: 100%;
}

.upload-box {
	max-width: 400px;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.upload-box label {
	display: block;
	margin-bottom: 10px;
}

.upload-box input[type="file"] {
	display: block;
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.upload-box button {
	background-color: #333;
	color: #fff;
	padding: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<header>
		<h1>Image Upload App</h1>
	</header>

	<div class="upload-box">
		<form id="uploadForm" enctype="multipart/form-data">
			<label for="image">Select Image:</label> <input type="file"
				id="image" name="file" accept="image/*" required> <br>
			<button type="button" onclick="uploadImage()">Upload Image</button>
		</form>
	</div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Uploaded Image</th>
					<th>Pill counts</th>
				</tr>
			</thead>
			<tbody id="dataGridBody">
				<!-- Data will be dynamically populated here -->
			</tbody>
		</table>

	</div>

	<footer> &copy; 2024 Image Upload App </footer>
</body>
<script type="text/javascript">
getUploadImage();
function uploadImage() {
    var formData = new FormData($("#uploadForm")[0]);
    $.ajax({
        type: "POST",
        url: "/api/uploadImg",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            // Handle success, e.g., show a success message
            console.log(response);
            getUploadImage();
            console.log("Image uploaded successfully");
        },
        error: function (error) {
            // Handle error, e.g., show an error message
            console.error("Error uploading image", error);
        }
    });
}
function getUploadImage() {
    $.ajax({
        type: "GET",
        url: "/api/getUploadImg",
        data: '',
        processData: false,
        contentType: false,
        success: function (response) {
        	$("#dataGridBody").empty();
			console.log(response);
            // Iterate over the response data and append rows to the table
            $.each(response.retResponse, function (index, item) {
            	var newRow = $("<tr>");
                // Append cells to the row
                newRow.append("<td width='10%'>" + item.id + "</td>");
                newRow.append("<td width='10%'>" + item.name + "</td>");
                // Append an image cell with the Base64 source
                newRow.append("<td width='60%'><img style='width:362px;height:150px;' src='data:image/png;base64," + item.byteImage + "' alt='Image'></td>");
                newRow.append("<td width='20%'>" + item.imgPillCount + "</td>");
                // Append the row to the table
                $("#dataGridBody").append(newRow);
            });
        },
        error: function (error) {
            // Handle error, e.g., show an error message
            console.error("Error uploading image", error);
        }
    });
}
</script>
</html>
