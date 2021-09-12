<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Accept a card payment</title>
    <meta name="description" content="A demo of a card payment on Stripe" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Cabin+Condensed&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/FOODHUB/URLToReachResourcesFolder/css/checkout.css" />
    <script src="https://js.stripe.com/v3/"></script>
    <script src="https://polyfill.io/v3/polyfill.min.js?version=3.52.1&features=fetch"></script>
    <script src="/FOODHUB/URLToReachResourcesFolder/js/client.js" defer></script>
  </head>

  <body>
    <!-- Display a payment form -->
    <form id="payment-form">
      <p id="heading"><b>Welcome to the FOODHUB payments</b></p>

      <input type="text" id="email" placeholder="Email address" required="required"/>
      <div id="card-element"><!--Stripe.js injects the Card Element--></div>
      <button id="submit">
        <div class="spinner hidden" id="spinner"></div>
        <span id="button-text">Pay now</span>
      </button>
      <p id="card-error" role="alert"></p>
      <p class="result-message hidden">
        Payment succeeded, see the result in your
        <a href="" target="_blank">Stripe dashboard.</a> Refresh the page to pay again.
      </p>
    </form>
  </body>
</html>
