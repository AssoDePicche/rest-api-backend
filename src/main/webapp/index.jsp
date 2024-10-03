<!DOCTYPE html>
<html lang="en" class="h-100" data-bs-theme="dark">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <title>Caravanas.com</title>
  </head>
  <body class="d-flex h-100 text-bg-dark">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
      <header class="mb-auto text-center">
        <h3 class="float-md-start mb-0">Caravanas.com</h3>
      </header>

      <main class="px-3 container">
        <div class="text-center">
          <h1>Coming soon.</h1>
        </div>

        <form class="container">
          <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" aria-describedby="email-type-hint">
            <div id="email-type-hint" class="form-text">We'll never share your email with anyone else.</div>
          </div>

          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password">
          </div>

          <div class="mb-3">
            <button type="submit" class="btn btn-primary" id="signin">Sign In</button>

            <button type="submit" class="btn btn-link" id="signup">Sign Up</button>
          </div>
        </form>
      </main>

      <footer class="mt-auto text-center text-white-50">
        <p>
          AssoDePicche &copy; 2024.
        </p>
      </footer>
    </div>
  </body>
</html>
