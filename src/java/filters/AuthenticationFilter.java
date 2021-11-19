package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //this is where the magic happens
        //any code before chain.doFilter will be executed b4 request
        HttpServletRequest httpReq = (HttpServletRequest) request;//we convert so we can call session
        HttpSession session = httpReq.getSession();
        String email = (String) session.getAttribute("email");

        //now we can test that the email exists.
        if (email == null) {
            HttpServletResponse httpRes = (HttpServletResponse) response;
            System.out.println("Email is null!");
            httpRes.sendRedirect("login");
            return;
        }

        chain.doFilter(request, response);//this will either call upon next filter in chain or load the requested servlet.

        //any code after chain will be executed after request
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //dont care
    }

    @Override
    public void destroy() {
    }

}
