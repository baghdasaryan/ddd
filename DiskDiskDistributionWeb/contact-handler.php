<?php

if (isset($_POST['email'])) {

	$email_to = "1985george@gmail.com";
	$email_subject = "DDD Contact Us Message";

	function died($error) {
		// your error code can go here
		echo "We are very sorry, but there were error(s) found with the form you submitted. ";
		echo "These errors appear below.<br /><br />";
		echo $error . "<br /><br />";
		echo "Please go back and fix these errors.<br /><br />";
		die();
	}

	// check that user has provided all required data
	if (!isset($_POST['first_name']) || !isset($_POST['last_name']) || !isset($_POST['email']) || !isset($_POST['message'])) {
		died('We are sorry, but there appears to be a problem with the form you submitted.');
	}

	$first_name = $_POST['first_name'];
	$last_name = $_POST['last_name'];
	$email_from = $_POST['email'];
	$message = $_POST['message'];

	$error_message = "";
	$email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';
	$string_exp = "/^[A-Za-z .'-]+$/";

	if (!preg_match($email_exp, $email_from)) {
		$error_message .= 'The Email Address you entered does not appear to be valid.<br />';
	}

	if (!preg_match($string_exp, $first_name)) {
		$error_message .= 'The First Name you entered does not appear to be valid.<br />';
	}

	if (!preg_match($string_exp, $last_name)) {
		$error_message .= 'The Last Name you entered does not appear to be valid.<br />';
	}

	if (strlen($message) < 2) {
		$error_message .= 'The Message you entered does not appear to be valid.<br />';
	}

	if (strlen($error_message) > 0) {
		died($error_message);
	}

	$email_message = "Form details below.\n\n";

	function clean_string($string) {
		$bad = array("content-type", "bcc:", "to:", "cc:", "href");
		return str_replace($bad, "", $string);
	}

	$email_message .= "First Name: " . clean_string($first_name) . "\n";
	$email_message .= "Last Name: " . clean_string($last_name) . "\n";
	$email_message .= "Email: " . clean_string($email_from) . "\n";
	$email_message .= "Message: " . clean_string($message) . "\n";

	// create email headers
	$email_headers = 'MIME-Version: 1.0' . "\r\n";
	$email_headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
	$email_headers .= 'From: ' . $email_from . "\r\n";
	$email_headers .= 'Reply-To: ' . $email_from . "\r\n";
	$email_headers .= 'X-Mailer: PHP/' . phpversion();

	if (mail($email_to, $email_subject, $email_message, $email_headers)) {
		echo("<p>Message successfully sent!</p>" );
	} else {
		echo("<p>Message delivery failed...</p>"
	);
}
		

?>

<!-- place your own success html below -->

Thank you for contacting us. We will be in touch with you very soon.

<?php
}
die();
?>