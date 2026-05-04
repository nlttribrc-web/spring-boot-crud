window.onload = function () {
  const ui = SwaggerUIBundle({
    url: "/v3/api-docs",
    dom_id: "#swagger-ui",
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    layout: "StandaloneLayout",

    responseInterceptor: function (response) {
      try {
        if (response.url.includes("/authenticate") && response.status === 200) {
          const data = JSON.parse(response.text);

          const token = data.accessToken || data.token;

          if (token) {
            console.log("JWT detected:", token);

            ui.preauthorizeApiKey("BearerAuth", token);

            localStorage.setItem("token", token);
          }
        }
      } catch (e) {
        console.error("Token parse error", e);
      }

      return response;
    }
  });

  window.ui = ui;
};