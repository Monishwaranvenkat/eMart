package com.eMart.main.security;

import com.eMart.main.model.InvoiceModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
       // response.setContentType("application/json");
        //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.getWriter().write(new JSONObject()
                    .put("timestamp", LocalDateTime.now())
                    .put("message", authException.getMessage())
                    .toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");

    }
}
