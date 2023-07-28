package lk.ijse.gdse.pos.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Initialized Filter");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
       HttpServletResponse res= (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "PUT,DELETE");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("Filter Destroyed!");

    }
}
